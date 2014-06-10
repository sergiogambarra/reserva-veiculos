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
import srv.modelo.Reserva;
import srv.modelo.Veiculo;

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

        String data_saida = request.getParameter("dataSaida");
        String hora_saida = request.getParameter("horaSaida");
        String datetime_saida = data_saida + " " + hora_saida + ":00";

        String data_retorno = request.getParameter("dataRetorno");
        String hora_retorno = request.getParameter("horaRetorno");
        String datetime_retorno = data_retorno + " " + hora_retorno + ":00";

        Date dateSaida = new Date();
        Date dateRetorno = new Date();
        try {
            dateSaida = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(datetime_saida);
            dateRetorno = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(datetime_retorno);
        } catch (ParseException ex) {
            ex.getMessage();
        }

        InterfaceReservaDAO iReservaDao = new ReservaDAO();
        List<Veiculo> listaVeiculosDisp = new ArrayList<Veiculo>();
        listaVeiculosDisp = iReservaDao.consultarDisponibilidadeVeiculo(dateSaida, dateRetorno);

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
                        .append("<a href=\"ControleReserva?action=formularioReserva&placa=").append(listaVeiculosDisp.get(i).getPlaca()).append("\"><div class=\"iconeSelecionar\" alt=\"Visualizar informações do Servidor.\" title=\"Visualizar Servidor\"></div>selecionar</a>")
                        .append("</li></ul>")
                        .append("</div>")
                        .append("</td></tr>");
            }
            tabela.append("</tbody></table>");
        }

        response.getWriter().write(tabela.toString());
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
