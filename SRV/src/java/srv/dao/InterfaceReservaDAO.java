/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package srv.dao;

import java.util.Date;
import java.util.List;
import srv.modelo.Reserva;
import srv.modelo.Veiculo;

/**
 *
 * @author Douglas
 */
public interface InterfaceReservaDAO {
    public abstract void inserirReserva(Reserva reserva);
    public abstract List<Veiculo> consultarDisponibilidadeVeiculo(Date dataSaida, Date dataRetorno);
    public abstract List<Veiculo> consultarDisponibilidadeVeiculo(Date dataSaida, Date dataRetorno, String idReserva);
    public abstract int gerarIdReserva();
    public abstract List listaReservas(String matricula);
    public abstract List listaReservasOutros(String matricula);
    public Reserva consultarIdReserva(int id_reserva);
    public abstract void excluirReserva(Reserva reserva);
    public Reserva consultarID_Reserva(int id_reserva);
    public abstract void atualizar(Reserva reserv);
}
