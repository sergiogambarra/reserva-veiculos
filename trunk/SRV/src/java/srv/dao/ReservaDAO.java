/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package srv.dao;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
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
    public void inserirReserva(Reserva reserva) {
        session = Conexao.getInstance();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            session.save(reserva);
            tx.commit();
        } catch (Exception e) {
            e.getMessage();            
        } finally{
            session.close();
        }
    }

    @Override
    public void consultarDisponibilidadeVeiculo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List listaReservas(){
        session = Conexao.getInstance();
        List list = session.createQuery("from Reserva").list();
        return list;
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
    public int gerarIdReserva(){
        GregorianCalendar calendar = new GregorianCalendar(); 
        StringBuilder idReserva= new StringBuilder();
        idReserva.append(Integer.toString(calendar.get(Calendar.YEAR)).substring(2, 4));
        
        
        if(calendar.get(Calendar.MONTH) < 10){
           idReserva.append('0');
           idReserva.append(calendar.get(Calendar.MONTH));
        }else{
           idReserva.append(calendar.get(Calendar.MONTH)); 
        }
        if(calendar.get(Calendar.HOUR) < 10){
           idReserva.append('0');
           idReserva.append(calendar.get(Calendar.HOUR));
        }else{
           idReserva.append(calendar.get(Calendar.HOUR)); 
        }
        if(calendar.get(Calendar.MINUTE) < 10){
           idReserva.append('0');
           idReserva.append(calendar.get(Calendar.MINUTE));
        }else{
           idReserva.append(calendar.get(Calendar.MINUTE)); 
        }
        if(calendar.get(Calendar.SECOND) < 10){
           idReserva.append('0');
           idReserva.append(calendar.get(Calendar.SECOND));
        }else{
           idReserva.append(calendar.get(Calendar.SECOND)); 
        }
        
        int id = Integer.parseInt(idReserva.toString());
        
        return id;
    }
    
}
