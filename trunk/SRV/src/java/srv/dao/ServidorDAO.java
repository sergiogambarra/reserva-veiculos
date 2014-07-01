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
import org.hibernate.exception.ConstraintViolationException;
import srv.modelo.Servidor;
import srv.util.Conexao;

/**
 *
 * @author Erick
 */
public class ServidorDAO implements InterfaceServidorDAO {

    private Session session;
    private List list;


    @Override
    public Servidor buscarServidor(String matriculaSIAPE) {
        session = Conexao.getInstance();
        Query query = session.createQuery("from Servidor s where s.matriculaSIAPE = :matricula_siape");
        Servidor s = (Servidor) query.setString("matricula_siape", matriculaSIAPE).uniqueResult();

        return s;
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

    
    @Override
    public void excluir(Servidor serv)throws Exception{
        session = Conexao.getInstance();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete(serv);
            tx.commit();
        } finally {
            session.close();
        }
    }

    @Override
    public void alterarSenha(String matriculaSIAPE, String novaSenha){
        session = Conexao.getInstance();
        int rowCount = 0;
        try {
            Transaction tx = session.beginTransaction();
            String sql = "update Servidor set senha = :senha where matricula_siape = :matriculaSIAPE";
            Query query = session.createQuery(sql);
            
            query.setString("senha", novaSenha);
            query.setString("matriculaSIAPE", matriculaSIAPE);

            rowCount = query.executeUpdate();
            tx.commit();

            session.close();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }    
    }
    
    @Override
    public void atualizar(Servidor serv) {
        session = Conexao.getInstance();
        int rowCount = 0;
        try {
            Transaction tx = session.beginTransaction();
            String sql = "update Servidor set nome = :nome, "
                    + "email = :email, sexo = :sexo, data_nascimento = :data_nascimento, cpf = :cpf,rg = :rg, orgao_expedidor = :orgao_expedidor, "
                    + "naturalidade = :naturalidade, estado = :estado, nacionalidade = :nacionalidade, estado_civil = :estado_civil, telefone1 = :telefone1, telefone2 = :telefone2, "
                    + "motorista = :motorista, cnh = :cnh, status_serv = :status_serv, informacoes = :informacoes"
                    + " where matricula_siape = :matriculaSIAPE";
            Query query = session.createQuery(sql);

            query.setString("nome", serv.getNome());
            query.setString("email", serv.getEmail());
            query.setString("sexo", serv.getSexo());
            query.setDate("data_nascimento", serv.getData_nascimento());
            query.setString("cpf", serv.getCpf());
            query.setString("rg", serv.getRg());
            query.setString("orgao_expedidor", serv.getOrgao_expedidor());
            query.setString("naturalidade", serv.getNaturalidade());
            query.setString("estado", serv.getEstado());
            query.setString("nacionalidade", serv.getNacionalidade());
            query.setString("estado_civil", serv.getEstado_civil());
            query.setString("telefone1", serv.getTelefone1());
            query.setString("telefone2", serv.getTelefone2());
            query.setBoolean("motorista", serv.isMotorista());
            query.setString("cnh", serv.getCnh());
            query.setBoolean("status_serv", serv.isStatus_serv());
            query.setString("informacoes", serv.getInformacoes());
            query.setString("matriculaSIAPE", serv.getMatriculaSIAPE());

            rowCount = query.executeUpdate();
            tx.commit();

            session.close();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
    }
 
    @Override
    public List todosServidores() {
        session = Conexao.getInstance();
        List list = session.createQuery("from Servidor").list();
        return list;
    }
    
    @Override
    public List todosServidoresMotoristas() {
        int isMotorista = 1;
        session = Conexao.getInstance();
        Query query = session.createQuery("from Servidor where motorista = :motorista");
        List list = query.setInteger("motorista", isMotorista).list();
        
        return list;
    }
    
    /* Parte especifica de parametros para consulta*/
    
     @Override
    public List buscarServidorPorNome(String nome) {
       session = Conexao.getInstance();
       Query query = session.createQuery("from Servidor s where s.nome like :nome");
       List s = query.setString("nome", "%"+nome+"%").list();

        return s;
    }
     
     @Override
    public List buscarServidorPorMatricula(String matricula_siape) {
       session = Conexao.getInstance();
    Query query = session.createQuery("from Servidor s where s.matriculaSIAPE = :matricula_siape");
       List s = query.setString("matricula_siape", matricula_siape).list();

        return s;
    }
     
    @Override
     public List buscarServidorPorMotorista(String motorista) {
       session = Conexao.getInstance();
       Query query = session.createQuery("from Servidor s where s.motorista = :motorista");
       List s = query.setString("motorista", motorista).list();

        return s;
    }

    @Override
     public List buscarServidorPorStatus(String status) {
       session = Conexao.getInstance();
       Query query = session.createQuery("from Servidor s where s.status_serv = :status_serv");
       List s = query.setString("status_serv", status).list();

        return s;
    }
    
    @Override
    public List buscarServidorPorNomeMatricula(String nome, String matricula_siape) {
       session = Conexao.getInstance();
       Query query = session.createQuery("from Servidor s where (s.nome like :nome) and (s.matriculaSIAPE = :matricula_siape)");
       List s = query.setString("nome", "%"+nome+"%").setString("matricula_siape", matricula_siape).list();

        return s;
    }
    
    @Override
    public List buscarServidorPorNomeMotorista(String nome, String motorista) {
       session = Conexao.getInstance();
       Query query = session.createQuery("from Servidor s where (s.nome like :nome) and (s.motorista = :motorista)");
       List s = query.setString("nome", "%"+nome+"%").setString("motorista", motorista).list();

        return s;
    }
    
    @Override
    public List buscarServidorPorNomeStatus(String nome, String status) {
       session = Conexao.getInstance();
       Query query = session.createQuery("from Servidor s where (s.nome like :nome) and (s.status_serv = :status_serv)");
       List s = query.setString("nome", "%"+nome+"%").setString("status_serv", status).list();

        return s;
    }
    
    @Override
    public List buscarServidorPorMatriculaMotorista(String matricula_siape, String motorista) {
       session = Conexao.getInstance();
       Query query = session.createQuery("from Servidor s where (s.matriculaSIAPE = :matricula_siape) and (s.motorista = :motorista)");
       List s = query.setString("matricula_siape", matricula_siape).setString("motorista", motorista).list();

        return s;
    }
    
    @Override
    public List buscarServidorPorMatriculaStatus(String matricula_siape, String status) {
       session = Conexao.getInstance();
       Query query = session.createQuery("from Servidor s where (s.matriculaSIAPE = :matricula_siape) and (s.status_serv = :status_serv)");
       List s = query.setString("matricula_siape", matricula_siape).setString("status_serv", status).list();

        return s;
    }
    
    @Override
    public List buscarServidorPorMotoristaStatus(String motorista, String status) {
       session = Conexao.getInstance();
       Query query = session.createQuery("from Servidor s where (s.motorista = :motorista) and (s.status_serv = :status_serv)");
       List s = query.setString("motorista", motorista).setString("status_serv", status).list();

        return s;
    }
    
    @Override
    public List buscarServidorPorNomeMatriculaMotorista(String nome, String matricula_siape, String motorista) {
       session = Conexao.getInstance();
       Query query = session.createQuery("from Servidor s where (s.nome like :nome) and (s.matriculaSIAPE = :matricula_siape) and (s.motorista = :motorista)");
       List s = query.setString("nome", "%"+nome+"%").setString("matricula_siape", matricula_siape).setString("motorista", motorista).list();

        return s;
    }
    
    @Override
    public List buscarServidorPorNomeMatriculaStatus(String nome, String matricula_siape, String status) {
       session = Conexao.getInstance();
       Query query = session.createQuery("from Servidor s where (s.nome like :nome) and (s.matriculaSIAPE = :matricula_siape) and (s.status_serv = :status_serv)");
       List s = query.setString("nome", "%"+nome+"%").setString("matricula_siape", matricula_siape).setString("status_serv", status).list();

        return s;
    }
    
    @Override
    public List buscarServidorPorNomeMotoristaStatus(String nome, String motorista, String status) {
       session = Conexao.getInstance();
       Query query = session.createQuery("from Servidor s where (s.nome like :nome) and (s.motorista = :motorista) and (s.status_serv = :status_serv)");
       List s = query.setString("nome", "%"+nome+"%").setString("motorista", motorista).setString("status_serv", status).list();

        return s;
    }
    
    @Override
    public List buscarServidorPorMatriculaMotoristaStatus(String matricula_siape, String motorista, String status) {
       session = Conexao.getInstance();
       Query query = session.createQuery("from Servidor s where (s.matriculaSIAPE = :matricula_siape) and (s.motorista = :motorista) and (s.status_serv = :status_serv)");
       List s = query.setString("matricula_siape", matricula_siape).setString("motorista", motorista).setString("status_serv", status).list();

        return s;
    }
    
    @Override
    public List buscarServidorPorNomeMatriculaMotoristaStatus(String nome, String matricula_siape, String motorista, String status) {
       session = Conexao.getInstance();
       Query query = session.createQuery("from Servidor s where (s.nome like :nome) and (s.matriculaSIAPE = :matricula_siape) and (s.motorista = :motorista) and (s.status_serv = :status_serv)");
       List s = query.setString("nome", "%"+nome+"%").setString("matricula_siape", matricula_siape).setString("motorista", motorista).setString("status_serv", status).list();

        return s;
    }
     
    //tem 15 if no total, mas tem um que foi aproveitado e está a baixo. 


    @Override
    public Servidor consultarMatricula(String matriculaSIAPE) {
        session = Conexao.getInstance();
        try {
            Query query = session.createQuery("from Servidor l where l.matriculaSIAPE = :matricula_siape");
            Servidor s = (Servidor) query.setString("matricula_siape", matriculaSIAPE).uniqueResult();
            return s;
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
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
            if (listAux.get(i).equals(ufAtual)) {
                String test = comboBox.get(i).replaceFirst("\\\">", "\\\" selected>");
                comboBox.add(i + 1, test);
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
        comboBox.add("<option value=\"div\">Divorciado</option>");
        comboBox.add("<option value=\"uni\">União Estável</option>");
        List<String> listAux = new ArrayList<String>();

        listAux.add("sol");
        listAux.add("cas");
        listAux.add("viu");
        listAux.add("sep");
        listAux.add("div");
        listAux.add("uni");

        for (int i = 0; i < listAux.size(); i++) {
            if (listAux.get(i).equals(ecAtual)) {
                String test = comboBox.get(i).replaceFirst("\\\">", "\\\" selected>");
                comboBox.add(i + 1, test);
                comboBox.remove(i);
                break;
            }
        }

        return comboBox;
    }

    @Override
    public void visualizar(Servidor serv) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}