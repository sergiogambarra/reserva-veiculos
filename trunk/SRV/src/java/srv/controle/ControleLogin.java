/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package srv.controle;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import srv.modelo.Funcionario;

/**
 *
 * @author Erick
 */
@WebServlet(name = "ControleLogin", urlPatterns = {"/ControleLogin"})
public class ControleLogin extends HttpServlet {

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
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        try 
        {
            String acao = request.getParameter("action");
            if (acao.equals("Entrar")) 
            {
                try 
                {
                    String login = request.getParameter("login");
                    String senha = request.getParameter("senha");


                    Funcionario f = new Funcionario();
                    f.setLogin(login);
                    f.setSenha(senha);

                    if (f.VerificarLogin() == 0) 
                    {
                        request.getSession().setAttribute("funcionario", f);

                        request.getRequestDispatcher("pag1.jsp").forward(request, response);

                    } 
                    else if (f.VerificarLogin() == 1) 
                    {
                        request.getSession().setAttribute("administrador", f);

                        request.getRequestDispatcher("pag1.jsp").forward(request, response);

                    } 
                    else 
                    {
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }
                } 
                catch (Exception e) 
                {
                    request.setAttribute("mensagem", e.getMessage());
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
            }
            else if (acao.equals("deslogar")) 
            {
                if (request.getSession().getAttribute("funcionario") != null)
                {
                    request.getSession().setAttribute("funcionario", null);
                }
                if (request.getSession().getAttribute("administrador") != null)
                {
                  
                    request.getSession().removeAttribute("administrador");
                }
                
                HttpSession session = request.getSession(); 
                
                request.getRequestDispatcher("Login.jsp").forward(request, response);
                session.invalidate();

            }
        } 
        catch (Exception e) 
        {
            request.setAttribute("mensagem", e.getMessage());
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
