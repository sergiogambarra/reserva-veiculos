/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package srv.controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import srv.dao.InterfaceReservaDAO;
import srv.dao.ReservaDAO;
import srv.modelo.Veiculo;
import srv.util.Validacoes;

/**
 *
 * @author Douglas
 */
public class ControleReservaAjax extends HttpServlet {

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
        System.out.println("aqui");
        response.setContentType("text/html;charset=UTF-8");
        try {
            System.out.println("aqui");
        } catch (Exception e) {
            e.getMessage();
        } finally {
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.context = config.getServletContext();
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
    private ServletContext context;

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
        response.setContentType("text/html;charset=UTF-8");
        boolean validaoPeriodo = false;
        String data_saida = Validacoes.validarDataEntradaMysql(request.getParameter("dataSaida"));

        String hora_saida = request.getParameter("horaSaida");
        String datetime_saida = data_saida + " " + hora_saida + ":00";

        String data_retorno = Validacoes.validarDataEntradaMysql(request.getParameter("dataRetorno"));
        String hora_retorno = request.getParameter("horaRetorno");
        String datetime_retorno = data_retorno + " " + hora_retorno + ":00";

        String id_reserva = "";
        if (!request.getParameter("id_reserva").equals("")) {
            id_reserva = request.getParameter("id_reserva");
        }

        Date dateSaida = new Date();
        Date dateRetorno = new Date();
        try {
            dateSaida = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(datetime_saida);
            dateRetorno = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(datetime_retorno);

            data_saida = Validacoes.validarDataSaidaMysqlString(data_saida);
            data_retorno = Validacoes.validarDataSaidaMysqlString(data_retorno);

            StringBuilder msgErro = new StringBuilder();
            msgErro.append("<tr><td>Para o período ser correto, as seguintes regras devem ser respeitadas:</tr></td>");
            msgErro.append("<tr><td>- A data de saída e a data de retorno (data e horário) não podem ser menores que a data atual (data e horário).</tr></td>");
            msgErro.append("<tr><td>- Saída (data e horário) não podem ser igual ao retorno (data e horário).</tr></td>");
            msgErro.append("<tr><td>- Saída não pode ser maior que o retorno.</tr></td>");
            msgErro.append("<tr><td>- Retorno não pode ser menor que a saída.</tr></td>");
            msgErro.append("<tr><td>&nbsp</tr></td>");
            msgErro.append("<tr><td>Refaça a sua consulta com um período correto.</tr></td>");
            msgErro.append("</table>");

            if (Validacoes.validarPeriodoReservaDate(dateSaida, dateRetorno) == 0) {

                if (Validacoes.validarPeriodoReserva(datetime_saida, datetime_retorno) > 0) {
                    validaoPeriodo = true;
                    StringBuilder msgErroFinal = new StringBuilder();
                    msgErroFinal.append("<table id=\"idListaErros\">");
                    //msgErroFinal.append("<tr><td>Erro no período: data de saída maior que a data de retorno.</tr></td>");
                    msgErroFinal.append("<tr><td>O horário de saída não pode ser maior que o horário de retorno.</tr></td>");
                    msgErroFinal.append("<tr><td>&nbsp</tr></td>");
//                    msgErroFinal.append(msgErro);
                    request.setAttribute("mensagem", "O horário de saída não pode ser maior que o horário de retorno");
                    response.getWriter().write(msgErroFinal.toString());


                } else if (Validacoes.validarPeriodoReserva(
                        datetime_saida, datetime_retorno) == 0) {
                    validaoPeriodo = true;
                    //datas iguais
                    StringBuilder msgErroFinal = new StringBuilder();
                    msgErroFinal.append("<table id=\"idListaErros\">");
                    //msgErroFinal.append("<tr><td>Erro no período: data de saída igual à data de retorno.</tr></td>");
                    msgErroFinal.append("<tr><td>O horário de retorno deve ser maior que o horário de saída.</tr></td>");
                    msgErroFinal.append("<tr><td>&nbsp</tr></td>");
                    request.setAttribute("mensagem", "O horário de retorno deve ser maior que o horário de saída");
                    //msgErroFinal.append(msgErro);
                    response.getWriter().write(msgErroFinal.toString());
                }

            }

            if (validaoPeriodo == false) {
                if (Validacoes.validarPeriodoComDataHoje(datetime_saida) < 0) {
                    validaoPeriodo = true;
                    //data de saída maior que retorno
            /*
                     * 1)Datetime não podem ser menores que o atual;
                     * 2)Se datas forem iguais, horário retorno deve ser maior que o de saída
                     * 3)Data de retorno não pode ser menor que data de saída
                     */
                    StringBuilder msgErroFinal = new StringBuilder();
                    msgErroFinal.append("<table id=\"idListaErros\">");
                    msgErroFinal.append("<tr><td>A data e o horário de saída não podem ser menores que a data e o horário atual.</tr></td>");
                    msgErroFinal.append("<tr><td>&nbsp</tr></td>");
                    response.getWriter().write(msgErroFinal.toString());

                } else if (Validacoes.validarPeriodoComDataHoje(datetime_retorno) < 0) {
                    validaoPeriodo = true;
                    StringBuilder msgErroFinal = new StringBuilder();
                    msgErroFinal.append("<table id=\"idListaErros\">");
                    msgErroFinal.append("<tr><td>A data e o horário de retorno não podem ser menores que a data e o horário atual.</tr></td>");
                    msgErroFinal.append("<tr><td>&nbsp</tr></td>");
//                    msgErroFinal.append(msgErro);
                    response.getWriter().write(msgErroFinal.toString());

                } else if (Validacoes.validarPeriodoReserva(datetime_saida, datetime_retorno) > 0) {
                    validaoPeriodo = true;
                    StringBuilder msgErroFinal = new StringBuilder();
                    msgErroFinal.append("<table id=\"idListaErros\">");
//                msgErroFinal.append("<tr><td>Erro no período: data de saída maior que a data de retorno.</tr></td>");
                    msgErroFinal.append("<tr><td>A data de saída não pode ser maior que a data de retorno.</tr></td>");
                    msgErroFinal.append("<tr><td>&nbsp</tr></td>");
//                    msgErroFinal.append(msgErro);
                    response.getWriter().write(msgErroFinal.toString());

                } else if (Validacoes.validarPeriodoReserva(
                        datetime_saida, datetime_retorno) == 0) {
                    validaoPeriodo = true;
                    //datas iguais
                    StringBuilder msgErroFinal = new StringBuilder();
                    msgErroFinal.append("<table id=\"idListaErros\">");
//                msgErroFinal.append("<tr><td>Erro no período: data de saída igual à data de retorno.</tr></td>");
                    msgErroFinal.append("<tr><td>A data de retorno deve ser maior que a data de saída.</tr></td>");
                    msgErroFinal.append("<tr><td>&nbsp</tr></td>");
//                msgErroFinal.append(msgErro);
                    response.getWriter().write(msgErroFinal.toString());
                }
            }

            if (validaoPeriodo == false) {

                InterfaceReservaDAO iReservaDao = new ReservaDAO();
                List<Veiculo> listaVeiculosDisp = new ArrayList<Veiculo>();
                // @@@

                if (id_reserva.equals("")) {
                    listaVeiculosDisp = iReservaDao.consultarDisponibilidadeVeiculo(dateSaida, dateRetorno);
                } else {
                    listaVeiculosDisp = iReservaDao.consultarDisponibilidadeVeiculo(dateSaida, dateRetorno, id_reserva);
                }

                response.setContentType("text/xml");
                response.setHeader("Cache-Control", "no-cache");

                StringBuilder tabela = new StringBuilder();

                if (listaVeiculosDisp.size() == 0) {
                    tabela.append("Não há veículos disponíveis para esta data/hora.");
                } else {
                    tabela.append("<table id=\"idTabelaListaVeiculos\" class=\"tabelaListaVeiculos\">")
                            .append("<thead>")
                            .append("<td id=\"Placa\" class=\"colunaDuzentos\">Placa</td>")
                            .append("<td id=\"Modelo\">Modelo</td>")
                            .append("<td id=\"Marca\" class=\"colunaDuzentos\">Marca</td>")
                            .append("<td id=\"Renavam\" class=\"colunaDuzentos\">Renavam</td>")
                            .append("<td id=\"Acao\" class=\"colunaAcoesHead\" >Ação</td>")
                            .append("</thead>")
                            .append("<tbody>");
                    for (int i = 0; i < listaVeiculosDisp.size(); i++) {
                        tabela.append("<tr>")
                                .append("<td headers=\"Placa\">")
                                .append(listaVeiculosDisp.get(i).getPlaca())
                                .append("</td>")
                                .append("<td headers=\"Modelo\">")
                                .append(listaVeiculosDisp.get(i).getModelo())
                                .append("</td>")
                                .append("<td headers=\"Marca\">")
                                .append(listaVeiculosDisp.get(i).getMarca())
                                .append("</td>")
                                .append("<td headers=\"Renavam\">")
                                .append(listaVeiculosDisp.get(i).getRenavam())
                                .append("</td>")
                                .append("<td headers=\"Acao\" class=\"colunaAcoes\">")
                                .append("<div class=\"divColunaAcoes\">")
                                .append("<ul><li>")
                                .append("<a href=\"ControleReserva?action=formularioReserva&placa=").append(listaVeiculosDisp.get(i).getPlaca())
                                .append("&inputDataSaida=").append(data_saida)
                                .append("&inputHoraSaida=").append(hora_saida)
                                .append("&inputDataRetorno=").append(data_retorno)
                                .append("&inputHoraRetorno=").append(hora_retorno)
                                .append("&id_reserva=").append(id_reserva)
                                .append("\"><div class=\"iconeSelecionar\" alt=\"Visualizar informações do Servidor.\" title=\"Visualizar Servidor\"></div>selecionar</a>")
                                .append("</li></ul>")
                                .append("</div>")
                                .append("</td></tr>");
                    }
                    tabela.append("</tbody></table>");
                }
                response.getWriter().write(tabela.toString());
            }

        } catch (Exception ex) {
            StringBuilder msgErroFinal = new StringBuilder();
            msgErroFinal.append("<table id=\"idListaErros\">");
            msgErroFinal.append("<tr><td>Todos os campos são de preenchimento obrigatório.</td></tr>");
            msgErroFinal.append("</table>");
            response.getWriter().write(msgErroFinal.toString());
        }

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
