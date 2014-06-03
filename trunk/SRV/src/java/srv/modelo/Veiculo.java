/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package srv.modelo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import java.util.Collection;
import javax.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author Paula
 */
@Entity
@Table(name = "veiculo")
@SuppressWarnings("serial")
public class Veiculo implements java.io.Serializable {

    private String placa;
    private String ano;
    private String marca;
    private String modelo;
    private String combustivel;
    private int capacidade;
    private String renavam;
    private boolean manutencao;
    private Date manutencao_data_inicial;
    private Date manutencao_data_final;
    @Cascade(CascadeType.ALL)
    private Collection<Reserva> reservas;

    public Veiculo() {
        this.placa = placa;
    }

    public Veiculo(String placa, String ano, String marca, String modelo, String combustivel, int capacidade, String renavam, boolean manutencao, Date manutencao_data_inicial, Date manutencao_data_final) {
        this.placa = placa;
        this.ano = ano;
        this.marca = marca;
        this.modelo = modelo;
        this.combustivel = combustivel;
        this.capacidade = capacidade;
        this.renavam = renavam;
        this.manutencao = manutencao;
        this.manutencao_data_inicial = manutencao_data_inicial;
        this.manutencao_data_final = manutencao_data_final;
    }

    @Column(name = "ano")
    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    @Column(name = "marca")
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Column(name = "modelo")
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Column(name = "combustivel")
    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    @Column(name = "capacidade")
    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    @Column(name = "renavam")
    public String getRenavam() {
        return renavam;
    }

    public void setRenavam(String renavam) {
        this.renavam = renavam;
    }

    @Column(name = "manutencao")
    public boolean isManutencao() {
        return manutencao;
    }

    public void setManutencao(boolean manutencao) {
        this.manutencao = manutencao;
    }

    @Column(name = "manutencao_data_inicial")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getManutencao_data_inicial() {
        return manutencao_data_inicial;
    }

    public void setManutencao_data_inicial(Date manutencao_data_inicial) {
        this.manutencao_data_inicial = manutencao_data_inicial;
    }

    @Column(name = "manutencao_data_final")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getManutencao_data_final() {
        return manutencao_data_final;
    }

    public void setManutencao_data_final(Date manutencao_data_final) {
        this.manutencao_data_final = manutencao_data_final;
    }

    @Id
    @Column(name = "placa")
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    @OneToMany(mappedBy = "veiculo", targetEntity = Reserva.class, fetch = FetchType.LAZY)
    public Collection<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(Collection<Reserva> reservas) {
        this.reservas = reservas;
    }
}
