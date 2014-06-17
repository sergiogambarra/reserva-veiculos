/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package srv.dao;

import java.util.List;
import srv.modelo.Servidor;

/**
 *
 * @author Erick
 */
public interface InterfaceServidorDAO {
 
    public abstract Servidor buscarServidor(String matriculaSIAPE);
//    public abstract List buscarServidorPorNome(String nome);
    public abstract void salvar(Servidor serv);
    public abstract void excluir(Servidor serv) throws Exception;
    public abstract void atualizar(Servidor serv);
    public abstract void visualizar(Servidor serv);
    public abstract List todosServidores();
    public abstract List todosServidoresMotoristas();
    public Servidor consultarMatricula(String parameter);
    public abstract List<String> editarServidorSelecionarEstado(String ufAtual);
    public abstract List<String> editarEstadoCivil(String ecAtual);
    public void alterarSenha(String matriculaSIAPE, String novaSenha);
}
