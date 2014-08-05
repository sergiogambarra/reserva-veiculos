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
import javax.servlet.http.HttpSession;
import org.hibernate.exception.ConstraintViolationException;
import srv.dao.InterfaceServidorDAO;
import srv.dao.ServidorDAO;
import srv.modelo.Servidor;
import srv.util.Validacoes;

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

            HttpSession session = request.getSession();
            Servidor user = new Servidor();
            if (request.getSession().getAttribute("administrador") != null) {
                user = (Servidor) session.getAttribute("administrador");
            } else if (request.getSession().getAttribute("servidor") != null) {
                user = (Servidor) session.getAttribute("servidor");
            }
            //Criação de uma string para trabalhar com os formularios de consuta
            //String form = request.getParameter("frm");

            String acao = request.getParameter("action");
            //Se for CADASTRAR ou ATUALIZAR
            if (acao.equals("cadastrarServidor") || acao.equals("atualizarServidor")) {
                try {
                    String matricula_siape = request.getParameter("iMatriculaSiape");

                    String nome = request.getParameter("sNomeCompleto");
                    String email = request.getParameter("sEmail");
                    String sexo = request.getParameter("sexo");
                    String data_nascimento = request.getParameter("sDataNascimento");
                    String cpf = request.getParameter("sCpf");
                    String rg = request.getParameter("sRg");
                    String orgao_expedidor = request.getParameter("sOrgaoExpedidor");
                    String telefone1 = request.getParameter("sTelefone1");
                    String motorista = request.getParameter("bMotorista");
                    String status_serv = request.getParameter("status_serv");

                    //CAMPOS NÃO OBRIGATÓRIOS
                    String naturalidade = request.getParameter("sNaturalidade");
                    String estado = request.getParameter("estado");
                    String nacionalidade = request.getParameter("sNacionalidade");
                    String estado_civil = request.getParameter("estadoCivil");
                    String cnh = request.getParameter("sCnh");
                    String informacoes = request.getParameter("sInfoComplementar");
                    String telefone2 = request.getParameter("sTelefone2");

                    Servidor serv = new Servidor();
                    serv.setMatriculaSIAPE(matricula_siape);
                    serv.setNome(nome);
                    serv.setEmail(email);
                    serv.setSexo(sexo);

                    //A DATA SERÁ A SENHA INICIAL
                    Date date;
                    date = new SimpleDateFormat("yyyy-MM-dd").parse(data_nascimento);
                    serv.setData_nascimento(date);
                    serv.setCpf(cpf);
                    serv.setRg(rg);
                    serv.setOrgao_expedidor(orgao_expedidor);
                    serv.setTelefone1(telefone1);

                    //Motorista
                    if (motorista.equals("1")) {
                        serv.setMotorista(true);
                    } else {
                        serv.setMotorista(false);
                    }

                    //Ativo ou Inativo
                    if (status_serv.equalsIgnoreCase("1")) {
                        serv.setStatus_serv(true);
                    } else {
                        serv.setStatus_serv(false);
                    }

                    //SETANDO CAMPOS NÃO OBRIGATÓRIOS (VERIFICA CAMPOS NULOS)
                    if (!(naturalidade.equalsIgnoreCase(""))) {
                        serv.setNaturalidade(naturalidade);
                    } else {
                        serv.setNaturalidade(null);
                    }

                    if (!(estado.equalsIgnoreCase(""))) {
                        serv.setEstado(estado);
                    } else {
                        serv.setEstado(null);
                    }

                    if (!(nacionalidade.equalsIgnoreCase(""))) {
                        serv.setNacionalidade(nacionalidade);
                    } else {
                        serv.setNacionalidade(null);
                    }

                    if (!(estado_civil.equalsIgnoreCase("0"))) {
                        serv.setEstado_civil(estado_civil);
                    } else {
                        serv.setEstado_civil(null);
                    }

                    if (!(informacoes.equalsIgnoreCase(""))) {
                        serv.setInformacoes(informacoes);
                    } else {
                        serv.setInformacoes(null);
                    }

                    if (!(telefone2.equalsIgnoreCase(""))) {
                        serv.setTelefone2(telefone2);
                    } else {
                        serv.setTelefone2(null);
                    }

                    if (!(cnh.equalsIgnoreCase(""))) {
                        serv.setCnh(cnh);
                    } else {
                        serv.setCnh(null);
                    }

                    /*if (motorista.equalsIgnoreCase("1")) {
                     serv.setCnh(cnh);
                     }*/

                    //PEGA DIA E ANO PARA GERAR A SENHA
                    SimpleDateFormat dataSenha = new SimpleDateFormat("ddyyyy");
                    dataSenha.format(date);
                    String senha = dataSenha.format(date);
                    ServidorDAO sdao = new ServidorDAO();

                    if (acao.equals("cadastrarServidor")) {
                        //Verifica se a Matricula não existe no BD
                        if (!(Validacoes.ValidarMatriculaExiste(matricula_siape))) {
                            throw new Exception(Validacoes.getMensagemErro());
                        }

                        //Senha só pode ser alterada pelo próprio servidor
                        //Senha inicial == Data Nascimento ddyyyy
                        serv.setSenha(senha);
                        sdao.salvar(serv);

                        BarraNavegacao.setarNavegacao(request, "Lista de Servidores", "ControleServidor?action=listaServidores&pagina=1", null, null);
                        request.setAttribute("mensagem", "Cadastro efetuado com Sucesso.");
                        request.getRequestDispatcher("ControleServidor?action=listaServidores&pagina=1").forward(request, response);;
                    } else if (acao.equals("atualizarServidor")) {

                        sdao.atualizar(serv);
                        
                        BarraNavegacao.setarNavegacao(request, "Lista de Servidores", "ControleServidor?action=listaServidores&pagina=1", null, null);
                        request.setAttribute("mensagem", "Cadastro alterado com sucesso.");
                        request.getRequestDispatcher("ControleServidor?action=listaServidores&pagina=1").forward(request, response);;
                    }
                } catch (Exception e) {
                    request.setAttribute("mensagem", e.getMessage());
                    request.getRequestDispatcher("cadastrarServidor.jsp").forward(request, response);
                }
            } else if (acao.equalsIgnoreCase("editarServidor") || acao.equalsIgnoreCase("visualizarServidor")) {
                InterfaceServidorDAO idao = new ServidorDAO();
                String matricula = request.getParameter("matricula");;
                Servidor s = idao.consultarMatricula(matricula);
                request.setAttribute("matricula", s);
                request.setAttribute("dao", idao);

                if (acao.equalsIgnoreCase("editarServidor")) {
                    BarraNavegacao.setarNavegacao(request, "Lista de Servidores", "ControleServidor?action=listaServidores&pagina=1", "Editar Servidor", "ControleServidor?action=editarServidor&matricula=" + matricula);
                    request.getRequestDispatcher("/formAtualizarServidor.jsp").forward(request, response);
                } else {
                    BarraNavegacao.setarNavegacao(request, "Lista de Servidores", "ControleServidor?action=listaServidores&pagina=1", "Visualizar Servidor", "ControleServidor?action=visualizarServidor&matricula=" + matricula);
                    request.getRequestDispatcher("/formVisualizarServidor.jsp").forward(request, response);
                }
            } else if (acao.equals("excluirServidor")) {
                try {
                    String matricula = request.getParameter("matricula");
                    if (!Validacoes.ValidarQualUsuarioLogado(user.getMatriculaSIAPE(), matricula)) {
                        throw new Exception(Validacoes.getMensagemErro());
                    }
                    InterfaceServidorDAO idao = new ServidorDAO();
                    Servidor servidor = idao.consultarMatricula(matricula);
                    idao.excluir(servidor);

                    request.setAttribute("mensagem", "Cadastro excluído com sucesso.");
                    request.getRequestDispatcher("ControleServidor?action=listaServidores&pagina=1").forward(request, response);;
                } catch (ConstraintViolationException e) {
                    request.setAttribute("mensagem", "Não foi possível excluir. Há reservas associadas.");
                    request.getRequestDispatcher("ControleServidor?action=listaServidores&pagina=1").forward(request, response);;
                }
            } else if (acao.equals("listaServidores")) {// Parte de consulta ###################################################
                try {
                    String numPagina = request.getParameter("pagina");
                    
                    InterfaceServidorDAO sdao = new ServidorDAO();
                    List<Servidor> lista = sdao.todosServidores(numPagina);
                    
                    //Início Paginação
                    int totalRegistros = sdao.todosServidoresCount();
                    int totalPaginas = totalRegistros / 10;
                    if(totalRegistros % 10 != 0){
                        totalPaginas++;
                    }
                    //Fim Paginação

                    BarraNavegacao.setarNavegacao(request, "Lista de Servidores", "ControleServidor?action=listaServidores&pagina=1", null, null);
                    request.setAttribute("totalRegistros", totalRegistros);
                    request.setAttribute("totalPaginas", totalPaginas);
                    request.setAttribute("listaserv", lista);
                    request.getRequestDispatcher("listaServidores.jsp").forward(request, response);
                } catch (Exception e) {
                    request.setAttribute("mensagem", e.getMessage());
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
            } else if (acao.equals("formAlterarSenha")) {
                BarraNavegacao.setarNavegacao(request, "Alterar Senha", "ControleServidor?action=formAlterarSenha", null, null);
                request.getRequestDispatcher("alterarSenha.jsp").forward(request, response);

                /* Parte especifica de parametros para consulta*/
            } else if (acao.equals("consultarServidor")) {
                try {

                    String nome = request.getParameter("nome");
                    String matricula_siape = request.getParameter("MatriculaSiape");
                    String motorista = request.getParameter("motorista");
                    String status = request.getParameter("status_serv");
                    InterfaceServidorDAO isdao = new ServidorDAO();

                    if (nome.equals("") && matricula_siape.equals("") && motorista == null && status == null) {
                        request.setAttribute("mensagem", "Não foram informados dados para a consulta.");
                        request.getRequestDispatcher("ControleServidor?action=listaServidores&pagina=1").forward(request, response);

                    } else if (!nome.equals("") && matricula_siape.equals("") && motorista == null && status == null) {
                        List<Servidor> lista = isdao.buscarServidorPorNome(nome);
                        if (lista.isEmpty()) {
                            request.setAttribute("mensagem", "Não foram encontrados resultados para esta consulta");
                            request.getRequestDispatcher("ControleServidor?action=listaServidores&pagina=1").forward(request, response);
                        } else {
                            request.setAttribute("listaserv", lista);
                            request.getRequestDispatcher("listaServidores.jsp").forward(request, response);
                        }

                    } else if (nome.equals("") && !matricula_siape.equals("") && motorista == null && status == null) {
                        List<Servidor> lista = isdao.buscarServidorPorMatricula(matricula_siape);
                        if (lista.isEmpty()) {
                            request.setAttribute("mensagem", "Não foram encontrados resultados para esta consulta");
                            request.getRequestDispatcher("ControleServidor?action=listaServidores&pagina=1").forward(request, response);
                        } else {
                            request.setAttribute("listaserv", lista);
                            request.getRequestDispatcher("listaServidores.jsp").forward(request, response);
                        }

                    } else if (nome.equals("") && matricula_siape.equals("") && motorista != null && status == null) {
                        List<Servidor> lista = isdao.buscarServidorPorMotorista(motorista);
                        if (lista.isEmpty()) {
                            request.setAttribute("mensagem", "Não foram encontrados resultados para esta consulta");
                            request.getRequestDispatcher("ControleServidor?action=listaServidores&pagina=1").forward(request, response);
                        } else {
                            request.setAttribute("listaserv", lista);
                            request.getRequestDispatcher("listaServidores.jsp").forward(request, response);
                        }

                    } else if (nome.equals("") && matricula_siape.equals("") && motorista == null && status != null) {
                        List<Servidor> lista = isdao.buscarServidorPorStatus(status);
                        if (lista.isEmpty()) {
                            request.setAttribute("mensagem", "Não foram encontrados resultados para esta consulta");
                            request.getRequestDispatcher("ControleServidor?action=listaServidores&pagina=1").forward(request, response);
                        } else {
                            request.setAttribute("listaserv", lista);
                            request.getRequestDispatcher("listaServidores.jsp").forward(request, response);
                        }
                    } else if (!nome.equals("") && !matricula_siape.equals("") && motorista == null && status == null) {
                        List<Servidor> lista = isdao.buscarServidorPorNomeMatricula(nome, matricula_siape);
                        if (lista.isEmpty()) {
                            request.setAttribute("mensagem", "Não foram encontrados resultados para esta consulta");
                            request.getRequestDispatcher("ControleServidor?action=listaServidores&pagina=1").forward(request, response);
                        } else {
                            request.setAttribute("listaserv", lista);
                            request.getRequestDispatcher("listaServidores.jsp").forward(request, response);
                        }
                    } else if (!nome.equals("") && matricula_siape.equals("") && motorista != null && status == null) {
                        List<Servidor> lista = isdao.buscarServidorPorNomeMotorista(nome, motorista);
                        if (lista.isEmpty()) {
                            request.setAttribute("mensagem", "Não foram encontrados resultados para esta consulta");
                            request.getRequestDispatcher("ControleServidor?action=listaServidores&pagina=1").forward(request, response);
                        } else {
                            request.setAttribute("listaserv", lista);
                            request.getRequestDispatcher("listaServidores.jsp").forward(request, response);
                        }
                    } else if (!nome.equals("") && matricula_siape.equals("") && motorista == null && status != null) {
                        List<Servidor> lista = isdao.buscarServidorPorNomeStatus(nome, status);
                        if (lista.isEmpty()) {
                            request.setAttribute("mensagem", "Não foram encontrados resultados para esta consulta");
                            request.getRequestDispatcher("ControleServidor?action=listaServidores&pagina=1").forward(request, response);
                        } else {
                            request.setAttribute("listaserv", lista);
                            request.getRequestDispatcher("listaServidores.jsp").forward(request, response);
                        }
                    } else if (nome.equals("") && !matricula_siape.equals("") && motorista != null && status == null) {
                        List<Servidor> lista = isdao.buscarServidorPorMatriculaMotorista(matricula_siape, motorista);
                        if (lista.isEmpty()) {
                            request.setAttribute("mensagem", "Não foram encontrados resultados para esta consulta");
                            request.getRequestDispatcher("ControleServidor?action=listaServidores&pagina=1").forward(request, response);
                        } else {
                            request.setAttribute("listaserv", lista);
                            request.getRequestDispatcher("listaServidores.jsp").forward(request, response);
                        }
                    } else if (nome.equals("") && !matricula_siape.equals("") && motorista == null && status != null) {
                        List<Servidor> lista = isdao.buscarServidorPorMatriculaStatus(matricula_siape, status);
                        if (lista.isEmpty()) {
                            request.setAttribute("mensagem", "Não foram encontrados resultados para esta consulta");
                            request.getRequestDispatcher("ControleServidor?action=listaServidores&pagina=1").forward(request, response);
                        } else {
                            request.setAttribute("listaserv", lista);
                            request.getRequestDispatcher("listaServidores.jsp").forward(request, response);
                        }
                    } else if (nome.equals("") && matricula_siape.equals("") && motorista != null && status != null) {
                        List<Servidor> lista = isdao.buscarServidorPorMotoristaStatus(motorista, status);
                        if (lista.isEmpty()) {
                            request.setAttribute("mensagem", "Não foram encontrados resultados para esta consulta");
                            request.getRequestDispatcher("ControleServidor?action=listaServidores&pagina=1").forward(request, response);
                        } else {
                            request.setAttribute("listaserv", lista);
                            request.getRequestDispatcher("listaServidores.jsp").forward(request, response);
                        }
                    } else if (!nome.equals("") && !matricula_siape.equals("") && motorista != null && status == null) {
                        List<Servidor> lista = isdao.buscarServidorPorNomeMatriculaMotorista(nome, matricula_siape, motorista);
                        if (lista.isEmpty()) {
                            request.setAttribute("mensagem", "Não foram encontrados resultados para esta consulta");
                            request.getRequestDispatcher("ControleServidor?action=listaServidores&pagina=1").forward(request, response);
                        } else {
                            request.setAttribute("listaserv", lista);
                            request.getRequestDispatcher("listaServidores.jsp").forward(request, response);
                        }
                    } else if (!nome.equals("") && !matricula_siape.equals("") && motorista == null && status != null) {
                        List<Servidor> lista = isdao.buscarServidorPorNomeMatriculaStatus(nome, matricula_siape, status);
                        if (lista.isEmpty()) {
                            request.setAttribute("mensagem", "Não foram encontrados resultados para esta consulta");
                            request.getRequestDispatcher("ControleServidor?action=listaServidores&pagina=1").forward(request, response);
                        } else {
                            request.setAttribute("listaserv", lista);
                            request.getRequestDispatcher("listaServidores.jsp").forward(request, response);
                        }
                    } else if (!nome.equals("") && matricula_siape.equals("") && motorista != null && status != null) {
                        List<Servidor> lista = isdao.buscarServidorPorNomeMotoristaStatus(nome, motorista, status);
                        if (lista.isEmpty()) {
                            request.setAttribute("mensagem", "Não foram encontrados resultados para esta consulta");
                            request.getRequestDispatcher("ControleServidor?action=listaServidores&pagina=1").forward(request, response);
                        } else {
                            request.setAttribute("listaserv", lista);
                            request.getRequestDispatcher("listaServidores.jsp").forward(request, response);
                        }
                    } else if (nome.equals("") && !matricula_siape.equals("") && motorista != null && status != null) {
                        List<Servidor> lista = isdao.buscarServidorPorMatriculaMotoristaStatus(matricula_siape, motorista, status);
                        if (lista.isEmpty()) {
                            request.setAttribute("mensagem", "Não foram encontrados resultados para esta consulta");
                            request.getRequestDispatcher("ControleServidor?action=listaServidores&pagina=1").forward(request, response);
                        } else {
                            request.setAttribute("listaserv", lista);
                            request.getRequestDispatcher("listaServidores.jsp").forward(request, response);
                        }
                    } else if (!nome.equals("") && !matricula_siape.equals("") && motorista != null && status != null) {
                        List<Servidor> lista = isdao.buscarServidorPorNomeMatriculaMotoristaStatus(nome, matricula_siape, motorista, status);
                        if (lista.isEmpty()) {
                            request.setAttribute("mensagem", "Não foram encontrados resultados para esta consulta");
                            request.getRequestDispatcher("ControleServidor?action=listaServidores&pagina=1").forward(request, response);
                        } else {
                            request.setAttribute("listaserv", lista);
                            request.getRequestDispatcher("listaServidores.jsp").forward(request, response);
                        }
                    }


                } catch (Exception e) {
                    request.setAttribute("mensagem", e.getMessage());
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }

            } else if (acao.equals("alterarSenha")) {
                try {
                    String matriculaSIAPE = user.getMatriculaSIAPE();
                    String senhaAtual = request.getParameter("sSenhaAtual");
                    String novaSenha = request.getParameter("sNovaSenha");
                    String confirmaSenha = request.getParameter("sConfirmaSenha");
                    //VALIDAÇÃO SENHA ATUAL == NOVA SENHA
                    if (senhaAtual.equals(user.getSenha())) {
                        if (novaSenha.equals(confirmaSenha)) {
                            InterfaceServidorDAO idao = new ServidorDAO();
                            idao.alterarSenha(matriculaSIAPE, novaSenha);

                            Servidor f = (Servidor) idao.buscarServidor(matriculaSIAPE);

                            if (f.getPerfil() == 0) {
                                request.getSession().setAttribute("servidor", f);
                            } else if (f.getPerfil() == 1) {
                                request.getSession().setAttribute("administrador", f);
                            }

                            request.setAttribute("mensagem", "Senha alterada com sucesso.");
                            request.getRequestDispatcher("ControleReserva?action=listaReservas").forward(request, response);
                        }
                        //VALIDAÇÃO SENHA ATUAL != NOVA SENHA
                    } else {
                        request.setAttribute("mensagem", "Não foi possível efetuar a operação. Senha atual incorreta.");
                        request.getRequestDispatcher("alterarSenha.jsp").forward(request, response);
                    }
                } catch (Exception e) {
                    request.setAttribute("mensagem", e.getMessage());
                    request.getRequestDispatcher("alterarSenha.jsp").forward(request, response);
                }

            } else if (acao.equals("novoServidor")) {
                try {
                     BarraNavegacao.setarNavegacao(request, "Novo Servidor", "ControleServidor?action=novoServidor", null, null);
                     request.getRequestDispatcher("cadastrarServidor.jsp").forward(request, response);
                } catch (Exception e) {
                    request.setAttribute("mensagem", e.getMessage());
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
            }
        } catch (Exception e) {
            request.setAttribute("mensagem", e.getMessage());
            request.getRequestDispatcher("ControleServidor?action=listaServidores&pagina=1").forward(request, response);;
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
