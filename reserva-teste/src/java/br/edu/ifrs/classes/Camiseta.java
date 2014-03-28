/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;



public class Camiseta extends Camisas{
    
    private int IdCamse;
    private float Preco;
    private Boolean Regata;
    
      public Camiseta(){
        
        
        Preco =  (float)15.00;
    }

      
       /**
     * @return the IdCamse
     */
    public int getIdCamse() {
        return IdCamse;
    }

    /**
     * @param IdCamse the IdCamse to set
     */
    public void setIdCamse(int IdCamse) {
        this.IdCamse = IdCamse;
    }
      
     /**
     * @return the Regata
     */
    public Boolean getRegata() {
        return Regata;
    }

    /**
     * @param Regata the Regata to set
     */
    public void setRegata(Boolean Regata) {
        this.Regata = Regata;
    }
    /**
     * @return the Preco
     */
    public float getPreco() {
        return Preco;
    }

    /**
     * @param Preco the Preco to set
     */
    public void setPreco(float Preco) {
        this.Preco = Preco;
    }

  
    
    public void CadastrarProduto() throws Exception{
              
         super.CadastrarProduto();
         
        try{
        Class.forName("com.mysql.jdbc.Driver");
          Connection c; 
          c = DriverManager.getConnection("jdbc:mysql://localhost:3306/vendadecamisas","root","");
          
          PreparedStatement p;
          p= c.prepareStatement("insert into camiseta (preco,regata,idcamse) values (?,?,?)");
          
          
          
          p.setFloat(1, Preco);
          p.setBoolean(2, Regata);
          p.setInt(3, IdCamse);
          
          p.execute();
          
          p.close();
          c.close();
       
        } catch (Exception e) {
            throw new Exception("Falha no banco: "+e.getMessage());
        }
    }

   

   
    
}
