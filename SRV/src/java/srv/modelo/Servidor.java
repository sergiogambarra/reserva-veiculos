/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package srv.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import srv.dao.ServidorDAO;
import java.util.Collection;
import javax.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author Erick
 */
@Entity
@Table(name = "servidor")
@SuppressWarnings("serial")
public class Servidor implements Serializable {

    private String matriculaSIAPE;
    private String nome;
    private String senha;
    private String email;
    private int perfil;
    private String sexo;
    private Date data_nascimento;
    private String cpf;
    private String rg;
    private String orgao_expedidor;
    private String naturalidade;
    private String estado;
    private String nacionalidade;
    private String estado_civil;
    private String telefone1;
    private String telefone2;
    private boolean motorista;
    private String cnh;
    private boolean status_serv;
    private String informacoes;
    
    @Cascade(CascadeType.ALL)
    private Collection<Reserva> reservas;

    public Servidor(String matriculaSIAPE, String nome, String senha, String email, int perfil, String sexo, Date data_nascimento, String cpf, String rg, String orgao_expedidor, String naturalidade, String estado, String nacionalidade, String estado_civil, String telefone1, String telefone2, boolean motorista, String cnh, boolean status_serv, String informacoes) {
        this.matriculaSIAPE = matriculaSIAPE;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.perfil = perfil;
        this.sexo = sexo;
        this.data_nascimento = data_nascimento;
        this.cpf = cpf;
        this.rg = rg;
        this.orgao_expedidor = orgao_expedidor;
        this.naturalidade = naturalidade;
        this.estado = estado;
        this.nacionalidade = nacionalidade;
        this.estado_civil = estado_civil;
        this.telefone1 = telefone1;
        this.telefone2 = telefone2;
        this.motorista = motorista;
        this.cnh = cnh;
        this.status_serv = status_serv;
        this.informacoes = informacoes;

    }

    public Servidor() {
        this.matriculaSIAPE = matriculaSIAPE;
    }
    
    public void EnviarSenha() {
        ServidorDAO fdao = new ServidorDAO();
        Servidor s = fdao.buscarServidor(this.matriculaSIAPE);
        if (s != null) {
            Servidor serv = s;
            SimpleEmail simplemail = new SimpleEmail();

            try {
                simplemail.setDebug(true);
                simplemail.setSmtpPort(465);
                simplemail.setHostName("smtp.gmail.com");
                simplemail.setAuthentication("email@email", "senha");
                simplemail.setSSL(true);
                simplemail.addTo(serv.getEmail()); //pode ser qualquer um email  
                simplemail.setFrom("email@email.com"); //aqui necessita ser o email que voce fara a autenticacao  
                simplemail.setSubject("Senha do SRV");
                simplemail.setMsg("Sua senha é: " + serv.getSenha());
                simplemail.send();

            } catch (EmailException e) {
            }
        }
    }

    @Id
    @Column(name = "matricula_siape")
    public String getMatriculaSIAPE() {
        return matriculaSIAPE;
    }

    public void setMatriculaSIAPE(String matriculaSIAPE) {
        this.matriculaSIAPE = matriculaSIAPE;
    }

    @Column(name = "senha")
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Column(name = "perfil")
    public int getPerfil() {
        return perfil;
    }

    public void setPerfil(int perfil) {
        this.perfil = perfil;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "nome")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column(name = "cpf")
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Column(name = "cnh")
    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    @Column(name = "motorista")
    public boolean isMotorista() {
        return motorista;
    }

    public void setMotorista(boolean motorista) {
        this.motorista = motorista;
    }

    @Column(name = "rg")
    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    @Column(name = "orgao_expedidor")
    public String getOrgao_expedidor() {
        return orgao_expedidor;
    }

    public void setOrgao_expedidor(String orgao_expedidor) {
        this.orgao_expedidor = orgao_expedidor;
    }

    @Column(name = "telefone1")
    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    @Column(name = "telefone2")
    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    @Column(name = "estado")
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Column(name = "naturalidade")
    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    @Column(name = "informacoes")
    public String getInformacoes() {
        return informacoes;
    }

    public void setInformacoes(String informacoes) {
        this.informacoes = informacoes;
    }

    @Column(name = "data_nascimento")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    @Column(name = "sexo")
    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Column(name = "estado_civil")
    public String getEstado_civil() {
        return estado_civil;
    }

    public void setEstado_civil(String estado_civil) {
        this.estado_civil = estado_civil;
    }

    @Column(name = "nacionalidade")
    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    @OneToMany(mappedBy = "servidor", targetEntity = Reserva.class, fetch = FetchType.LAZY)
    public Collection<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(Collection<Reserva> reservas) {
        this.reservas = reservas;
    }

    @Column(name = "status_serv")
    public boolean isStatus_serv() {
        return status_serv;
    }

    public void setStatus_serv(boolean status_serv) {
        this.status_serv = status_serv;
    }
}
