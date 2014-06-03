<div class="containerLogadoMenu">
    <nav class="menuAcoes">
        <ul>
            
            <li><a href="ControleReserva?action=listaReservas">Lista de Reservas</a></li>
            <%
                if (request.getSession().getAttribute("administrador") != null) {
            %>  
            <li><a href="ControleVeiculo?action=listaVeiculos">Lista de Veículos</a></li>
            <li><a href="ControleServidor?action=listaServidores">Lista de Servidores</a></li>
            <%
                    }
            %>
            <li><a href="ControleReserva?action=formularioReserva">Nova Reserva</a></li>
            <%
                if (request.getSession().getAttribute("administrador") != null) {
            %>  
            <li><a href="cadastrarServidor.jsp">Novo Servidor</a></li>
            <li><a href="cadastrarVeiculo.jsp">Novo Veículo</a></li>
            <%
                    }
            %>
            <li><a href="formPeriodo.jsp">OUTRO</a></li>
        </li>
    </nav>
</div>