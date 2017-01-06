package br.com.controlehoras.controle;

import br.com.controlehoras.dao.FuncionarioDao;
import br.com.controlehoras.dao.TurnoDao;
import br.com.controlehoras.enumeradores.Cargo;
import br.com.controlehoras.enumeradores.Situacao;
import br.com.controlehoras.modelo.Funcionario;
import br.com.controlehoras.modelo.Turno;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.omnifaces.util.Messages;

/**
 *
 * @author Tardeli
 */
@ManagedBean
@SessionScoped
public class FuncionarioBean implements Serializable {

    private Funcionario funcionario;
    private FuncionarioDao funcionarioDao = new FuncionarioDao();
    private List<Funcionario> listaObjetos = new ArrayList<>();
    private List<Turno> listaTurnos = new ArrayList<>();
    private TurnoDao turnoDao = new TurnoDao();
    
    private Cargo[] cargo;
    private Situacao[] situacao;
        
    public FuncionarioBean() {
        this.getListaObjetos();
    }
       
    public void novo() {
        this.funcionario = new Funcionario();
    }


    public String salvar() {
//        Funcionario f = funcionarioDao.buscarObjeto(this.funcionario.getMatricula());
//        if (f==null) {
            funcionarioDao.salvarOuAtualizarObjeto(this.funcionario);
            getListaObjetos();
            Messages.addGlobalInfo("Salvo com sucesso!");
            this.funcionario = new Funcionario();
            return "erro";
//        } 
//        else {
//            funcionarioDao.salvarOuAtualizarObjeto(this.funcionario);
//            getListaObjetos();
//            Messages.addGlobalInfo("Atualizado com Sucesso!");
//            this.funcionario = new Funcionario();
//            return "erro.xhtml";
//        }
    }

    public void excluir(Funcionario c) {
        this.funcionario = c;
        Messages.addGlobalInfo("Excluido com Sucesso!");
        funcionarioDao.deletarObjeto(funcionario);
        getListaObjetos();
        this.funcionario = new Funcionario();
    }

    public void carregarDadosEditar(Funcionario c) {
        this.funcionario = c;
    }

    public Funcionario getFuncionario() {
        if(funcionario==null){
            return funcionario = new Funcionario();
        }
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
   
    public List<Funcionario> getListaObjetos() {
        return listaObjetos = funcionarioDao.listarObjetos();
    }

    public void setListaObjetos(List<Funcionario> listaObjetos) {
        this.listaObjetos = listaObjetos;
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

    public void setTamanho(Cargo[] cargo) {
        this.cargo = cargo;
    }
    
    public Situacao[] getSituacao() {
        return Situacao.values();
    }

    public void setSituacao(Situacao[] situacao) {
        this.situacao = situacao;
    }
    
    

}
