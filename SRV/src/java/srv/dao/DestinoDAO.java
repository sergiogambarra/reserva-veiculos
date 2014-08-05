/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package srv.dao;

import java.util.List;
import org.hibernate.Session;
import srv.util.Conexao;

/**
 *
 * @author Douglas
 */
public class DestinoDAO implements InterfaceDestinoDAO {

    private Session session;

    @Override
    public List buscarDestinos() {
        session = Conexao.getInstance();
        List list = session.createQuery("from Destino").list();

        return list;
    }
}
