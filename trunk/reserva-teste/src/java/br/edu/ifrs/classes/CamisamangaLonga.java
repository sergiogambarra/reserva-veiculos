
package br.edu.ifrs.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;



public class CamisamangaLonga extends Camisas{
    
    private float Preco;
    private int IdCaml;
    
    
    public CamisamangaLonga(){
       
       
        Preco =  (float)20.00;
    }
    
    /**
     * @return the IdCaml
     */
    public int getIdCaml() {
        return IdCaml;
    }

    /**
     * @param IdCaml the IdCaml to set
     */
    public void setIdCaml(int IdCaml) {
        this.IdCaml = IdCaml;
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
          p= c.prepareStatement("insert into camisamangalonga (preco,idcaml) values (?,?)");
          
          
          
          p.setFloat(1, Preco);
          p.setInt(2, IdCaml);
          
          p.execute();
          
          p.close();
          c.close();
       
        } catch (Exception e) {
            throw new Exception("Falha no banco: "+e.getMessage());
        }
    }

    
    
    
}
