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
    private String login;
    private String senha;
    private int administrador;
    
    public Funcionario()
    {
        this.administrador = administrador;
    }
    
    public int VerificarLogin()
    {
        /*
         * 1 para administrador
         * 0 para Funcionario normal
         * -1 para senha incorreta
         */
        FuncionarioDAO fdao = new FuncionarioDAO();
        List l = fdao.buscarFuncionario(login);
        
        if (!l.isEmpty())
        {
            Funcionario func = (Funcionario) l.get(0);
            if (func.senha.equals(senha))
            {
                if (func.administrador == 1)
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
    
    @Id
    @Column(name="login")
    public String getLogin() 
    {
        return login;
    }

    public void setLogin(String login) 
    {
        this.login = login;
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
    
    @Column(name="administrador")
    public int getAdministrador() 
    {
        return administrador;
    }

    public void setAdministrador(int administrador) 
    {
        this.administrador = administrador;
    }
    
   
}
