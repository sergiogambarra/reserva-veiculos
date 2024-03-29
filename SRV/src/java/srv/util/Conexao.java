/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package srv.util;

import java.sql.Connection;
import java.sql.DriverManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author Erick
 */
public class Conexao 
{
    private static final SessionFactory sessionFactory;
    private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
    
    static
    {
        try
        {
            sessionFactory = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
        }
        catch (Throwable t)
        {
            throw new ExceptionInInitializerError(t);
        }
    }
    
    public static Session getInstance()
    {
        Session session = (Session) threadLocal.get();
        session = sessionFactory.openSession();
        threadLocal.set(session);
        return session;
    }
}
