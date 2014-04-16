/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package srv.modelo;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import srv.dao.FuncionarioDAO;

/**
 *
 * @author Erick
 */
@Entity
@Table(name = "funcionario")
@SuppressWarnings("serial")
public class Funcionario implements java.io.Serializable
{
    private String matriculaSIAPE;
    private String senha;
    private int perfil;
    private String email;
    
    public Funcionario()
    {
        this.perfil = perfil;
    }
    
    public int VerificarLogin()
    {
        /*
         * 1 para administrador
         * 0 para Funcionario normal
         * -1 para senha incorreta
         */
        FuncionarioDAO fdao = new FuncionarioDAO();
        List l = fdao.buscarFuncionario(matriculaSIAPE);
        
        if (!l.isEmpty())
        {
            Funcionario func = (Funcionario) l.get(0);
            if (func.senha.equals(senha))
            {
                if (func.perfil == 1)
                {    
                    return 1;
                }
                else
                {
                    return 0;
                }
            }
        }
        return -1;
    }
    
    public void EnviarSenha()
    {
        FuncionarioDAO fdao = new FuncionarioDAO();
        List l = fdao.buscarFuncionario(matriculaSIAPE);
        if (!l.isEmpty())
        {
            Funcionario func = (Funcionario) l.get(0);
            SimpleEmail simplemail = new SimpleEmail();  
  
        try {  
        simplemail.setDebug(true);  
        simplemail.setSmtpPort(465);
        simplemail.setHostName("smtp.gmail.com");  
        simplemail.setAuthentication("erickrpeck@gmail.com","!*)%!(($");  
        simplemail.setSSL(true);
        simplemail.addTo(func.getEmail()); //pode ser qualquer um email  
        simplemail.setFrom("erickrpeck@gmail.com"); //aqui necessita ser o email que voce fara a autenticacao  
        simplemail.setSubject("Senha do SRV");  
        simplemail.setMsg("Sua senha é: "+func.getSenha());  
        simplemail.send();  
  
        } catch (EmailException e) {  
  
        System.out.println(e.getMessage());  
  
        }
        }
    }
    
    @Id
    @Column(name="matricula_siape")
    public String getMatriculaSIAPE() 
    {
        return matriculaSIAPE;
    }

    public void setMatriculaSIAPE(String matriculaSIAPE) 
    {
        this.matriculaSIAPE = matriculaSIAPE;
    }
    
    @Column(name="senha")
    public String getSenha() 
    {
        return senha;
    }

    public void setSenha(String senha) 
    {
        this.senha = senha;
    }
    
    @Column(name="perfil")
    public int getPerfil() 
    {
        return perfil;
    }

    public void setPerfil(int perfil) 
    {
        this.perfil = perfil;
    }
    
    @Column(name="email")
    public String getEmail() 
    {
        return email;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }
   
}
