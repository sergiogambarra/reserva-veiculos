/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package srv.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import srv.modelo.Funcionario;
import srv.util.Conexao;

/**
 *
 * @author Erick
 */
public class FuncionarioDAO{
    
     private Session session;


    public List buscarFuncionario(String login) {
        session = Conexao.getInstance();
        Query query = session.createQuery("from Funcionario l where l.login like :login");
        List list = query.setString("login", login).list();
        
        return list;
    }
}
