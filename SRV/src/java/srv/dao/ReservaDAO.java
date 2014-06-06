/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package srv.dao;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import srv.modelo.Reserva;
import srv.util.Conexao;

/**
 *
 * @author Douglas
 */
public class ReservaDAO implements InterfaceReservaDAO {

    private Session session;

    @Override
    public void inserirReserva(Reserva reserva) {
        session = Conexao.getInstance();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(reserva);
            tx.commit();
        } catch (Exception e) {
            e.getMessage();
        } finally {
            session.close();
        }
    }

    @Override
    public void consultarDisponibilidadeVeiculo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List listaReservas(String matricula) {
        session = Conexao.getInstance();
        try {
            Transaction tx = session.beginTransaction();
            List list;
            if (null != matricula) {

                String sql = "from Reserva r"
                        + " where r.matricula_siape = :matriculaSIAPE";

                Query query = session.createQuery(sql);
                query.setString("matriculaSIAPE", matricula);

                list = query.list();
                tx.commit();
            } else {
                String sql = "from Reserva r"
                        + " where r.matricula_siape <> :matriculaSIAPE";

                Query query = session.createQuery(sql);
                query.setString("matriculaSIAPE", matricula);

                list = query.list();
                tx.commit();
            }

            session.close();
            return list;

        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List listaReservasOutros(String matricula) {
        session = Conexao.getInstance();
        try {
            Transaction tx = session.beginTransaction();
            List list;

            String sql = "from Reserva r"
                    + " where r.matricula_siape <> :matriculaSIAPE";

            Query query = session.createQuery(sql);
            query.setString("matriculaSIAPE", matricula);

            list = query.list();
            tx.commit();

            session.close();
            return list;

        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return null;
    }
   
    @Override
    public Reserva consultarID_Reserva(int id_reserva ) {
        session = Conexao.getInstance();
        Query query = session.createQuery("from Reserva l where l.id_reserva = :id_reserva");
        Reserva r = (Reserva) query.setInteger("id_reserva", id_reserva).uniqueResult();

        return r;
    }
    
    
    @Override
    public void excluirReserva(Reserva reserva) {
        session = Conexao.getInstance();
        Transaction tx = null;
        
    ///////////////////////////
        try {
            tx = session.beginTransaction();
            session.delete(reserva);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    @Override
    public Reserva consultarIdReserva(int id_reserva) {
        session = Conexao.getInstance();
        Query query = session.createQuery("from Reserva l where l.id_reserva = :id_reserva");
        Reserva r = (Reserva) query.setInteger("id_reserva", id_reserva).uniqueResult();

        return r;
    }

    @Override
    public void consultarDisponibilidadeReserva() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void consultarDadosReserva() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void excluirReserva() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int gerarIdReserva() {
        GregorianCalendar calendar = new GregorianCalendar();
        StringBuilder idReserva = new StringBuilder();
        idReserva.append(Integer.toString(calendar.get(Calendar.YEAR)).substring(2, 4));


        if (calendar.get(Calendar.MONTH) < 10) {
            idReserva.append('0');
            idReserva.append(calendar.get(Calendar.MONTH));
        } else {
            idReserva.append(calendar.get(Calendar.MONTH));
        }
        if (calendar.get(Calendar.HOUR) < 10) {
            idReserva.append('0');
            idReserva.append(calendar.get(Calendar.HOUR));
        } else {
            idReserva.append(calendar.get(Calendar.HOUR));
        }
        if (calendar.get(Calendar.MINUTE) < 10) {
            idReserva.append('0');
            idReserva.append(calendar.get(Calendar.MINUTE));
        } else {
            idReserva.append(calendar.get(Calendar.MINUTE));
        }
        if (calendar.get(Calendar.SECOND) < 10) {
            idReserva.append('0');
            idReserva.append(calendar.get(Calendar.SECOND));
        } else {
            idReserva.append(calendar.get(Calendar.SECOND));
        }

        int id = Integer.parseInt(idReserva.toString());

        return id;
    }
}