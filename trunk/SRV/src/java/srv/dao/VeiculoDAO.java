/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package srv.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import srv.modelo.Veiculo;
import srv.util.Conexao;

/**
 *
 * @author Paula
 */
public class VeiculoDAO implements InterfaceVeiculoDAO {

    private Session session;

    @Override
    public Veiculo buscarVeiculo(String placa) {
        session = Conexao.getInstance();
        Query query = session.createQuery("from Veiculo l where l.placa = :placa");
        Veiculo v = (Veiculo) query.setString("placa", placa).uniqueResult();

        return v;
    }

    @Override
    public void salvar(Veiculo veic) {
        session = Conexao.getInstance();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(veic);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void excluir(Veiculo veic) throws Exception {
        session = Conexao.getInstance();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete(veic);
            tx.commit();
        } finally {
            session.close();
        }
    }

    @Override
    public void atualizar(Veiculo veic) {
        session = Conexao.getInstance();
        int rowCount = 0;
        try {
            Transaction tx = session.beginTransaction();
            String sql = "update Veiculo set ano = :ano, "
                    + "marca = :marca, modelo = :modelo, combustivel = :combustivel, renavam = :renavam, capacidade = :capacidade, "
                    + "manutencao = :manutencao, manutencao_data_inicial = :manutencao_data_inicial, "
                    + "manutencao_data_final = :manutencao_data_final "
                    + " where placa = :placa";
            Query query = session.createQuery(sql);

            query.setString("ano", veic.getAno());
            query.setString("marca", veic.getMarca());
            query.setString("modelo", veic.getModelo());
            query.setString("combustivel", veic.getCombustivel());
            query.setString("renavam", veic.getRenavam());
            query.setInteger("capacidade", veic.getCapacidade());
            query.setBoolean("manutencao", veic.isManutencao());
            query.setDate("manutencao_data_inicial", veic.getManutencao_data_inicial());
            query.setDate("manutencao_data_final", veic.getManutencao_data_final());
            query.setString("placa", veic.getPlaca());

            rowCount = query.executeUpdate();
            tx.commit();

            session.close();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List todosVeiculo(String numPagina) {
        session = Conexao.getInstance();

        int limite = 10;
        int offset = (Integer.parseInt(numPagina) * limite) - limite;

        Query query = session.createQuery("from Veiculo");
        query.setFirstResult(offset);//Equivale ao OFFSET sql
        query.setMaxResults(limite);//Equivale ao LIMIT sql

        List list = query.list();
        return list;
    }
    
    @Override
    public int todosVeiculosCount(){
        int totalRegistros = 0;
        
        session = Conexao.getInstance();
        List list = session.createQuery("from Veiculo").list();
        
        totalRegistros = list.size();
        
        return totalRegistros;  
    }

    /**
     *
     * @param placa
     * @return
     */
    @Override
    public Veiculo consultarPlaca(String placa) {
        session = Conexao.getInstance();
        try {
            Query query = session.createQuery("from Veiculo l where l.placa = :placa");
            Veiculo v = (Veiculo) query.setString("placa", placa).uniqueResult();
            return v;
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }
    //Parte especica de consulta por determinados parametros

    @Override
    public List buscarVeiculoPorAno(String ano) {
        session = Conexao.getInstance();
        Query query = session.createQuery("from Veiculo v where v.ano like :ano");
        List s = query.setString("ano", "%" + ano + "%").list();

        return s;
    }

    @Override
    public List buscarVeiculoPorPlaca(String placa) {
        session = Conexao.getInstance();
        Query query = session.createQuery("from Veiculo v where v.placa like :placa");
        List s = query.setString("placa", "%" + placa + "%").list();

        return s;
    }

    @Override
    public List buscarVeiculoPorRenavam(String renavam) {
        session = Conexao.getInstance();
        Query query = session.createQuery("from Veiculo v where v.renavam like :renavam");
        List s = query.setString("renavam", "%" + renavam + "%").list();

        return s;
    }

    @Override
    public List buscarVeiculoPorAnoPlaca(String ano, String placa) {
        session = Conexao.getInstance();
        Query query = session.createQuery("from Veiculo v where (v.ano like :ano) and (v.placa like :placa)");
        List s = query.setString("ano", "%" + ano + "%").setString("placa", "%" + placa + "%").list();

        return s;
    }

    @Override
    public List buscarVeiculoPorAnoRenavam(String ano, String renavam) {
        session = Conexao.getInstance();
        Query query = session.createQuery("from Veiculo v where (v.ano like :ano) and (v.renavam like :renavam)");
        List s = query.setString("ano", "%" + ano + "%").setString("renavam", "%" + renavam + "%").list();

        return s;
    }

    @Override
    public List buscarVeiculoPorPlacaRenavam(String placa, String renavam) {
        session = Conexao.getInstance();
        Query query = session.createQuery("from Veiculo v where (v.placa like :placa) and (v.renavam like :renavam)");
        List s = query.setString("placa", "%" + placa + "%").setString("renavam", "%" + renavam + "%").list();

        return s;
    }

    @Override
    public List buscarVeiculoPorAnoPlacaRenavam(String ano, String placa, String renavam) {
        session = Conexao.getInstance();
        Query query = session.createQuery("from Veiculo v where (v.ano like :ano) and (v.placa like :placa) and (v.renavam like :renavam)");
        List s = query.setString("ano", "%" + ano + "%").setString("placa", "%" + placa + "%").setString("renavam", "%" + renavam + "%").list();

        return s;
    }
/////////////////////////////////////Fim da consulta por parametro//////////////////////////

    @Override
    public void visualizar(Veiculo veic) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
