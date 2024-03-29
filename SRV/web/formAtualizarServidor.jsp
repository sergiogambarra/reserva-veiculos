
<%@page import="srv.util.Validacoes"%>
<%@page import="srv.dao.InterfaceServidorDAO"%>
<%@page import="java.util.List"%>
<%@page import="srv.modelo.Servidor"%>
<%@include file="ValidarLoginAdministrador.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Dados Servidor</title>
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
        <script type="text/javascript" type="text/javascript" src="cidades-estados-1.0"></script>
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
                            <h2>Editar Servidor</h2>
                            <div class="camposObrigatorios">
                                *Campos obrigatórios
                            </div>
                            <form action="ControleServidor" name="formCadastroServidor">
                                <input type="hidden" name="action" value="atualizarServidor"/>
                                <div class="formularioCadastrarServidorBox">
                                    <ul>
                                        <li>
                                            <div class="formCadastroLabel"><label for="iMatriculaSiape"><img src="imagens/asterisco.png" alt="Campo obrigatório"/>Matrícula SIAPE</label> </div>
                                            <div class="formCadastroInput"><input type="text" id="id" name="iMatriculaSiape" placeholder="SIAPE" value="${matricula.matriculaSIAPE}" maxlength="7" readonly="readonly" onclick="naoAlterarId()"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="sNomeCompleto"><img src="imagens/asterisco.png" alt="Campo obrigatório"/>Nome</label> </div>
                                            <div class="formCadastroInput"><input type="text" id="sNomeCompleto" value="${matricula.nome}" name="sNomeCompleto" placeholder="Nome completo" onKeyPress="return mascaraLetras(event);" maxlength="50"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="sEmail"><img src="imagens/asterisco.png" alt="Campo obrigatório"/>Email</label> </div>
                                            <div class="formCadastroInput"><input type="text" value="${matricula.email}" id="sEmail" name="sEmail" placeholder="Email institucional" maxlength="50"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel">
                                                <label for="sexo"><img src="imagens/asterisco.png" alt="Campo obrigatório"/>Sexo</label>
                                            </div>
                                            <div class="formCadastroInput">
                                                <input type="radio" id="sexom" name="sexo" value="m" 
                                                       <% if (((Servidor) request.getAttribute("matricula")).getSexo().equals("m")) {%>
                                                       checked
                                                       <% }%>   
                                                       />
                                                <label class="radioSexo" for="Sexo">Masculino</label>
                                                <input type="radio" id="sexof" name="sexo" value="f" 
                                                        <% if (((Servidor) request.getAttribute("matricula")).getSexo().equals("f")) {%>
                                                        checked
                                                        <% }%>   
                                                        />
                                                <label class="radioSexo" for="Sexo">Feminino<label>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="sDataNascimento">Data Nascimento</label> </div>
                                            <%
                                                Servidor s = (Servidor)request.getAttribute("matricula");
                                                System.out.println("DN: " + s.getData_nascimento());
                                                String sDataNascimento = Validacoes.extrairDateParaString(s.getData_nascimento());
                                                System.out.println("DN: " + sDataNascimento);
                                            %>
                                            <div class="formCadastroInput">
                                                <input type="text" id="sDataNascimento" name="sDataNascimento"  maxlength="10" value ="<%= sDataNascimento %>" onKeyPress="return mascaraData(event);"/>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="sCpf"><img src="imagens/asterisco.png" alt="Campo obrigatório"/>CPF</label> </div>
                                            <div class="formCadastroInput"><input type="text" value="${matricula.cpf}" id="sCpf" name="sCpf" placeholder="CPF" onKeyPress="return mascaraCpf(event)" onKeyPress="return mascaraMatSiap(event);" maxlength="14"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="sRg"><img src="imagens/asterisco.png" alt="Campo obrigatório"/>RG</label> </div>
                                            <div class="formCadastroInput"><input type="text" value="${matricula.rg}" id="sRg" name="sRg" placeholder="Identidade" maxlength="15"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="sOrgaoExpedidor"><img src="imagens/asterisco.png" alt="Campo obrigatório"/>Orgão Expedidor</label> </div>
                                            <div class="formCadastroInput"><input type="text" value="${matricula.orgao_expedidor}" id="sOrgaoExpedidor" name="sOrgaoExpedidor" placeholder="Orgão expedidor" maxlength="10"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="sNaturalidade">Naturalidade</label> </div>
                                            <div class="formCadastroInput"><input type="text" value="${matricula.naturalidade}" id="sNaturalidade" name="sNaturalidade" placeholder="Cidade de nascimento" onKeyPress="return mascaraLetras(event);" maxlength="30"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel">
                                                <label for="sUf" id="estado" value="${matricula.estado}">UF</label>
                                            </div>
                                            <div class="formCadastroInput">
                                                <select id="estado" name="estado"/>
                                                <option value="0">Selecione o Estado</option>
                                                <%
                                                    List<String> listEstados = ((InterfaceServidorDAO) request.getAttribute("dao")).editarServidorSelecionarEstado(((Servidor) request.getAttribute("matricula")).getEstado());
                                                    for (int i = 0; i < listEstados.size(); i++) {
                                                %><%= listEstados.get(i)%><%
                                                    }
                                                %>
                                                </select>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="sNacionalidade">Nacionalidade</label> </div>
                                            <div class="formCadastroInput"><input type="text" value="${matricula.nacionalidade}" id="sNacionalidade" name="sNacionalidade" placeholder="Nacionalidade" onKeyPress="return mascaraLetras(event);" maxlength="20"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="sEstadoCivil">Estado Civil</label> </div>
                                            <div class="formCadastroInput">
                                                <select id="estadoCivil" name="estadoCivil">
                                                    <%
                                                        List<String> listEstCivil = ((InterfaceServidorDAO) request.getAttribute("dao")).editarEstadoCivil(((Servidor) request.getAttribute("matricula")).getEstado_civil());
                                                        for (int i = 0; i < listEstCivil.size(); i++) {
                                                    %><%= listEstCivil.get(i)%><%
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="sTelefone1"><img src="imagens/asterisco.png" alt="Campo obrigatório"/>Telefone 1</label> </div>
                                            <div class="formCadastroInput"><input type="text" value="${matricula.telefone1}" id="sTelefone1" name="sTelefone1" placeholder="(xx)xxxx-xxxx" onKeyPress="return mascaraTelefone(event);" maxlength="14" /> </div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="sTelefone2">Telefone 2</label> </div>
                                            <div class="formCadastroInput"><input type="text" value="${matricula.telefone2}" id="sTelefone2" name="sTelefone2" placeholder="(xx)xxxx-xxxx" onKeyPress="return mascaraTelefone(event);" maxlength="14" /></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel">
                                                <label for="bMotorista"><img src="imagens/asterisco.png" alt="Campo obrigatório"/>Motorista</label>
                                            </div>
                                            <div class="formCadastroInput">
                                                <input type="radio" id="bMotorista" name="bMotorista" value="1" 
                                                       <% if (((Servidor) request.getAttribute("matricula")).isMotorista()) {%>checked<% }%>          
                                                       />
                                                <label class="radioMotorista" for="bMotorista" >Sim</label>
                                                <input type="radio" id="bMotorista" name="bMotorista" value="0" 
                                                               <% if (!((Servidor) request.getAttribute("matricula")).isMotorista()) {%>checked<% }%>          
                                                               />
                                                <label class="radioMotorista" for="bMotorista">Não<label>
                                            </div>
                                            </li>
                                            <li>
                                                <div class="formCadastroLabel"><label for="sCnh">CNH</label> </div>
                                                <div class="formCadastroInput"><input value="${matricula.cnh}" type="text" id="sCnh" name="sCnh" placeholder="CNH" onKeyPress="return mascaraMatSiap(event);" maxlength="11"/></div>
                                            </li>
                                            <li>
                                                <div class="formCadastroLabel">
                                                    <label for="status" id="status_serv" value="${matricula.status_serv}"><img src="imagens/asterisco.png" alt="Campo obrigatório"/>Status</label>
                                                </div>
                                                <div class="formCadastroInput">
                                                    <input type="radio" id="status_serv" value="1" name="status_serv"
                                                           <% if (((Servidor) request.getAttribute("matricula")).isStatus_serv()) {%>checked<% }%>   
                                                           />
                                                    <label class="radioStatus" for="Status" >Ativo</label>
                                                    <input type="radio" value="0" id="status_serv" name="status_serv"
                                                                   <% if (!((Servidor) request.getAttribute("matricula")).isStatus_serv()) {%>checked<% }%>   
                                                                   />
                                                    <label class="radioStatus" for="Status">Inativo<label> 
                                                </div>
                                            </li>
                                            <li class="liTextArea">
                                                <div class="formCadastroLabel"><label for="sInfoComplementar">Informações Complementares</label> </div>
                                                <div class="formCadastroInput"><textarea type="text" name="sInfoComplementar" id="sInfoComplementar" placeholder="Informações complementares" maxlength="1000">${matricula.informacoes}</textarea></div>
                                            </li>
                                            <li>
                                            <div class="formCadastroInputCancelar"><input type="button" value="Cancelar" onclick="window.location = ('ControleServidor?action=listaServidores&pagina=1')" /></div>
                                            <div class="formCadastroInputSalvar"><input type="submit" value="Salvar" onclick="return validarServidor()"/></div>
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