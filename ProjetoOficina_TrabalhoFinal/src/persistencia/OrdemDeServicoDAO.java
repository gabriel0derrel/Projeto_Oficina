/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import conexao.ConexaoBD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelos.ICrud;
import modelos.OrdemDeServico;
import modelos.Veiculo;

/**
 *
 * @author misuka
 */
public class OrdemDeServicoDAO implements ICrud<OrdemDeServico>{
    
    private Connection conexao = null;
    private final ICrud<Veiculo> veiculoDB = new VeiculoDAO();

    public OrdemDeServicoDAO() throws Exception{
        conexao = ConexaoBD.getConexao();
        if(conexao == null) throw new Exception("ERRO DE CONEXAO");
    }
    
    @Override
    public void incluir(OrdemDeServico objeto) throws Exception {
        try{
            String sql = "insert into OrdemDeServico(status, inicio, fim, valorTotal, valorPago, diferenca, placa) "
                  + "values(?::statusEnum ,? ,? ,?::money ,?::money ,?::money ,? );";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, objeto.getStatus());
            preparedStatement.setDate(2, new Date(objeto.getDataInicio().getTime()));
            preparedStatement.setDate(3, new Date(objeto.getDataFim().getTime()));
            preparedStatement.setString(4, String.format("%.2f", Float.valueOf(objeto.getValorTotal())));
            preparedStatement.setString(5, String.format("%.2f", Float.valueOf(objeto.getValorPago())));
            preparedStatement.setString(6, String.format("%.2f", Float.valueOf(objeto.getDiferenca())));
            preparedStatement.setString(7, objeto.getVeiculo().getPlaca());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw new Exception("Incluir Persistencia: " + erro);
        }
    }

    @Override
    public void alterar(OrdemDeServico objeto) throws Exception {
        try{
            consultar(objeto);
            String sql = "update OrdemDeServico set status = ?::statusEnum, inicio = ?, fim = ?, "
                    + "valorTotal = ?::money, valorPago = ?::money, diferenca = ?::money, placa = ? "
                     + "where (idOrdem = ?);";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, objeto.getStatus());
            preparedStatement.setDate(2, new Date(objeto.getDataInicio().getTime()));
            preparedStatement.setDate(3, new Date(objeto.getDataFim().getTime()));
            preparedStatement.setString(4, String.format("%.2f", Float.valueOf(objeto.getValorTotal())));
            preparedStatement.setString(5, String.format("%.2f", Float.valueOf(objeto.getValorPago())));
            preparedStatement.setString(6, String.format("%.2f", Float.valueOf(objeto.getDiferenca())));
            preparedStatement.setString(7, objeto.getVeiculo().getPlaca());
            preparedStatement.setInt(8, objeto.getIdOrdem());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw new Exception("Alterar Persistencia: " + erro);
        }
    }

    @Override
    public OrdemDeServico consultar(OrdemDeServico objeto) throws Exception {
        try{
            OrdemDeServico objBusca = new OrdemDeServico();
            String sql = "select * from OrdemDeServico where idOrdem = ?;";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, objeto.getIdOrdem());
            ResultSet rs = preparedStatement.executeQuery();
            if(!rs.isBeforeFirst()) throw new Exception("Ordem de Serviço nao encontrada");
            while(rs.next()){
                objBusca.setIdOrdem(rs.getInt("idOrdem"));
                objBusca.setStatus(rs.getString("status"));
                objBusca.setDataInicio(rs.getDate("inicio"));
                objBusca.setDataFim(rs.getDate("fim"));
                objBusca.setValorTotal(rs.getString("valorTotal"));
                objBusca.setValorPago(rs.getString("valorPago"));
                objBusca.setDiferenca(rs.getString("diferenca"));
                Veiculo veiculo = new Veiculo(rs.getString("placa"));
                objBusca.setVeiculo(veiculoDB.consultar(veiculo));
            }
            return objBusca;
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw new Exception("Consultar Persistencia: " + erro);
        }
    }

    @Override
    public List<OrdemDeServico> listar() throws Exception {
        try{
            List<OrdemDeServico> lista = new ArrayList<>();
            String sql = "select * from OrdemDeServico order by idOrdem;";
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                OrdemDeServico objBusca = new OrdemDeServico();
                objBusca.setIdOrdem(rs.getInt("idOrdem"));
                objBusca.setStatus(rs.getString("status"));
                objBusca.setDataInicio(rs.getDate("inicio"));
                objBusca.setDataFim(rs.getDate("fim"));
                objBusca.setValorTotal(rs.getString("valorTotal"));
                objBusca.setValorPago(rs.getString("valorPago"));
                objBusca.setDiferenca(rs.getString("diferenca"));
                Veiculo veiculo = new Veiculo(rs.getString("placa"));
                objBusca.setVeiculo(veiculoDB.consultar(veiculo));
                lista.add(objBusca);
            }
            return lista;
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw new Exception("Consultar Persistencia: " + erro);
        }
    }

    
    
}
