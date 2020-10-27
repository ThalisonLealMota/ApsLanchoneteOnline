<%-- 
    Document   : fazerpedido
    Created on : 20/10/2020, 15:30:48
    Author     : Ferreira
--%>

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
        <title>Fazer Pedido</title>
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
                    <a class="menu_link" href="<%=request.getContextPath()%>/home/pedidos.jsp">Pedidos</a>
                <a class="menu_link active" href="<%=request.getContextPath()%>/home/fazerpedido.jsp">Fazer Pedido</a>
                <input class="btl" type="submit" name="enviar" value="Logout">
            </div>
        </form>
                
                
                
        <div id="main">
        <div class="login-box">
        <h2 style="">Fazer Pedido</h2>
        <form class="boxi" action="<%=request.getContextPath()%>/ServletFazerPedido" method="post" autocomplete="off">
            <div class="container">
                <div class="msg" style="color: black; position: relative; right: 200px; bottom:100px;">${message}</div>
                <select class="cbbox" id="Pao" name="cbIngrediente" style="position: relative; right: 200px; bottom:100px;">
                    <%
                        DAOIngrediente daoIngrediente = new DAOIngrediente();
                        Ingrediente ingrediente = new Ingrediente();
                        List<Ingrediente> ingredientes = daoIngrediente.listar();
                        out.println("<option>Escolha um Ingrediente</option>");
                        for(Object obj : ingredientes){
                            ingrediente = (Ingrediente)obj;
                            if(ingrediente.getQuantidade() > 0){
                            out.println("<option value=" + ingrediente.getId() + ">" + ingrediente.getNome() + " - " +ingrediente.getTipo() + " - " + ingrediente.getPreco() + "</option>");
                            }
                        }
                    %>
                </select>
                <input style="position: relative; right: 200px; bottom:100px;" type="number" name="quantidadeingrediente" value="1" min="1">
                <input style="position: relative; right: 200px; bottom:100px;" class="btn" type="submit" name="enviar" value="Adicionar">
                <input style="position: relative; right: 200px; bottom:100px;" class="btn" type="submit" name="enviar" value="Finalizar">
                <input style="position: relative; right: 200px; bottom:100px;" class="btn" type="submit" name="enviar" value="Limpar">
                
                 <table style="position: fixed; top: -100px; left: 200px;">

                         <%
                         int i = 1;
                         float t = 0;
                         %>
                         <%
                         if(request.getSession().getAttribute("list") != null){%>
                    <thead>
                         <tr>
                             <td>#</td>
                             <td>IdIngrediente</td>
                             <td>Quantidade</td>
                             <td>Preço</td>
                         </tr>
                    </thead>
                         <%
                         List<Lanche> list = (List)request.getSession().getAttribute("list");
                         for (Lanche u : list){
                             DecimalFormat df = new DecimalFormat("0.00");
                             List<Ingrediente> ing = daoIngrediente.encontrar(u.getIdIngrediente().getId());
                             Ingrediente in = ing.get(0);
                             t += u.getPreco();
                         %>
                     <tbody>                         
                         <tr>
                             <td><%=i++%></td>
                             <td><%=in.getNome()%></td>
                             <td><%=u.getQuantidade()%></td>
                             <td><%=df.format(u.getPreco())%></td>
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
