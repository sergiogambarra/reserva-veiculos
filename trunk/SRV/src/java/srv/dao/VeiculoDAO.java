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
import srv.modelo.Veiculo;
import srv.util.Conexao;

/**
 *
 * @author Paula
 */
public class VeiculoDAO implements InterfaceVeiculoDAO  {

    private Session session;

    @Override
    public List buscarVeiculo(String placa) {
        session = Conexao.getInstance();
        Query query = session.createQuery("from Veiculo l where l.placa like :placa");
        List list = query.setString("placa", placa).list();

        return list;
    }

    @Override
    public void salvar(Veiculo veic) {
        session = Conexao.getInstance();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(veic);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void excluir(Veiculo veic) {
        session = Conexao.getInstance();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete(veic);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void atualizar(Veiculo veic) {
        session = Conexao.getInstance();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(veic);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List visualizar(Veiculo veic) {
        session = Conexao.getInstance();
        List list = session.createQuery("from Veiculo").list();
        return list;
    }

    @Override
    public List todosVeiculo() {
        session = Conexao.getInstance();
        List list = session.createQuery("from Veiculo").list();
        return list;
    }

    /**
     *
     * @param placa
     * @return
     */
    @Override
    public List<Veiculo> consultarPlaca(String placa) {
        session = Conexao.getInstance();
        Query query = session.createQuery("from Veiculo l where l.placa like :placa");
        List list = query.setString("placa", placa).list();

        return list;
    }
}
