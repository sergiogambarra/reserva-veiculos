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
import org.hibernate.exception.ConstraintViolationException;
import srv.dao.InterfaceVeiculoDAO;
import srv.dao.VeiculoDAO;
import srv.modelo.Veiculo;
import srv.util.Validacoes;

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
                    String combustivel = request.getParameter("combustivel");

                    //CAMPOS NÃO OBRIGATÓRIOS (SE NÃO ESTIVER EM MANUTENÇÃO)
                    String manutencao_data_inicial = request.getParameter("sManDataInicial");
                    String manutencao_data_final = request.getParameter("sManDataFinal");

                    Veiculo veic = new Veiculo();
                    veic.setPlaca(placa);
                    veic.setAno(ano);
                    veic.setMarca(marca);
                    veic.setCapacidade(capacidade);
                    veic.setModelo(modelo);
                    veic.setRenavam(renavam);
                    veic.setCombustivel(combustivel);

                    if (manutencao.equals("t")) {
                        veic.setManutencao(true);
                        // SETANDO CAMPOS NÃO OBRIGATÓRIOS
                        Date date1;
                        date1 = new SimpleDateFormat("yyyy-MM-dd").parse(manutencao_data_inicial);
                        veic.setManutencao_data_inicial(date1);

                        Date date2;
                        date2 = new SimpleDateFormat("yyyy-MM-dd").parse(manutencao_data_final);
                        veic.setManutencao_data_final(date2);
                    } else {
                        veic.setManutencao(false);
                        veic.setManutencao_data_inicial(null);
                        veic.setManutencao_data_final(null);
                    }

                    VeiculoDAO vdao = new VeiculoDAO();

                    if (acao.equals("cadastrarVeiculo")) {
                        //Verifica se a Placa não existe no BD
                        if (!(Validacoes.ValidarPlacaExiste(placa))) {
                            throw new Exception(Validacoes.getMensagemErro());
                        }

                        vdao.salvar(veic);
                        InterfaceVeiculoDAO idao = new VeiculoDAO();
                        List<Veiculo> lista = idao.todosVeiculo();

                        request.setAttribute("mensagem", "Cadastro efetuado com Sucesso.");
                        request.setAttribute("listaveic", lista);
                        request.getRequestDispatcher("listaVeiculos.jsp").forward(request, response);
                    } else if (acao.equals("atualizarVeiculo")) {
                        vdao.atualizar(veic);
                        InterfaceVeiculoDAO idao = new VeiculoDAO();
                        List<Veiculo> lista = idao.todosVeiculo();

                        request.setAttribute("mensagem", "Cadastro alterado com sucesso.");
                        request.setAttribute("listaveic", lista);
                        request.getRequestDispatcher("listaVeiculos.jsp").forward(request, response);
                    }
                } catch (Exception e) {
                    request.setAttribute("mensagem", e.getMessage());
                    request.getRequestDispatcher("cadastrarVeiculo.jsp").forward(request, response);
                }
            } else if (acao.equalsIgnoreCase("editarVeiculo") || acao.equalsIgnoreCase("visualizarVeiculo")) {
                InterfaceVeiculoDAO idao = new VeiculoDAO();
                Veiculo v = idao.consultarPlaca(request.getParameter("placa"));
                request.setAttribute("placa", v);
                request.setAttribute("dao", idao);

                if (acao.equalsIgnoreCase("editarVeiculo")) {
                    request.getRequestDispatcher("/formAtualizarVeiculo.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("/formVisualizarVeiculo.jsp").forward(request, response);
                }
            } else if (acao.equals("excluirVeiculo")) {
                try {
                    InterfaceVeiculoDAO idao = new VeiculoDAO();
                    Veiculo veiculo = idao.consultarPlaca(request.getParameter("placa"));
                    idao.excluir(veiculo);

                    List<Veiculo> lista = idao.todosVeiculo();

                    request.setAttribute("mensagem", "Cadastro excluído com sucesso.");
                    request.setAttribute("listaveic", lista);
                    request.getRequestDispatcher("listaVeiculos.jsp").forward(request, response);
                } catch (ConstraintViolationException e) {
                    request.setAttribute("mensagem", "Não foi possível excluir. Há reservas associadas.");
                    InterfaceVeiculoDAO idao = new VeiculoDAO();
                    List<Veiculo> lista = idao.todosVeiculo();
                    request.setAttribute("listaveic", lista);
                    request.getRequestDispatcher("listaVeiculos.jsp").forward(request, response);
                }
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
            InterfaceVeiculoDAO idao = new VeiculoDAO();
            List<Veiculo> lista = idao.todosVeiculo();
            request.setAttribute("listaveic", lista);
            request.getRequestDispatcher("listaVeiculos.jsp").forward(request, response);
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
