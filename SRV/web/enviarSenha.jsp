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
</head>
<body>
  <section class="container">
    <div class="containerLogin">
    <div class="login">
      <h1>Login no SRV</h1>
      <form method="post" action="COntroleLogin" name="">
          <input type="hidden" name="action" value="EnviarSenha"/>
          <p><input type="text" name="inputMatricula" value="" placeholder="Matricula"></p>
      	<p class="submit"><input type="submit" name="buttonLogin" value="Enviar Senha" class="formLoginBotao"></p>
      </form>
      <div class="login-help">
      <p> <a href="login.html">Login</a></p>
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