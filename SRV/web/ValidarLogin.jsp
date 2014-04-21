<%
    if (request.getSession().getAttribute("servidor")== null  &&  request.getSession().getAttribute("administrador")== null) {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
%>