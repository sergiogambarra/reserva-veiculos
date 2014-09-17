<%-- 
    Document   : atualizarReserva
    Created on : 07/06/2014, 22:32:12
    Author     : paula
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="srv.modelo.Reserva"%>
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
        <title>Alterar Reserva</title>
<link rel="stylesheet" type="text/css" href="css/styleLogin.css" media="all"  />
        <link rel="stylesheet" type="text/css" href="css/styleLogin.css" title="default" media="all"  />
        <link rel="stylesheet" type="text/css" href="css/styleLoginContraste.css" title="contraste" media="all"  />
        <link rel="stylesheet" type="text/css" href="css/styleContent.css" media="all"  />
        <link rel="stylesheet" type="text/css" href="css/styleContent.css" title="default" media="all"  />
        <link rel="stylesheet" type="text/css" href="css/styleContraste.css" title="contraste" media="all"  />
        <script type="text/javascript" type="text/javascript" src="js/contraste.js"></script>
        <script type="text/javascript" type="text/javascript" src="js/validacoesJs.js"></script>
        <script type="text/javascript" type="text/javascript" src="js/validacoesMascara.js"></script>
        <script type="text/javascript" type="text/javascript" src="js/jsAcessibilidade.js"></script>
    </head>
    <body id="corpo" onload="funcoesOnloadReserva();">
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
                            <h2>Alterar Reserva</h2>
<!--                            <div class="camposObrigatorios">
                                *Campos obrigatórios
                            </div>-->
                            <%
                                int id_reserva = (((Reserva) request.getAttribute("reserva")).getId_reserva());
                            %>
                            <form action="ControleReserva" name="formInserirReserva" method="POST" onsubmit="return validarReserva();">
                                <input type="hidden" name="action" value="atualizarReserva"/>
                                <input type="hidden" name="id_reserva" value="<%= id_reserva%>"/>
                                <%
                                    List<Servidor> lista = (List<Servidor>) request.getAttribute("listaserv");
                                    List<Destino> listad = (List<Destino>) request.getAttribute("listadest");
                                %>

                                <%

                                    String dataSaida;
                                    String horarioSaida;
                                    String dataRetorno;
                                    String horarioRetorno;
                                    String placa;
                                    String modelo;

                                    Reserva reserv = ((Reserva) request.getAttribute("reserva"));
                                    Veiculo veiculo = null;
                                    if (request.getAttribute("veiculo") != null) {
                                        veiculo = (Veiculo) request.getAttribute("veiculo");
                                        placa = veiculo.getPlaca();
                                        modelo = veiculo.getModelo();
                                    } else {
                                        placa = reserv.getVeiculo().getPlaca();
                                        modelo = reserv.getVeiculo().getModelo();
                                    }

                                    int id_destino = reserv.getDestino().getId_destino();
                                    String descricao_reserva = reserv.getDescricao_destino();

                                    String matriculaMotorista = reserv.getMatricula_siape_condutor();

                                    if (request.getAttribute("s_data_saida") != null && request.getAttribute("s_hora_saida") != null
                                            && request.getAttribute("s_data_retorno") != null
                                            && request.getAttribute("s_hora_retorno") != null) {
                                        dataSaida = request.getAttribute("s_data_saida").toString();
                                        horarioSaida = request.getAttribute("s_hora_saida").toString();
                                        dataRetorno = request.getAttribute("s_data_retorno").toString();
                                        horarioRetorno = request.getAttribute("s_hora_retorno").toString();
                                    } else {
                                        dataSaida = reserv.getData_saida().toString().substring(8, 10) 
                                                    + "/" + reserv.getData_saida().toString().substring(5, 7) 
                                                    + "/" + reserv.getData_saida().toString().substring(0, 4);
                                        dataRetorno = reserv.getData_retorno().toString().substring(8, 10)
                                                    + "/" + reserv.getData_retorno().toString().substring(5, 7) 
                                                    + "/" + reserv.getData_retorno().toString().substring(0, 4) ;
                                        horarioSaida = reserv.getData_saida().toString().substring(11, 13) + ":" + reserv.getData_saida().toString().substring(14, 16);
                                        horarioRetorno = reserv.getData_retorno().toString().substring(11, 13) + ":" + reserv.getData_retorno().toString().substring(14, 16);
                                    }

                                    Date data_saida_br = null;
                                    Date data_retorno_br = null;
                                    String data_saida = null;
                                    String data_retorno = null;

                                    try {
                                        data_saida_br = new SimpleDateFormat("dd/MM/yyyy").parse(dataSaida);
                                        data_saida = new SimpleDateFormat("yyyy-MM-dd").format(data_saida_br);

                                        data_retorno_br = new SimpleDateFormat("dd/MM/yyyy").parse(dataRetorno);
                                        data_retorno = new SimpleDateFormat("yyyy-MM-dd").format(data_retorno_br);
                                    } catch (java.text.ParseException e) {
                                        e.getMessage();
                                    }

                                %>

                                <div class="formularioCadastrarServidorBox">
                                    <ul>
                                        <li>
                                            <div class="formCadastroLabel">
                                                <label for="iDataSaida">Data de Saída</label>
                                            </div>
                                            <div class="formCadastroInput">
                                                <input type="text" id="inputDataSaida" name="inputDataSaida" value="<%= dataSaida%>" readonly="true"/>
                                                <label for="iHoraSaida" >Horário</label>
                                                <input type="text" id="inputHoraSaida" name="inputHoraSaida"  value="<%= horarioSaida%>" readonly="true"/>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel">
                                                <label for="iDataRetorno">Data de Retorno</label>
                                            </div>
                                            <div class="formCadastroInput">
                                                <input type="text" id="inputDataRetorno" name="inputDataRetorno" value="<%= dataRetorno%>" readonly="true"/>
                                                <label for="iHoraRetorno" >Horário</label>
                                                <input type="text" id="inputHoraRetorno" name="inputHoraRetorno"   value="<%= horarioRetorno%>" readonly="true"/>
                                            </div>                                                
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="iModeloVeiculo">Veículo</label> </div>
                                            <div class="formCadastroInput">
                                                <input type="text" id="inputModeloVeiculo" name="inputModeloVeiculo" value="<%= modelo%>" readonly="true">
                                                <div id="consultarDispon">
                                                    <a href="ControleReserva?action=consultarDispVeiculo&id_reserva=<%= id_reserva%>">Consultar Novo Veículo</a>
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel">
                                                <label for="iPlacaVeiculo">Placa</label>
                                            </div>
                                            <div class="formCadastroInput">
                                                <input type="text" id="inputPlacaVeiculo" name="inputPlacaVeiculo" maxlength="7" value="<%= placa%>" readonly="true"/>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel">
                                                <label for="inputMotorista"><img src="imagens/asterisco.png" alt="Campo obrigatório"/>Sou o Motorista</label> 
                                            </div>
                                            <div class="formCadastroInput">
                                                <label class="radioMotorista" for="inputMotorista" >Sim</label>
                                                <input type="radio" id="inputMotorista" name="inputMotorista" value="1" onchange="trocarMotorista(this.value);"
                                                       <% if (((Reserva) request.getAttribute("reserva")).getCondutor()) {%>checked<% }%>
                                                       />
                                                <label class="radioMotorista" for="bMotorista">Não</label>
                                                <input type="radio" id="inputMotorista" name="inputMotorista" value="0" onchange="trocarMotorista(this.value);"
                                                       <% if (!((Reserva) request.getAttribute("reserva")).getCondutor()) {%>checked<% }%>
                                                       />
                                            </div>
                                        </li>
                                        <div id="selecaoOutroMotorista" class="invisivel">
                                            <li >
                                                <div class="formCadastroLabel"><label for="inputOutroMotorista"><img src="imagens/asterisco.png" alt="Campo obrigatório"/>Motorista</label> </div>
                                                <div class="formCadastroInput">
                                                    <select id="inputOutroMotorista" name="inputOutroMotorista">
                                                        <option value="">Selecione</option>
                                                        <%
                                                            for (int i = 0; i < lista.size(); i++) {
                                                                if (matriculaMotorista.equals(lista.get(i).getMatriculaSIAPE())) {
                                                        %>
                                                        <option value="<%= matriculaMotorista%>" selected><%= lista.get(i).getNome()%></option>
                                                        <%
                                                                lista.get(i).setMatriculaSIAPE("");
                                                            }
                                                            if (!lista.get(i).getMatriculaSIAPE().equals("")) {
                                                        %>
                                                        <option value="<%= lista.get(i).getMatriculaSIAPE()%>">
                                                            <%= lista.get(i).getNome()%>
                                                        </option>
                                                        <%
                                                                }
                                                            }
                                                        %>
                                                    </select>
                                                </div>
                                            </li>
                                        </div>

                                        <li>
                                            <div class="formCadastroLabel"><label for="iOcupantes"><img src="imagens/asterisco.png" alt="Campo obrigatório"/>Número de Ocupantes</label> </div>
                                            <div class="formCadastroInput">
                                                <input type="number" id="iOcupantes" name="iOcupantes" value="${reserva.ocupantes}" min="1" max="${reserva.veiculo.capacidade}">
                                            </div>
                                        </li>

                                        <li>
                                            <div class="formCadastroLabel"><label for="iDestino"><img src="imagens/asterisco.png" alt="Campo obrigatório"/>Destino</label> </div>
                                            <div class="formCadastroInput">
                                                <select id="iDestino" name="inputDestino" onchange="exibirDescricaoDestino(this.value);">
                                                    <option value="<%= id_destino%>" selected><%= reserv.getDestino().getNome()%></option>
                                                    <%                                                        for (int i = 0;
                                                                i < listad.size();
                                                                i++) {
                                                            if (i == 0) {
                                                                continue;
                                                            }
                                                            if (i == (listad.size() - 1)) {
                                                    %>
                                                    <option value="<%= listad.get(i).getId_destino()%>">
                                                        <%= listad.get(i).getNome()%>
                                                    </option>
                                                    <option value="<%= listad.get(0).getId_destino()%>">
                                                        <%= listad.get(0).getNome()%>
                                                    </option>
                                                    <%
                                                        break;
                                                    } else {
                                                        //Quando for o id do destino selecionado, exluir da lista para não repetir o valor
                                                        if (id_destino == listad.get(i).getId_destino()) {
                                                            listad.remove(i);
                                                        }
                                                    %>
                                                    <option value="<%= listad.get(i).getId_destino()%>">
                                                        <%= listad.get(i).getNome()%>
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
                                                <div class="formCadastroLabel"><label for="sInfoComplementar">Informe o Destino</label> </div>
                                                <div class="formCadastroInput">
                                                    <input type="text" name="inputDestinoComplementar" id="inputDestinoComplementar" maxlength="45" size="55" placeholder="se não constar na lista de destino"
                                                           <%                                                            if (descricao_reserva
                                                                       != null) {
                                                           %>
                                                           value="<%= reserv.getDescricao_destino()%>"
                                                           <% }%>
                                                           />
                                                </div>
                                            </li>
                                        </div>
                                        <li class="formBotoes">
                                            <div class="formCadastroInputCancelar"><input type="button" value="Cancelar" onclick="window.location = ('ControleReserva?action=listaReservas')"/></div>
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
