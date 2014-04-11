/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.controle;


import br.edu.ifrs.classes.CamisamangaLonga;
import br.edu.ifrs.classes.Camisas;
import br.edu.ifrs.classes.Camiseta;
import br.edu.ifrs.classes.Polo;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author fernando
 */
@WebServlet(name = "ControlePedido", urlPatterns = {"/ControlePedido"})
public class ControlePedido extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
         String camisa;
         String cor;
         int tamanho;
      
        camisa = request.getParameter("camisa");
        tamanho = Integer.parseInt(request.getParameter("tamanho")); //Float.parseFloat()
        cor = request.getParameter("cor");
       

        try {
                            
               
           if (camisa.equals("Adidas")){
               
            CamisamangaLonga camlohg = new CamisamangaLonga();
            camlohg.setCor(cor);
            camlohg.setTamanho(tamanho);
            
            
            
            camlohg.CadastrarProduto();
               
           } else { 
           }
           if (camisa.equals("Nike")){
               
            Camiseta cami = new Camiseta();
            cami.setCor(cor);
            cami.setTamanho(tamanho);
           
            
            cami.CadastrarProduto();
               
            } else { 
                   
            Polo pol = new Polo();
            pol.setCor(cor);
            pol.setTamanho(tamanho);
            
            
            pol.CadastrarProduto();
               
           }
            
            
            
            
            //request.getRequestDispatcher("").forward(request, response);
            request.getRequestDispatcher("sucesso.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute( "mensagem", e.getMessage() );
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
