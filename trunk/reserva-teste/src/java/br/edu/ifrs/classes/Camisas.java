/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class Camisas {
    
    private int CodCami;
    private int Tamanho;
    private String Cor;
    
    
    
    
   /**
     * @return the CodCami
     */
    public int getCodCami() {
        return CodCami;
    }

    /**
     * @param CodCami the CodCami to set
     */
    public void setCodCami(int CodCami) {
        this.CodCami = CodCami;
    }

   
    public int getTamanho() {
        return Tamanho;
    }

   
    public void setTamanho(int Tamanho) {
        this.Tamanho = Tamanho;
    }

    
    public String getCor() {
        return Cor;
    }

    
    public void setCor(String Cor) {
        this.Cor = Cor;
    }

      
    
     public void CadastrarProduto() throws Exception{
              
        try{
        Class.forName("com.mysql.jdbc.Driver");
          Connection c; 
          c = DriverManager.getConnection("jdbc:mysql://localhost:3306/vendadecamisas","root","");
          
          PreparedStatement p;
          p= c.prepareStatement("insert into camisas (tamanho,cor,codcami) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
          
          p.setInt(1, Tamanho);
          p.setString(2, Cor);
           p.setInt(3, CodCami);
          
          
          p.executeUpdate();
         
          ResultSet rs = p.getGeneratedKeys();  
          if(rs.next()){  
              this.CodCami = rs.getInt(1);  
          rs.close();          
          }  
          
          p.close();
          c.close();
       
        } catch (Exception e) {
            throw new Exception("Falha no banco: "+e.getMessage());
        }
    }
     
      public List<Camisas> ConsultarPedido() throws Exception {
       
            List<Camisas> lista = new ArrayList();
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c;
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/vendadecamisas","root","");
            PreparedStatement p;
            p = c.prepareStatement("select * from camisas");
            
            ResultSet rs;
            rs = p.executeQuery();
            
            while (rs.next()) {
                Camisas camisas = new Camisas();
                camisas.Tamanho = rs.getInt("tamanho");
                camisas.Cor = rs.getString("cor");
                lista.add(camisas);
            }
            
            rs.close();
            p.close();
            c.close();
        } catch (Exception e) {
            throw new Exception("Falha no banco: "+e.getMessage());
        }
        
        return lista;
    }
        
    
       public void AtualizarProduto(){
           
       }
    
    
        public void DeletarProduto(){
            
        }

    
   
   
}
