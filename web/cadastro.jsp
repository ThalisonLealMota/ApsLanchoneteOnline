<link rel="stylesheet" href="style/styles.css"/>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro</title>
    </head>
    <body>
        <div class="login-box">
        <h1>Cadastro</h1>
        <div id="nav" class="vmenu" style="position: absolute; top: 18%;">
            <a class="menu_link active" href="cadastro.jsp">Cadastro</a>
            <a class="menu_link " href="login.jsp">Login</a>
        </div>
        <form class="box" action="ServletSalvarPessoa" method="post" autocomplete="off">
            <div class="container">
                <div class="msg">${message}</div>
                <div class="msg">${Sucessmessage}</div>
                <div class="row">
                    <div class="textbox">
                        <input type="text" placeholder="Username" name="nome" value="" 
                               required 
                               oninvalid="this.setCustomValidity('Entre com um Username valido!')"
                               oninput="this.setCustomValidity('')">
                    </div>
                </div>
                <div class="row">
                    <div class="textbox">
                        <input type="password" placeholder="Password" name="senha" value="" 
                               minlength="8" required 
                               oninvalid="this.setCustomValidity('Entre com uma Senha valido!')"
                               oninput="this.setCustomValidity('')">
                    </div>
                </div>
                <div class="row">
                    <div class="textbox">
                        <select class="cbbox" id="Pao" name="nvacesso">
                            <option value="1">Cliente</option>
                            <option value="3">Administrador</option>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col"></div>
                    <div class="col">
                        <input class="btn" type="submit" name="enviar" value="Salvar">
                    </div>
                </div>
            </div>
        </form>
        </div>
    </body>
</html>
