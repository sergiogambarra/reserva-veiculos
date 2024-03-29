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
        <title>Consultar Disponibilidade de Veículo</title>
        <link rel="stylesheet" type="text/css" href="css/styleLogin.css" title="default" media="all"  />
        <link rel="stylesheet" type="text/css" href="css/styleLoginContraste.css" title="contraste" media="all"  />
        <link rel="stylesheet" type="text/css" href="css/styleContent.css" title="default" media="all"  />
        <link rel="stylesheet" type="text/css" href="css/styleContraste.css" title="contraste" media="all"  />
        <link rel="stylesheet" href="css/jquery.datetimepicker.css" type="text/css" />
        <script type="text/javascript" type="text/javascript" src="js/contraste.js"></script>
        <script type="text/javascript" type="text/javascript" src="js/validacoesJs.js"></script>
        <script type="text/javascript" type="text/javascript" src="js/validacoesMascara.js"></script>
        <script type="text/javascript" type="text/javascript" src="js/ajaxDisponibilidadeVeiculo.js"></script>
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/jquery.datetimepicker.js"></script>
        <script type="text/javascript" src="js/calendarios.js"></script>
        <script type="text/javascript" type="text/javascript" src="js/jsAcessibilidade.js"></script>
    </head>
    <body id="corpo" onload="init()">
        <section class="container">
            <div class="cabecalho">
                <div class="cabecalhoLateral">
                    <%@include file="cabecalhoNomeUsuario.jsp"%>
                    <div class="cabecalhoLogout" id="desl"><a href='ControleLogin?action=deslogar'>Sair</a>&nbsp;|&nbsp;<a href='ControleServidor?action=formAlterarSenha'>Alterar Senha</a></div>
                </div>
                <div class="cabecalhoImagem" alt="SRV: Sistema de Reserva de Veículos para controle de frota." title="SRV: Sistema de Reserva de Veículos.">      
                </div>
                    <%@include file="menuAtalhos.jsp" %>
            </div>
            <div class="containerLogado">
                <%@include file="menu.jsp" %>
                <div class="containerLogadoBorda">
                    <div class="containerLogadoDados">
                        <div class="paginaAtual">
                            <table class="tabelaMensagem">
                                <thead>
                                <td>
                                    <div class="barraNavegacao">
                                        <%@include file="barraNavegacao.jsp" %>
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
                            <h2>Consultar Disponibilidade de Veículos</h2>
                            <div class="camposObrigatorios">
                                *Campos obrigatórios
                            </div>
                            <form action="veiculosdisponiveis" method="POST" id="formConsDispVeiculo" name="formConsDispVeiculo" onsubmit="return filtrarVeiculos(this);">
                                <input type="hidden" id="id_reserva" name="id_reserva" value=""/>
                                <div class="formularioCadastrarServidorBox">
                                    <ul>
                                        <li>
                                            <div class="formCadastroLabel">
                                                <label for="iDataSaida"><img src="imagens/asterisco.png" alt="Campo obrigatório"/>Data de Saída</label>
                                            </div>
                                            <div class="formCadastroInput">
                                                <input type="text" id="inputDataSaida" name="inputDataSaida" onKeyPress="return mascaraData(event);" maxlength="10"/>
                                                <label for="iHoraSaida" ><img src="imagens/asterisco.png" alt="Campo obrigatório"/>Horário de Saída: </label>
                                                <input type="text" id="inputHoraSaida" name="inputHoraSaida" />
                                            </div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel">
                                                <label for="iDataRetorno"><img src="imagens/asterisco.png" alt="Campo obrigatório"/>Data de Retorno</label>
                                            </div>
                                            <div class="formCadastroInput">
                                                <input type="text" id="inputDataRetorno" name="inputDataRetorno" onKeyPress="return mascaraData(event);" maxlength="10"/>
                                                <label for="iHoraRetorno" ><img src="imagens/asterisco.png" alt="Campo obrigatório"/>Horário de Retorno </label>
                                                <input type="text" id="inputHoraRetorno" name="inputHoraRetorno" onclick="getHoraRetorno()" />
                                            </div>
                                        </li>                                        
                                        <li class="formBotoes">
                                            <div class="formCadastroInputCancelar"><input type="button" value="Cancelar" onclick="window.location = ('ControleReserva?action=listaReservas')"/></div>
                                            <div class="formCadastroInputLimpar"><input type="reset" value="Limpar" onclick="limparTabela()"/></div>
                                            <div class="formCadastroInputSalvar"><input type="submit" value="Consultar"/></div>
                                        </li>
                                    </ul>
                                </div>
                                <div id="msg">   
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
