/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.controle;

import br.edu.ifrs.classes.Cliente;
import br.edu.ifrs.classes.PessoaFisica;
import br.edu.ifrs.classes.PessoaJuridica;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author fernando
 */
@WebServlet(name = "ControleCliente", urlPatterns = {"/ControleCliente"})
public class ControleCliente extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
         try {
             
        String acao = request.getParameter("action");
            /* String form = request.getParameter("frm");
            System.out.println("NOME DA PÁGINA DO FORMULÁRIO: "+form);
            */
            System.out.println("Ação a ser executada: " + acao);
            
             if(acao.equals("CC")){
                String form = request.getParameter("frm");
                if(form == null){
                    request.getRequestDispatcher("cadastrocompra.jsp").forward(request, response);
                }else{
                    System.out.println("NOME DO FORMULÁRIO: "+form);
                }
        }else if(acao.equals("AC")){
                try {
                    Cliente c;
                    c = new PessoaFisica();

                    int codcli = Integer.parseInt(request.getParameter("codcli"));

                    c.setCodcli(codcli);
                    c.buscarDados();
                    if (c.getCodcli() == -1) {
                        c = new PessoaJuridica();
                        c.setCodcli(codcli);
                        c.buscarDados();
                    }

                    request.setAttribute("cliente", c);
                    request.getRequestDispatcher("formAtualizarClientes.jsp").forward(request, response);
                } catch(Exception e) {            
                    request.setAttribute("mensagem", e.getMessage());
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
            }else if(acao.equals("EC")){
                
                try {
                    int codcli = Integer.parseInt(request.getParameter("codcli"));

                    Cliente cli = new Cliente();
                    cli.setCodcli(codcli);
                    cli.excluir();

                    request.getRequestDispatcher("sucesso.jsp").forward(request, response);
                } catch (Exception e) {
                    request.setAttribute("mensagem", e.getMessage());
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
             
          }else if(acao.equals("frmccli")){
                    String nome;
                    String telefone;
                    String endereco;
                    String regmel;
                    String cpf;
                    String rg;
                    String datadenascimento;
                    String cnpj;
                    String inscricaoestadual;
                    String inscricaomunicipal;
                    String datafundacao;

                    Calendar x = Calendar.getInstance();
                    nome = request.getParameter("nome");
                    telefone = request.getParameter("telefone"); //Float.parseFloat()
                    endereco = request.getParameter("endereco");
                    regmel = request.getParameter("regmel");
                    cpf = request.getParameter("cpf"); //Float.parseFloat()
                    rg = request.getParameter("rg"); //Float.parseFloat()
                    datadenascimento = request.getParameter("datadenascimento"); //Float.parseFloat()
                    x.set(Integer.parseInt(datadenascimento.substring(6, 10)), 
                                  Integer.parseInt(datadenascimento.substring(3, 5))-1, 
                                  Integer.parseInt(datadenascimento.substring(0, 2)));
                    cnpj = request.getParameter("cnpj"); //Float.parseFloat()
                    inscricaoestadual = request.getParameter("inscricaoestadual"); //Float.parseFloat()
                    inscricaomunicipal = request.getParameter("inscricaomunicipal");  //Float.parseFloat()
                    datafundacao = request.getParameter("datafundacao"); //Float.parseFloat()
                    x.set(Integer.parseInt(datafundacao.substring(6, 10)), 
                                  Integer.parseInt(datafundacao.substring(3, 5))-1, 
                                  Integer.parseInt(datafundacao.substring(0, 2)));


               if (regmel.equals("f")){

                PessoaFisica pf = new PessoaFisica();
                pf.setCPF(cpf);
                pf.setRG(rg);
                pf.setDataNascimento(x);
                pf.setNome(nome);
                pf.setEndereco(endereco);
                pf.setTelefone(telefone);

                pf.Cadastrar();

               } else {

                PessoaJuridica pj = new PessoaJuridica();
                pj.setCNPJ(cnpj);
                pj.setInscricaoEstadual(inscricaoestadual);
                pj.setInscricaoMunicipal(inscricaomunicipal);
                pj.setDataFundacao(x);
                pj.setEndereco(endereco);
                pj.setNome(nome);
                pj.setTelefone(telefone);

                pj.Cadastrar();
               }
            request.getRequestDispatcher("sucesso.jsp").forward(request, response);

              }else if(acao.equals("frmacli")){
                  try {
            String nome = request.getParameter("nome");
            String endereco = request.getParameter("endereco");
            String telefone = request.getParameter("telefone");
            int codcli = Integer.parseInt(request.getParameter("codcli"));
            String tipo = request.getParameter("tipo");
            
            Calendar x = Calendar.getInstance();
            
            if (tipo.equals("PF")) {
                String cpf = request.getParameter("cpf");
                String rg = request.getParameter("rg");
                String datadenascimento = request.getParameter("Datadenascimento");
                x.set(Integer.parseInt(datadenascimento.substring(6, 10)), 
                      Integer.parseInt(datadenascimento.substring(3, 5))-1, 
                      Integer.parseInt(datadenascimento.substring(0, 2)));
                
                PessoaFisica pf = new PessoaFisica();
                pf.setCodcli(codcli);
                pf.setNome(nome);
                pf.setEndereco(endereco);
                pf.setTelefone(telefone);
                pf.setCPF(cpf);
                pf.setRG(rg);
                pf.setDataNascimento(x);
                pf.atualizar();
            } else {
                String cnpj = request.getParameter("cnpj");
                String inscricaoestadual = request.getParameter("inscricaoestadual");
                String inscricaomunicipal = request.getParameter("inscricaomunicipal");
                String datafundacao = request.getParameter("datafundacao");
                x.set(Integer.parseInt(datafundacao.substring(6, 10)), 
                      Integer.parseInt(datafundacao.substring(3, 5))-1, 
                      Integer.parseInt(datafundacao.substring(0, 2)));
                
                PessoaJuridica pj = new PessoaJuridica();
                pj.setCodcli(codcli);
                pj.setNome(nome);
                pj.setEndereco(endereco);
                pj.setTelefone(telefone);
                pj.setCNPJ(cnpj);
                pj.setInscricaoEstadual(inscricaoestadual);
                pj.setInscricaoMunicipal(inscricaomunicipal);
                pj.setDataFundacao(x);
                pj.atualizar();
            }
            
            request.getRequestDispatcher("sucesso.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("mensagem", e.getMessage());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        }
              }else{
             try {
                Cliente c;
                c = new Cliente();
                List<Cliente> lista = c.consultar();

                request.setAttribute("listaClientes", lista);
                request.getRequestDispatcher("listaClientes.jsp").forward(request, response);
            } catch(Exception e) {            
                request.setAttribute("mensagem", e.getMessage());
                request.getRequestDispatcher("erro.jsp").forward(request, response);
            }
        }
                
            } catch (Exception e) {
                request.setAttribute( "mensagem", e.getMessage() );
                request.getRequestDispatcher("erro.jsp").forward(request, response);
            }
        }


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
