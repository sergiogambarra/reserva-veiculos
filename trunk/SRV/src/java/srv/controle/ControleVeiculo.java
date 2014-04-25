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
import srv.dao.InterfaceVeiculoDAO;
import srv.dao.VeiculoDAO;
import srv.modelo.Veiculo;

/**
 *
 * @author Paula
 */
@WebServlet(name = "ControleVeiculo", urlPatterns = {"/ControleVeiculo"})
public class ControleVeiculo extends HttpServlet {

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
            if (acao.equals("cadastrarVeiculo") || acao.equals("atualizarVeiculo")) {
                try {
                    String placa = request.getParameter("iPlaca");
                    String ano = request.getParameter("iAno");
                    String marca = request.getParameter("iMarca");
                    int capacidade = Integer.parseInt(request.getParameter("iCapacidade"));
                    String modelo = request.getParameter("iModelo");
                    String renavam = request.getParameter("iRenavam");
                    String manutencao = request.getParameter("manutencao");
                    String manutencao_data_inicial = request.getParameter("sManDataInicial");
                    String manutencao_data_final = request.getParameter("sManDataFinal");
                    
                    //COMBUSTIVEL
                    String gasolina = request.getParameter("gasolina");
                    String diesel = request.getParameter("diesel");
                    String alcool = request.getParameter("alcool");
                    String gnv = request.getParameter("gnv");
                    
                    Veiculo veiculo = new Veiculo();
                    veiculo.setPlaca(placa);
                    veiculo.setAno(ano);
                    veiculo.setMarca(marca);
                    veiculo.setCapacidade(capacidade);
                    veiculo.setModelo(modelo);
                    veiculo.setRenavam(renavam);

                    //FALTA VALIDAR TODOS OS CHECKBOX
                    veiculo.setCombustivel(gasolina);
                    
                    if (manutencao.equals("t")) {
                        veiculo.setManutencao(true);
                    } else {
                        veiculo.setManutencao(false);
                    }

                    // FALTA VALIDACAO DA DATA FINAL < DATA INICIAL
                    Date date1;
                    date1 = new SimpleDateFormat("yyyy-MM-dd").parse(manutencao_data_inicial);
                    veiculo.setManutencao_data_inicial(date1);

                    Date date2;
                    date2 = new SimpleDateFormat("yyyy-MM-dd").parse(manutencao_data_final);
                    veiculo.setManutencao_data_final(date2);

                    VeiculoDAO vdao = new VeiculoDAO();

                    if (acao.equals("cadastrarVeiculo")) {

                        vdao.salvar(veiculo);

                        InterfaceVeiculoDAO idao = new VeiculoDAO();
                        List<Veiculo> lista = idao.todosVeiculo();

                        request.setAttribute("listaveic", lista);
                        request.getRequestDispatcher("listaVeiculos.jsp").forward(request, response);
                    } else if (acao.equals("atualizarVeiculo")) {
                        vdao.atualizar(veiculo);
                        InterfaceVeiculoDAO idao = new VeiculoDAO();
                        List<Veiculo> lista = idao.todosVeiculo();

                        request.setAttribute("listaveic", lista);
                        request.getRequestDispatcher("listaVeiculos.jsp").forward(request, response);
                    }
                } catch (Exception e) {
                    request.setAttribute("mensagem", e.getMessage());
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
            } else if (acao.equalsIgnoreCase("editarVeiculo")) {
                InterfaceVeiculoDAO idao = new VeiculoDAO();
                List<Veiculo> list = idao.consultarPlaca(request.getParameter("placa"));
                Veiculo placa = list.get(0);
                request.setAttribute("placa", placa);
                request.getRequestDispatcher("/formAtualizarVeiculo.jsp").forward(request, response);
            } else if (acao.equals("excluirVeiculo")) {
                InterfaceVeiculoDAO idao = new VeiculoDAO();
                List<Veiculo> list = idao.consultarPlaca(request.getParameter("placa"));
                Veiculo veiculo = list.get(0);
                idao.excluir(veiculo);

                List<Veiculo> lista = idao.todosVeiculo();

                request.setAttribute("listaveic", lista);
                request.getRequestDispatcher("listaVeiculos.jsp").forward(request, response);
            } else if (acao.equals("listaVeiculos")) {
                try {
                    InterfaceVeiculoDAO sdao = new VeiculoDAO();
                    List<Veiculo> lista = sdao.todosVeiculo();

                    request.setAttribute("listaveic", lista);
                    request.getRequestDispatcher("listaVeiculos.jsp").forward(request, response);
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
