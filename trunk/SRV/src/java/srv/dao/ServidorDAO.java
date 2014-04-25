/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package srv.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import srv.modelo.Servidor;
import srv.util.Conexao;

/**
 *
 * @author Erick
 */
public class ServidorDAO implements InterfaceServidorDAO {

    private Session session;

    public List buscarServidor(String matriculaSIAPE) {
        session = Conexao.getInstance();
        Query query = session.createQuery("from Servidor l where l.matriculaSIAPE like :matricula_siape");
        List list = query.setString("matricula_siape", matriculaSIAPE).list();

        return list;
    }

    public void salvar(Servidor serv) {
        session = Conexao.getInstance();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(serv);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
    }

    public void excluir(Servidor serv) {
        session = Conexao.getInstance();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete(serv);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void atualizar(Servidor serv) {
        session = Conexao.getInstance();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(serv);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
    }

    public List visualizar(Servidor serv) {
        session = Conexao.getInstance();
        List list = session.createQuery("from Servidor").list();
        return list;
    }

    public List todosServidor() {
        session = Conexao.getInstance();
        List list = session.createQuery("from Servidor").list();
        return list;
    }

    public List<Servidor> consultarMatricula(String matriculaSIAPE) {
        session = Conexao.getInstance();
        Query query = session.createQuery("from Servidor l where l.matriculaSIAPE like :matricula_siape");
        List list = query.setString("matricula_siape", matriculaSIAPE).list();

        return list;
    }

    @Override
    public List<String> editarServidorSelecionarEstado(String ufAtual) {
        List<String> comboBox = new ArrayList<String>();
        comboBox.add("<option value=\"ac\">Acre</option>");
        comboBox.add("<option value=\"al\">Alagoas</option>");
        comboBox.add("<option value=\"ap\">Amapá</option>");
        comboBox.add("<option value=\"am\">Amazonas</option>");
        comboBox.add("<option value=\"ba\">Bahia</option>");
        comboBox.add("<option value=\"ce\">Ceará</option>");
        comboBox.add("<option value=\"df\">Distrito Federal</option>");
        comboBox.add("<option value=\"es\">Espirito Santo</option>");
        comboBox.add("<option value=\"go\">Goiás</option>");
        comboBox.add("<option value=\"ma\">Maranhão</option>");
        comboBox.add("<option value=\"ms\">Mato Grosso do Sul</option>");
        comboBox.add("<option value=\"mt\">Mato Grosso</option>");
        comboBox.add("<option value=\"mg\">Minas Gerais</option>");
        comboBox.add("<option value=\"pa\">Pará</option>");
        comboBox.add("<option value=\"pb\">Paraíba</option>");
        comboBox.add("<option value=\"pr\">Paraná</option>");
        comboBox.add("<option value=\"pe\">Pernambuco</option>");
        comboBox.add("<option value=\"pi\">Piauí</option>");
        comboBox.add("<option value=\"rj\">Rio de Janeiro</option>");
        comboBox.add("<option value=\"rn\">Rio Grande do Norte</option>");
        comboBox.add("<option value=\"rs\">Rio Grande do Sul</option>");
        comboBox.add("<option value=\"ro\">Rondônia</option>");
        comboBox.add("<option value=\"rr\">Roraima</option>");
        comboBox.add("<option value=\"sc\">Santa Catarina</option>");
        comboBox.add("<option value=\"sp\">São Paulo</option>");
        comboBox.add("<option value=\"se\">Sergipe</option>");
        comboBox.add("<option value=\"to\">Tocantins</option>");

        List<String> listAux = new ArrayList<String>();
        listAux.add("ac");
        listAux.add("al");
        listAux.add("ap");
        listAux.add("am");
        listAux.add("ba");
        listAux.add("ce");
        listAux.add("df");
        listAux.add("es");
        listAux.add("go");
        listAux.add("ma");
        listAux.add("ms");
        listAux.add("mt");
        listAux.add("mg");
        listAux.add("pa");
        listAux.add("pb");
        listAux.add("pr");
        listAux.add("pe");
        listAux.add("pi");
        listAux.add("rj");
        listAux.add("rn");
        listAux.add("rs");
        listAux.add("ro");
        listAux.add("rr");
        listAux.add("sc");
        listAux.add("sp");
        listAux.add("se");
        listAux.add("to");
        
        for (int i = 0; i < listAux.size(); i++) {
            if(listAux.get(i).equals(ufAtual)){
                String test = comboBox.get(i).replaceFirst("\\\">", "\\\" selected>");
                comboBox.add(i+1, test);
                comboBox.remove(i);
                break;
            }
        }
       
        return comboBox;
    }
    
    @Override
    public List<String> editarEstadoCivil(String ecAtual) {
        List<String> comboBox = new ArrayList<String>();
        
        comboBox.add("<option value=\"sol\">Solteiro</option>");
        comboBox.add("<option value=\"cas\">Casado</option>");
        comboBox.add("<option value=\"viu\">Viúvo</option>");
        comboBox.add("<option value=\"sep\">Separado</option>");

        List<String> listAux = new ArrayList<String>();
        
        listAux.add("sol");
        listAux.add("cas");
        listAux.add("viu");
        listAux.add("sep");
        
        for (int i = 0; i < listAux.size(); i++) {
            if(listAux.get(i).equals(ecAtual)){
                String test = comboBox.get(i).replaceFirst("\\\">", "\\\" selected>");
                comboBox.add(i+1, test);
                comboBox.remove(i);
                break;
            }
        }
       
        return comboBox;
    }
    
}
