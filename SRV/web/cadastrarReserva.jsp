<%-- 
    Document   : cadastrarReserva
    Created on : 20/05/2014, 10:01:57
    Author     : Douglas
--%>

<%@page import="srv.modelo.Destino"%>
<%@page import="srv.modelo.Veiculo"%>
<%@page import="srv.modelo.Servidor"%>
<%@page import="java.util.List"%>
<%@include file="ValidarLogin.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Novo Veículo</title>
        <link rel="stylesheet" href="css/styleLogin.css" type='text/css'>
        <link rel="stylesheet" href="css/styleContent.css" type='text/css'> 
        <script type="text/javascript" type="text/javascript" src="js/validacoesJs.js"></script>
        <!--        <script type="text/javascript" type="text/javascript" src="js/validacoesMascara.js"></script>
                <script type="text/javascript" type="text/javascript" src="cidades-estados-1.0"></script>-->
    </head>
    <body>
        <section class="container">
            <div class="cabecalho">
                <div class="cabecalhoLateral">
                    <%
                        Servidor servidor = new Servidor();
                            if (request.getSession().getAttribute("administrador") != null) {
                                servidor = (Servidor)request.getSession().getAttribute("administrador");
                            }
                        %>
                    <div class="cabecalhoUsuario">Bem vindo, <%= servidor.getNome() %></div>
                    
                    <div class="cabecalhoLogout" id="desl"><a href='ControleLogin?action=deslogar'>Logout</a></div>
                </div>
                <div class="cabecalhoImagem" alt="SRV: Sistema de Reserva de Veículos para controle de frota." title="SRV: Sistema de Reserva de Veículos.">      
                </div>
            </div>
            <div class="containerLogado">
                <%@include file="menu.jsp" %>
                <!-- A próxima div poderia servir para controle de permissões? -->
                <div class="containerLogadoBorda">
                    <div class="containerLogadoDados">
                        <div class="paginaAtual">
                            <table class="tabelaMensagem">
                                <thead>
                                  <td>
                                      <div class="barraNavegacao">
                                          <p>Você está em: 
                                            <script type="text/javascript">
                                                var pagina = document.title;
                                                document.write(pagina);
                                            </script>
                                          </p>
                                      </div>
                                        </td>
                                        <td>
                                            <div class="mensagem">
                                                  <%
                                                    if (request.getAttribute("mensagem") != null) {
                                                  %>
                                                  <p><%= request.getAttribute("mensagem")%> </p>
                                                  <%
                                                             }
                                                  %>
                                            </div>
                                        </td>
                                </thead>
                            </table>
                        </div>
                        <div class="formularioCadastrarServidor">            
                            <h2>Cadastrar Nova Reserva</h2>
                            <div class="camposObrigatorios">
                                *Campos obrigatórios
                            </div>
                            <form action="ControleReserva" name="formInserirReserva" method="POST">
                                <input type="hidden" name="action" value="inserirReserva"/>

                                <%
                                    List<Servidor> lista = (List<Servidor>) request.getAttribute("listaserv");
                                    List<Veiculo> listav = (List<Veiculo>) request.getAttribute("listaveic");
                                    List<Destino> listad = (List<Destino>) request.getAttribute("listadest");
                                %>

                                <div class="formularioCadastrarServidorBox">
                                    <ul>
                                        <li>
                                            <div class="formCadastroLabel">
                                                <label for="iDataSaida">Data de Saída</label>
                                            </div>
                                            <div class="formCadastroInput">
                                                <input type="date" id="inputDataSaida" name="inputDataSaida" />
                                                <label for="iHoraSaida" >Horário de Saída: </label>
                                                <input type="time" id="inputHoraSaida" name="inputHoraSaida" step="1800"/>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel">
                                                <label for="iDataRetorno">Data de Retorno</label>
                                            </div>
                                            <div class="formCadastroInput">
                                                <input type="date" id="inputDataRetorno" name="inputDataRetorno" />
                                                <label for="iHoraRetorno" >Horário de Retorno </label>
                                                <input type="time" id="inputHoraRetorno" name="inputHoraRetorno" step="1800"/>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="iModeloVeiculo">Veículo</label> </div>
                                            <div class="formCadastroInput">
                                                <select id="inputModeloVeiculo" name="inputModeloVeiculo">
                                                    <option value="">Modelo:</option>
                                                    <%
                                                        for (int i = 0; i < listav.size(); i++) {
                                                    %>
                                                    <option value="<%= listav.get(i).getPlaca() %>">
                                                        <%= listav.get(i).getModelo() %>
                                                    </option>
                                                    <%
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel">
                                                <label for="inputMotorista">*Sou o Motorista</label> 
                                            </div>
                                            <div class="formCadastroInput">
                                                <label class="radioMotorista" for="inputMotorista" >Sim</label>
                                                <input type="radio" id="inputMotorista" name="inputMotorista" value="1" onchange="trocarMotorista(this.value);" checked/>
                                                <label class="radioMotorista" for="bMotorista">Não</label>
                                                <input type="radio" id="inputMotorista" name="inputMotorista" value="0" onchange="trocarMotorista(this.value);"/>
                                            </div>
                                        </li>
                                        <div id="selecaoOutroMotorista" class="invisivel">
                                            <li >
                                                <div class="formCadastroLabel"><label for="inputOutroMotorista">Motorista</label> </div>
                                                <div class="formCadastroInput">
                                                    <select id="inputOutroMotorista" name="inputOutroMotorista">
                                                        <option value="">Selecione:</option>
                                                        <%
                                                            for (int i = 0; i < lista.size(); i++) {
                                                        %>
                                                        <option value="<%= lista.get(i).getMatriculaSIAPE() %>">
                                                            <%= lista.get(i).getNome() %>
                                                        </option>
                                                        <%
                                                            }
                                                        %>
                                                    </select>
                                                </div>
                                            </li>
                                        </div>
                                        <li>
                                            <div class="formCadastroLabel"><label for="iCapacidade">Capacidade</label> </div>
                                            <div class="formCadastroInput">
                                                <input type="number" id="iCapacidade" name="iCapacidade" value="5" min="1" max="50">
                                            </div>
                                        </li>
                                        <li>
                                             <div class="formCadastroLabel"><label for="iDestino">Destino</label> </div>
                                            <div class="formCadastroInput">
                                                <select id="iDestino" name="inputDestino" onchange="exibirDescricaoDestino(this.value);">
                                                    <option value="">Destino:</option>
                                                    <%
                                                        for (int i = 0; i < listad.size(); i++) {
                                                            if(i == 0){
                                                                continue;
                                                            }if(i == (listad.size() - 1)){
                                                    %>
                                                    <option value="<%= listad.get(i).getId_destino() %>">
                                                        <%= listad.get(i).getNome() %>
                                                    </option>
                                                    <option value="<%= listad.get(0).getId_destino() %>">
                                                        <%= listad.get(0).getNome() %>
                                                    </option>
                                                    <%
                                                        break;
                                                            }else{
                                                    %>
                                                    <option value="<%= listad.get(i).getId_destino() %>">
                                                        <%= listad.get(i).getNome() %>
                                                    </option>
                                                    <%
                                                        }
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                        </li>
                                        <div id="complementoDestino" class="invisivel">
                                            <li class="liTextArea">
                                                <div class="formCadastroLabel"><label for="sInfoComplementar">Destino: </label> </div>
                                                <div class="formCadastroInput">
                                                    <input type="text" name="inputDestinoComplementar" id="inputDestinoComplementar" maxlength="45" size="55" placeholder="Informações complementares"/>
                                                </div>
                                            </li>
                                        </div>
                                        <li class="formBotoes">
                                            <div class="formCadastroInputCancelar"><input type="submit" value="Cancelar"/></div>
                                            <div class="formCadastroInputLimpar"><input type="reset" value="Limpar"/></div>
                                            <div class="formCadastroInputSalvar"><input type="submit" value="Salvar"/></div>
                                        </li>
                                    </ul>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <section class="about">
            <p class="aboutLinks">
                &copy; 2014&ndash;2014 
                <a href="http://restinga.ifrs.edu.br" target="_parent">ADS 5º semestre 2014 - IFRS Campus Restinga</a>
            </p>
            <!-- <a href="http://www.cssflow.com/mit-license" target="_blank">MIT License</a><br> -->
        </section>
    </body>
</html>
