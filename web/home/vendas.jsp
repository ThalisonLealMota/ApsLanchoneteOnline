<%-- 
    Document   : fazerpedido
    Created on : 20/10/2020, 15:30:48
    Author     : Ferreira
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="entidade.Vendas"%>
<%@page import="dao.DAOVendas"%>
<%@page import="entidade.Pedido"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="entidade.Lanche"%>
<%@page import="dao.DAOIngrediente"%>
<%@page import="java.util.List"%>
<%@page import="entidade.Ingrediente"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style/styles.css"/>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vendas</title>
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
                    <a class="menu_link active" href="<%=request.getContextPath()%>/home/vendas.jsp">Listar Vendas</a>
                    <a class="menu_link" href="<%=request.getContextPath()%>/home/estoque.jsp">Estoque</a>
                </c:if>
                    <a class="menu_link" href="<%=request.getContextPath()%>/home/pedidos.jsp">Pedidos</a>
                <a class="menu_link" href="<%=request.getContextPath()%>/home/fazerpedido.jsp">Fazer Pedido</a>
                <input class="btl" type="submit" name="enviar" value="Logout">
            </div>
        </form>
                
                
                
                <div id="main" style="height: 150%;">
        <div class="login-box">
        <h2 style="">Vendas</h2>
        <form class="boxi" action="<%=request.getContextPath()%>/ServletVendas" method="post" autocomplete="off">
            <div class="container">
                <div class="msg" style="color: black; position: relative; right: 300px; bottom: 300px;">${message}</div>
                 
                    <input style="position: fixed; right: -50px; bottom: 452px;" class="btn" type="submit" name="enviar" value="Buscar">
                    <input style="position: fixed; right: -200px; bottom: 452px;" class="btn" type="submit" name="enviar" value="Limpar">
                 <table style="position: fixed; top: -370px; left: -300px; width: 900px;">
                     <p style="position: fixed; top: -470px; left:  -400px;">Entre: <input type="textt" placeholder="dd/mm/yyyy" name="data1" value=""
                                       maxlength="10" style="position: fixed; top: -490px; left:  -350px;"></p>
                     <p style="position: fixed; top: -470px; left:  -130px;"> e <input type="textt" placeholder="dd/mm/yyyy" name="data2" value=""
                                       maxlength="10" style="position: fixed; top: -490px; left:  -100px;"></p>
                         <%
                         int i = 1;
                         float t = 0;
                         %>
                         <%
                         if(request.getSession().getAttribute("vendas") != null){%>
                     <thead style="background: orange;">
                         <tr>
                             <td>#</td>
                             <td>Ingrediente</td>
                             <td>Quantidade</td>
                             <td>Preço por unidade</td>
                             <td>Preço total</td>
                         </tr>
                    </thead>
                         <%
                         List<Vendas> list = (List)request.getSession().getAttribute("vendas");
                         for (Vendas v : list){
                             t += (v.getPreco() * v.getQuantidade());
                             DecimalFormat df = new DecimalFormat("0.00");
                             DateFormat dataf = new SimpleDateFormat("dd/MM/yyyy");
                         %>
                     <tbody>                         
                         <tr>
                             <td><%=i++%></td>
                             <td><%=v.getNome()%></td>
                             <td><%=v.getQuantidade()%></td>
                             <td><%=df.format(v.getPreco())%></td>
                             <td><%=df.format((v.getPreco() * v.getQuantidade()))%></td>
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
