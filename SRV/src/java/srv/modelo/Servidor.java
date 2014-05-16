/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package srv.modelo;

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

/**
 *
 * @author Erick
 */
@Entity
@Table(name = "servidor")
@SuppressWarnings("serial")
public class Servidor implements java.io.Serializable {

    private String matriculaSIAPE;
    private String nome;
    private String senha;
    private String email;
    private int perfil; 
    private String cpf;
    private int cnh;
    private boolean motorista;
    private String rg;
    private String orgao_expedidor;
    private String estado_civil;
    private String sexo;
    private String telefone_comer;
    private String telefone_cel;
    private String estado;
    private boolean status_serv;
    private String cidade;
    private String nacionalidade;
    private String informacoes;
    private Date data_nascimento;

    public Servidor(String matriculaSIAPE, String nome, String senha, String email, int perfil, String cpf, int cnh, boolean motorista, String rg, String orgao_expedidor, String estado_civil, String sexo, String telefone_comer, String telefone_cel, String estado, boolean status_serv, String cidade, String nacionalidade, String informacoes, Date data_nascimento) {
        this.matriculaSIAPE = matriculaSIAPE;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.perfil = perfil;
        this.cpf = cpf;
        this.cnh = cnh;
        this.motorista = motorista;
        this.rg = rg;
        this.orgao_expedidor = orgao_expedidor;
        this.estado_civil = estado_civil;
        this.sexo = sexo;
        this.telefone_comer = telefone_comer;
        this.telefone_cel = telefone_cel;
        this.estado = estado;
        this.status_serv = status_serv;
        this.cidade = cidade;
        this.nacionalidade = nacionalidade;
        this.informacoes = informacoes;
        this.data_nascimento = data_nascimento;
    }

    public Servidor() {
        this.matriculaSIAPE = matriculaSIAPE;
    }

    public int VerificarLogin() {
        /*
         * 1 para administrador
         * 0 para Servidor normal
         * -1 para senha incorreta
         */
        ServidorDAO fdao = new ServidorDAO();
        List l = fdao.buscarServidor(matriculaSIAPE);

        if (!l.isEmpty()) {
            Servidor func = (Servidor) l.get(0);
            if (func.senha.equals(senha)) {
                if (func.perfil == 1) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
        return -1;
    }

    public void EnviarSenha() {
        ServidorDAO fdao = new ServidorDAO();
        List l = fdao.buscarServidor(matriculaSIAPE);
        if (!l.isEmpty()) {
            Servidor func = (Servidor) l.get(0);
            SimpleEmail simplemail = new SimpleEmail();

            try {
                simplemail.setDebug(true);
                simplemail.setSmtpPort(465);
                simplemail.setHostName("smtp.gmail.com");
                simplemail.setAuthentication("erickrpeck@gmail.com", "!*)%!(($");
                simplemail.setSSL(true);
                simplemail.addTo(func.getEmail()); //pode ser qualquer um email  
                simplemail.setFrom("erickrpeck@gmail.com"); //aqui necessita ser o email que voce fara a autenticacao  
                simplemail.setSubject("Senha do SRV");
                simplemail.setMsg("Sua senha é: " + func.getSenha());
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
    public int getCnh() {
        return cnh;
    }

    public void setCnh(int cnh) {
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

    @Column(name = "telefone_comer")
    public String getTelefone_comer() {
        return telefone_comer;
    }

    public void setTelefone_comer(String telefone_comer) {
        this.telefone_comer = telefone_comer;
    }

    @Column(name = "telefone_cel")
    public String getTelefone_cel() {
        return telefone_cel;
    }

    public void setTelefone_cel(String telefone_cel) {
        this.telefone_cel = telefone_cel;
    }

    @Column(name = "estado")
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Column(name = "cidade")
    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
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

    @Column(name = "status_serv")
    public boolean isStatus_serv() {
        return status_serv;
    }

    public void setStatus_serv(boolean status_serv) {
        this.status_serv = status_serv;
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
}
