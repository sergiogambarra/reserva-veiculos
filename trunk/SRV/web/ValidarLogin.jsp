<%
    if (request.getSession().getAttribute("funcionario")== null  &&  request.getSession().getAttribute("administrador")== null) {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
%>