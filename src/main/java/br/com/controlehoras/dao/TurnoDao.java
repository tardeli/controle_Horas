package br.com.controlehoras.dao;

import br.com.controlehoras.modelo.Turno;
import br.com.controlehoras.modelo.Turno;
import br.com.controlehoras.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Tardeli
 */
public class TurnoDao extends Generic_Dao<Turno> implements Serializable{
    private Session sessao;
    private Transaction transacao;
    
    public List<Turno> listarObjetosCriterios(){
        try {
            sessao = (Session) HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = sessao.createCriteria(Turno.class);
            //criteria.setFetchMode("funcionario", FetchMode.JOIN);
            criteria.createAlias("funcionario", "f");
            
            //criteria.createCriteria("funcionarios", "f");
            
            criteria.add(Restrictions.eq("nome", "T1"));
            
            criteria.addOrder(Order.asc("turno"));
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
