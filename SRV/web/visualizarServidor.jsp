<%-- 
    Document   : visualizarServidor
    Created on : 21/04/2014, 22:14:59
    Author     : Paula
--%>


<%


%>    
<%@include file="ValidarLoginAdministrador.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Visualizar Servidor</title>
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
                            <h2>Cadastrar Novo Servidor</h2>
                            <div class="camposObrigatorios">
                                *Campos obrigatórios
                            </div>
                            <form action="ControleServidor" name="formCadastroServidor">
                                <input type="hidden" name="action" value="Cadastrar"/>
                                <div class="formularioCadastrarServidorBox">
                                    <ul>
                                        <li>
                                            <div class="formCadastroLabel"><label for="iMatriculaSiape">Matricula SIAPE</label> </div>
                                            <div class="formCadastroInput"><input type="text" id="iMatriculaSiape" name="iMatriculaSiape" placeholder="SIAPE" size="7" maxlength="7"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="sNomeCompleto">Nome</label> </div>
                                            <div class="formCadastroInput"><input type="text" id="sNomeCompleto" name="sNomeCompleto" placeholder="Nome completo" size="50"/></div>
                                        <!--</li>
                                         <li>
                                            <div class="formCadastroLabel"><label for="sSenha">Senha</label> </div>
                                            <div class="formCadastroInput"><input type="password" name="sSenha" placeholder="Senha"/></div>
                                        </li>-->
                                        <li>
                                            <div class="formCadastroLabel"><label for="sEmail">Email</label> </div>
                                            <div class="formCadastroInput"><input type="text" id="sEmail" name="sEmail" placeholder="Email institucional"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="sexo">Sexo</label> </div>
                                            <div class="formCadastroInput"><label class="radioSexo" for="Sexo" >Masculino</label>
                                                <input type="radio" id="sexo" name="sexo" value="m"/>
                                                <label class="radioSexo" for="Sexo">Feminino<lable><input type="radio" id="sexo" name="sexo" value="f"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="sDataNascimento">Data Nascimento</label> </div>
                                            <div class="formCadastroInput"><input type="date" name="sDataNascimento" placeholder="aaaa-mm-dd"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="sCpf">CPF</label> </div>
                                            <div class="formCadastroInput"><input type="text" id="sCpf" name="sCpf" placeholder="CPF" size="14" maxlength="11"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="sRg">RG</label> </div>
                                            <div class="formCadastroInput"><input type="text" id="sRg" name="sRg" placeholder="Identidade"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="sOrgaoExpedidor">Orgão Expedidor</label> </div>
                                            <div class="formCadastroInput"><input type="text" id="sOrgaoExpedidor" name="sOrgaoExpedidor" placeholder="Orgão expedidor"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="sNaturalidade">Naturalidade</label> </div>
                                            <div class="formCadastroInput"><input type="text" id="sNaturalidade" name="sNaturalidade" placeholder="Cidade de nascimento"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="sUf">UF</label> </div>
                                            <div class="formCadastroInput">
                                                <select id="estado" name="estado"/>
                                                <option value="0">Selecione o Estado</option>
                                                <option value="ac">Acre</option>
                                                <option value="al">Alagoas</option>
                                                <option value="ap">Amapá</option>
                                                <option value="am">Amazonas</option>
                                                <option value="ba">Bahia</option>
                                                <option value="ce">Ceará</option>
                                                <option value="df">Distrito Federal</option>
                                                <option value="es">Espirito Santo</option>
                                                <option value="go">Goiás</option>
                                                <option value="ma">Maranhão</option>
                                                <option value="ms">Mato Grosso do Sul</option>
                                                <option value="mt">Mato Grosso</option>
                                                <option value="mg">Minas Gerais</option>
                                                <option value="pa">Pará</option>
                                                <option value="pb">Paraíba</option>
                                                <option value="pr">Paraná</option>
                                                <option value="pe">Pernambuco</option>
                                                <option value="pi">Piauí</option>
                                                <option value="rj">Rio de Janeiro</option>
                                                <option value="rn">Rio Grande do Norte</option>
                                                <option value="rs">Rio Grande do Sul</option>
                                                <option value="ro">Rondônia</option>
                                                <option value="rr">Roraima</option>
                                                <option value="sc">Santa Catarina</option>
                                                <option value="sp">São Paulo</option>
                                                <option value="se">Sergipe</option>
                                                <option value="to">Tocantins</option>
                                                </select>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="sNacionalidade">Nacionalidade</label> </div>
                                            <div class="formCadastroInput"><input type="text" id="sNacionalidade" name="sNacionalidade" placeholder="Nacionalidade"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="sEstadoCivil">Estado Civil</label> </div>
                                            <div class="formCadastroInput">
                                                <select id="estadoCivil" name="estadoCivil">
                                                    <option value="0">Estado Civil:</option>
                                                    <option value="1">Solteiro</option>
                                                    <option value="2">Casado</option>
                                                    <option value="3">Viúvo</option>
                                                    <option value="4">Separado</option>
                                                    <option value="5">Divorciado</option>
                                                    <option value="6">União Estável</option>
                                                </select>
                                            </div>
                                        </li>
                                        
                                        <li>
                                            <div class="formCadastroLabel"><label for="status">Status</label> </div>
                                            <div class="formCadastroInput"><label class="radioStatus" for="Status" >Ativo</label>
                                                <input type="radio" id="status_serv" name="status_serv" value="a"/>
                                                <label class="radioStatus" for="Status">Inativo<label><input type="radio" id="status_serv" name="status" value="i"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="sCnh">CNH</label> </div>
                                            <div class="formCadastroInput"><input type="text" id="sCnh" name="sCnh" placeholder="CNH"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="bMotorista">Motorista</label> </div>
                                            <div class="formCadastroInput"><label class="radioMotorista" for="bMotoristaTrue" >Sim</label>
                                                <input type="radio" id="bMotorista" name="bMotorista" value="t"/>
                                                <label class="radioMotorista" for="bMotorista">Não<lable><input type="radio" id="bMotorista" name="bMotorista" value="f"/></div>
                                        </li> 
                                        <li>
                                            <div class="formCadastroLabel"><label for="sTelefoneComercial">Telefone Comercial</label> </div>
                                            <div class="formCadastroInput"><input type="text" id="sTelefoneComercial" name="sTelefoneComercial" placeholder="(xx)xxxx-xxxx"  onKeyPress="MascaraTelefone(formCadastroServidor.sTelefoneComercial);" maxlength="14"  onBlur="ValidaTelefone(formCadastroServidor.sTelefoneComercial);"/> </div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="sTelefoneCelular">Telefone Celular</label> </div>
                                            <div class="formCadastroInput"><input type="text" id="sTelefoneCelular" name="sTelefoneCelular" placeholder="(xx)xxxx-xxxx"  onKeyPress="MascaraTelefone(formCadastroServidor.sTelefoneCelular);" maxlength="14"  onBlur="ValidaTelefone(formCadastroServidor.sTelefoneCelular);"/></div>
                                        </li>
                                        <li class="liTextArea">
                                            <div class="formCadastroLabel"><label for="sInfoComplementar">Informações Complementares</label> </div>
                                            <div class="formCadastroInput"><textarea type="text" name="sInfoComplementar" id="sInfoComplementar" placeholder="Informações complementares" maxlength="140"></textarea></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><input type="submit" value="Enviar"/></div>
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
