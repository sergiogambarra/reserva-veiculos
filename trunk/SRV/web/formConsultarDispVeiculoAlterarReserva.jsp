<%-- 
    Document   : cadastrarReserva
    Created on : 17/06/2014, 09:34:42
    Author     : paula
--%>

<%@page import="srv.modelo.Destino"%>
<%@page import="srv.modelo.Veiculo"%>
<%@page import="srv.modelo.Servidor"%>
<%@page import="java.util.List"%>
<%@page import="srv.modelo.Reserva"%>
<%@include file="ValidarLoginAdministrador.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Consulta Veículo</title>
        <link rel="stylesheet" href="css/styleLogin.css" type='text/css'>
        <link rel="stylesheet" href="css/styleContent.css" type='text/css'> 
        <script type="text/javascript" type="text/javascript" src="js/validacoesMascara.js"></script>
        <script type="text/javascript" type="text/javascript" src="js/validacoesJs.js"></script>
        <script type="text/javascript" type="text/javascript" src="js/ajaxDisponibilidadeVeiculo.js"></script>
        
    </head>
    <body onload="init()">
        <section class="container">
            <div class="cabecalho">
                <div class="cabecalhoLateral">
                    <%
                        Servidor servidor = new Servidor();
                        if (request.getSession().getAttribute("administrador") != null) {
                            servidor = (Servidor) request.getSession().getAttribute("administrador");
                        }
                        String id_reserva = request.getAttribute("id_reserva").toString();
                    %>
                    <div class="cabecalhoUsuario">Bem vindo, <%= servidor.getNome()%></div>

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
                            <h2>Consultar Disponibilidade de Veículos</h2>
                            <div class="camposObrigatorios">
                                *Campos obrigatórios
                            </div>
                            <form action="veiculosdisponiveis" method="POST" id="formConsDispVeiculo" name="formConsDispVeiculo" onsubmit="return filtrarVeiculos(this);">
                                <input type="hidden" id="id_reserva" name="id_reserva" value="<%= id_reserva%>"/>
                                <div class="formularioCadastrarServidorBox">
                                    <ul>
                                        <li>
                                            <div class="formCadastroLabel"> 
                                                <label for="iDataSaida">*Data de Saída</label>
                                            </div>
                                            <div class="formCadastroInput"> 
                                                <input type="date" id="inputDataSaida" name="inputDataSaida" onKeyPress="return mascaraData(event);" maxlength="10"/>
                                                <label for="iHoraSaida" >*Horário de Saída: </label>
                                                <input type="time" id="inputHoraSaida" name="inputHoraSaida" step="1800"/>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"> 
                                                <label for="iDataRetorno">*Data de Retorno</label>
                                            </div> 
                                            <div class="formCadastroInput"> 
                                                <input type="date" id="inputDataRetorno" name="inputDataRetorno" onKeyPress="return mascaraData(event);" maxlength="10"/>
                                                <label for="iHoraRetorno" >*Horário de Retorno </label>
                                                <input type="time" id="inputHoraRetorno" name="inputHoraRetorno" step="1800"/>
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
