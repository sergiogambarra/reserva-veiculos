/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package srv.util;

import java.util.List;
import srv.dao.ServidorDAO;
import srv.dao.VeiculoDAO;
import srv.modelo.Servidor;
import srv.modelo.Veiculo;

/**
 *
 * @author Erick
 */
public class Validacoes {
    private static String mensagemErro;

    /**
     * @return the mensagemErro
     */
    public static String getMensagemErro() {
        return mensagemErro;
    }

    /**
     * @param aMensagemErro the mensagemErro to set
     */
    public static void setMensagemErro(String aMensagemErro) {
        mensagemErro = aMensagemErro;
    }
    
    
    public static boolean ValidarLogin(String matriculaSIAPE, String senha)
    {
        if(matriculaSIAPE.equals("") || senha.equals(""))
        {
            setMensagemErro("Você não preencheu todos os campos");
            return false;
        }
        
        return true;
    }
    
    public static boolean ValidarUsuarioLogin(String matriculaSIAPE)
    {
        ServidorDAO fdao = new ServidorDAO();
        Servidor s = fdao.buscarServidor(matriculaSIAPE);
        if (s != null)
        {
            setMensagemErro("Matricula e/ou senha incorreto(s)");
            return false;  
        }
        return true;
    }
    
    public static boolean ValidarEnviarSenha(String matriculaSIAPE)
    {
        if(matriculaSIAPE.equals(""))
        {
            setMensagemErro("Você não digitou a matricula");
            return false;
        }
        
        return true;
    }
    
    public static boolean ValidarUsuarioEnviarSenha(String matriculaSIAPE)
    {
       ServidorDAO fdao = new ServidorDAO();
        Servidor s = fdao.buscarServidor(matriculaSIAPE);
        if (s != null)
        {
            setMensagemErro("Matricula incorreta");
            return false;  
        }
        return true;

    }

    public static boolean ValidarQualUsuarioLogado(String matriculaSIAPE, String matricula_siape) {
        if(matriculaSIAPE.equalsIgnoreCase(matricula_siape)){
            setMensagemErro("Operação recusada");
            return false;  
        }
        return true;
    }

    public static boolean ValidarMatriculaExiste(String matriculaSIAPE) {
        ServidorDAO fdao = new ServidorDAO();
        Servidor s = fdao.buscarServidor(matriculaSIAPE);
        if (s != null){
            setMensagemErro("Servidor já cadastrado");
            return false;  
        }
        return true;
    }

    public static boolean ValidarPlacaExiste(String placa) {
        VeiculoDAO vdao = new VeiculoDAO();
        Veiculo v = vdao.buscarVeiculo(placa);
        if (v != null){
            setMensagemErro("Placa já cadastrada");
            return false;  
        }
        return true;
    }
    
}
