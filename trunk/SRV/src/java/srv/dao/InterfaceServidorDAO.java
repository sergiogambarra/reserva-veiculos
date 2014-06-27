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
    public abstract List buscarServidorPorNome(String nome);
    public abstract Servidor consultarMatricula(String parameter);
    public abstract List<String> editarServidorSelecionarEstado(String ufAtual);
    public abstract List<String> editarEstadoCivil(String ecAtual);
    public void alterarSenha(String matriculaSIAPE, String novaSenha);
    public abstract List buscarServidorPorNomeconsultarMatricula(String nome,String MatriculaSiape );
    public abstract List buscarServidorPorNomeStatus(String nome, int atatus);
    public abstract List buscarServidorPorNomeNomeMotorista(String nome, String nomeMotorista);
    public abstract List buscarServidorPorNomeconsultarMatriculaStatus(String nome, String MatriculaSiape, int status );
    public abstract List buscarServidorPorNomeNomeMotoristaStatus(String nome, String nomeMotorista, int status);
    public abstract List buscarServidorPorNomeNomeMotoristaconsultarMatricula(String nome, String nomeMotorista, String MatriculaSiape);
    public abstract List buscarServidorPorNomeNomeMotoristaconsultarMatriculaStatus(String nome, String nomeMotorista, String MatriculaSiape, int status);
    public abstract List buscarServidorPorNomeMotorista(String nomeMotorista);
    public abstract List buscarServidorPorNomeMotoristaStatus(String nomeMotorista, int status);
    public abstract List buscarServidorPorNomeMotoristaconsultarMatricula(String nomeMotorista, String MatriculaSiape);
    public abstract List buscarServidorPorNomeMotoristaconsultarMatriculaStatus(String nomeMotorista, String MatriculaSiape, int status);
    public abstract List buscarServidorPorStatus(String status);
    public abstract List buscarServidorPorStatusconsultarMatricula(String MatriculaSiape, int status);
    
    
}
