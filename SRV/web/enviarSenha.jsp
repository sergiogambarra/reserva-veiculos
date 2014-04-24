<!DOCTYPE html>
<!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]> <html class="lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]> <html class="lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> 
<html lang="pt-br"> <!--<![endif]-->
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>Login SRV</title>
  <link rel="stylesheet" href="css/styleLogin.css" type='text/css'>
  <script type="text/javascript" src="js/validacoesMascara.js" charset="UTF-8"></script>
</head>
<body>
  <section class="container">
    <div class="containerLogin">
    <div class="login">
      <h1>Login no SRV</h1>
      <form method="post" action="ControleLogin" onSubmit="validarMatSiap(document.forms['formEnviarSenha']['inputMatricula'].value)" name="formEnviarSenha">
          <input type="hidden" name="action" value="EnviarSenha"/>
          <p><input type="text" name="inputMatricula" value="" placeholder="Matricula" onKeyPress="return mascaraMatSiap(event);" maxlength="7"></p>
          <%
            if (request.getAttribute("mensagem") != null) {
          %>
          <p><%= request.getAttribute("mensagem")%> </p>
          <%
                     }
          %>
      	<p class="submit"><input type="submit" name="buttonLogin" value="Enviar Senha" class="formLoginBotao"></p>
      </form>
      <div class="login-help">
      <p> <a href="login.jsp">Login</a></p>
    </div>
    </div>
  </div>
  </section>
  <section class="about">
    <p class="about-links">
      &copy; 2014&ndash;2014 
      <a href="http://restinga.ifrs.edu.br" target="_parent">ADS 5º semestre 2014 - IFRS Campus Restinga</a>
    </p>
  </section>
</body>
</html>