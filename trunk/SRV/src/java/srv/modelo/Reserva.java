/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package srv.modelo;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Douglas
 */
@Entity
@Table(name = "reserva")
@SuppressWarnings("serial")
public class Reserva implements java.io.Serializable{
    private int id_reserva;
    private String matricula_siape;
    private Date data_saida;
    private Date data_retorno;
    private String placa;
    private boolean condutor;
    private String matricula_siape_condutor;
    private int id_destino;
    private String descricao_destino;
    private Date reserva_datetime;
    
    public Reserva() {
    }
    
    public Reserva(int id_reserva, String matricula_siape, Date data_saida, Date data_retorno, String placa, boolean condutor, String matricula_siape_condutor, int id_destino, String descricao_destino, Date reserva_datetime) {
        this.id_reserva = id_reserva;
        this.matricula_siape = matricula_siape;
        this.data_saida = data_saida;
        this.data_retorno = data_retorno;
        this.placa = placa;
        this.condutor = condutor;
        this.matricula_siape_condutor = matricula_siape_condutor;
        this.id_destino = id_destino;
        this.descricao_destino = descricao_destino;
        this.reserva_datetime = reserva_datetime;
    }
    
    @Id
    @Column(name = "id_reserva")
    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    @Column(name = "matricula_siape")
    public String getMatricula_siape() {
        return matricula_siape;
    }

    public void setMatricula_siape(String matricula_siape) {
        this.matricula_siape = matricula_siape;
    }
    
    @Column(name = "data_saida")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getData_saida() {
        return data_saida;
    }
    
    public void setData_saida(Timestamp data_saida) {
        this.data_saida = data_saida;
    }

    @Column(name = "data_retorno")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getData_retorno() {
        return data_retorno;
    }

    public void setData_retorno(Timestamp data_retorno) {
        this.data_retorno = data_retorno;
    }

    @Column(name = "placa")
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
    
    @Column(name = "condutor")
    public boolean getCondutor() {
        return condutor;
    }

    public void setCondutor(boolean condutor) {
        this.condutor = condutor;
    }

    @Column(name = "matricula_siape_condutor")
    public String getMatricula_siape_condutor() {
        return matricula_siape_condutor;
    }

    public void setMatricula_siape_condutor(String matricula_siape_condutor) {
        this.matricula_siape_condutor = matricula_siape_condutor;
    }

    @Column(name = "id_destino")
    public int getId_destino() {
        return id_destino;
    }

    public void setId_destino(int id_destino) {
        this.id_destino = id_destino;
    }

    @Column(name = "descricao_destino")
    public String getDescricao_destino() {
        return descricao_destino;
    }

    public void setDescricao_destino(String descricao_destino) {
        this.descricao_destino = descricao_destino;
    }

    @Column(name = "reserva_datetime")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getReserva_datetime() {
        return reserva_datetime;
    }

    public void setReserva_datetime(Timestamp reserva_datetime) {
        this.reserva_datetime = reserva_datetime;
    }    
}
