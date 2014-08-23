/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package srv.util;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    public static boolean ValidarLogin(String matriculaSIAPE, String senha) {
        if (matriculaSIAPE.equals("") || senha.equals("")) {
            setMensagemErro("Você não preencheu todos os campos");
            return false;
        }

        return true;
    }

    public static boolean ValidarUsuarioLogin(String matriculaSIAPE) {
        ServidorDAO fdao = new ServidorDAO();
        Servidor s = fdao.buscarServidor(matriculaSIAPE);
        if (s != null) {
            setMensagemErro("Matricula e/ou senha incorreto(s)");
            return false;
        }
        return true;
    }

    public static boolean ValidarEnviarSenha(String matriculaSIAPE) {
        if (matriculaSIAPE.equals("")) {
            setMensagemErro("Você não digitou a matricula");
            return false;
        }

        return true;
    }

    public static boolean ValidarUsuarioEnviarSenha(String matriculaSIAPE) {
        ServidorDAO fdao = new ServidorDAO();
        Servidor s = fdao.buscarServidor(matriculaSIAPE);
        if (s != null) {
            setMensagemErro("Matricula incorreta");
            return false;
        }
        return true;

    }

    public static boolean ValidarQualUsuarioLogado(String matriculaSIAPE, String matricula_siape) {
        if (matriculaSIAPE.equalsIgnoreCase(matricula_siape)) {
            setMensagemErro("Operação recusada");
            return false;
        }
        return true;
    }

    public static boolean ValidarMatriculaExiste(String matriculaSIAPE) {
        ServidorDAO fdao = new ServidorDAO();
        Servidor s = fdao.buscarServidor(matriculaSIAPE);
        if (s != null) {
            setMensagemErro("Servidor já cadastrado");
            return false;
        }
        return true;
    }

    public static boolean ValidarPlacaExiste(String placa) {
        VeiculoDAO vdao = new VeiculoDAO();
        Veiculo v = vdao.buscarVeiculo(placa);
        if (v != null) {
            setMensagemErro("Placa já cadastrada");
            return false;
        }
        return true;
    }

    public static boolean ValidarStatusUsuario(String matriculaSIAPE) {
        ServidorDAO fdao = new ServidorDAO();
        Servidor s = fdao.buscarServidor(matriculaSIAPE);
        if (!s.isStatus_serv()) {
            setMensagemErro("Usuário inativo. Contate o administrador.");
            return false;
        }
        return true;
    }

    public static String validarDataEntradaMysql(String input) {

        Date dataFormatada = null;
        String output = null;

        try {

            dataFormatada = new SimpleDateFormat("dd/MM/yyyy").parse(input);
            output = new SimpleDateFormat("yyyy-MM-dd").format(dataFormatada);

        } catch (Exception e) {
            e.getMessage();
        }

        return output;  
    }
    
    public static String validarDataSaidaMysqlString(String input) {

        StringBuilder output = new StringBuilder();
        
        try {
            
            output.append(input.substring(8))
                .append("/")
                .append(input.substring(5,7))
                .append("/")
                .append(input.substring(0,4));
            
        } catch (Exception e) {
            e.getMessage();
        }

        return output.toString();
    }
    
    public static Date formatarDatetimeParaDate(String sDatetime){
        Date output = null;
        try {
            output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sDatetime);
        } catch (Exception e) {
            e.getMessage();
        }
        return output;        
    }
    
    public static String extrairDateParaString(Date dtDate){
        String output = null;
        try {
            output = validarDataSaidaMysqlString(new SimpleDateFormat("yyyy-MM-dd").format(dtDate));
        } catch (Exception e) {
            e.getMessage();
        }
        return output;        
    }
    
    public static String extrairHoraDeDateMysqlParaString(Date dtDate){
        String output = null;
        try {
            output = dtDate.toString().substring(11, 13) + ":" + dtDate.toString().substring(14, 16);
        } catch (Exception e) {
            e.getMessage();
        }
        return output.toString();        
    }
    
}
