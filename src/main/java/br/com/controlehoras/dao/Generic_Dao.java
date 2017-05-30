package br.com.controlehoras.dao;

import br.com.controlehoras.util.HibernateUtil;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;


/**
 *
 * @author Tardeli
 */
public class Generic_Dao<Entidade>{
    
    private Session sessao;
    private Transaction transacao;
    private Class<Entidade> classe;
    
    public Generic_Dao(){
        this.classe = (Class<Entidade>) ((ParameterizedType) 
                getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    } 
    
    public void salvarOuAtualizarObjeto(Entidade entidade){
        try {
            sessao = (Session) HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
            sessao.saveOrUpdate(entidade);
            //sessao.flush();
            transacao.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            sessao.close();
        }
    }
    
    public void deletarObjeto(Entidade entidade){
        try {
            sessao = (Session) HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
            sessao.delete(entidade);
            transacao.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            sessao.close();
        }
    }
       
    public List<Entidade> listarObjetos(){
        try {
            sessao = (Session) HibernateUtil.getSessionFactory().openSession();
            
            Criteria criteria = sessao.createCriteria(classe); 
            
            return criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally{
            sessao.close();
        } 
    }
    
    public Entidade buscarObjeto(Long codigo){
        sessao = (Session) HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria criteria = sessao.createCriteria(classe);
            criteria.add(Restrictions.idEq(codigo));
            Entidade objeto = (Entidade) criteria.uniqueResult();
            return objeto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally{
            sessao.close();
        } 
    }
    
    
}
