/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package srv.controle;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
            if (request.getSession().getAttribute("administrador") != null) {
                user = (Servidor) session.getAttribute("administrador");
            } else if (request.getSession().getAttribute("servidor") != null) {
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
                    String id_reserva = request.getParameter("id_reserva");

                    //Se vier da pag formConsultarDipVeiculoAlterarReserva, o id_reserva não será nulo
                    //Busca os dados da Reserva para mostrar no formulário

                    Reserva reserva = null;
                    if (!id_reserva.equals("")) {
                        InterfaceReservaDAO idao = new ReservaDAO();
                        reserva = idao.consultarIdReserva(Integer.parseInt(id_reserva));
                    }

                    InterfaceServidorDAO sdao = new ServidorDAO();
                    List<Servidor> lista = sdao.todosServidoresMotoristas();

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

                    if (!id_reserva.equals("")) {
                        BarraNavegacao.setarNavegacao(request, "Consultar Disponibilidade", "ControleReserva?action=consultarDispVeiculo="+id_reserva, "Editar Reserva", "#");
                        request.setAttribute("reserva", reserva);
                        request.getRequestDispatcher("formAtualizarReserva.jsp").forward(request, response);
                    } else {
                        BarraNavegacao.setarNavegacao(request, "Consultar Disponibilidade", "ControleReserva?action=consultarDispVeiculo", "Editar Reserva", "#");
                        request.getRequestDispatcher("cadastrarReserva.jsp").forward(request, response);
                    }


                } catch (Exception e) {
                    request.setAttribute("mensagem", e.getMessage());
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
            }
            if (acao.equals("consultarDispVeiculo")) {
                try {

//                    InterfaceServidorDAO sdao = new ServidorDAO();
//                    List<Servidor> lista = sdao.todosServidoresMotoristas();
//
//                    for (int i = 0; i < lista.size(); i++) {
//                        if (lista.get(i).getMatriculaSIAPE().equals(user.getMatriculaSIAPE())) {
//                            lista.remove(i);
//                        }
//                    }
//
//                    String nnn = "";
//                    InterfaceVeiculoDAO vdao = new VeiculoDAO();
//                    List<Veiculo> listav = vdao.todosVeiculo(nnn);
//
//                    InterfaceDestinoDAO ddao = new DestinoDAO();
//                    List<Destino> listad = ddao.buscarDestinos();
//
//                    request.setAttribute("listaserv", lista);
//                    request.setAttribute("listaveic", listav);
//                    request.setAttribute("listadest", listad);
                    request.setAttribute("usuario", user);


                    if (request.getParameter("id_reserva") != null) {
                        int id_reserva = Integer.parseInt(request.getParameter("id_reserva"));
                        request.setAttribute("id_reserva", id_reserva);
                        BarraNavegacao.setarNavegacao(request, "Consultar Disponibilidade", "ControleReserva?action=consultarDispVeiculo&id_reserva="+id_reserva, null, null);
                        request.getRequestDispatcher("formConsultarDispVeiculoAlterarReserva.jsp").forward(request, response);
                    } else {
                        BarraNavegacao.setarNavegacao(request, "Consultar Disponibilidade", "ControleReserva?action=consultarDispVeiculo", null, null);
                        request.getRequestDispatcher("formConsultarDispVeiculo.jsp").forward(request, response);
                    }
                } catch (Exception e) {
                    request.setAttribute("mensagem", e.getMessage());
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
            }

            if (acao.equals("inserirReserva") || acao.equals("atualizarReserva")) {
                ReservaDAO rdao = new ReservaDAO();
                int id_reserva = 0;
                String placa = null;
                //Para Nova Reserva... gerar ID novo
                if (acao.equals("inserirReserva")) {
                    id_reserva = rdao.gerarIdReserva();
                    placa = request.getParameter("inputPlacaVeiculo");
                }
                //Para atualizar Reserva... continua com o mesmo ID
                if (acao.equals("atualizarReserva")) {
                    id_reserva = (Integer.parseInt(request.getParameter("id_reserva")));
                    placa = request.getParameter("inputPlacaVeiculo");
                }

                String matricula_siape = user.getMatriculaSIAPE();
                Date data_saida_br = null;
                Date data_retorno_br = null;
                String data_saida = null;
                String data_retorno = null;
                String data = request.getParameter("inputDataSaida");
                System.out.println("Teste data: " + data);
                try {
                    data_saida_br = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("inputDataSaida"));
                    data_saida = new SimpleDateFormat("yyyy-MM-dd").format(data_saida_br);

                    data_retorno_br = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("inputDataRetorno"));
                    data_retorno = new SimpleDateFormat("yyyy-MM-dd").format(data_retorno_br);
                } catch (Exception e) {
                    e.getMessage();
                }

                String hora_saida = request.getParameter("inputHoraSaida");
                String datetime_saida = data_saida + " " + hora_saida + ":00";

                String hora_retorno = request.getParameter("inputHoraRetorno");
                String datetime_retorno = data_retorno + " " + hora_retorno + ":00";


                int iCondutor = Integer.parseInt(request.getParameter("inputMotorista"));
                boolean condutor;
                String matricula_siape_condutor;

                int ocupantes = Integer.parseInt(request.getParameter("iCapacidade"));

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
                        id_reserva, matricula_siape, date_saida, date_retorno, placa, condutor, matricula_siape_condutor, ocupantes, id_destino, descricao, date_atual);

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

                    InterfaceDestinoDAO ddao = new DestinoDAO();
                    List<Destino> listad = ddao.buscarDestinos();

                    request.setAttribute("listadest", listad);
                    request.setAttribute("listaReservasOutros", listaOutros);
                    request.setAttribute("listaReservas", listar);
                    BarraNavegacao.setarNavegacao(request, "Lista de Reservas", "ControleReserva?action=listaReservas", null, null);
                    request.getRequestDispatcher("listaReservas.jsp").forward(request, response);
                } catch (Exception e) {
                    request.setAttribute("mensagem", e.getMessage());
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
            }
            if (acao.equals("editarReserva") || acao.equals("visualizarReserva")) {
                InterfaceReservaDAO idao = new ReservaDAO();
                int id_reserva = Integer.parseInt(request.getParameter("id_reserva"));
                Reserva r = idao.consultarIdReserva(id_reserva);
                request.setAttribute("reserva", r);

                InterfaceServidorDAO sdao = new ServidorDAO();
                List<Servidor> lista = sdao.todosServidoresMotoristas();

                for (int i = 0; i < lista.size(); i++) {
                    if (lista.get(i).getMatriculaSIAPE().equals(user.getMatriculaSIAPE())) {
                        lista.remove(i);
                    }
                }

                InterfaceDestinoDAO ddao = new DestinoDAO();
                List<Destino> listad = ddao.buscarDestinos();

                request.setAttribute("listaserv", lista);
                request.setAttribute("listadest", listad);
                request.setAttribute("usuario", user);

                if (acao.equalsIgnoreCase("editarReserva")) {
                    BarraNavegacao.setarNavegacao(request, "Lista de Reservas", "ControleReserva?action=listaReservas", "Editar Reserva", "ControleReserva?action=editarReserva&id_reserva=" + id_reserva);
                    request.getRequestDispatcher("/formAtualizarReserva.jsp").forward(request, response);
                } else {
                    BarraNavegacao.setarNavegacao(request, "Lista de Reservas", "ControleReserva?action=listaReservas", "Visualizar Reserva", "ControleReserva?action=visualizarReserva&id_reserva=" + id_reserva);
                    request.getRequestDispatcher("/formVisualizarReserva.jsp").forward(request, response);
                }
            } else if (acao.equals("excluirReserva")) {
                int id_reserva = Integer.parseInt(request.getParameter("reserva"));
                //if (!Validacoes.ValidarQualUsuarioLogado(user.getMatriculaSIAPE(), matricula)) {
                //throw new Exception(Validacoes.getMensagemErro());
                InterfaceReservaDAO idao = new ReservaDAO();
                Reserva reserva = idao.consultarID_Reserva(id_reserva);
                idao.excluirReserva(reserva);

                request.setAttribute("mensagem", "Reserva excluída com sucesso.");
                request.getRequestDispatcher("ControleReserva?action=listaReservas").forward(request, response);

            } else if (acao.equals("consultarReservas")) {
                try {
                    String matricula_siape = user.getMatriculaSIAPE();
                    String data_saida = request.getParameter("DataSaida");
                    String data_retorno = request.getParameter("DataRetorno");
                    String destino = request.getParameter("destino");
                    InterfaceReservaDAO irdao = new ReservaDAO();

                    if (data_saida.equals("") && data_retorno.equals("") && destino.equals("")) {
                        request.setAttribute("mensagem", "Não foram informados dados para a consulta.");
                        request.getRequestDispatcher("ControleReserva?action=listaReservas").forward(request, response);

                    } else if (!data_saida.equals("") && data_retorno.equals("") && destino.equals("")) {
                        data_saida = data_saida.substring(6, 10) + "-" + data_saida.substring(3, 5) + "-" + data_saida.substring(0, 2);
                        List<Reserva> lista = irdao.buscarReservaPorSaida(matricula_siape, data_saida);
                        List<Reserva> listaOutros = irdao.buscarReservaPorSaidaOutros(matricula_siape, data_saida);
                        if (lista.isEmpty() && listaOutros.isEmpty()) {
                            request.setAttribute("mensagem", "Não foram encontrados resultados para esta consulta");
                            request.getRequestDispatcher("ControleReserva?action=listaReservas").forward(request, response);
                        } else {
                            request.setAttribute("listaReservasOutros", listaOutros);
                            request.setAttribute("listaReservas", lista);
                            InterfaceDestinoDAO ddao = new DestinoDAO();
                            List<Destino> listad = ddao.buscarDestinos();

                            request.setAttribute("listadest", listad);
                            request.getRequestDispatcher("listaReservas.jsp").forward(request, response);
                        }
                    } else if (data_saida.equals("") && !data_retorno.equals("") && destino.equals("")) {
                        data_retorno = data_retorno.substring(6, 10) + "-" + data_retorno.substring(3, 5) + "-" + data_retorno.substring(0, 2);
                        List<Reserva> lista = irdao.buscarReservaPorRetorno(matricula_siape, data_retorno);
                        List<Reserva> listaOutros = irdao.buscarReservaPorRetornoOutros(matricula_siape, data_retorno);
                        if (lista.isEmpty() && listaOutros.isEmpty()) {
                            request.setAttribute("mensagem", "Não foram encontrados resultados para esta consulta");
                            request.getRequestDispatcher("ControleReserva?action=listaReservas").forward(request, response);
                        } else {
                            request.setAttribute("listaReservasOutros", listaOutros);
                            request.setAttribute("listaReservas", lista);
                            InterfaceDestinoDAO ddao = new DestinoDAO();
                            List<Destino> listad = ddao.buscarDestinos();

                            request.setAttribute("listadest", listad);
                            request.getRequestDispatcher("listaReservas.jsp").forward(request, response);
                        }
                    } else if (data_saida.equals("") && data_retorno.equals("") && !destino.equals("")) {
                        List<Reserva> lista = irdao.buscarReservaPorDestino(matricula_siape, destino);
                        List<Reserva> listaOutros = irdao.buscarReservaPorDestinoOutros(matricula_siape, destino);
                        if (lista.isEmpty() && listaOutros.isEmpty()) {
                            request.setAttribute("mensagem", "Não foram encontrados resultados para esta consulta");
                            request.getRequestDispatcher("ControleReserva?action=listaReservas").forward(request, response);
                        } else {
                            request.setAttribute("listaReservasOutros", listaOutros);
                            request.setAttribute("listaReservas", lista);
                            InterfaceDestinoDAO ddao = new DestinoDAO();
                            List<Destino> listad = ddao.buscarDestinos();

                            request.setAttribute("listadest", listad);
                            request.getRequestDispatcher("listaReservas.jsp").forward(request, response);
                        }
                    } else if (!data_saida.equals("") && !data_retorno.equals("") && destino.equals("")) {
                        data_saida = data_saida.substring(6, 10) + "-" + data_saida.substring(3, 5) + "-" + data_saida.substring(0, 2);
                        data_retorno = data_retorno.substring(6, 10) + "-" + data_retorno.substring(3, 5) + "-" + data_retorno.substring(0, 2);
                        List<Reserva> lista = irdao.buscarReservaPorSaidaRetorno(matricula_siape, data_saida, data_retorno);
                        List<Reserva> listaOutros = irdao.buscarReservaPorSaidaRetornoOutros(matricula_siape, data_saida, data_retorno);
                        if (lista.isEmpty() && listaOutros.isEmpty()) {
                            request.setAttribute("mensagem", "Não foram encontrados resultados para esta consulta");
                            request.getRequestDispatcher("ControleReserva?action=listaReservas").forward(request, response);
                        } else {
                            request.setAttribute("listaReservasOutros", listaOutros);
                            request.setAttribute("listaReservas", lista);
                            InterfaceDestinoDAO ddao = new DestinoDAO();
                            List<Destino> listad = ddao.buscarDestinos();

                            request.setAttribute("listadest", listad);
                            request.getRequestDispatcher("listaReservas.jsp").forward(request, response);
                        }
                    } else if (!data_saida.equals("") && data_retorno.equals("") && !destino.equals("")) {
                        data_saida = data_saida.substring(6, 10) + "-" + data_saida.substring(3, 5) + "-" + data_saida.substring(0, 2);
                        List<Reserva> lista = irdao.buscarReservaPorSaidaDestino(matricula_siape, data_saida, destino);
                        List<Reserva> listaOutros = irdao.buscarReservaPorSaidaDestinoOutros(matricula_siape, data_saida, destino);
                        if (lista.isEmpty() && listaOutros.isEmpty()) {
                            request.setAttribute("mensagem", "Não foram encontrados resultados para esta consulta");
                            request.getRequestDispatcher("ControleReserva?action=listaReservas").forward(request, response);
                        } else {
                            request.setAttribute("listaReservasOutros", listaOutros);
                            request.setAttribute("listaReservas", lista);
                            InterfaceDestinoDAO ddao = new DestinoDAO();
                            List<Destino> listad = ddao.buscarDestinos();

                            request.setAttribute("listadest", listad);
                            request.getRequestDispatcher("listaReservas.jsp").forward(request, response);
                        }
                    } else if (data_saida.equals("") && !data_retorno.equals("") && !destino.equals("")) {
                        data_retorno = data_retorno.substring(6, 10) + "-" + data_retorno.substring(3, 5) + "-" + data_retorno.substring(0, 2);
                        List<Reserva> lista = irdao.buscarReservaPorRetornoDestino(matricula_siape, data_retorno, destino);
                        List<Reserva> listaOutros = irdao.buscarReservaPorRetornoDestinoOutros(matricula_siape, data_retorno, destino);
                        if (lista.isEmpty() && listaOutros.isEmpty()) {
                            request.setAttribute("mensagem", "Não foram encontrados resultados para esta consulta");
                            request.getRequestDispatcher("ControleReserva?action=listaReservas").forward(request, response);
                        } else {
                            request.setAttribute("listaReservasOutros", listaOutros);
                            request.setAttribute("listaReservas", lista);
                            InterfaceDestinoDAO ddao = new DestinoDAO();
                            List<Destino> listad = ddao.buscarDestinos();

                            request.setAttribute("listadest", listad);
                            request.getRequestDispatcher("listaReservas.jsp").forward(request, response);
                        }
                    } else if (!data_saida.equals("") && !data_retorno.equals("") && !destino.equals("")) {
                        data_saida = data_saida.substring(6, 10) + "-" + data_saida.substring(3, 5) + "-" + data_saida.substring(0, 2);
                        data_retorno = data_retorno.substring(6, 10) + "-" + data_retorno.substring(3, 5) + "-" + data_retorno.substring(0, 2);
                        List<Reserva> lista = irdao.buscarReservaPorSaidaRetornoDestino(matricula_siape, data_saida, data_retorno, destino);
                        List<Reserva> listaOutros = irdao.buscarReservaPorSaidaRetornoDestinoOutros(matricula_siape, data_saida, data_retorno, destino);
                        if (lista.isEmpty() && listaOutros.isEmpty()) {
                            request.setAttribute("mensagem", "Não foram encontrados resultados para esta consulta");
                            request.getRequestDispatcher("ControleReserva?action=listaReservas").forward(request, response);
                        } else {
                            request.setAttribute("listaReservasOutros", listaOutros);
                            request.setAttribute("listaReservas", lista);
                            InterfaceDestinoDAO ddao = new DestinoDAO();
                            List<Destino> listad = ddao.buscarDestinos();

                            request.setAttribute("listadest", listad);
                            request.getRequestDispatcher("listaReservas.jsp").forward(request, response);
                        }
                    }

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