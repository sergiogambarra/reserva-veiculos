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
                    String combustivel = request.getParameter("combustivel");

                    Veiculo veic = new Veiculo();
                    veic.setPlaca(placa);
                    veic.setAno(ano);
                    veic.setMarca(marca);
                    veic.setCapacidade(capacidade);
                    veic.setModelo(modelo);
                    veic.setRenavam(renavam);
                    veic.setCombustivel(combustivel);

                    VeiculoDAO vdao = new VeiculoDAO();

                    if (acao.equals("cadastrarVeiculo")) {
                        //Verifica se a Placa não existe no BD
                        if (!(Validacoes.ValidarPlacaExiste(placa))) {
                            throw new Exception(Validacoes.getMensagemErro());
                        }

                        vdao.salvar(veic);
                        request.setAttribute("mensagem", "Cadastro efetuado com Sucesso.");
                        request.getRequestDispatcher("ControleVeiculo?action=listaVeiculos&pagina=1").forward(request, response);;
                    } else if (acao.equals("atualizarVeiculo")) {
                        vdao.atualizar(veic);
                        
                        request.setAttribute("mensagem", "Cadastro alterado com sucesso.");
                        request.getRequestDispatcher("ControleVeiculo?action=listaVeiculos&pagina=1").forward(request, response);;
                    }
                } catch (Exception e) {
                    request.setAttribute("mensagem", e.getMessage());
                    request.getRequestDispatcher("cadastrarVeiculo.jsp").forward(request, response);
                }
            } else if (acao.equalsIgnoreCase("editarVeiculo") || acao.equalsIgnoreCase("visualizarVeiculo")) {
                InterfaceVeiculoDAO idao = new VeiculoDAO();
                String placa = request.getParameter("placa");
                Veiculo v = idao.consultarPlaca(placa);
                request.setAttribute("placa", v);
                request.setAttribute("dao", idao);

                if (acao.equalsIgnoreCase("editarVeiculo")) {
                    BarraNavegacao.setarNavegacao(request, "Lista de Veiculos", "ControleVeiculo?action=listaVeiculos&pagina=1", "Editar Veículo", "ControleVeiculo?action=editarVeiculo&placa=" + placa);
                    request.getRequestDispatcher("/formAtualizarVeiculo.jsp").forward(request, response);
                } else {
                    BarraNavegacao.setarNavegacao(request, "Lista de Veiculos", "ControleVeiculo?action=listaVeiculos&pagina=1", "Visualizar Veículo", "ControleVeiculo?action=visualizarVeiculo&placa=" + placa);
                    request.getRequestDispatcher("/formVisualizarVeiculo.jsp").forward(request, response);
                }
            } else if (acao.equals("excluirVeiculo")) {
                try {
                    InterfaceVeiculoDAO idao = new VeiculoDAO();
                    Veiculo veiculo = idao.consultarPlaca(request.getParameter("placa"));
                    idao.excluir(veiculo);

                    request.setAttribute("mensagem", "Cadastro excluído com sucesso.");
                    request.getRequestDispatcher("ControleVeiculo?action=listaVeiculos&pagina=1").forward(request, response);;
                } catch (ConstraintViolationException e) {
                    request.setAttribute("mensagem", "Não foi possível excluir. Há reservas associadas.");
                    request.getRequestDispatcher("ControleVeiculo?action=listaVeiculos&pagina=1").forward(request, response);;
                }
            } else if (acao.equals("listaVeiculos")) {// Parte de consulta ###############################
                try {
                    String numPagina = request.getParameter("pagina");
                    
                    InterfaceVeiculoDAO sdao = new VeiculoDAO();
                    List<Veiculo> lista = sdao.todosVeiculo(numPagina);
                    
                    //Início Paginação
                    int totalRegistros = sdao.todosVeiculosCount();
                    int totalPaginas = totalRegistros / 10;
                    if(totalRegistros % 10 != 0){
                        totalPaginas++;
                    }
                    //Fim Paginação
                    
                    BarraNavegacao.setarNavegacao(request, "Lista de Veículos", "ControleVeiculo?action=listaVeiculos&pagina=1", null, null);
                    request.setAttribute("totalRegistros", totalRegistros);
                    request.setAttribute("totalPaginas", totalPaginas);
                    request.setAttribute("listaveic", lista);
                    request.getRequestDispatcher("listaVeiculos.jsp").forward(request, response);
                } catch (Exception e) {
                    request.setAttribute("mensagem", e.getMessage());
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
                //Parte especica de consulta por determinados parametros
            } else if (acao.equals("consultarVeiculo")) {
                try {

                    String ano = request.getParameter("ano");
                    String placa = request.getParameter("placa");
                    String renavam = request.getParameter("renavam");
                    InterfaceVeiculoDAO ivdao = new VeiculoDAO();

                    if (ano.equals("") && placa.equals("") && renavam.equals("")) {
                        List<Veiculo> lista = ivdao.buscarVeiculoPorPlaca(placa);
                        request.setAttribute("mensagem", "Não foram informados dados para a consulta.");
                        int totalRegistros = lista.size();
                            int totalPaginas = totalRegistros / 10;
                            if(totalRegistros % 10 != 0){
                                totalPaginas++;
                            }
                    
                            request.setAttribute("totalRegistros", totalRegistros);
                            request.setAttribute("totalPaginas", totalPaginas);
                            request.setAttribute("listaveic", lista);
                            BarraNavegacao.setarNavegacao(request, "Lista de Veículos", "ControleVeiculo?action=listaVeiculos&pagina=1", null, null);
                            request.getRequestDispatcher("listaVeiculos.jsp").forward(request, response);

                    } else if (!ano.equals("") && placa.equals("") && renavam.equals("")) {
                        List<Veiculo> lista = ivdao.buscarVeiculoPorAno(ano);
                        if (lista.isEmpty()) {
                            request.setAttribute("mensagem", "Não foram encontrados resultados para esta consulta");
                            int totalRegistros = lista.size();
                            int totalPaginas = totalRegistros / 10;
                            if(totalRegistros % 10 != 0){
                                totalPaginas++;
                            }
                    
                            request.setAttribute("totalRegistros", totalRegistros);
                            request.setAttribute("totalPaginas", totalPaginas);
                            request.setAttribute("listaveic", lista);
                            BarraNavegacao.setarNavegacao(request, "Lista de Veículos", "ControleVeiculo?action=listaVeiculos&pagina=1", null, null);
                            request.getRequestDispatcher("listaVeiculos.jsp").forward(request, response);
                        } else {
                            int totalRegistros = lista.size();
                            int totalPaginas = totalRegistros / 10;
                            if(totalRegistros % 10 != 0){
                                totalPaginas++;
                            }
                            
                            request.setAttribute("totalRegistros", totalRegistros);
                            request.setAttribute("totalPaginas", totalPaginas);
                            request.setAttribute("listaveic", lista);
                            BarraNavegacao.setarNavegacao(request, "Lista de Veículos", "ControleVeiculo?action=listaVeiculos&pagina=1", null, null);
                            request.getRequestDispatcher("listaVeiculos.jsp").forward(request, response);
                        }
                    } else if (ano.equals("") && !placa.equals("") && renavam.equals("")) {
                        List<Veiculo> lista = ivdao.buscarVeiculoPorPlaca(placa);
                        if (lista.isEmpty()) {
                            request.setAttribute("mensagem", "Não foram encontrados resultados para esta consulta");
                            int totalRegistros = lista.size();
                            int totalPaginas = totalRegistros / 10;
                            if(totalRegistros % 10 != 0){
                                totalPaginas++;
                            }
                    
                            request.setAttribute("totalRegistros", totalRegistros);
                            request.setAttribute("totalPaginas", totalPaginas);
                            request.setAttribute("listaveic", lista);
                            BarraNavegacao.setarNavegacao(request, "Lista de Veículos", "ControleVeiculo?action=listaVeiculos&pagina=1", null, null);
                            request.getRequestDispatcher("listaVeiculos.jsp").forward(request, response);
                        } else {
                            int totalRegistros = lista.size();
                            int totalPaginas = totalRegistros / 10;
                            if(totalRegistros % 10 != 0){
                                totalPaginas++;
                            }
                    
                            request.setAttribute("totalRegistros", totalRegistros);
                            request.setAttribute("totalPaginas", totalPaginas);
                            request.setAttribute("listaveic", lista);
                            BarraNavegacao.setarNavegacao(request, "Lista de Veículos", "ControleVeiculo?action=listaVeiculos&pagina=1", null, null);
                            request.getRequestDispatcher("listaVeiculos.jsp").forward(request, response);
                        }
                    } else if (ano.equals("") && placa.equals("") && !renavam.equals("")) {
                        List<Veiculo> lista = ivdao.buscarVeiculoPorRenavam(renavam);
                        if (lista.isEmpty()) {
                            request.setAttribute("mensagem", "Não foram encontrados resultados para esta consulta");
                            int totalRegistros = lista.size();
                            int totalPaginas = totalRegistros / 10;
                            if(totalRegistros % 10 != 0){
                                totalPaginas++;
                            }
                    
                            request.setAttribute("totalRegistros", totalRegistros);
                            request.setAttribute("totalPaginas", totalPaginas);
                            request.setAttribute("listaveic", lista);
                            BarraNavegacao.setarNavegacao(request, "Lista de Veículos", "ControleVeiculo?action=listaVeiculos&pagina=1", null, null);
                            request.getRequestDispatcher("listaVeiculos.jsp").forward(request, response);
                        } else {
                            int totalRegistros = lista.size();
                            int totalPaginas = totalRegistros / 10;
                            if(totalRegistros % 10 != 0){
                                totalPaginas++;
                            }
                    
                            request.setAttribute("totalRegistros", totalRegistros);
                            request.setAttribute("totalPaginas", totalPaginas);
                            request.setAttribute("listaveic", lista);
                            BarraNavegacao.setarNavegacao(request, "Lista de Veículos", "ControleVeiculo?action=listaVeiculos&pagina=1", null, null);
                            request.getRequestDispatcher("listaVeiculos.jsp").forward(request, response);
                        }
                    } else if (!ano.equals("") && !placa.equals("") && renavam.equals("")) {
                        List<Veiculo> lista = ivdao.buscarVeiculoPorAnoPlaca(ano, placa);
                        if (lista.isEmpty()) {
                            request.setAttribute("mensagem", "Não foram encontrados resultados para esta consulta");
                            int totalRegistros = lista.size();
                            int totalPaginas = totalRegistros / 10;
                            if(totalRegistros % 10 != 0){
                                totalPaginas++;
                            }
                    
                            request.setAttribute("totalRegistros", totalRegistros);
                            request.setAttribute("totalPaginas", totalPaginas);
                            request.setAttribute("listaveic", lista);
                            BarraNavegacao.setarNavegacao(request, "Lista de Veículos", "ControleVeiculo?action=listaVeiculos&pagina=1", null, null);
                            request.getRequestDispatcher("listaVeiculos.jsp").forward(request, response);
                        } else {
                            int totalRegistros = lista.size();
                            int totalPaginas = totalRegistros / 10;
                            if(totalRegistros % 10 != 0){
                                totalPaginas++;
                            }
                    
                            request.setAttribute("totalRegistros", totalRegistros);
                            request.setAttribute("totalPaginas", totalPaginas);
                            request.setAttribute("listaveic", lista);
                            BarraNavegacao.setarNavegacao(request, "Lista de Veículos", "ControleVeiculo?action=listaVeiculos&pagina=1", null, null);
                            request.getRequestDispatcher("listaVeiculos.jsp").forward(request, response);
                        }
                    } else if (!ano.equals("") && placa.equals("") && !renavam.equals("")) {
                        List<Veiculo> lista = ivdao.buscarVeiculoPorAnoRenavam(ano, renavam);
                        if (lista.isEmpty()) {
                            request.setAttribute("mensagem", "Não foram encontrados resultados para esta consulta");
                            int totalRegistros = lista.size();
                            int totalPaginas = totalRegistros / 10;
                            if(totalRegistros % 10 != 0){
                                totalPaginas++;
                            }
                    
                            request.setAttribute("totalRegistros", totalRegistros);
                            request.setAttribute("totalPaginas", totalPaginas);
                            request.setAttribute("listaveic", lista);
                            BarraNavegacao.setarNavegacao(request, "Lista de Veículos", "ControleVeiculo?action=listaVeiculos&pagina=1", null, null);
                            request.getRequestDispatcher("listaVeiculos.jsp").forward(request, response);
                        } else {
                            int totalRegistros = lista.size();
                            int totalPaginas = totalRegistros / 10;
                            if(totalRegistros % 10 != 0){
                                totalPaginas++;
                            }
                    
                            request.setAttribute("totalRegistros", totalRegistros);
                            request.setAttribute("totalPaginas", totalPaginas);
                            request.setAttribute("listaveic", lista);
                            BarraNavegacao.setarNavegacao(request, "Lista de Veículos", "ControleVeiculo?action=listaVeiculos&pagina=1", null, null);
                            request.getRequestDispatcher("listaVeiculos.jsp").forward(request, response);
                        }
                    } else if (ano.equals("") && !placa.equals("") && !renavam.equals("")) {
                        List<Veiculo> lista = ivdao.buscarVeiculoPorPlacaRenavam(placa, renavam);
                        if (lista.isEmpty()) {
                            request.setAttribute("mensagem", "Não foram encontrados resultados para esta consulta");
                            int totalRegistros = lista.size();
                            int totalPaginas = totalRegistros / 10;
                            if(totalRegistros % 10 != 0){
                                totalPaginas++;
                            }
                    
                            request.setAttribute("totalRegistros", totalRegistros);
                            request.setAttribute("totalPaginas", totalPaginas);
                            request.setAttribute("listaveic", lista);
                            BarraNavegacao.setarNavegacao(request, "Lista de Veículos", "ControleVeiculo?action=listaVeiculos&pagina=1", null, null);
                            request.getRequestDispatcher("listaVeiculos.jsp").forward(request, response);
                        } else {
                            int totalRegistros = lista.size();
                            int totalPaginas = totalRegistros / 10;
                            if(totalRegistros % 10 != 0){
                                totalPaginas++;
                            }
                    
                            request.setAttribute("totalRegistros", totalRegistros);
                            request.setAttribute("totalPaginas", totalPaginas);
                            request.setAttribute("listaveic", lista);
                            BarraNavegacao.setarNavegacao(request, "Lista de Veículos", "ControleVeiculo?action=listaVeiculos&pagina=1", null, null);
                            request.getRequestDispatcher("listaVeiculos.jsp").forward(request, response);
                        }
                    } else if (!ano.equals("") && !placa.equals("") && !renavam.equals("")) {
                        List<Veiculo> lista = ivdao.buscarVeiculoPorAnoPlacaRenavam(ano, placa, renavam);

                        if (lista.isEmpty()) {
                            request.setAttribute("mensagem", "Não foram encontrados resultados para esta consulta");
                            int totalRegistros = lista.size();
                            int totalPaginas = totalRegistros / 10;
                            if(totalRegistros % 10 != 0){
                                totalPaginas++;
                            }
                    
                            request.setAttribute("totalRegistros", totalRegistros);
                            request.setAttribute("totalPaginas", totalPaginas);
                            request.setAttribute("listaveic", lista);
                            BarraNavegacao.setarNavegacao(request, "Lista de Veículos", "ControleVeiculo?action=listaVeiculos&pagina=1", null, null);
                            request.getRequestDispatcher("listaVeiculos.jsp").forward(request, response);
                        } else {
                            int totalRegistros = lista.size();
                            int totalPaginas = totalRegistros / 10;
                            if(totalRegistros % 10 != 0){
                                totalPaginas++;
                            }
                    
                            request.setAttribute("totalRegistros", totalRegistros);
                            request.setAttribute("totalPaginas", totalPaginas);
                            request.setAttribute("listaveic", lista);
                            BarraNavegacao.setarNavegacao(request, "Lista de Veículos", "ControleVeiculo?action=listaVeiculos&pagina=1", null, null);
                            request.getRequestDispatcher("listaVeiculos.jsp").forward(request, response);
                        }
                    }

                } catch (Exception e) {
                    request.setAttribute("mensagem", e.getMessage());
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
            } else if (acao.equals("novoVeiculo")) {
                try {
                     BarraNavegacao.setarNavegacao(request, "Novo Veículo", "ControleVeiculo?action=novoVeiculo", null, null);
                     request.getRequestDispatcher("cadastrarVeiculo.jsp").forward(request, response);
                } catch (Exception e) {
                    request.setAttribute("mensagem", e.getMessage());
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
            }
        } catch (Exception e) {
            request.setAttribute("mensagem", e.getMessage());
            request.getRequestDispatcher("ControleVeiculo?action=listaVeiculos&pagina=1").forward(request, response);;
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
