<%-- 
    Document   : comprovantedepagamento
    Created on : 20/05/2013, 21:08:31
    Author     : fernando
--%>

<%@page import="br.edu.ifrs.classes.Camisas"%>
<%@page import="br.edu.ifrs.classes.PessoaJuridica"%>
<%@page import="br.edu.ifrs.classes.PessoaFisica"%>
<%@page import="java.util.List"%>
<%@page import="br.edu.ifrs.classes.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<HTML>
 <HEAD>
  <TITLE> Sites de compras via Internet </TITLE>
  <meta http-equiv="Content-type" content="text/html" charset="utf-8">
  <script language="javaScript" src="js/algoritmo.js"></script>
  <link href="estilos.css" rel="stylesheet" type="text/css">
 </HEAD>

 <BODY>

  <h1> Comprovante do Pedido </h1>
   <a href="ControleCliente?action=CC">Novo Cadastro de Cliente</a><br><br>
  <hr>
  <h2> Tabela de informações do Cliente </h2>
  <table border="1" align="center">
    <tr bgcolor="yellow">
	  <th>Cliente</th>
	  <th>Telefone</th>
	  <th>Endereco</th>
          <th>Ações</th>
          <!--
          <th>CPF</th>
	  <th>RG</th>
	  <th>Datade Nascimento</th>
          <th>CNPJ</th>
	  <th>Inscricao Estadual</th>
          <th>Inscricao Municipal</th>
	  <th>Data Fundacao</th>
          -->
	</tr>
         <%
        Cliente clie = new Cliente();
        List<Cliente> x = (List<Cliente>) request.getAttribute("listaClientes");
        x = clie.consultar();
        for (int i=0; i<x.size(); i++) {
        %>
       
	<tr class="minhaclasse3">
      <td><% out.print(x.get(i).getNome()); %></td>
      <td ><% out.print(x.get(i).getTelefone()); %></td>
      <td ><% out.print(x.get(i).getEndereco()); %></td>
          <td>
                            <a href="ControleCliente?action=AC&id=<%= clie.getCodcli() %>">
                                Atualizar
                            </a>
                            <a href="ControleCliente?action=EC&id=<%= clie.getCodcli() %>">
                                Excluir
                            </a>
                        </td>
	</tr>
         <%
        }
        %>
        <%-- 
	<%
       PessoaFisica pesf = new PessoaFisica();
        List<PessoaFisica> y;
        y = pesf.consultar();
        for (int i=0; i<y.size(); i++) {
        %>
    <tr class="minhaclasse3">
      <td><% out.print(y.get(i).getCPF()); %></td>
      <td ><% out.print(y.get(i).getRG()); %></td>
      <td ><% out.print(y.get(i).getDatadeNascimento()); %></td>
	</tr>
         <%
        }
        %>
        
         <%
        PessoaJuridica pesj = new PessoaJuridica();
        List<PessoaJuridica> h;
        h = pesj.consultar();
        for (int i=0; i<h.size(); i++) {
        %>
        <tr class="minhaclasse3">
      <td><% out.print(h.get(i).getCNPJ()); %></td>
      <td ><% out.print(h.get(i).getInscricaoEstadual()); %></td>
      <td ><% out.print(h.get(i).getInscricaoMunicipal()); %></td>
      <td ><% out.print(h.get(i).getDataFundacao()); %></td>
	</tr>
         <%
        }
        %>
	--%>
  </table>
<br>
   
  <h2> Tabela de informações do Pedido </h2>
    <table border="1" align="center">
    <tr bgcolor="yellow">
	  <th>Cor</th>
          <th>Tamanho</th>
          <th>Modelo</th>
          <th>Preço</th>
	  <th>Tipo de Tecido</th>
	  <th>Estilo/regata</th>      
     </tr>
       <%
        Camisas cam = new Camisas();
        List<Camisas> a;
        a = cam.ConsultarPedido();
        for (int i=0; i<a.size(); i++) {
        %>
       
	<tr class="minhaclasse3">
      <td><% out.print(a.get(i).getCor()); %></td>
      <td ><% out.print(a.get(i).getTamanho()); %></td>
           
	</tr>
         <%
        }
        %>
 </table>
  <br>
  <a href="cadastrocompraPedido.jsp">Novo Cadastro de Pedido</a><br><br>
  <a href="index.jsp">Voltar ao inicio</a>
  
 </BODY>
</HTML>