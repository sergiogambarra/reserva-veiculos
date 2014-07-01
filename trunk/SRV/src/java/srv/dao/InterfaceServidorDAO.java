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
    public abstract void salvar(Servidor serv);
    public abstract void excluir(Servidor serv) throws Exception;
    public abstract void atualizar(Servidor serv);
    public abstract void visualizar(Servidor serv);
    public abstract List todosServidores();
    public abstract List todosServidoresMotoristas();
    public abstract Servidor consultarMatricula(String parameter);
    public abstract List<String> editarServidorSelecionarEstado(String ufAtual);
    public abstract List<String> editarEstadoCivil(String ecAtual);
    public abstract void alterarSenha(String matriculaSIAPE, String novaSenha);
    public abstract List buscarServidorPorNome(String nome);
    public abstract List buscarServidorPorMatricula(String matricula_siape);
    public abstract List buscarServidorPorMotorista(String motorista);
    public abstract List buscarServidorPorStatus(String status);
    public abstract List buscarServidorPorNomeMatricula(String nome, String matricula_siape);
    public abstract List buscarServidorPorNomeMotorista(String nome, String motorista);
    public abstract List buscarServidorPorNomeStatus(String nome, String status);
    public abstract List buscarServidorPorMatriculaMotorista(String matricula_siape, String motorista);
    public abstract List buscarServidorPorMatriculaStatus(String matricula_siape, String status);
    public abstract List buscarServidorPorMotoristaStatus(String motorista, String status);
    public abstract List buscarServidorPorNomeMatriculaMotorista(String nome, String matricula_siape, String motorista);
    public abstract List buscarServidorPorNomeMatriculaStatus(String nome, String matricula_siape, String status);
    public abstract List buscarServidorPorNomeMotoristaStatus(String nome, String motorista, String status);
    public abstract List buscarServidorPorMatriculaMotoristaStatus(String matricula_siape, String motorista, String status);
    public List buscarServidorPorNomeMatriculaMotoristaStatus(String nome, String matricula_siape, String motorista, String status);
    
}
