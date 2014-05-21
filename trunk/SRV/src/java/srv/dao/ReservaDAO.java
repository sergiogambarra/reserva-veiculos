/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package srv.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import srv.modelo.Reserva;
import srv.util.Conexao;

/**
 *
 * @author Douglas
 */
public class ReservaDAO implements InterfaceReservaDAO{
    
    private Session session;
    
    @Override
    public void gravarDados(Reserva reserva) {
        session = Conexao.getInstance();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            session.save(reserva);
            tx.commit();
        } catch (Exception e) {
        } finally{
            session.close();
        }
        
    }

    @Override
    public void consultarDisponibilidadeVeiculo() {
        throw new UnsupportedOperationException("Not supported yet.");
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
    
}
