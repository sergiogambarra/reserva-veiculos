/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package srv.dao;

import java.util.List;
import srv.modelo.Reserva;

/**
 *
 * @author Douglas
 */
public interface InterfaceReservaDAO {
    public abstract void inserirReserva(Reserva reserva);
    public abstract void consultarDisponibilidadeVeiculo();
    public abstract void consultarDisponibilidadeReserva();
    public abstract void consultarDadosReserva();
    public abstract void excluirReserva();
    public abstract int gerarIdReserva();
    public abstract List listaReservas(String matricula);
    public abstract List listaReservasOutros(String matricula);
}
