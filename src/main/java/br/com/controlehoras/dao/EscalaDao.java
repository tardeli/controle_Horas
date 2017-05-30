/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlehoras.dao;

import br.com.controlehoras.modelo.Escala;
import br.com.controlehoras.util.HibernateUtil;
import java.io.Serializable;
import java.util.Date;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Tardeli
 */
public class EscalaDao extends Generic_Dao<Escala> implements Serializable{

    private Session sessao;
    private Transaction transacao;

    public Long salvar(Escala escala) {
        Long codigo = null;
        try {
            sessao = (Session) HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
            codigo = (Long) sessao.save(escala);
            transacao.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessao.close();
        }
        return codigo;
    }

    public Boolean pesquisarData(Date data) {
        sessao = (Session) HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria criteria = sessao.createCriteria(Escala.class);
            criteria.add(Restrictions.eq("data", data));
            Escala escala = (Escala) criteria.uniqueResult();
            if (escala == null) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            sessao.close();
        }
    }

    public Escala ultimaDataEscala() {
        sessao = (Session) HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria criteria = sessao.createCriteria(Escala.class);
            criteria.addOrder(Order.desc("data"));
            return (Escala) criteria.list().get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            sessao.close();
        }
    }
    
}
