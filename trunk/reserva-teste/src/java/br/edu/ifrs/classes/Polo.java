/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class Polo extends Camisas{
    
    private float Preco;
    private String Tecido;
    private int IdPol;
    
    public Polo(){
        
        Preco =  (float)25.00;
    }
    
    
     /**
     * @return the IdPol
     */
    public int getIdPol() {
        return IdPol;
    }

    /**
     * @param IdPol the IdPol to set
     */
    public void setIdPol(int IdPol) {
        this.IdPol = IdPol;
    }
     /**
     * @return the Tecido
     */
    public String getTecido() {
        return Tecido;
    }

    /**
     * @param Tecido the Tecido to set
     */
    public void setTecido(String Tecido) {
        this.Tecido = Tecido;
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
          p= c.prepareStatement("insert into polo (preco,tecido,idpol) values (?,?,?)");
          
          
         
          p.setFloat(1, Preco);
          p.setString(2, Tecido);
          p.setInt(3, IdPol);
          
          p.execute();
          
          p.close();
          c.close();
       
        } catch (Exception e) {
            throw new Exception("Falha no banco: "+e.getMessage());
        }
    }

    

   
    
}
