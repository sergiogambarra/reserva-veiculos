<%-- 
    Document   : Login
    Created on : 10/04/2014, 12:02:49
    Author     : Erick
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head lang="pt-br">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <link rel="stylesheet" href="css/styleContent.css" type='text/css'>
        <link rel="stylesheet" href="css/styleLogin.css" type='text/css'>
        <title>Login SRV</title>
    </head>
    <body>
        <section class="container">
            <div class="containerLogin">
                <div class="login">
                    <h1>Login no SRV</h1>
                        <form name="frm" action="ControleLogin" method="post">
                            <input type="hidden" name="action" value="Entrar"/>
                            <input type="text" name="login" id="login" value="" placeholder="Matricula"></p>
                            <p><input type="password" name="senha" id="senha" value="" placeholder="Senha"></p>
                            <p class="submit"><input type="submit" name="buttonLogin" value="Login" class="formLoginBotao"></p>
                        </form>
                        <div class="login-help">
                            <p> <a href="#">Esqueci a senha</a></p>
                        </div>
                </div>
            </div>
        </section>

        <section class="about">
            <p class="about-links">
                &copy; 2014&ndash;2014 
                <a href="http://restinga.ifrs.edu.br" target="_parent">ADS 5º semestre 2014 - IFRS Campus Restinga</a>
            </p>
      <!-- <a href="http://www.cssflow.com/mit-license" target="_blank">MIT License</a><br> -->
        </section>
    </body>
</html>
