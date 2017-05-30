package br.com.controlehoras.controle;

import br.com.controlehoras.dao.EscalaDao;
import br.com.controlehoras.dao.FuncionarioDao;
import br.com.controlehoras.dao.ItemEscalaDao;
import br.com.controlehoras.dao.TurnoDao;
import br.com.controlehoras.enumeradores.Cargo;
import br.com.controlehoras.modelo.Escala;
import br.com.controlehoras.modelo.Funcionario;
import br.com.controlehoras.modelo.ItemEscala;
import br.com.controlehoras.modelo.Turno;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import org.omnifaces.util.Messages;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Tardeli
 */
@ManagedBean
@ViewScoped
public class EscalaBean implements Serializable {

    private Escala escala = new Escala();
    private EscalaDao escalaDao = new EscalaDao();
    private ItemEscala itemEscala = new ItemEscala();
    private List<ItemEscala> itensEscala = new ArrayList<>();
    private ItemEscalaDao itemEscalaDao = new ItemEscalaDao();
    private Funcionario funcionario;
    private FuncionarioDao funcionarioDao = new FuncionarioDao();
    private List<Funcionario> listaObjetos = new ArrayList<>();
    private List<Funcionario> listaFuncionarios = new ArrayList<>();
    private List<Funcionario> listaSelecionada = new ArrayList<>();
    private List<Turno> listaTurnos = new ArrayList<>();
    private TurnoDao turnoDao = new TurnoDao();

    private DualListModel<Funcionario> funcionarios;

    private boolean verificarDataVaziu;

    public EscalaBean() {
        listaFuncionarios = this.getListaObjetos();
        carregarDados();
        ultimaDataIncrementada();
    }

    public void novo() {

    }
    
    public void ultimaDataIncrementada(){
        Date dataMaisUmDia = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(escalaDao.ultimaDataEscala().getData());
        cal.add(Calendar.DATE, 1);
        dataMaisUmDia= cal.getTime();
        escala.setData(dataMaisUmDia);
    }

    public void limpar() {
        itensEscala = new ArrayList<>();
        escala = new Escala();
        listaFuncionarios.clear();
        listaSelecionada.clear();
        carregarDados();
        ultimaDataIncrementada();
    }

    public void carregarDados() {
        funcionarios = new DualListModel<>(listaFuncionarios, listaSelecionada);
    }

    public void atualizarDados() {
        limpar();
        listaFuncionarios = this.getListaObjetos();
        funcionarios = new DualListModel<>(listaFuncionarios, listaSelecionada);
        ultimaDataIncrementada();
    }

    public void enviar() {
        if (funcionarios.getTarget().isEmpty()) {
            Messages.addGlobalInfo("Selecione Funcionário para enviar");
        } else {
            for (Funcionario fun : funcionarios.getTarget()) {
                ItemEscala i = new ItemEscala();
                i.setFuncionario(fun);
                i.setTurno(itemEscala.getTurno());

                itensEscala.add(i);
            }
            listaFuncionarios = funcionarios.getSource();
            listaSelecionada.clear();
            carregarDados();
        }
    }

    public void editarItemEscala() {
        ItemEscala itemEscala = new ItemEscala();
        itemEscala = this.itemEscala;
        itensEscala.remove(this.escala);
        itensEscala.add(itemEscala);
    }

    public String converterData_String(Date data) {
        SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy");
        return out.format(this.escala.getData());
    }

    public Date converterString_Data(String data) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse(data);
        return date;
    }

    public void salvar() {
        EscalaDao escalaDao = new EscalaDao();
        if (escalaDao.pesquisarData(this.escala.getData())) {
            Messages.addGlobalInfo("Já existe cadastro para esta data: " + converterData_String(this.escala.getData()));
        } else {
            Long codigo = escalaDao.salvar(escala);
            ItemEscalaDao itemEscalaDao = new ItemEscalaDao();
            itemEscalaDao.salvar(codigo, itensEscala);
            Messages.addGlobalInfo("Salvo com sucesso!\nCódigo:" + codigo + "\nData: " + converterData_String(this.escala.getData()));
            atualizarDados();
            ultimaDataIncrementada();
        }
    }

    public void excluir(ItemEscala itemEscala) {
        itensEscala.remove(itemEscala);
        listaFuncionarios.add(itemEscala.getFuncionario());
        carregarDados();

    }

    public void carregarDadosEditar(ItemEscala itemEscala) {
        this.itemEscala = itemEscala;
    }

    public void teste() {
        String data = null;
        //data = converterData_String(this.getEscala().getData());
//        if(this.getEscala().getData()!=null){
//            verificarDataVaziu = true;
//        }else{
//            verificarDataVaziu = false;
//        }
//        String resultado = String.valueOf(verificarDataVaziu);
//        Messages.addGlobalInfo(resultado);
        Messages.addGlobalInfo(this.getEscala().getData().toString());
    }

    public Funcionario getFuncionario() {
        if (funcionario == null) {
            return funcionario = new Funcionario();
        }
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<Turno> getListaTurnos() {
        return listaTurnos = turnoDao.listarObjetos();
    }

    public void setListaTurnos(List<Turno> listaTurnos) {
        this.listaTurnos = listaTurnos;
    }

    public Cargo[] getCargo() {
        return Cargo.values();
    }

    public List<Funcionario> getListaSelecionada() {
        return listaSelecionada;
    }

    public void setListaSelecionada(List<Funcionario> listaSelecionada) {
        this.listaSelecionada = listaSelecionada;
    }

    public DualListModel<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(DualListModel<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public List<ItemEscala> getItensEscala() {
        return itensEscala;
    }

    public void setItensEscala(List<ItemEscala> itensEscala) {
        this.itensEscala = itensEscala;
    }

    public ItemEscala getItemEscala() {
        return itemEscala;
    }

    public void setItemEscala(ItemEscala itemEscala) {
        this.itemEscala = itemEscala;
    }

    public List<Funcionario> getListaObjetos() {
        return listaObjetos = funcionarioDao.listarObjetosCriterios();
    }

    public void setListaObjetos(List<Funcionario> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }

    public List<Funcionario> getListaFuncionarios() {
        return listaFuncionarios;
    }

    public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
        this.listaFuncionarios = listaFuncionarios;
    }

    public Escala getEscala() {
        return escala;
    }

    public void setEscala(Escala escala) {
        this.escala = escala;
    }

    public boolean isVerificarDataVaziu() {
        return verificarDataVaziu;
    }

    public void setVerificarDataVaziu(boolean verificarDataVaziu) {
        this.verificarDataVaziu = verificarDataVaziu;
    }

}
