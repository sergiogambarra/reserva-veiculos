/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package srv.modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author Douglas
 */
@Entity
@Table(name = "destino")
@SuppressWarnings("serial")
public class Destino implements Serializable {
    private int id_destino;
    private String nome;
    @Cascade(CascadeType.ALL)
    private Collection<Reserva> reservas;

    public Destino() {
    }

    public Destino(int id_destino, String nome) {
        this.id_destino = id_destino;
        this.nome = nome;
    }
    
    @Id
    @Column(name = "id_destino")
    public int getId_destino() {
        return id_destino;
    }

    public void setId_destino(int id_destino) {
        this.id_destino = id_destino;
    }
    
    @Column(name = "nome")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @OneToMany(mappedBy="destino", targetEntity = Reserva.class, fetch = FetchType.LAZY)
    public Collection<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(Collection<Reserva> reservas) {
        this.reservas = reservas;
    }
}
