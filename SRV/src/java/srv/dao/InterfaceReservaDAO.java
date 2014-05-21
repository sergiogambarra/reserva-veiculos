/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package srv.dao;

import srv.modelo.Reserva;

/**
 *
 * @author Douglas
 */
public interface InterfaceReservaDAO {
    public abstract void gravarDados(Reserva reserva);
    public abstract void consultarDisponibilidadeVeiculo();
    public abstract void consultarDisponibilidadeReserva();
    public abstract void consultarDadosReserva();
    public abstract void excluirReserva();
}
