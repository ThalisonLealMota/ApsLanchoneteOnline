<%-- 
    Document   : fazerpedido
    Created on : 20/10/2020, 15:30:48
    Author     : Ferreira
--%>

<%@page import="entidade.Pedido"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="entidade.Lanche"%>
<%@page import="dao.DAOIngrediente"%>
<%@page import="java.util.List"%>
<%@page import="entidade.Ingrediente"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style/style.css"/>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Seus Pedidos</title>
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
                    <a class="menu_link" href="<%=request.getContextPath()%>/home/cadastroingredientes.jsp">Cadastrar Ingredientes</a>
                    <a class="menu_link" href="<%=request.getContextPath()%>/home/vendas.jsp">Listar Vendas</a>
                </c:if>
                    <a class="menu_link active" href="<%=request.getContextPath()%>/home/pedidos.jsp">Pedidos</a>
                <a class="menu_link" href="<%=request.getContextPath()%>/home/fazerpedido.jsp">Fazer Pedido</a>
                <input class="btl" type="submit" name="enviar" value="Logout">
            </div>
        </form>
                
                
                
        <div id="main">
        <div class="login-box">
        <h2 style="">Seus Pedidos</h2>
        <form class="boxi" action="<%=request.getContextPath()%>/ServletSalvarPedido" method="post" autocomplete="off">
            <div class="container">
                <div class="msg" style="color: black; position: relative; right: 300px; bottom: 300px; width: 400px;">${message}</div>
                 
                <input style="position: fixed; right: 300px; bottom: -200px;" class="btn" type="submit" name="enviar" value="Cancelar">    
                
                <input style="position: fixed; right: 450px; bottom: -200px;" class="btn" type="submit" name="enviar" value="Finalizar">
                
                <table style="position: fixed; top: -300px; left: -300px; width: 900px;">

                         <%
                         int i = 1;
                         float t = 0;
                         %>
                         <%
                         if(request.getSession().getAttribute("pedidos") != null){%>
                         <thead style="background-color: orange;">
                         <tr>
                             <td>#</td>
                             <td>IdPedido</td>
                             <td>IdLanche</td>
                             <td>Preço</td>
                         </tr>
                    </thead>
                         <%
                         List<Pedido> list = (List)request.getSession().getAttribute("pedidos");
                         for (Pedido p : list){
                             t += p.getPreco();
                             DecimalFormat df = new DecimalFormat("0.00");
                         %>
                         <tbody style="background-color: none; border-collapse: separate;">                         
                         <tr>
                             <td><%=i++%></td>
                             <td><%=p.getIdPedido()%></td>
                             <td><%=p.getIdLanche()%></td>
                             <td><%=df.format(p.getPreco())%></td>
                         </tr>
                         <%}}%>
                         <tr><%if(i > 1){
                             DecimalFormat df = new DecimalFormat("0.00");%>
                             <td>Total: <%=df.format(t)%></td>
                             <%}%>
                         </tr>
                     </tbody>
                 </table>
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
