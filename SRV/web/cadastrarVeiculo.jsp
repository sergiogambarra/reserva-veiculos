<%-- 
    Document   : cadastrarVeiculo
    Created on : 24/04/2014, 23:48:25
    Author     : Paula
--%>

<%@include file="ValidarLoginAdministrador.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Novo Veículo</title>
        <link rel="stylesheet" type="text/css" href="css/styleLogin.css" media="all"  />
        <link rel="stylesheet" type="text/css" href="css/styleLogin.css" title="default" media="all"  />
        <link rel="stylesheet" type="text/css" href="css/styleLoginContraste.css" title="contraste" media="all"  />
        <link rel="stylesheet" type="text/css" href="css/styleContent.css" media="all"  />
        <link rel="stylesheet" type="text/css" href="css/styleContent.css" title="default" media="all"  />
        <link rel="stylesheet" type="text/css" href="css/styleContraste.css" title="contraste" media="all"  />
        <link rel="stylesheet" href="css/jquery.datetimepicker.css" type="text/css" />
        
        <script type="text/javascript" type="text/javascript" src="js/contraste.js"></script>
        <script type="text/javascript" type="text/javascript" src="js/validacoesJs.js"></script>
        <script type="text/javascript" type="text/javascript" src="js/validacoesMascara.js"></script>
        <script type="text/javascript" type="text/javascript" src="js/jsAcessibilidade.js"></script>
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/jquery.datetimepicker.js"></script>
        <script type="text/javascript" src="js/calendarios.js"></script>

    </head>
    <body id="corpo" onload="pegarCookies();">
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
                            <h2>Cadastrar Novo Veículo</h2>
                            <div class="camposObrigatorios">
                                *Campos obrigatórios
                            </div>
                            <form action="ControleVeiculo" name="formCadastroVeiculo">
                                <input type="hidden" name="action" value="cadastrarVeiculo"/>
                                <div class="formularioCadastrarServidorBox">
                                    <ul>
                                        <li>
                                            <div class="formCadastroLabel"><label for="iPlaca"><img src="imagens/asterisco.png" alt="Campo obrigatório"/>Placa</label> </div>
                                            <div class="formCadastroInput">
                                                <input type="text" id="iPlaca" name="iPlaca" placeholder="XXX-0000"  pattern="[A-Z]{3}-[0-9]{4}" maxlength="8" style="text-transform: uppercase"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="iAno"><img src="imagens/asterisco.png" alt="Campo obrigatório"/>Ano</label> </div>
                                            <div class="formCadastroInput">
                                                <input type="text" id="iAno" name="iAno" placeholder="ANO" onKeyPress="return mascaraMatSiap(event);" maxlength="4"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="iMarca"><img src="imagens/asterisco.png" alt="Campo obrigatório"/>Marca</label> </div>
                                            <div class="formCadastroInput">
                                                <input type="text" id="iMarca" name="iMarca" placeholder="MARCA" onKeyPress="return mascaraLetras(event);" maxlength="15"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="iModelo"><img src="imagens/asterisco.png" alt="Campo obrigatório"/>Modelo</label> </div>
                                            <div class="formCadastroInput">
                                                <input type="text" id="iModelo" name="iModelo" placeholder="MODELO" maxlength="25"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel">
                                                <label for="sCombustivel"><img src="imagens/asterisco.png" alt="Campo obrigatório"/>Combustível</label> </div>
                                            <div class="formCadastroInput">
                                                <input type="radio" id="gasolina" name="combustivel" value="g"/>
                                                <label class="radioManutencao" for="sCombustivel">Gasolina</label>
                                                <input type="radio" id="alcool" name="combustivel" value="a"/>
                                                <label class="radioManutencao" for="sCombustivel">Álcool</label>
                                                <input type="radio" id="diesel" name="combustivel" value="c"/>
                                                <label class="radioManutencao" for="sCombustivel">Diesel</label>
                                                <input type="radio" id="gnv" name="combustivel" value="n"/>
                                                <label class="radioManutencao" for="sCombustivel">GNV</label>
                                                <input type="radio" id="flex" name="combustivel" value="f"/>
                                                <label class="radioManutencao" for="sCombustivel">Flex</label>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="iRenavam"><img src="imagens/asterisco.png" alt="Campo obrigatório"/>Renavam</label> </div>
                                            <div class="formCadastroInput">
                                                <input type="text" id="iRenavam" name="iRenavam" placeholder="RENAVAM" onKeyPress="return mascaraMatSiap(event);" maxlength="11"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="iCapacidade"><img src="imagens/asterisco.png" alt="Campo obrigatório"/>Capacidade</label> </div>
                                            <div class="formCadastroInput">
                                                <input type="text" id="iCapacidade" name="iCapacidade" onKeyPress="return mascaraMatSiap(event);" value="5" maxlength="2">
                                            </div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel">
                                                <label for="Manutencao" ><img src="imagens/asterisco.png" alt="Campo obrigatório"/>Manutenção</label>
                                            </div>
                                            <div class="formCadastroInput">
                                                <input type="radio" id="manutencaoS" name="manutencao" value="t" onClick="validarManutencao(this.value);"/>
                                                <label class="radioManutencao" for="manutencao" >Sim</label>
                                                <input type="radio" id="manutencaoN" checked name="manutencao" value="f" onClick="validarManutencao(this.value);"/>
                                                <label class="radioManutencao" for="Manutencao" >Não</label>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="sManDataInicial">Data Inicial</label> </div>
                                            <div class="formCadastroInput">
                                                <input type="text" name="sManDataInicial" placeholder="aaaa-mm-dd" id="sManDataInicial" disabled onKeyPress="return mascaraData(event);" maxlength="10"/>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="sManDataFinal">Data Final</label> </div>
                                            <div class="formCadastroInput">
                                                <input type="text" name="sManDataFinal" placeholder="aaaa-mm-dd" id="sManDataFinal" disabled onKeyPress="return mascaraData(event);" maxlength="10"/>
                                            </div>
                                        </li>
                                        <li class="formBotoes">
                                            <div class="formCadastroInputCancelar"><input type="button" value="Cancelar" onclick="window.location = ('ControleVeiculo?action=listaVeiculos')"/></div>
                                            <div class="formCadastroInputLimpar"><input type="reset" value="Limpar"/></div>
                                            <div class="formCadastroInputSalvar"><input type="submit" value="Salvar" onclick="return validarVeiculo()"/></div>
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
