<%@include file="ValidarLogin.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Novo Servidor</title>
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
                            <h2>Cadastrar Servidor</h2>
                            <div class="camposObrigatorios">
                                *Campos obrigatórios
                            </div>
                            <form action="ControleServidor" name="formCadastroServidor">
                                <input type="hidden" name="action" value="cadastrarServidor"/>
                                <div class="formularioCadastrarServidorBox">
                                    <ul>
                                        <li>
                                            <div class="formCadastroLabel"><label for="iMatriculaSiape"><img src="imagens/asterisco.png" alt="Campo obrigatório"/>Matrícula SIAPE</label> </div>
                                            <div class="formCadastroInput"><input type="text" id="iMatriculaSiape" name="iMatriculaSiape" placeholder="SIAPE" onKeyPress="return mascaraMatSiap(event);" maxlength="7"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="sNomeCompleto"><img src="imagens/asterisco.png" alt="Campo obrigatório"/>Nome</label> </div>
                                            <div class="formCadastroInput"><input type="text" id="sNomeCompleto" name="sNomeCompleto" placeholder="Nome completo" onKeyPress="return mascaraLetras(event);" maxlength="50"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="sEmail"><img src="imagens/asterisco.png" alt="Campo obrigatório"/>Email</label> </div>
                                            <div class="formCadastroInput"><input type="text" id="sEmail" name="sEmail" placeholder="Email institucional" maxlength="50"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="sexo"><img src="imagens/asterisco.png" alt="Campo obrigatório"/>Sexo</label> </div>
                                            <div class="formCadastroInput">
                                                <input type="radio" id="sexo" name="sexo" value="m"/>
                                                <label class="radioSexo" for="Sexo" >Masculino</label>
                                                <input type="radio" id="sexo" name="sexo" value="f"/>
                                                <label class="radioSexo" for="Sexo">Feminino</label>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel">
                                                <label for="sDataNascimento"><img src="imagens/asterisco.png" alt="Campo obrigatório"/>Data Nascimento</label> 
                                            </div>
                                            <div class="formCadastroInput">
                                                <input type="text" id="sDataNascimento" name="sDataNascimento"  maxlength="10" onKeyPress="return mascaraData(event);""/>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="sCpf"><img src="imagens/asterisco.png" alt="Campo obrigatório"/>CPF</label> </div>
                                            <div class="formCadastroInput"><input type="text" placeholder="CPF" id="sCpf" name="sCpf" onKeyPress="return mascaraCpf(event);" onKeyPress="return mascaraMatSiap(event);" maxlength="14"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="sRg"><img src="imagens/asterisco.png" alt="Campo obrigatório"/>RG</label> </div>
                                            <div class="formCadastroInput"><input type="text" id="sRg" name="sRg" placeholder="Identidade" maxlength="15"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="sOrgaoExpedidor"><img src="imagens/asterisco.png" alt="Campo obrigatório"/>Orgão Expedidor</label> </div>
                                            <div class="formCadastroInput"><input type="text" id="sOrgaoExpedidor" name="sOrgaoExpedidor" placeholder="Orgão expedidor" maxlength="10"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="sNaturalidade">Naturalidade</label> </div>
                                            <div class="formCadastroInput"><input type="text" id="sNaturalidade" name="sNaturalidade" placeholder="Cidade de nascimento" onKeyPress="return mascaraLetras(event);" maxlength="30"/></div>
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
                                            <div class="formCadastroInput"><input type="text" id="sNacionalidade" name="sNacionalidade" placeholder="Nacionalidade" onKeyPress="return mascaraLetras(event);" maxlength="20"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="sEstadoCivil">Estado Civil</label> </div>
                                            <div class="formCadastroInput">
                                                <select id="estadoCivil" name="estadoCivil">
                                                    <option value="">Estado Civil:</option>
                                                    <option value="sol">Solteiro</option>
                                                    <option value="cas">Casado</option>
                                                    <option value="viu">Viúvo</option>
                                                    <option value="sep">Separado</option>
                                                    <option value="div">Divorciado</option>
                                                    <option value="uni">União Estável</option>
                                                </select>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="sTelefone1"><img src="imagens/asterisco.png" alt="Campo obrigatório"/>Telefone 1</label> </div>
                                            <div class="formCadastroInput"><input type="text" id="sTelefone1" name="sTelefone1" placeholder="(xx)xxxx-xxxx" onKeyPress="return mascaraTelefone(event);" maxlength="14"/> </div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="sTelefone2">Telefone 2</label> </div>
                                            <div class="formCadastroInput"><input type="text" id="sTelefone2" name="sTelefone2" placeholder="(xx)xxxx-xxxx" onKeyPress="return mascaraTelefone(event);" maxlength="14"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel">
                                                <label for="bMotorista"><img src="imagens/asterisco.png" alt="Campo obrigatório"/>Motorista</label> </div>
                                            <div class="formCadastroInput">
                                                <input type="radio" id="bMotorista" name="bMotorista" value="1" />
                                                <label class="radioMotorista" for="bMotorista" >Sim</label>
                                                <input type="radio" id="bMotorista" name="bMotorista" value="0"/>
                                                <label class="radioMotorista" for="bMotorista">Não</label>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel"><label for="sCnh">CNH</label> </div>
                                            <div class="formCadastroInput"><input type="text" id="sCnh" name="sCnh" placeholder="CNH" onKeyPress="return mascaraMatSiap(event);" maxlength="11"/></div>
                                        </li>
                                        <li>
                                            <div class="formCadastroLabel">
                                                <label for="status" id="status_serv"><img src="imagens/asterisco.png" alt="Campo obrigatório"/>Status</label> </div>
                                            <div class="formCadastroInput">
                                                <input type="radio" id="status_serv" name="status_serv" value="1" checked/>
                                                <label class="radioStatus" for="Status" >Ativo</label>
                                                <input type="radio" id="status_serv" name="status_serv" value="0"/>
                                                <label class="radioStatus" for="Status">Inativo</label>
                                            </div>
                                        </li>
                                        <li class="liTextArea">
                                            <div class="formCadastroLabel"><label for="sInfoComplementar">Informações Complementares</label> </div>
                                            <div class="formCadastroInput"><textarea type="text" name="sInfoComplementar" id="sInfoComplementar" placeholder="Informações complementares" maxlength="1000"></textarea></div>
                                        </li>
                                        <li class="formBotoes">
                                            <div class="formCadastroInputCancelar"><input type="button" value="Cancelar" onclick="window.location = ('ControleServidor?action=listaServidores&pagina=1')" /></div>
                                            <div class="formCadastroInputLimpar"><input type="reset" value="Limpar"/></div>
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