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
        <link rel="stylesheet" href="css/styleLogin.css" type='text/css'>
        <link rel="stylesheet" href="css/styleContent.css" type='text/css'> 
        <script type="text/javascript" type="text/javascript" src="js/validacoesMascara.js"></script>
        <script type="text/javascript" type="text/javascript" src="js/validacoesJs.js"></script>
<!--    <script type="text/javascript" type="text/javascript" src="cidades-estados-1.0"></script>-->
    </head>
    <body>
        <section class="container">
            <div class="cabecalho">
                <div class="cabecalhoLateral">
                    <div class="cabecalhoUsuario">Bem vindo, Servidor Fulano de Tal</div>
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
                            <h2>Cadastrar Novo Veículo</h2>
                            <div class="camposObrigatorios">
                                *Campos obrigatórios
                            </div>
                            <form action="ControleVeiculo" name="formCadastroVeiculo">
                                <input type="hidden" name="action" value="cadastrarVeiculo"/>
                                <div class="formularioCadastrarServidorBox">
                                    <ul>
                                        <li>
                                            <div class="formCadastroLabel"><label for="iPlaca">* Placa</label> </div>
                                            <div class="formCadastroInput">
                                                <input type="text" id="iPlaca" name="iPlaca" placeholder="PLACA" maxlength="7"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="iAno">* Ano</label> </div>
                                            <div class="formCadastroInput">
                                                <input type="text" id="iAno" name="iAno" placeholder="ANO" maxlength="4"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="iMarca">* Marca</label> </div>
                                            <div class="formCadastroInput">
                                                <input type="text" id="iMarca" name="iMarca" placeholder="MARCA" maxlength="15"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="iModelo">* Modelo</label> </div>
                                            <div class="formCadastroInput">
                                                <input type="text" id="iModelo" name="iModelo" placeholder="MODELO" maxlength="25"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel">
                                                <label for="sCombustivel">* Combustível</label> </div>
                                            <div class="formCadastroInput">
                                                <input type="radio" id="gasolina" name="combustivel" value="g"/>
                                                <label class="radioManutencao" for="sCombustivel">Gasolina</label>
                                                <input type="radio" id="alcool" name="combustivel" value="a"/>
                                                <label class="radioManutencao" for="sCombustivel">Álcool</label>
                                                <input type="radio" id="diesel" name="combustivel" value="c"/>
                                                <label class="radioManutencao" for="sCombustivel">Diesel</label>
                                                <input type="radio" id="gnv" name="combustivel" value="n"/>
                                                <label class="radioManutencao" for="sCombustivel">GNV</label>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="iRenavam">* Renavam</label> </div>
                                            <div class="formCadastroInput">
                                                <input type="text" id="iRenavam" name="iRenavam" placeholder="RENAVAM" maxlength="11"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="iCapacidade">* Capacidade</label> </div>
                                            <div class="formCadastroInput">
                                                <input type="number" id="iCapacidade" name="iCapacidade" value="5" min="1" max="50">
                                            </div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel">
                                                <label for="Manutencao" >* Manutenção</label>
                                            </div>
                                            <div class="formCadastroInput">
                                                <input type="radio" id="manutencaoS" name="manutencao" value="t" onClick="validarManutencao(this.value);"/>
                                                <label class="radioManutencao" for="manutencao" >Sim</label>
                                                <input type="radio" id="manutencaoN" checked name="manutencao" value="f" onClick="validarManutencao(this.value);"/>
                                                <label class="radioManutencao" for="Manutencao" >Não</label>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="sManDataInicial">* Data Inicial (dd-mm-aaaa)</label> </div>
                                            <div class="formCadastroInput"><input type="date" name="sManDataInicial" id="sManDataInicial" disabled/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="sManDataFinal">* Data Final (dd-mm-aaaa)</label> </div>
                                            <div class="formCadastroInput"><input type="date" name="sManDataFinal" id="sManDataFinal" disabled/></div>
                                        </li>
                                        <li class="formBotoes">
                                            <div class="formCadastroInputCancelar"><input type="submit" value="Cancelar" /></div>
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
