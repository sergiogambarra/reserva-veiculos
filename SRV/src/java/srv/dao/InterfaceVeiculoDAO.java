/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package srv.dao;
import java.util.List;
import srv.modelo.Veiculo;

/**
 *
 * @author Paula
 */
public interface InterfaceVeiculoDAO {
 
    public abstract Veiculo buscarVeiculo(String placa);
    public abstract void salvar(Veiculo veic);
    public abstract void excluir(Veiculo veic) throws Exception;;
    public abstract void atualizar(Veiculo veic);
    public abstract void visualizar(Veiculo veic);
    public abstract List todosVeiculo(String numPagina);
    public abstract int todosVeiculosCount();
    public abstract List buscarVeiculoPorAno(String ano);
    public Veiculo consultarPlaca(String parameter);
    public abstract List buscarVeiculoPorPlaca(String placa);
    public abstract List buscarVeiculoPorRenavam(String renavam);
    public abstract List buscarVeiculoPorAnoPlaca(String ano, String placa);
    public abstract List buscarVeiculoPorAnoRenavam(String ano, String renavam);
    public abstract List buscarVeiculoPorPlacaRenavam(String placa, String renavam);
    public abstract List buscarVeiculoPorAnoPlacaRenavam(String ano, String placa, String renavam);
}
