package br.com.controlehoras.dao;

import br.com.controlehoras.modelo.Funcionario;
import br.com.controlehoras.modelo.Status;
import br.com.controlehoras.util.HibernateUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Tardeli
 */
public class StatusDao extends Generic_Dao<Status> implements Serializable {

    private Session sessao;
    private Transaction transacao;

    public List<Status> listarObjetosCriterios() {

        try {
            sessao = (Session) HibernateUtil.getSessionFactory().openSession();
            Query consulta = sessao.createQuery("from Status s WHERE s.status = :status");
            //Status status = new Status();
            //consulta.setParameter("status", status.getStatus().Atestado);
            return consulta.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            sessao.close();
        }
    }
}
