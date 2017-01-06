/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlehoras.util;

import java.sql.Connection;
import java.sql.SQLException;
import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.ReturningWork;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Tardeli
 */
public class HibernateUtil {

    private static final SessionFactory fabricaSessoes;
    
    static {
        try {
            
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            fabricaSessoes = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return fabricaSessoes;
    }
    
    public static Connection getConnection(){
        Session sessao = fabricaSessoes.openSession();
        Connection conexao = sessao.doReturningWork(new ReturningWork<Connection>() {
            @Override
            public Connection execute(Connection conn) throws SQLException {
                return conn;
            }    
        });
        return conexao;
    }
}