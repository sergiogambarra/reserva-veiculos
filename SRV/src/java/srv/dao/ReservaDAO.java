/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package srv.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import srv.modelo.Reserva;
import srv.modelo.Veiculo;
import srv.util.Conexao;

/**
 *
 * @author Douglas
 */
public class ReservaDAO implements InterfaceReservaDAO {

    private Session session;

    @Override
    public void inserirReserva(Reserva reserva) {
        session = Conexao.getInstance();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(reserva);
            tx.commit();
        } catch (Exception e) {
            e.getMessage();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Veiculo> consultarDisponibilidadeVeiculo(Date dataSaida, Date dataRetorno) {
        List<Veiculo> veiculosDisponiveis = new ArrayList<Veiculo>();
        session = Conexao.getInstance();
        StringBuilder qr = new StringBuilder();

        qr.append("from Veiculo ")
                .append("where placa not in(")
                .append("select r.placa from Reserva r where(")
                .append("((r.data_saida < r.data_retorno) and (r.data_saida < ?) and ((r.data_saida >= ?) or (r.data_saida < ?)))")
                .append(" and ")
                .append("((r.data_retorno > ?) and ((r.data_retorno <= ?) or (r.data_retorno > ?)))")
                .append(")")
                .append(")");
        /*
        qr.append("from Veiculo ")
                .append("where placa not in(")
                .append("select r.placa from Reserva r where(")
                .append("((r.data_saida < r.data_retorno) and (r.data_saida < ?) and ((r.data_saida >= ?) or (r.data_saida < ?)))")
                .append(" and ")
                .append("((r.data_retorno > ?) and ((r.data_retorno <= ?) or (r.data_retorno > ?)))")
                .append(")")
                .append(")");
        */

        try {
            Query query = session.createQuery(qr.toString())
                    .setTimestamp(0, dataRetorno)
                    .setTimestamp(1, dataSaida)
                    .setTimestamp(2, dataSaida)
                    .setTimestamp(3, dataSaida)
                    .setTimestamp(4, dataRetorno)
                    .setTimestamp(5, dataRetorno);
            veiculosDisponiveis = query.list();
        } catch (Exception e) {
            session.close();
            e.getMessage();
        } finally {
            session.close();
        }

        return veiculosDisponiveis;
    }

    @Override
    public List<Veiculo> consultarDisponibilidadeVeiculo(Date dataSaida, Date dataRetorno, String idReserva) {
        List<Veiculo> veiculosDisponiveis = new ArrayList<Veiculo>();
        session = Conexao.getInstance();
        StringBuilder qr = new StringBuilder();

        qr.append("from Veiculo ")
                .append("where placa not in(")
                .append("select r.placa from Reserva r where(")
                .append("((r.data_saida < r.data_retorno) and (r.data_saida < ?) and ((r.data_saida >= ?) or (r.data_saida < ?)))")
                .append(" and ")
                .append("((r.data_retorno > ?) and ((r.data_retorno <= ?) or (r.data_retorno > ?)))")
                .append(")")
                .append("and r.id_reserva <> ?")
                .append(")");

        try {
            Query query = session.createQuery(qr.toString())
                    .setTimestamp(0, dataRetorno)
                    .setTimestamp(1, dataSaida)
                    .setTimestamp(2, dataSaida)
                    .setTimestamp(3, dataSaida)
                    .setTimestamp(4, dataRetorno)
                    .setTimestamp(5, dataRetorno)
                    .setString(6, idReserva);
            veiculosDisponiveis = query.list();
        } catch (Exception e) {
            session.close();
            e.getMessage();
        } finally {
            session.close();
        }

        return veiculosDisponiveis;
    }

    @Override
    public List listaReservas(String matricula, String numPagina) {
        session = Conexao.getInstance();
        int limite = 10;
        int offset = (Integer.parseInt(numPagina) * limite) - limite;

        try {
            Transaction tx = session.beginTransaction();
            List list;
            if (null != matricula) {

                String sql = "from Reserva r"
                        + " where r.matricula_siape = :matriculaSIAPE order by data_saida";

                Query query = session.createQuery(sql);
                query.setString("matriculaSIAPE", matricula);
                query.setFirstResult(offset);//Equivale ao OFFSET sql
                query.setMaxResults(limite);//Equivale ao LIMIT sql

                list = query.list();
                tx.commit();
            } else {
                String sql = "from Reserva r"
                        + " where r.matricula_siape <> :matriculaSIAPE order by data_saida";

                Query query = session.createQuery(sql);
                query.setString("matriculaSIAPE", matricula);
                query.setFirstResult(offset);//Equivale ao OFFSET sql
                query.setMaxResults(limite);//Equivale ao LIMIT sql

                list = query.list();
                tx.commit();
            }

            session.close();
            return list;

        } catch (HibernateException ex) {
            ex.getMessage();
        }
        return null;
    }

    @Override
    public int todasReservasCount(String matricula) {
        session = Conexao.getInstance();
        int totalRegistros = 0;
        try {
            Transaction tx = session.beginTransaction();
            List list;
            if (null != matricula) {

                String sql = "from Reserva r"
                        + " where r.matricula_siape = :matriculaSIAPE";

                Query query = session.createQuery(sql);
                query.setString("matriculaSIAPE", matricula);

                list = query.list();
                tx.commit();
            } else {
                String sql = "from Reserva r"
                        + " where r.matricula_siape <> :matriculaSIAPE";

                Query query = session.createQuery(sql);
                query.setString("matriculaSIAPE", matricula);

                list = query.list();
                tx.commit();
            }

            session.close();

            totalRegistros = list.size();
            return totalRegistros;

        } catch (HibernateException ex) {
            ex.getMessage();
        }
        return totalRegistros;
    }

    @Override
    public List buscarReservaPorSaida(String matricula_siape, String data_saida) {
        session = Conexao.getInstance();

        List list;
        String sql = "from Reserva r"
                + " where (r.matricula_siape = :matriculaSIAPE) and (r.data_saida like :data_saida)";

        Query query = session.createQuery(sql);
        query.setString("matriculaSIAPE", matricula_siape);
        query.setString("data_saida", "%" + data_saida + "%");
        list = query.list();
        return list;
    }

    @Override
    public List buscarReservaPorSaidaOutros(String matricula_siape, String data_saida) {
        session = Conexao.getInstance();
        List list;
        String sql = "from Reserva r"
                + " where (r.matricula_siape <> :matriculaSIAPE) and (r.data_saida like :data_saida)";

        Query query = session.createQuery(sql);
        query.setString("matriculaSIAPE", matricula_siape);
        query.setString("data_saida", "%" + data_saida + "%");
        list = query.list();

        return list;
    }

    @Override
    public List buscarReservaPorRetorno(String matricula_siape, String data_retorno) {
        session = Conexao.getInstance();
        List list;
        String sql = "from Reserva r"
                + " where (r.matricula_siape = :matriculaSIAPE) and (r.data_retorno like :data_retorno)";

        Query query = session.createQuery(sql);
        query.setString("matriculaSIAPE", matricula_siape);
        query.setString("data_retorno", "%" + data_retorno + "%");
        list = query.list();
        return list;
    }

    @Override
    public List buscarReservaPorRetornoOutros(String matricula_siape, String data_retorno) {
        session = Conexao.getInstance();
        List list;
        String sql = "from Reserva r"
                + " where (r.matricula_siape <> :matriculaSIAPE) and (r.data_retorno like :data_retorno)";

        Query query = session.createQuery(sql);
        query.setString("matriculaSIAPE", matricula_siape);
        query.setString("data_retorno", "%" + data_retorno + "%");
        list = query.list();

        return list;
    }

    @Override
    public List buscarReservaPorDestino(String matricula_siape, String destino) {
        session = Conexao.getInstance();
        List list;
        String sql = "from Reserva r"
                + " where (r.matricula_siape = :matriculaSIAPE) and (r.destino like :destino)";

        Query query = session.createQuery(sql);
        query.setString("matriculaSIAPE", matricula_siape);
        query.setString("destino", "%" + destino + "%");
        list = query.list();
        return list;
    }

    @Override
    public List buscarReservaPorDestinoOutros(String matricula_siape, String destino) {
        session = Conexao.getInstance();
        List list;
        String sql = "from Reserva r"
                + " where (r.matricula_siape <> :matriculaSIAPE) and (r.destino like :destino)";

        Query query = session.createQuery(sql);
        query.setString("matriculaSIAPE", matricula_siape);
        query.setString("destino", "%" + destino + "%");
        list = query.list();

        return list;
    }

    @Override
    public List buscarReservaPorSaidaRetorno(String matricula_siape, String data_saida, String data_retorno) {
        session = Conexao.getInstance();

        List list;
        String sql = "from Reserva r"
                + " where (r.matricula_siape = :matriculaSIAPE) and (r.data_saida like :data_saida) and (r.data_retorno like :data_retorno)";

        Query query = session.createQuery(sql);
        query.setString("matriculaSIAPE", matricula_siape);
        query.setString("data_saida", "%" + data_saida + "%");
        query.setString("data_retorno", "%" + data_retorno + "%");
        list = query.list();
        return list;
    }

    @Override
    public List buscarReservaPorSaidaRetornoOutros(String matricula_siape, String data_saida, String data_retorno) {
        session = Conexao.getInstance();
        List list;
        String sql = "from Reserva r"
                + " where (r.matricula_siape <> :matriculaSIAPE) and (r.data_saida like :data_saida) and (r.data_retorno like :data_retorno)";

        Query query = session.createQuery(sql);
        query.setString("matriculaSIAPE", matricula_siape);
        query.setString("data_saida", "%" + data_saida + "%");
        query.setString("data_retorno", "%" + data_retorno + "%");
        list = query.list();

        return list;
    }

    @Override
    public List buscarReservaPorSaidaDestino(String matricula_siape, String data_saida, String destino) {
        session = Conexao.getInstance();

        List list;
        String sql = "from Reserva r"
                + " where (r.matricula_siape = :matriculaSIAPE) and (r.data_saida like :data_saida) and (r.destino like :destino)";

        Query query = session.createQuery(sql);
        query.setString("matriculaSIAPE", matricula_siape);
        query.setString("data_saida", "%" + data_saida + "%");
        query.setString("destino", "%" + destino + "%");
        list = query.list();
        return list;
    }

    @Override
    public List buscarReservaPorSaidaDestinoOutros(String matricula_siape, String data_saida, String destino) {
        session = Conexao.getInstance();
        List list;
        String sql = "from Reserva r"
                + " where (r.matricula_siape <> :matriculaSIAPE) and (r.data_saida like :data_saida) and (r.destino like :destino)";

        Query query = session.createQuery(sql);
        query.setString("matriculaSIAPE", matricula_siape);
        query.setString("data_saida", "%" + data_saida + "%");
        query.setString("destino", "%" + destino + "%");
        list = query.list();

        return list;
    }

    @Override
    public List buscarReservaPorRetornoDestino(String matricula_siape, String data_retorno, String destino) {
        session = Conexao.getInstance();

        List list;
        String sql = "from Reserva r"
                + " where (r.matricula_siape = :matriculaSIAPE) and (r.data_retorno like :data_retorno) and (r.destino like :destino)";

        Query query = session.createQuery(sql);
        query.setString("matriculaSIAPE", matricula_siape);
        query.setString("data_retorno", "%" + data_retorno + "%");
        query.setString("destino", "%" + destino + "%");
        list = query.list();
        return list;
    }

    @Override
    public List buscarReservaPorRetornoDestinoOutros(String matricula_siape, String data_retorno, String destino) {
        session = Conexao.getInstance();
        List list;
        String sql = "from Reserva r"
                + " where (r.matricula_siape <> :matriculaSIAPE) and (r.data_retorno like :data_retorno) and (r.destino like :destino)";

        Query query = session.createQuery(sql);
        query.setString("matriculaSIAPE", matricula_siape);
        query.setString("data_retorno", "%" + data_retorno + "%");
        query.setString("destino", "%" + destino + "%");
        list = query.list();

        return list;
    }

    @Override
    public List buscarReservaPorSaidaRetornoDestino(String matricula_siape, String data_saida, String data_retorno, String destino) {
        session = Conexao.getInstance();

        List list;
        String sql = "from Reserva r"
                + " where (r.matricula_siape = :matriculaSIAPE) and (r.data_saida like :data_saida) and (r.data_retorno like :data_retorno) and (r.destino like :destino)";

        Query query = session.createQuery(sql);
        query.setString("matriculaSIAPE", matricula_siape);
        query.setString("data_saida", "%" + data_saida + "%");
        query.setString("data_retorno", "%" + data_retorno + "%");
        query.setString("destino", "%" + destino + "%");
        list = query.list();
        return list;
    }

    @Override
    public List buscarReservaPorSaidaRetornoDestinoOutros(String matricula_siape, String data_saida, String data_retorno, String destino) {
        session = Conexao.getInstance();
        List list;
        String sql = "from Reserva r"
                + " where (r.matricula_siape <> :matriculaSIAPE) and (r.data_saida like :data_saida) and (r.data_retorno like :data_retorno) and (r.destino like :destino)";

        Query query = session.createQuery(sql);
        query.setString("matriculaSIAPE", matricula_siape);
        query.setString("data_saida", "%" + data_saida + "%");
        query.setString("data_retorno", "%" + data_retorno + "%");
        query.setString("destino", "%" + destino + "%");
        list = query.list();

        return list;
    }

    @Override
    public List listaReservasOutros(String matricula, String numPaginaOutros) {
        session = Conexao.getInstance();
        int limite = 10;
        int offset = (Integer.parseInt(numPaginaOutros) * limite) - limite;
        try {
            Transaction tx = session.beginTransaction();
            List list;

            String sql = "from Reserva r"
                    + " where r.matricula_siape <> :matriculaSIAPE order by data_saida";

            Query query = session.createQuery(sql);
            query.setString("matriculaSIAPE", matricula);
            query.setFirstResult(offset);//Equivale ao OFFSET sql
            query.setMaxResults(limite);//Equivale ao LIMIT sql

            list = query.list();
            tx.commit();

            session.close();
            return list;

        } catch (HibernateException ex) {
            ex.getMessage();
        }
        return null;
    }

    @Override
    public int todasReservasOutrosCount(String matricula) {
        session = Conexao.getInstance();
        int totalRegistros = 0;
        try {


            Transaction tx = session.beginTransaction();
            List list;

            String sql = "from Reserva r"
                    + " where r.matricula_siape <> :matriculaSIAPE";

            Query query = session.createQuery(sql);
            query.setString("matriculaSIAPE", matricula);

            list = query.list();
            tx.commit();

            session.close();

            totalRegistros = list.size();

            return totalRegistros;

        } catch (HibernateException ex) {
            ex.getMessage();
        }
        return totalRegistros;
    }

    @Override
    public Reserva consultarID_Reserva(int id_reserva) {
        session = Conexao.getInstance();
        try {
            Query query = session.createQuery("from Reserva l where l.id_reserva = :id_reserva");
            Reserva r = (Reserva) query.setInteger("id_reserva", id_reserva).uniqueResult();

            return r;
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public void excluirReserva(Reserva reserva) {
        session = Conexao.getInstance();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete(reserva);
            tx.commit();
        } catch (HibernateException e) {
            e.getMessage();
        } finally {
            session.close();
        }
    }

    @Override
    public Reserva consultarIdReserva(int id_reserva) {
        session = Conexao.getInstance();
        Query query = session.createQuery("from Reserva l where l.id_reserva = :id_reserva");
        Reserva r = (Reserva) query.setInteger("id_reserva", id_reserva).uniqueResult();

        return r;
    }

    @Override
    public int gerarIdReserva() {
        GregorianCalendar calendar = new GregorianCalendar();
        StringBuilder idReserva = new StringBuilder();
        idReserva.append(Integer.toString(calendar.get(Calendar.YEAR)).substring(2, 4));


        if (calendar.get(Calendar.MONTH) < 10) {
            idReserva.append('0');
            idReserva.append(calendar.get(Calendar.MONTH));
        } else {
            idReserva.append(calendar.get(Calendar.MONTH));
        }
        if (calendar.get(Calendar.HOUR) < 10) {
            idReserva.append('0');
            idReserva.append(calendar.get(Calendar.HOUR));
        } else {
            idReserva.append(calendar.get(Calendar.HOUR));
        }
        if (calendar.get(Calendar.MINUTE) < 10) {
            idReserva.append('0');
            idReserva.append(calendar.get(Calendar.MINUTE));
        } else {
            idReserva.append(calendar.get(Calendar.MINUTE));
        }
        if (calendar.get(Calendar.SECOND) < 10) {
            idReserva.append('0');
            idReserva.append(calendar.get(Calendar.SECOND));
        } else {
            idReserva.append(calendar.get(Calendar.SECOND));
        }

        int id = Integer.parseInt(idReserva.toString());

        return id;
    }

    @Override
    public void atualizar(Reserva reserv) {
        session = Conexao.getInstance();
        int rowCount = 0;
        try {
            Transaction tx = session.beginTransaction();
            String sql = "update Reserva set data_saida = :data_saida, "
                    + "data_retorno = :data_retorno, placa = :placa, condutor = :condutor, matricula_siape_condutor = :matricula_siape_condutor, ocupantes = :ocupantes,"
                    + "id_destino = :id_destino, descricao_destino = :descricao_destino, reserva_datetime = :reserva_datetime "
                    + " where id_reserva = :id_reserva";
            Query query = session.createQuery(sql);

            query.setTimestamp("data_saida", reserv.getData_saida());
            query.setTimestamp("data_retorno", reserv.getData_retorno());
            query.setString("placa", reserv.getPlaca());
            query.setBoolean("condutor", reserv.getCondutor());
            query.setString("matricula_siape_condutor", reserv.getMatricula_siape_condutor());
            query.setInteger("ocupantes", reserv.getOcupantes());
            query.setInteger("id_destino", reserv.getId_destino());
            query.setString("descricao_destino", reserv.getDescricao_destino());
            query.setTimestamp("reserva_datetime", reserv.getReserva_datetime());
            query.setInteger("id_reserva", reserv.getId_reserva());

            rowCount = query.executeUpdate();
            tx.commit();

            session.close();
        } catch (HibernateException ex) {
            ex.getMessage();
        }
    }
}