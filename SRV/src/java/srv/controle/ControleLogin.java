/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package srv.controle;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import srv.dao.InterfaceServidorDAO;
import srv.dao.ServidorDAO;
import srv.modelo.Servidor;
import srv.util.Validacoes;

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String acao = request.getParameter("action");
            if (acao.equals("Entrar")) {
                try {
                    String matriculaSIAPE = request.getParameter("inputMatricula");
                    String senha = request.getParameter("inputSenha");

                    if (!Validacoes.ValidarLogin(matriculaSIAPE, senha)) {
                        throw new Exception(Validacoes.getMensagemErro());
                    }
                    InterfaceServidorDAO sdao = new ServidorDAO();

                    Servidor f = (Servidor) sdao.buscarServidor(matriculaSIAPE);

                    if (!Validacoes.ValidarStatusUsuario(matriculaSIAPE)) {
                        throw new Exception(Validacoes.getMensagemErro());
                    }

                    if (f.getSenha().equals(senha)) {
                        if (f.getPerfil() == 0) {
                            request.getSession().setAttribute("servidor", f);
                            request.getRequestDispatcher("ControleReserva?action=listaReservas").forward(request, response);
                        } else if (f.getPerfil() == 1) {
                            request.getSession().setAttribute("administrador", f);
                            request.getRequestDispatcher("ControleReserva?action=listaReservas").include(request, response);
                        }
                    } else {
                        if (!Validacoes.ValidarUsuarioLogin(matriculaSIAPE)) {
                            throw new Exception(Validacoes.getMensagemErro());
                        }
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }
                } catch (Exception e) {
                    request.setAttribute("mensagem", e.getMessage());
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } else if (acao.equals("EnviarSenha")) {
                try {
                    String matriculaSIAPE = request.getParameter("inputMatricula");

                    if (!Validacoes.ValidarEnviarSenha(matriculaSIAPE)) {
                        throw new Exception(Validacoes.getMensagemErro());
                    }

                    Servidor f = new Servidor();
                    f.setMatriculaSIAPE(matriculaSIAPE);
                    if (!Validacoes.ValidarUsuarioEnviarSenha(matriculaSIAPE)) {
                        throw new Exception(Validacoes.getMensagemErro());
                    }
                    f.EnviarSenha();
                    request.setAttribute("mensagem", "Senha enviada com sucesso!");
                    request.getRequestDispatcher("enviarSenha.jsp").forward(request, response);

                } catch (Exception e) {

                    request.setAttribute("mensagem", e.getMessage());
                    request.getRequestDispatcher("enviarSenha.jsp").forward(request, response);
                }
            } else if (acao.equals("deslogar")) {
                if (request.getSession().getAttribute("servidor") != null) {
                    request.getSession().setAttribute("servidor", null);
                }
                if (request.getSession().getAttribute("administrador") != null) {

                    request.getSession().removeAttribute("administrador");
                }

                HttpSession session = request.getSession();
                request.getRequestDispatcher("index.jsp").forward(request, response);
                session.invalidate();
            }
        } catch (Exception e) {
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
