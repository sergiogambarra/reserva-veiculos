<%@page import="srv.modelo.Destino"%>
<%@page import="srv.modelo.Reserva"%>
<%@page import="java.util.List"%>
<%-- 
    Document   : listaReservas
    Created on : 21/04/2014, 18:38:38
    Author     : Paula
--%>

<%@page import="srv.modelo.Servidor"%>
<%@include file="ValidarLogin.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Lista de Reservas</title>
        <link rel="stylesheet" type="text/css" href="css/styleLogin.css" media="all"  />
        <link rel="stylesheet" type="text/css" href="css/styleLogin.css" title="default" media="all"  />
        <link rel="stylesheet" type="text/css" href="css/styleLoginContraste.css" title="contraste" media="all"  />
        <link rel="stylesheet" type="text/css" href="css/styleContent.css" media="all"  />
        <link rel="stylesheet" type="text/css" href="css/styleContent.css" title="default" media="all"  />
        <link rel="stylesheet" type="text/css" href="css/styleContraste.css" title="contraste" media="all"  />
        <script type="text/javascript" type="text/javascript" src="js/contraste.js"></script>
        <script type="text/javascript" type="text/javascript" src="js/validacoesJs.js"></script>
        <script type="text/javascript" type="text/javascript" src="js/jsAcessibilidade.js"></script>
    </head>
    <body id="corpo" onload="pegarCookies()">
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
                        <div class="filtroData">
                            <!--
                                <form action="#" class="filtroDataFormulario">
                                      <label name="filtroLabelDataInicio">De</label>
                                      <input type="date" name="filtroDataInicio" class="inputDataInicio"/>
                                      <label name="filtroLabelDataRetorno">Até</label>
                                      <input type="date" name="dataInicio" class="inputDataInicio"/>
                                </form>
                            -->
                            <form name="consultaReserva" action="ControleReserva">
                                
                                <input type="hidden" name="action" value="consultarReservas"/>    
 
                                <div class="formFiltroDate">
                                    <label for="DataSaida">Data de Saida</label>
                                    <input type="text" id="DataSaida" name="DataSaida" placeholder="dd/mm/aaaa" maxlength="10"/>
                                </div>
                                
                                <div class="formFiltroDate">
                                    <label for="DataRetorno">Data de Retorno</label>
                                    <input type="text" id="DataRetorno" name="DataRetorno" placeholder="dd/mm/aaaa" maxlength="10"/>
                                </div>
                                <div class="formFiltro">
                                    <label for="destino">Destino</label>
                                    <select id="destino" name="destino">
                                            <option value="">Selecione:</option>
                                            <%
                                            List<Destino> listad = (List<Destino>) request.getAttribute("listadest");
                                                for (int i = 0; i < listad.size(); i++) {
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
                                <div class="formFiltroConsultar"><input type="submit" value="Consultar"/></div>
                            </form>

                        </div>
                        <table summary="Tabela com a lista das minhas reservas." class="tabelaListaVeiculos">
                            <thead>
                            <p>Minhas Reservas</p>
                            <td id="Responsavel" class="colunaDuzentos">Responsável</td>
                            <td id="DataSaida" class="colunaDuzentos">Data de saída</td>
                            <td id="HorarioSaida" class="colunaDuzentos">Horário de saída</td>
                            <td id="Destino" class="colunaDuzentos">Destino</td>
                            <td id="Placa" class="colunaDuzentos">Placa</td>
                            <td id="Modelo" class="colunaDuzentos">Modelo</td>
                            <td id="Acoes" class="colunaAcoesHead" >Ações</td>
                            </thead>
                            <%

                                String dataSaida;
                                String horarioSaida;
                                String nomeDestino;
                                List<Reserva> lista = (List<Reserva>) request.getAttribute("listaReservas");
                                for (int i = 0; i < lista.size(); i++) {
                                    Reserva reserv = lista.get(i);

//                                        data = new SimpleDateFormat("yyyy-MM-dd HH:").parse(reserv.getData_saida());
                                    dataSaida = reserv.getData_saida().toString().substring(8, 10) + "-" + reserv.getData_saida().toString().substring(5, 7) + "-" + reserv.getData_saida().toString().substring(0, 4);
                                    horarioSaida = reserv.getData_saida().toString().substring(11, 13) + ":" + reserv.getData_saida().toString().substring(14, 16);
                                    if(reserv.getDescricao_destino() == null){                                      
                                        nomeDestino = reserv.getDestino().getNome(); 
                                    }else{
                                         nomeDestino = reserv.getDescricao_destino();
                                    }
                            %>
                            <tbody>
                                <tr>
                                    <td headers="Responsavel"><%= reserv.getServidor().getNome()%></td>
                                    <td headers="DataSaida"><%= dataSaida%></td>
                                    <td headers="HorarioSaida"><%= horarioSaida%></td>
                                    <td headers="Destino"><%= nomeDestino %></td>
                                    <td headers="Placa"><%= reserv.getVeiculo().getPlaca()%></td>
                                    <td headers="Modelo"><%= reserv.getVeiculo().getModelo()%></td>
                                    <td headers="Acoes" class="colunaAcoes">
                                        <div class="divColunaAcoes">
                                            <ul>
                                                <li><a href="ControleReserva?action=editarReserva&id_reserva=<%= reserv.getId_reserva() %>" ><div class="iconeEditar" alt="Editar informações da reserva." title="Editar reserva"></div></a></li>
                                                <li><a href="ControleReserva?action=visualizarReserva&id_reserva=<%= reserv.getId_reserva() %>" ><div class="iconeVisualizar" alt="Visualizar informações da reserva." title="Visualizar reserva"></div></a></li>
                                                <li><a href="ControleReserva?action=excluirReserva&reserva=<%= reserv.getId_reserva() %>"><div class="iconeDeletar" alt="Deletar Reserva." title="Deletar Reserva" onclick="return exluirReserva()"></div></a></li>
                                            </ul>
                                        </div>
                                    </td>
                                </tr>
                                <%
                                    }
                                %>
                            <table class="tabelaListaVeiculos" summary="Tabela com a lista das outras reservas.">
                                <thead>
                                <br><br><p>Outras Reservas</p>
                                <td id="Responsavel" class="colunaDuzentos">Responsável</td>
                                <td id="DataSaida" class="colunaDuzentos">Data de saída</td>
                                <td id="HorarioSaida" class="colunaDuzentos">Horário de saída</td>
                                <td id="Destino" class="colunaDuzentos">Destino</td>
                                <td id="Placa" class="colunaDuzentos">Placa</td>
                                <td id="Modelo" class="colunaDuzentos">Modelo</td>
                                <td id="Acoes" class="colunaAcoesHead" >Ações</td>
                                </thead>
                                <%
                    //                                    String nome;
                    //                                    String z;
                                    List<Reserva> listaOutros = (List<Reserva>) request.getAttribute("listaReservasOutros");
                                    for (int i = 0; i < listaOutros.size(); i++) {
                                        Reserva reserv = listaOutros.get(i);
                    //                                        data = new SimpleDateFormat("yyyy-MM-dd HH:").parse(reserv.getData_saida());
                                        dataSaida = reserv.getData_saida().toString().substring(8, 10) + "-" + reserv.getData_saida().toString().substring(5, 7) + "-" + reserv.getData_saida().toString().substring(0, 4);
                                        horarioSaida = reserv.getData_saida().toString().substring(11, 13) + ":" + reserv.getData_saida().toString().substring(14, 16);
                                        if(reserv.getDescricao_destino() == null){                                      
                                            nomeDestino = reserv.getDestino().getNome(); 
                                        }else{
                                            nomeDestino = reserv.getDescricao_destino();
                                        }
                                %>
                                <tbody>
                                    <tr>
                                        <td headers="Responsavel"><%= reserv.getServidor().getNome()%></td>
                                    <td headers="DataSaida"><%= dataSaida%></td>
                                    <td headers="HorarioSaida"><%= horarioSaida%></td>
                                    <td headers="Destino"><%= nomeDestino%></td>
                                    <td headers="Placa"><%= reserv.getVeiculo().getPlaca()%></td>
                                    <td headers="Modelo"><%= reserv.getVeiculo().getModelo()%></td>
                                        <td headers="Acoes" class="colunaAcoes">
                                            <div class="divColunaAcoes">
                                                <ul>
                                                    <%
                                                        if (request.getSession().getAttribute("administrador") != null) {
                                                    %>  
                                                    <li><a href="ControleReserva?action=editarReserva&id_reserva=<%= reserv.getId_reserva() %>"><div class="iconeEditar" alt="Editar informações da reserva." title="Editar reserva"></div></a></li>
                                                    <%                            }
                                                    %>
                                                    <li><a href="ControleReserva?action=visualizarReserva&id_reserva=<%= reserv.getId_reserva() %>"><div class="iconeVisualizar" alt="Visualizar informações da reserva." title="Visualizar reserva"></div></a></li>
                                                    <%
                                                        if (request.getSession().getAttribute("administrador") != null) {
                                                    %>  
                                                    <li><a href="ControleReserva?action=excluirReserva&reserva=<%= reserv.getId_reserva() %>"><div class="iconeDeletar" alt="Deletar Reserva." title="Deletar Reserva" onclick="return exluirReserva()"></div></a></li>
                                                    <%                            }
                                                    %>

                                                </ul>
                                            </div>
                                        </td>
                                    </tr>
                                    <%
                                        }
                                    %>
                                </tbody>
                            </table>

                            </tbody>
                        </table>
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
