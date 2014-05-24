<%-- 
    Document   : cadastrarReserva
    Created on : 20/05/2014, 10:01:57
    Author     : Douglas
--%>

<%@page import="srv.modelo.Destino"%>
<%@page import="srv.modelo.Veiculo"%>
<%@page import="srv.modelo.Servidor"%>
<%@page import="java.util.List"%>
<%@include file="ValidarLoginAdministrador.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Novo Veículo</title>
        <link rel="stylesheet" href="css/styleLogin.css" type='text/css'>
        <link rel="stylesheet" href="css/styleContent.css" type='text/css'> 
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
                            Você está em: 
                            <script type="text/javascript">
                                var pagina = document.title;
                                document.write(pagina);
                            </script>
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
                                                <label for="bMotorista">*Motorista</label> </div>
                                            <div class="formCadastroInput">
                                                <label class="radioMotorista" for="bMotorista" >Sim</label>
                                                <input type="radio" id="inputMotorista" name="inputMotorista" value="1" checked/>
                                                <label class="radioMotorista" for="bMotorista">Não</label>
                                                <input type="radio" id="inputMotorista" name="inputMotorista" value="0"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="iCapacidade">Capacidade</label> </div>
                                            <div class="formCadastroInput">
                                                <input type="number" id="iCapacidade" name="iCapacidade" value="5" min="1" max="50">
                                            </div>
                                        </li>
                                        <li>
                                             <div class="formCadastroLabel"><label for="iDestino">Destino</label> </div>
                                            <div class="formCadastroInput">
                                                <select id="iDestino" name="inputDestino">
                                                    <option value="">Destino:</option>
                                                    <%
                                                        for (int i = 0; i < listad.size(); i++) {
                                                    %>
                                                    <option value="<%= listad.get(i).getId_destino() %>">
                                                        <%= listad.get(i).getNome() %>
                                                    </option>
                                                    <%
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                        </li>
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
