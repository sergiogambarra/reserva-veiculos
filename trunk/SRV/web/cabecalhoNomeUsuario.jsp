<%@page import="srv.modelo.Servidor"%>
<%
                        Servidor servidor = new Servidor();
                        if (request.getSession().getAttribute("administrador") != null) {
                            servidor = (Servidor) request.getSession().getAttribute("administrador");
                        } else if (request.getSession().getAttribute("servidor") != null) {
                            servidor = (Servidor) request.getSession().getAttribute("servidor");
                        }
                    %>
                    <div class="cabecalhoUsuario">Bem vindo, <%= servidor.getNome()%></div>