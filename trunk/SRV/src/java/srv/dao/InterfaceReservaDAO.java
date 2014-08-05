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
    public abstract List listaReservas(String matricula, String numPagina);
    public abstract List listaReservasOutros(String matricula, String numPaginaOutros);
    public Reserva consultarIdReserva(int id_reserva);
    public abstract void excluirReserva(Reserva reserva);
    public Reserva consultarID_Reserva(int id_reserva);
    public abstract void atualizar(Reserva reserv);
    public abstract List buscarReservaPorSaida(String matricula_siape, String data_saida);
    public abstract List buscarReservaPorSaidaOutros(String matricula_siape, String data_saida);
    public abstract List buscarReservaPorRetorno(String matricula_siape, String data_retorno);
    public abstract List buscarReservaPorRetornoOutros(String matricula_siape, String data_retorno);
    public abstract List buscarReservaPorDestino(String matricula_siape, String destino);
    public abstract List buscarReservaPorDestinoOutros(String matricula_siape, String destino);
    public abstract List buscarReservaPorSaidaRetorno(String matricula_siape, String data_saida, String data_retorno);
    public abstract List buscarReservaPorSaidaRetornoOutros(String matricula_siape, String data_saida, String data_retorno);
    public abstract List buscarReservaPorSaidaDestino(String matricula_siape, String data_saida, String destino);
    public abstract List buscarReservaPorSaidaDestinoOutros(String matricula_siape, String data_saida, String destino);
    public abstract List buscarReservaPorRetornoDestino(String matricula_siape, String data_retorno, String destino);
    public abstract List buscarReservaPorRetornoDestinoOutros(String matricula_siape, String data_retorno, String destino);
    public abstract List buscarReservaPorSaidaRetornoDestino(String matricula_siape, String data_saida, String data_retorno, String destino);
    public abstract List buscarReservaPorSaidaRetornoDestinoOutros(String matricula_siape, String data_saida, String data_retorno, String destino);
    public int todasReservasCount(String matricula);
    public int todasReservasOutrosCount(String matricula);
}
