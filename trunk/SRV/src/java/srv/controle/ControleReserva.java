/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package srv.controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import srv.dao.DestinoDAO;
import srv.dao.InterfaceDestinoDAO;
import srv.dao.InterfaceReservaDAO;
import srv.dao.InterfaceServidorDAO;
import srv.dao.InterfaceVeiculoDAO;
import srv.dao.ReservaDAO;
import srv.dao.ServidorDAO;
import srv.dao.VeiculoDAO;
import srv.modelo.Destino;
import srv.modelo.Reserva;
import srv.modelo.Servidor;
import srv.modelo.Veiculo;
import srv.util.Validacoes;
/**
 *
 * @author Douglas
 */
@WebServlet(name = "ControleReserva", urlPatterns = {"/ControleReserva"})
public class ControleReserva extends HttpServlet {

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
            HttpSession session = request.getSession();
            Servidor user = new Servidor();
            if(request.getSession().getAttribute("administrador") != null){
                 user = (Servidor) session.getAttribute("administrador");
            }else if(request.getSession().getAttribute("servidor") != null){
                  user = (Servidor) session.getAttribute("servidor");
            }
            
//            Servidor user = (Servidor) session.getAttribute("administrador");
            String acao = request.getParameter("action");

            if (acao.equals("formularioReserva")) {
                try {
                    String placa = request.getParameter("placa");
                    
                    String data_saida = request.getParameter("inputDataSaida");
                    String hora_saida = request.getParameter("inputHoraSaida");
                    String data_retorno = request.getParameter("inputDataRetorno");
                    String hora_retorno = request.getParameter("inputHoraRetorno");
                
                    InterfaceServidorDAO sdao = new ServidorDAO();
                    List<Servidor> lista = sdao.todosServidores();

                    for (int i = 0; i < lista.size(); i++) {
                        if (lista.get(i).getMatriculaSIAPE().equals(user.getMatriculaSIAPE())) {
                            lista.remove(i);
                        }
                    }

                    InterfaceVeiculoDAO vdao = new VeiculoDAO();
                    Veiculo veiculo = vdao.buscarVeiculo(placa);

                    InterfaceDestinoDAO ddao = new DestinoDAO();
                    List<Destino> listad = ddao.buscarDestinos();

                    request.setAttribute("listaserv", lista);
                    request.setAttribute("veiculo", veiculo);
                    request.setAttribute("listadest", listad);
                    request.setAttribute("usuario", user);
                    request.setAttribute("s_data_saida", data_saida);
                    request.setAttribute("s_hora_saida", hora_saida);
                    request.setAttribute("s_data_retorno", data_retorno);
                    request.setAttribute("s_hora_retorno", hora_retorno);

                    request.getRequestDispatcher("cadastrarReserva.jsp").forward(request, response);
                } catch (Exception e) {
                    request.setAttribute("mensagem", e.getMessage());
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
            }
            if (acao.equals("consultarDispVeiculo")) {
                try {

                    InterfaceServidorDAO sdao = new ServidorDAO();
                    List<Servidor> lista = sdao.todosServidores();

                    for (int i = 0; i < lista.size(); i++) {
                        if (lista.get(i).getMatriculaSIAPE().equals(user.getMatriculaSIAPE())) {
                            lista.remove(i);
                        }
                    }

                    InterfaceVeiculoDAO vdao = new VeiculoDAO();
                    List<Veiculo> listav = vdao.todosVeiculo();

                    InterfaceDestinoDAO ddao = new DestinoDAO();
                    List<Destino> listad = ddao.buscarDestinos();

                    request.setAttribute("listaserv", lista);
                    request.setAttribute("listaveic", listav);
                    request.setAttribute("listadest", listad);
                    request.setAttribute("usuario", user);

                    //request.getRequestDispatcher("cadastrarReserva.jsp").forward(request, response);
                    request.getRequestDispatcher("formConsultarDispVeiculo.jsp").forward(request, response);
                } catch (Exception e) {
                    request.setAttribute("mensagem", e.getMessage());
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
            }

            if (acao.equals("inserirReserva") || acao.equals("atualizarReserva")) {
                ReservaDAO rdao = new ReservaDAO();
                int id_reserva = 0;
                //Para Nova Reserva... gerar ID novo
                if (acao.equals("inserirReserva")) {
                    id_reserva = rdao.gerarIdReserva();
                }
                //Para atualizar Reserva... continua com o mesmo ID
                if (acao.equals("atualizarReserva")) {
                    id_reserva = (Integer.parseInt(request.getParameter("id_reserva")));
                }

                String matricula_siape = user.getMatriculaSIAPE();
                String data_saida = request.getParameter("inputDataSaida");
                String hora_saida = request.getParameter("inputHoraSaida");
                String datetime_saida = data_saida + " " + hora_saida + ":00";

                String data_retorno = request.getParameter("inputDataRetorno");
                String hora_retorno = request.getParameter("inputHoraRetorno");
                String datetime_retorno = data_retorno + " " + hora_retorno + ":00";

                String placa = request.getParameter("inputPlacaVeiculo");
                int iCondutor = Integer.parseInt(request.getParameter("inputMotorista"));
                boolean condutor;
                String matricula_siape_condutor;

                if (iCondutor == 1) {
                    condutor = true;
                    matricula_siape_condutor = matricula_siape;
                } else {
                    condutor = false;
                    matricula_siape_condutor = request.getParameter("inputOutroMotorista");
                }
                int id_destino = Integer.parseInt(request.getParameter("inputDestino"));
                String descricao = null;

                if (id_destino == 1) {
                    descricao = request.getParameter("inputDestinoComplementar");
                }

                Date date_saida = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(datetime_saida);
                Date date_retorno = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(datetime_retorno);

                DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date date_atual = new Date();
                Timestamp timestamp_atual = new Timestamp(date_atual.getTime());

                Reserva reserva = new Reserva(
                        id_reserva, matricula_siape, date_saida, date_retorno, placa, condutor, matricula_siape_condutor, id_destino, descricao, date_atual);

                if (acao.equals("inserirReserva")) {
                    rdao.inserirReserva(reserva);
                    request.setAttribute("mensagem", "Reserva efetuada com sucesso.");
                } else {
                    rdao.atualizar(reserva);
                    request.setAttribute("mensagem", "Reserva atualizada com sucesso.");
                }

                request.getRequestDispatcher("ControleReserva?action=listaReservas").forward(request, response);
            }
            
            if (acao.equals("listaReservas")) {
                try {
                    
                    InterfaceReservaDAO irdao = new ReservaDAO();
                    List<Reserva> listar = irdao.listaReservas(user.getMatriculaSIAPE());
                    
                    List<Reserva> listaOutros = irdao.listaReservasOutros(user.getMatriculaSIAPE());                
  
                    request.setAttribute("listaReservasOutros", listaOutros);
                    request.setAttribute("listaReservas", listar);
                    request.getRequestDispatcher("listaReservas.jsp").forward(request, response);
                } catch (Exception e) {
                    request.setAttribute("mensagem", e.getMessage());
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
            }
            if (acao.equals("editarReserva") || acao.equals("visualizarReserva")) {
                InterfaceReservaDAO idao = new ReservaDAO();
                Reserva r = idao.consultarIdReserva(Integer.parseInt(request.getParameter("id_reserva")));
                request.setAttribute("reserva", r);

                InterfaceServidorDAO sdao = new ServidorDAO();
                List<Servidor> lista = sdao.todosServidores();

                for (int i = 0; i < lista.size(); i++) {
                    if (lista.get(i).getMatriculaSIAPE().equals(user.getMatriculaSIAPE())) {
                        lista.remove(i);
                    }
                }

                InterfaceVeiculoDAO vdao = new VeiculoDAO();
                List<Veiculo> listav = vdao.todosVeiculo();

                InterfaceDestinoDAO ddao = new DestinoDAO();
                List<Destino> listad = ddao.buscarDestinos();

                request.setAttribute("listaserv", lista);
                request.setAttribute("listaveic", listav);
                request.setAttribute("listadest", listad);
                request.setAttribute("usuario", user);

                if (acao.equalsIgnoreCase("editarReserva")) {
                    request.getRequestDispatcher("/formAtualizarReserva.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("/formVisualizarReserva.jsp").forward(request, response);
                }
            }else if (acao.equals("excluirReserva")) {
                int id_reserva = Integer.parseInt (request.getParameter("reserva"));
                //if (!Validacoes.ValidarQualUsuarioLogado(user.getMatriculaSIAPE(), matricula)) {
                    //throw new Exception(Validacoes.getMensagemErro());
                InterfaceReservaDAO idao = new ReservaDAO();
                Reserva reserva = idao.consultarID_Reserva(id_reserva);
                idao.excluirReserva(reserva);
                
                request.setAttribute("mensagem", "Reserva excluída com sucesso.");
                request.getRequestDispatcher("ControleReserva?action=listaReservas").forward(request, response);
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