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
    public abstract List todosVeiculo();
    public abstract List buscarVeiculoPorAno(String ano);
    public Veiculo consultarPlaca(String parameter);
}
