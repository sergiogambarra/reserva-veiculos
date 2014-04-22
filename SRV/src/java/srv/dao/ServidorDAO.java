/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package srv.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import srv.modelo.Servidor;
import srv.util.Conexao;

/**
 *
 * @author Erick
 */
public class ServidorDAO implements InterfaceServidorDAO{
    
     private Session session;


    public List buscarServidor(String matriculaSIAPE) {
        session = Conexao.getInstance();
        Query query = session.createQuery("from Servidor l where l.matriculaSIAPE like :matricula_siape");
        List list = query.setString("matricula_siape", matriculaSIAPE).list();
        
        return list;
    }
    
    public void salvar(Servidor serv) {
        session = Conexao.getInstance();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(serv);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
    }

    public void excluir(Servidor serv) {
        session = Conexao.getInstance();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete(serv);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void atualizar(Servidor serv) {
        session = Conexao.getInstance();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(serv);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
    }

    public List visualizar(Servidor serv) {
        session = Conexao.getInstance();
        List list = session.createQuery("from Servidor").list();
        return list;
    }

   public List todosServidor() {
        session = Conexao.getInstance();
        List list = session.createQuery("from Servidor").list();
        return list;
    }
    
    
}
