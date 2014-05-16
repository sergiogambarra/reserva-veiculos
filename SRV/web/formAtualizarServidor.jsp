
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
        <link rel="stylesheet" href="css/styleLogin.css" type='text/css'>
        <link rel="stylesheet" href="css/styleContent.css" type='text/css'> 
        <script type="text/javascript" type="text/javascript" src="js/validacoesMascara.js"></script>
        <script type="text/javascript" type="text/javascript" src="cidades-estados-1.0"></script>
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
                            Você está em: 
                            <script type="text/javascript">
                                var pagina = document.title;
                                document.write(pagina);
                            </script>
                        </div>
                        <div class="formularioCadastrarServidor">            
                            <h2>Editar Servidor</h2>
                            <div class="camposObrigatorios">
                                *Campos obrigatórios
                            </div>
                            <form action="ControleServidor" name="formAtualizaServidor">
                                <input type="hidden" name="action" value="atualizarServidor"/>
                                <div class="formularioCadastrarServidorBox">
                                    <ul>
                                        <li>
                                            <div class="formCadastroLabel"><label for="iMatriculaSiape">*Matricula SIAPE</label> </div>
                                            <div class="formCadastroInput"><input type="text" id="iMatriculaSiape" name="iMatriculaSiape" placeholder="SIAPE" value="${matricula.matriculaSIAPE}"size="7" maxlength="7"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="sNomeCompleto">*Nome</label> </div>
                                            <div class="formCadastroInput"><input type="text" id="sNomeCompleto" value="${matricula.nome}" name="sNomeCompleto" placeholder="Nome completo" size="50"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="sEmail">*Email</label> </div>
                                            <div class="formCadastroInput"><input type="text" value="${matricula.email}" id="sEmail" name="sEmail" placeholder="Email institucional"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel">
                                                <label for="sexo">*Sexo</label>
                                            </div>
                                            <div class="formCadastroInput">
                                                <label class="radioSexo" for="Sexo">Masculino</label>
                                                <input type="radio" id="sexom" name="sexo" value="m" 
                                                       <% if (((Servidor) request.getAttribute("matricula")).getSexo().equals("m")) {%>
                                                       checked
                                                       <% }%>   
                                                       />
                                                <label class="radioSexo" for="Sexo">Feminino<label>
                                                        <input type="radio" id="sexof" name="sexo" value="f" 
                                                               <% if (((Servidor) request.getAttribute("matricula")).getSexo().equals("f")) {%>
                                                               checked
                                                               <% }%>   
                                                               />
                                                        </div>
                                                        </li>
                                                        <li>
                                                            <div class="formCadastroLabel"><label for="sDataNascimento">*Data Nascimento</label> </div>
                                                            <div class="formCadastroInput"><input value="${matricula.data_nascimento}" type="date" name="sDataNascimento" placeholder="aaaa-mm-dd"/></div>
                                                        </li>
                                                        <li>
                                                            <div class="formCadastroLabel"><label for="sCpf">*CPF</label> </div>
                                                            <div class="formCadastroInput"><input type="text" value="${matricula.cpf}" id="sCpf" name="sCpf" placeholder="CPF" maxlength="11"/></div>
                                                        </li>
                                                        <li>
                                                            <div class="formCadastroLabel"><label for="sRg">*RG</label> </div>
                                                            <div class="formCadastroInput"><input type="text" value="${matricula.rg}" id="sRg" name="sRg" placeholder="Identidade"/></div>
                                                        </li>
                                                        <li>
                                                            <div class="formCadastroLabel"><label for="sOrgaoExpedidor">*Orgão Expedidor</label> </div>
                                                            <div class="formCadastroInput"><input type="text" value="${matricula.orgao_expedidor}" id="sOrgaoExpedidor" name="sOrgaoExpedidor" placeholder="Orgão expedidor"/></div>
                                                        </li>
                                                        <li>
                                                            <div class="formCadastroLabel"><label for="sNaturalidade">Naturalidade</label> </div>
                                                            <div class="formCadastroInput"><input type="text" value="${matricula.naturalidade}" id="sNaturalidade" name="sNaturalidade" placeholder="Cidade de nascimento"/></div>
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
                                                            <div class="formCadastroInput"><input type="text" value="${matricula.nacionalidade}" id="sNacionalidade" name="sNacionalidade" placeholder="Nacionalidade"/></div>
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
                                            <div class="formCadastroLabel">
                                                <label for="status" id="status_serv" value="${matricula.status_serv}">Status</label>
                                            </div>
                                            <div class="formCadastroInput">
                                                <label class="radioStatus" for="Status" >Ativo</label>
                                                <input type="radio" id="status_serv" value="true" name="status_serv"
                                                       <% if (((Servidor) request.getAttribute("matricula")).isStatus_serv()) {%>checked<% }%>   
                                                       />
                                                <label class="radioStatus" for="Status">Inativo<label>
                                                        <input type="radio" value="false" id="status_serv" name="status_serv"
                                                               <% if (!((Servidor) request.getAttribute("matricula")).isStatus_serv()) {%>checked<% }%>   
                                                               />
                                                        </div>
                                                        </li>
                                                                        <li>
                                                                            <div class="formCadastroLabel"><label for="sCnh">CNH</label> </div>
                                                                            <div class="formCadastroInput"><input value="${matricula.cnh}" type="text" id="sCnh" name="sCnh" placeholder="CNH"/></div>
                                                                        </li>
                                                                        <li>
                                                                            <div class="formCadastroLabel">
                                                                                <label for="bMotorista">Motorista</label>
                                                                            </div>
                                                                            <div class="formCadastroInput">
                                                                                <label class="radioMotorista" for="bMotorista" >Sim</label>
                                                                                <input type="radio" id="bMotorista" name="bMotorista" value="t"
                                                                                       <% if (((Servidor) request.getAttribute("matricula")).isMotorista()) {%>checked<% }%>          
                                                                                       />
                                                                                <label class="radioMotorista" for="bMotorista">Não<label>
                                                                                        <input type="radio" id="bMotorista" name="bMotorista" value="f"
                                                                                               <% if (!((Servidor) request.getAttribute("matricula")).isMotorista()) {%>checked<% }%>          
                                                                                               />
                                                                                        </div>
                                                                                        </li>
                                                                                        <li>
                                                                                            <div class="formCadastroLabel"><label for="sTelefoneComercial">Telefone Comercial</label> </div>
                                                                                            <div class="formCadastroInput"><input type="text" value="${matricula.telefone_comer}" id="sTelefoneComercial" name="sTelefoneComercial" placeholder="(xx)xxxx-xxxx"  onKeyPress="MascaraTelefone(formCadastroServidor.sTelefoneComercial);" maxlength="14"  onBlur="ValidaTelefone(formCadastroServidor.sTelefoneComercial);"/> </div>
                                                                                        </li>
                                                                                        <li>
                                                                                            <div class="formCadastroLabel"><label for="sTelefoneCelular">Telefone Celular</label> </div>
                                                                                            <div class="formCadastroInput"><input type="text" value="${matricula.telefone_cel}" id="sTelefoneCelular" name="sTelefoneCelular" placeholder="(xx)xxxx-xxxx"  onKeyPress="MascaraTelefone(formCadastroServidor.sTelefoneCelular);" maxlength="14"  onBlur="ValidaTelefone(formCadastroServidor.sTelefoneCelular);"/></div>
                                                                                        </li>
                                                                                        <li class="liTextArea">
                                                                                            <div class="formCadastroLabel"><label for="sInfoComplementar">Informações Complementares</label> </div>
                                                                                            <div class="formCadastroInput"><textarea type="text" name="sInfoComplementar" id="sInfoComplementar" placeholder="Informações complementares" maxlength="140">${matricula.informacoes}</textarea></div>
                                                                                        </li>
                                                                                        <li>
                                                                                            <div class="formCadastroLabel"><input type="submit" value="Editar"/></div>
                                                                                            <div class="formCadastroInput"><input type="reset" value="Resetar"/></div>
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