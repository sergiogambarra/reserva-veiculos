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
        List<Veiculo> listaReservas = new ArrayList<Veiculo>();
        listaReservas = iReservaDao.consultarDisponibilidadeVeiculo(dateSaida, dateRetorno);

        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        
        String tabela =
                "<table class=\"tabelaListaVeiculos\">"
                + "<thead>"
                + "<td id=\"Placa\" class=\"colunaDuzentos\">Placa</td>"
                + "<td id=\"Modelo\">Modelo</td>"
                + "<td id=\"Marca\" class=\"colunaDuzentos\">Marca</td>"
                + "<td id=\"Renavam\" class=\"colunaDuzentos\">Renavam</td>"
                + "<td id=\"Acoes\" class=\"colunaAcoesHead\" >Ações</td>"
                + "</thead>"
                + "<tbody>";
        for (int i = 0; i < listaReservas.size(); i++) {
            tabela += "<tr>";
            tabela += "<td headers=\"Placa\">";
            tabela += listaReservas.get(i).getPlaca() + "</td>";
            tabela += "<td headers=\"Modelo\">";
            tabela += listaReservas.get(i).getModelo() + "</td>";
            tabela += "<td headers=\"Marca\">";
            tabela += listaReservas.get(i).getMarca() + "</td>";
            tabela += "<td headers=\"Renavam\">";
            tabela += listaReservas.get(i).getRenavam() + "</td>";
            tabela += "<td headers=\"Acoes\" class=\"colunaAcoes\"> SELECIONAR </td></tr>";
        }
        tabela += "</tbody></table>";
        response.getWriter().write(tabela);
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
