<div class="containerLogadoMenu">
    <nav class="menuAcoes">
        <ul>
            
            <li><a href="ControleReserva?action=listaReservas">Lista de Reservas</a></li>
            <%
                if (request.getSession().getAttribute("administrador") != null) {
            %>  
            <li><a href="ControleVeiculo?action=listaVeiculos&pagina=1">Lista de Veículos</a></li>
            <li><a href="ControleServidor?action=listaServidores">Lista de Servidores</a></li>
            <%
                    }
            %>
            <li><a href="ControleReserva?action=consultarDispVeiculo">Nova Reserva</a></li>
            <%
                if (request.getSession().getAttribute("administrador") != null) {
            %>  
            <li><a href="ControleServidor?action=novoServidor">Novo Servidor</a></li>
            <li><a href="ControleVeiculo?action=novoVeiculo">Novo Veículo</a></li>
            <%
                    }
            %>
        </li>
    </nav>
</div>