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
<link rel="stylesheet" href="<%=request.getContextPath()%>/style/styles.css"/>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Estoque</title>
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
                    <a class="menu_link active" href="<%=request.getContextPath()%>/home/estoque.jsp">Estoque</a>
                </c:if>
                    <a class="menu_link" href="<%=request.getContextPath()%>/home/pedidos.jsp">Pedidos</a>
                <a class="menu_link" href="<%=request.getContextPath()%>/home/fazerpedido.jsp">Fazer Pedido</a>
                <input class="btl" type="submit" name="enviar" value="Logout">
            </div>
        </form>
                
                
                
        <div id="main">
        <div class="login-box">
        <h2 style="">Estoque</h2>
        <form class="boxi" action="<%=request.getContextPath()%>/ServletAlterarIngrediente" method="post" autocomplete="off">
            <div class="container">
                <div class="msg" style="color: black; position: relative; right: 350px; bottom: 300px; width: 400px;">${message}</div>
               
                
                <input style="position: fixed; right: 350px; bottom:250px;" type="numbert" placeholder="IdIngrediente" name="idingrediente" value="" min="0">
                <input style="position: fixed; right: 350px; bottom:200px;" type="textt" placeholder="NomeIngrediente" name="nomeingrediente">
                <select class="cbbox" name="tipoingrediente" style="position: fixed; right: 350px; bottom:150px; height: 48px;;">
                    <option></option>
                    <option>Pao</option>
                    <option>Carne</option>
                    <option>Salada</option>
                    <option>Molho</option>
                </select> 
                <input style="position: fixed; right: 350px; bottom:100px;" type="numbert" placeholder="QuantidadeIngrediente" name="quantidadeingrediente">
                <input style="position: fixed; right: 350px; bottom:50px;" type="numbert" name="precoingrediente" placeholder="Preço" value="" min="0" step=".01">
                <input style="position: fixed; right: 470px; bottom:-200px;" class="btn" type="submit" name="enviar" value="Alterar">
                <input style="position: fixed; right: 340px; bottom:-200px;" class="btn" type="submit" name="enviar" value="Excluir">
                
                <table style="position: fixed; top: -300px; left: 000px; width: 600px;">

                         <%
                         DAOIngrediente daoIngrediente = new DAOIngrediente();
                         int i = 1;
                         float t = 0;
                         %>
                         <%
                         if(!daoIngrediente.listar().isEmpty()){%>
                         <thead style="background-color: orange;">
                         <tr>
                             <td>IDLanche</td>
                             <td>Nome</td>
                             <td>Tipo</td>
                             <td>Quantidade</td>
                             <td>Preço</td>
                         </tr>
                    </thead>
                         <%
                         List<Ingrediente> list = daoIngrediente.listar();
                         for (Ingrediente in : list){
                             DecimalFormat df = new DecimalFormat("0.00");
                         %>
                         <tbody style="border-collapse: separate;">                         
                         <tr>
                             <td><%=in.getId()%></td>
                             <td><%=in.getNome()%></td>
                             <td><%=in.getTipo()%></td>
                             <td><%=in.getQuantidade()%></td>
                             <td><%=df.format(in.getPreco())%></td>
                         </tr>
                         <%}}%>
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
