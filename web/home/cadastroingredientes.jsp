<%-- 
    Document   : cadastroingredientes
    Created on : 19/10/2020, 14:36:50
    Author     : Ferreira
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style/styles.css"/>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Ingrediente</title>
    </head>
    <body>
        <div class="login-box">
        <h1>Olá, ${CURRENT_USER} Bem Vindo!</h1>
        <c:choose>
            <c:when  test="${CURRENT_USER != null}">
        <form action="<%=request.getContextPath()%>/ServletLogout" method="post">    
            <div id="nav" class="vmenu">
                <a class="menu_link" href="<%=request.getContextPath()%>/home/welcome.jsp">Inicio</a>
                <c:if test="${CURRENT_NVA >= 2}">
                    <a class="menu_link active" href="<%=request.getContextPath()%>/home/cadastroingredientes.jsp">Cadastrar Ingredientes</a>
                    <a class="menu_link" href="<%=request.getContextPath()%>/home/vendas.jsp">Listar Vendas</a>
                    <a class="menu_link" href="<%=request.getContextPath()%>/home/estoque.jsp">Estoque</a>
                </c:if>
                    <a class="menu_link" href="<%=request.getContextPath()%>/home/pedidos.jsp">Pedidos</a>
                <a class="menu_link" href="<%=request.getContextPath()%>/home/fazerpedido.jsp">Fazer Pedido</a>
                <input class="btl" type="submit" name="enviar" value="Logout">
            </div>
        </form>
                
                
                
        <div id="main">
        <div class="login-box">
        <h2 style="">Cadastro Ingredientes</h2>
        <form class="boxi" action="<%=request.getContextPath()%>/ServletSalvarIngrediente" method="post" autocomplete="off">
            <div class="container">
                <div class="msg" style="color: black">${sucmessage}</div>
                <div class="row">
                    <div class="textbox">
                        <input type="text" placeholder="Nome" name="nome" value="">
                    </div>
                </div>
                <div class="row">
                    <div class="textbox">
                        <input type="radio" placeholder="Tipo" name="tipo" value="Pao">Pão
                        <input type="radio" placeholder="Tipo" name="tipo" value="Carne">Carne
                        <input type="radio" placeholder="Tipo" name="tipo" value="Salada">Salada
                        <input type="radio" placeholder="Tipo" name="tipo" value="Molho">Molho
                    </div>
                </div>
                    <div class="row">
                        <div class="textbox">
                            <input type="number" name="quantidade" placeholder="Quantidade" value="" min="1">
                        </div>
                    </div>
                <div class="row">
                    <div class="textbox">
                        <input type="number" name="preco" placeholder="Preço" value="" min="0" step=".01">
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
            </div>
        </div>
            </c:when>
            <c:when test="${CURRENT_USER == null}">
                <c:redirect url="../login.jsp"/>
            </c:when>
    </c:choose>
    </body>
</html>
