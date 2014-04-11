
package br.edu.ifrs.classes;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
  
     

public class PessoaJuridica extends Cliente{
    
   
    private String CNPJ;
    private String InscricaoEstadual;
    private String InscricaoMunicipal;
    private Calendar DataFundacao;

  
    /**
     * @return the CNPJ
     */
    public String getCNPJ() {
        return CNPJ;
    }

    /**
     * @param CNPJ the CNPJ to set
     */
    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }
     

     /**
     * @return the InscricaoEstadual
     */
    public String getInscricaoEstadual() {
        return InscricaoEstadual;
    }

    /**
     * @param InscricaoEstadual the InscricaoEstadual to set
     */
    public void setInscricaoEstadual(String InscricaoEstadual) {
        this.InscricaoEstadual = InscricaoEstadual;
    }

    /**
     * @return the InscricaoMunicipal
     */
    public String getInscricaoMunicipal() {
        return InscricaoMunicipal;
    }

    /**
     * @param InscricaoMunicipal the InscricaoMunicipal to set
     */
    public void setInscricaoMunicipal(String InscricaoMunicipal) {
        this.InscricaoMunicipal = InscricaoMunicipal;
    }

    

    /**
     * @return the DataFundacao
     */
    public Calendar getDataFundacao() {
        return DataFundacao;
    }

    /**
     * @param DataFundacao the DataFundacao to set
     */
    public void setDataFundacao(Calendar DataFundacao) {
        this.DataFundacao = DataFundacao;
    }

    
    
   
     public void Cadastrar() throws Exception {
         
       
        super.Cadastrar();
        
        
         try{
          Class.forName("com.mysql.jdbc.Driver");
          Connection c; 
          c = DriverManager.getConnection("jdbc:mysql://localhost:3306/vendadecamisas","root","");
          
          PreparedStatement p;
          p= c.prepareStatement("insert into Pessoajuridica (CNPJ,InscricaoEstadual,InscricaoMunicipal,DataFundacao,codcli_clientes) values (?,?,?,?,?)");
          
          p.setString(1, getCNPJ());
          p.setString(2, getInscricaoEstadual());
          p.setString(3, getInscricaoMunicipal());
          p.setDate(4, new Date(this.getDataFundacao().getTimeInMillis()));
          p.setInt(5, this.getCodcli());
          
          p.execute();
          
          p.close();
          c.close();
       
        } catch(Exception e) {
            System.out.println("Neste momento estamos fora do ar : "); 
        }
     }
 public void buscarDados() throws Exception {
        try {
          Class.forName("com.mysql.jdbc.Driver");
          Connection c; 
          c = DriverManager.getConnection("jdbc:mysql://localhost:3306/vendadecamisas","root","");
            
            PreparedStatement p;
            p = c.prepareStatement("select * from pessoa_juridica where codcli_clientes = ?");
            p.setInt(1, this.getCodcli());
            
            ResultSet rs;
            rs = p.executeQuery();
            
            if (rs.next()) {
                super.buscarDados();
                this.setCNPJ(rs.getString("cnpj"));
                this.setInscricaoEstadual(rs.getString("InscricaoEstadual"));
                this.setInscricaoMunicipal(rs.getString("InscricaoMunicipal"));
                this.setDataFundacao(Calendar.getInstance());
                this.getDataFundacao().setTimeInMillis(rs.getDate("DataFundacao").getTime());
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
            p = c.prepareStatement("update pessoa_juridica cnpj=?, InscricaoEstadual=?, InscricaoMunicipal=?, DataFundacao=? where codcli_clientes = ?");
            p.setString(1, this.getCNPJ());
            p.setString(2, this.getInscricaoEstadual());
            p.setString(3, this.getInscricaoMunicipal());
            p.setDate(4, new Date(this.getDataFundacao().getTimeInMillis()));
            p.setInt(5, this.getCodcli());
            
            p.execute();
            p.close();
            c.close();
        } catch (Exception ex) {
            throw new Exception ("Falha no banco: "+ex.getMessage());
        }
    } 

   

}
