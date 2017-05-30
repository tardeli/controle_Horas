package br.com.controlehoras.dao;

import br.com.controlehoras.modelo.Escala;
import br.com.controlehoras.modelo.ItemEscala;
import br.com.controlehoras.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.omnifaces.util.Messages;


/**
 *
 * @author Tardeli
 */
public class ItemEscalaDao extends Generic_Dao<ItemEscala>{
    private Session sessao;
    private Transaction transacao;
    
    public void salvar(Long codigo, List<ItemEscala> itensEscala) {
        sessao = (Session) HibernateUtil.getSessionFactory().openSession();
        try {
            transacao = sessao.beginTransaction();
            EscalaDao escalaDao = new EscalaDao();
            Escala escala = escalaDao.buscarObjeto(codigo);
            
            if(escala==null){
                Messages.addGlobalInfo("Erro");
            }else{
                for (ItemEscala item : itensEscala) {
                    item.setEscala(escala);
                    ItemEscalaDao itemEscalaDao = new ItemEscalaDao();
                    itemEscalaDao.salvarOuAtualizarObjeto(item);
                }
                Messages.addGlobalInfo("Itens Salvos com sucesso!");
            }
            transacao.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessao.close();
        }
        
    }
}
