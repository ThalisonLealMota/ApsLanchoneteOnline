<link rel="stylesheet" href="style/style.css"/>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <div class="login-box">
        <h1>Login</h1>
        <div id="nav" class="vmenu" style="position: absolute; top: 18%;">
            <a class="menu_link" href="cadastro.jsp">Cadastro</a>
            <a class="menu_link active" href="login.jsp">Login</a>
        </div>
        <form class="box" action="ServletLoginPessoa" method="post" autocomplete="off">
            <div class="container">
                <div class="msg">${message}</div>
                <div class="row">
                    <div class="textbox">
                        <input type="text" placeholder="Username" name="nome" value="">
                    </div>
                </div>
                <div class="row">
                    <div class="textbox">
                        <input type="password" placeholder="Password" name="senha" value="" >
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <input class="btn" type="submit" name="enviar" value="Login">
                    </div>
                </div>
            </div>
            </form>
        </div>
    </body>
</html>
