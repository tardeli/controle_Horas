package br.com.controlehoras.dao;

import br.com.controlehoras.enumeradores.Situacao;
import br.com.controlehoras.modelo.Funcionario;
import br.com.controlehoras.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import static org.hibernate.criterion.Restrictions.eq;

/**
 *
 * @author Tardeli
 */
public class FuncionarioDao extends Generic_Dao<Funcionario> implements Serializable {
    private Session sessao;
    private Transaction transacao;
    
    public List<Funcionario> listarObjetosCriterios(){
        try {
            sessao = (Session) HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = sessao.createCriteria(Funcionario.class);
            criteria.createCriteria ("turno", "t");
            
            //criteria.add(Restrictions.eq("t.nome", "T1"));
            //criteria.add(Restrictions.eq("situacao", Situacao.Ativo));
            criteria.addOrder(Order.asc("t.nome"));
            criteria.addOrder(Order.asc("nomeGuerra"));
            
            return criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally{
            sessao.close();
        } 
    }
    
}
