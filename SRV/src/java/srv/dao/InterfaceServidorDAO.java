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
 
    public abstract List buscarServidor(String matriculaSIAPE);
    public abstract void salvar(Servidor serv);
    public abstract void excluir(Servidor serv);
    public abstract void atualizar(Servidor serv);
    public abstract List visualizar(Servidor serv);
    public abstract List todosServidor();
    public List<Servidor> consultarMatricula(String parameter);
    public abstract List<String> editarServidorSelecionarEstado(String ufAtual);
    public abstract List<String> editarEstadoCivil(String ecAtual);
}
