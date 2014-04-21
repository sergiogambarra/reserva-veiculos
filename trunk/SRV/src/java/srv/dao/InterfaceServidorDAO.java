/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package srv.dao;

import java.util.List;

/**
 *
 * @author Erick
 */
public interface InterfaceServidorDAO {
 
    public abstract List buscarServidor(String matriculaSIAPE);
}
