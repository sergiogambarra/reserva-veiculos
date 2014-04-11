
package br.edu.ifrs.classes;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Aluno
 */
public class Cliente {
    
    private int codcli;
    private String nome;
    private String Telefone;
    private String Endereco;
   
     
    public int getCodcli() {
        return codcli;
    }

   
    public void setCodcli(int codcli) {
        this.codcli = codcli;
    } 
    
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the Telefone
     */
    public String getTelefone() {
        return Telefone;
    }

    /**
     * @param Telefone the Telefone to set
     */
    public void setTelefone(String Telefone) {
        this.Telefone = Telefone;
    }

    /**
     * @return the Endereco
     */
    public String getEndereco() {
        return Endereco;
    }

    /**
     * @param Endereco the Endereco to set
     */
    public void setEndereco(String Endereco) {
        this.Endereco = Endereco;
    }
    
     public void Cadastrar() throws Exception{
              
        try{
        Class.forName("com.mysql.jdbc.Driver");
          Connection c; 
          c = DriverManager.getConnection("jdbc:mysql://localhost:3306/vendadecamisas","root","");
          
          PreparedStatement p;
          p= c.prepareStatement("insert into clientes (nome,telefone,endereco) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
          
          p.setString(1, nome);
          p.setString(2, Telefone);
          p.setString(3, Endereco);
          
          p.executeUpdate();
          
          ResultSet rs = p.getGeneratedKeys();  
          if(rs.next()){  
              this.codcli = rs.getInt(1);  
          }   
          rs.close();
          p.close();
          c.close();
       
        } catch (Exception e) {
            throw new Exception("Falha no banco: "+e.getMessage());
        }
    }
        

        public List<Cliente> consultar() throws Exception {
       
            List<Cliente> lista = new ArrayList();
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c;
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/vendadecamisas","root","");
            PreparedStatement p;
            p = c.prepareStatement("select * from clientes");
            
            ResultSet rs;
            rs = p.executeQuery();
            
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.nome = rs.getString("nome");
                cliente.Telefone = rs.getString("telefone");
                cliente.Endereco = rs.getString("endereco");
                lista.add(cliente);
            }
            
            rs.close();
            p.close();
            c.close();
        } catch (Exception e) {
            throw new Exception("Falha no banco: "+e.getMessage());
        }
        
        return lista;
    }

       public void buscarDados() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c;
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/vendadecamisas","root","");
            
            PreparedStatement p;
            p = c.prepareStatement("select * from clientes where codcli = ?");
            p.setInt(1, this.codcli);
            
            ResultSet rs;
            rs = p.executeQuery();
            
            if (rs.next()) {
                this.setNome(rs.getString("nome"));
                this.setEndereco(rs.getString("telefone"));
                this.setTelefone(rs.getString("endereco"));
            }
            
            p.close();
            rs.close();
            c.close();
        } catch (Exception ex) {
            throw new Exception ("Falha no banco: "+ex.getMessage());
        }
    }
    
    public void atualizar() throws Exception {
        try {
             Class.forName("com.mysql.jdbc.Driver");
            Connection c;
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/vendadecamisas","root","");
            
            PreparedStatement p;
            p = c.prepareStatement("update clientes set nome = ?, endereco = ?, telefone = ? where codcli = ?");
            p.setString(1, this.nome);
            p.setString(2, this.Endereco);
            p.setString(3, this.Telefone);
            p.setInt(4, codcli);
            
            p.execute();
            
            p.close();
            c.close();
        } catch (Exception ex) {
            throw new Exception ("Falha no banco: "+ex.getMessage());
        }
    }
    
    public void excluir() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c;
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/vendadecamisas","root","");
            
            PreparedStatement p;
            
            p = c.prepareStatement("delete from Pessoafisica where codcli_clientes = ?");
            p.setInt(1, codcli);
            p.execute();
            
            p = c.prepareStatement("delete from Pessoajuridica where codcli_clientes = ?");
            p.setInt(1, codcli);
            p.execute();
            
            p = c.prepareStatement("delete from clientes where codcli = ?");
            p.setInt(1,codcli);
            p.execute();
            
            p.close();
            c.close();
        } catch (Exception ex) {
            throw new Exception ("Falha no banco: "+ex.getMessage());
        }
    }   
    
}
