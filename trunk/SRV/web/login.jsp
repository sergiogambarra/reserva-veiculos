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
      <form name="formLogin" method="post" action="ControleLogin" onsubmit="return validarMatSiap(document.forms['formLogin']['inputMatricula'].value)" >
          <input type="hidden" name="action" value="Entrar"/>
          <p><input type="text" id="inputMatricula" name="inputMatricula" onKeyPress="return mascaraMatSiap(event);" placeholder="Matricula" maxlength="7">
          <p><input type="password" name="inputSenha"  placeholder="Password" maxlength="8"></p>
          <%
            if (request.getAttribute("mensagem") != null) {
          %>
          <p><%= request.getAttribute("mensagem")%> </p>
          <%
                     }
          %>
      	<p class="submit"><input type="submit" name="buttonLogin" value="Login" class="formLoginBotao"></p>
      </form>
      <div class="login-help">
      <p> <a href="enviarSenha.jsp">Esqueci a senha</a></p>
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