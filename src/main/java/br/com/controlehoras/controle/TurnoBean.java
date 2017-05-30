package br.com.controlehoras.controle;

import br.com.controlehoras.dao.TurnoDao;
import br.com.controlehoras.enumeradores.Cargo;
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
public class TurnoBean implements Serializable {

    private Turno turno = new Turno();
    private TurnoDao turnoDao = new TurnoDao();
    private List<Turno> listaObjetos = new ArrayList<>();

    public TurnoBean() {
        this.getListaObjetos();
    }
    
    
    
    public void novo() {
        this.turno = new Turno();
    }
    


    public String salvar() {
//        Turno f = turnoDao.buscarObjeto(this.turno.getMatricula());
//        if (f==null) {
            turnoDao.salvarOuAtualizarObjeto(this.turno);
            getListaObjetos();
            Messages.addGlobalInfo("Salvo com sucesso!");
            this.turno = new Turno();
            return "erro";
//        } 
//        else {
//            turnoDao.salvarOuAtualizarObjeto(this.turno);
//            getListaObjetos();
//            Messages.addGlobalInfo("Atualizado com Sucesso!");
//            this.turno = new Turno();
//            return "erro.xhtml";
//        }
    }

    public void excluir(Turno c) {
        this.turno = c;
        Messages.addGlobalInfo("Excluido com Sucesso!");
        turnoDao.deletarObjeto(turno);
        getListaObjetos();
        this.turno = new Turno();
    }

    public void carregarDadosEditar(Turno c) {
        this.turno = c;
    }

    public Turno getTurno() {
        if(turno==null){
            return turno = new Turno();
        }
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }
   
    public List<Turno> getListaObjetos() {
        return listaObjetos = turnoDao.listarObjetos();
    }

    public void setListaObjetos(List<Turno> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }
    
    public Cargo[] getCargo() {
        return Cargo.values();
    }
}
