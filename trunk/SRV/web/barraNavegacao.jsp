<%-- 
    Document   : barraNavegacao
    Created on : 23/06/2014, 20:46:46
    Author     : Paula
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String menu = (String) session.getAttribute("menu");
    String linkmenu = (String) session.getAttribute("linkmenu");
    String sub = (String) session.getAttribute("sub");
    String linksub = (String) session.getAttribute("linksub");
    String submenu = (String) session.getAttribute("submenu");
    String submenusub = (String) session.getAttribute("submenusub");
%>

<p id="barraNavegacao"> 
    Você está em: 
    <%
        if (menu != null) {
    %>
    <a href="<%= linkmenu%>"><%= menu%></a>
    <%
        }
        if (sub != null) {
    %>
    > <a href="<%= linksub%>"><%= sub%></a>
    <%
        }
        if (submenu != null) {
    %>
    > <a href="ControleReserva?action=listaReservas"><%= submenu%></a>
    <%
        }
        if (submenusub != null) {
    %>
    > <a href="ControleReserva?action=listaReservas"><%=submenusub%></a>
    <%
        }
    %>
</p>


