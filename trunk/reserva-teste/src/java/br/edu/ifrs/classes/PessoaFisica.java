
package br.edu.ifrs.classes;



import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 *
 * @author Aluno
 */
public class PessoaFisica extends Cliente{
    
    
    private String CPF;
    private String RG;
    private Calendar dataNascimento;
    
     /**
     * @return the CPF
     */
    public String getCPF() {
        return CPF;
    }

    /**
     * @param CPF the CPF to set
     */
    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    /**
     * @return the RG
     */
    public String getRG() {
        return RG;
    }

    /**
     * @param RG the RG to set
     */
    public void setRG(String RG) {
        this.RG = RG;
    }



    /**
     * @return the dataNascimento
     */
    public Calendar getDataNascimento() {
        return dataNascimento;
    }

    /**
     * @param dataNascimento the dataNascimento to set
     */
    public void setDataNascimento(Calendar dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

     
    
    
     public void Cadastrar() throws Exception {
            
       
        super.Cadastrar();
        
     
        try{
        Class.forName("com.mysql.jdbc.Driver");
          Connection c; 
          c = DriverManager.getConnection("jdbc:mysql://localhost:3306/vendadecamisas","root","");
          
          PreparedStatement p;
          p= c.prepareStatement("insert into Pessoafisica (CPF,RG,DatadeNascimento,codcli_clientes) values (?,?,?,?)");
          
          p.setString(1, getCPF());
          p.setString(2, getRG());
          p.setDate(3, new Date(this.getDataNascimento().getTimeInMillis()));
          p.setInt(4, this.getCodcli());
          
          p.execute();
          
          p.close();
          c.close();
       
        } catch(Exception e) {
             throw new Exception("Falha no banco: "+e.getMessage());
        }
     }
  public void buscarDados() throws Exception {
        try {
          Class.forName("com.mysql.jdbc.Driver");
          Connection c; 
          c = DriverManager.getConnection("jdbc:mysql://localhost:3306/vendadecamisas","root","");
            
            PreparedStatement p;
            p = c.prepareStatement("select * from pessoa_fisica where codcli_clientes = ?");
            p.setInt(1, this.getCodcli());
            
            ResultSet rs;
            rs = p.executeQuery();
            
            if (rs.next()) {
                super.buscarDados();
                this.setCPF(rs.getString("cpf"));
                this.setRG(rs.getString("rg"));
                this.setDataNascimento(Calendar.getInstance());
                this.getDataNascimento().setTimeInMillis(rs.getDate("Datadenascimento ").getTime());
            } else {
                this.setCodcli(-1);
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
            super.atualizar();
            
          Class.forName("com.mysql.jdbc.Driver");
          Connection c; 
          c = DriverManager.getConnection("jdbc:mysql://localhost:3306/vendadecamisas","root","");
            
            PreparedStatement p;
            p = c.prepareStatement("update pessoa_fisica set cpf=?, rg=?, data_nascimento=? where codcli_clientes = ?");
            p.setString(1, this.getCPF());
            p.setString(2, this.getRG());
            p.setDate(3, new Date(this.getDataNascimento().getTimeInMillis()));
            p.setInt(4, this.getCodcli());
            
            p.execute();
            p.close();
            c.close();
        } catch (Exception ex) {
            throw new Exception ("Falha no banco: "+ex.getMessage());
        }
    }

} 
