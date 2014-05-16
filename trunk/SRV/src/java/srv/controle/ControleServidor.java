/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package srv.controle;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import srv.dao.InterfaceServidorDAO;
import srv.dao.ServidorDAO;
import srv.modelo.Servidor;

/**
 *
 * @author Paula
 */
@WebServlet(name = "ControleServidor", urlPatterns = {"/ControleServidor"})
public class ControleServidor extends HttpServlet {

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
        try {
            String acao = request.getParameter("action");
            //Se for CADASTRAR ou ATUALIZAR
            if (acao.equals("cadastrarServidor") || acao.equals("atualizarServidor")) {
                try {
                    String matricula_siape = request.getParameter("iMatriculaSiape");
                    String nome = request.getParameter("sNomeCompleto");
                    String email = request.getParameter("sEmail");
                    String cpf = request.getParameter("sCpf");
                    int cnh = Integer.parseInt(request.getParameter("sCnh"));
                    String motorista = request.getParameter("bMotorista");
                    String rg = request.getParameter("sRg");
                    String orgao_expedidor = request.getParameter("sOrgaoExpedidor");
                    String estado_civil = request.getParameter("estadoCivil");
                    String telefone_comer = request.getParameter("sTelefoneComercial");
                    String telefone_cel = request.getParameter("sTelefoneCelular");
                    String estado = request.getParameter("estado");
                    String cidade = request.getParameter("sCidade");
                    String nacionalidade = request.getParameter("sNacionalidade");
                    String informacoes = request.getParameter("sInfoComplementar");
                    String status_serv = request.getParameter("status_serv");
                    String sexo = request.getParameter("sexo");
                    String data_nascimento = request.getParameter("sDataNascimento");

                    Servidor serv = new Servidor();
                    serv.setMatriculaSIAPE(matricula_siape);
                    serv.setNome(nome);
                    serv.setEmail(email);
                    serv.setCpf(cpf);
                    serv.setCnh(cnh);
                    if (motorista.equals("t")) {
                        serv.setMotorista(true);
                    } else {
                        serv.setMotorista(false);
                    }
                    serv.setRg(rg);
                    serv.setOrgao_expedidor(orgao_expedidor);
                    serv.setEstado_civil(estado_civil);
                    serv.setTelefone_comer(telefone_comer);
                    serv.setTelefone_cel(telefone_cel);
                    serv.setEstado(estado);
                    serv.setCidade(cidade);
                    serv.setNacionalidade(nacionalidade);
                    serv.setInformacoes(informacoes);
                    serv.setSexo(sexo);
                    
                    //Ativo ou Inativo
                    if (status_serv.equalsIgnoreCase("a")) {
                        serv.setStatus_serv(true);
                    } else {
                        serv.setStatus_serv(false);
                    }

                    //A DATA SERÁ A SENHA INICIAL
                    Date date;
                    date = new SimpleDateFormat("yyyy-MM-dd").parse(data_nascimento);
                    serv.setData_nascimento(date);
                    
                    //PEGA DIA E ANO PARA GERAR A SENHA
                    SimpleDateFormat dataSenha = new SimpleDateFormat("ddyyyy");  
                    dataSenha.format(date);
                    String senha = dataSenha.format(date);
                    ServidorDAO sdao = new ServidorDAO();

                    if (acao.equals("cadastrarServidor")) {
                        //Senha só pode ser alterada pelo próprio servidor
                        serv.setSenha(senha);
                        sdao.salvar(serv);
                        
                        InterfaceServidorDAO idao = new ServidorDAO();
                        List<Servidor> lista = idao.todosServidor();

                        request.setAttribute("listaserv", lista);
                        
                        request.getRequestDispatcher("listaServidores.jsp").forward(request, response);
                    } else if (acao.equals("atualizarServidor")) {
                        sdao.atualizar(serv);
                        InterfaceServidorDAO idao = new ServidorDAO();
                        List<Servidor> lista = idao.todosServidor();

                        request.setAttribute("listaserv", lista);
                        request.getRequestDispatcher("listaServidores.jsp").forward(request, response);
                    }
                } catch (Exception e) {
                    request.setAttribute("mensagem", e.getMessage());
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
            }else if (acao.equalsIgnoreCase("editarServidor")){
                InterfaceServidorDAO idao = new ServidorDAO();
                List<Servidor> list = idao.consultarMatricula(request.getParameter("matricula"));
                Servidor matricula = list.get(0);
                request.setAttribute("matricula", matricula);
                request.setAttribute("dao", idao);
                request.getRequestDispatcher("/formAtualizarServidor.jsp").forward(request, response);
            }else if (acao.equals("excluirServidor")) {
                InterfaceServidorDAO idao = new ServidorDAO();
                List<Servidor> list = idao.consultarMatricula(request.getParameter("matricula"));
                Servidor servidor = list.get(0);
                idao.excluir(servidor);
                
                List<Servidor> lista = idao.todosServidor();

                request.setAttribute("listaserv", lista);
                request.getRequestDispatcher("listaServidores.jsp").forward(request, response);
            }else if (acao.equals("listaServidores")) {
                 try {
                    InterfaceServidorDAO sdao = new ServidorDAO();
                    List<Servidor> lista = sdao.todosServidor();
                    
                    request.setAttribute("listaserv", lista);
                    request.getRequestDispatcher("listaServidores.jsp").forward(request, response);
                } catch (Exception e) {
                    request.setAttribute("mensagem", e.getMessage());
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
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
