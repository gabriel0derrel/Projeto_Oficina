/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import conexao.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelos.ICrud;
import modelos.ItensPeca;
import modelos.OrdemDeServico;
import modelos.Pecas;

/**
 *
 * @author misuka
 */
public class ItensPecaDAO implements ICrud<ItensPeca>{

    private Connection conexao = null;
    private ICrud<Pecas> pecaDB = new PecaDAO();
    private ICrud<OrdemDeServico> ordemDB = new OrdemDeServicoDAO();

    public ItensPecaDAO() throws Exception{
        conexao = ConexaoBD.getConexao();
        if(conexao == null) throw new Exception("ERRO DE CONEXAO");
    }

    @Override
    public void incluir(ItensPeca objeto) throws Exception {
        try{
            String sql = "insert into ItensPeca(idOrdem, idPeca, valorTotal, valorUnitario, quantidade) "
                  + "values(? ,? ,?::money ,?::money ,? );";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, objeto.getOrdem().getIdOrdem());
            preparedStatement.setInt(2, objeto.getPeca().getIdPeca());
            preparedStatement.setString(3, String.format("%.2f", Float.valueOf(objeto.getValorTotal())));
            preparedStatement.setString(4, String.format("%.2f", Float.valueOf(objeto.getValorUnitario())));
            preparedStatement.setInt(5, objeto.getQuantidade());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw new Exception("Incluir Persistencia: " + erro);
        }    
    }

    @Override
    public void alterar(ItensPeca objeto) throws Exception {
        try{
            consultar(objeto);
            String sql = "update ItensPeca set idOrdem = ?, idPeca = ?, valorTotal = ?::money, valorUnitario = ?::money, quantidade = ? "
                  + "where (idItensPeca = ?);";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, objeto.getOrdem().getIdOrdem());
            preparedStatement.setInt(2, objeto.getPeca().getIdPeca());
            preparedStatement.setString(3, String.format("%.2f", Float.valueOf(objeto.getValorTotal())));
            preparedStatement.setString(4, String.format("%.2f", Float.valueOf(objeto.getValorUnitario())));
            preparedStatement.setInt(5, objeto.getQuantidade());
            preparedStatement.setInt(6, objeto.getIdItensPeca());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw new Exception("Alterar Persistencia: " + erro);
        }
    }

    @Override
    public ItensPeca consultar(ItensPeca objeto) throws Exception {
        try{
            ItensPeca objBusca = new ItensPeca();
            String sql = "select * from ItensPeca where idItensPeca = ?;";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, objeto.getIdItensPeca());
            ResultSet rs = preparedStatement.executeQuery();
            if(!rs.isBeforeFirst()) throw new Exception("Item de Serviço nao encontrada");
            while(rs.next()){
                objBusca.setIdItensPeca(rs.getInt("idItensPeca"));
                objBusca.setPeca( pecaDB.consultar( new Pecas(rs.getInt("idPeca")) ) );
                objBusca.setOrdem( ordemDB.consultar( new OrdemDeServico(rs.getInt("idOrdem")) ) );
                objBusca.setQuantidade(rs.getInt("quantidade"));
                objBusca.setValorUnitario(rs.getString("valorUnitario"));
                objBusca.setValorTotal(rs.getString("valorTotal"));
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
    public List<ItensPeca> listar() throws Exception {
        try{
            List<ItensPeca> lista = new ArrayList<>();
            String sql = "select * from ItensPeca order by idItensPeca; ";
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                ItensPeca objBusca = new ItensPeca();
                objBusca.setIdItensPeca(rs.getInt("idItensPeca"));
                objBusca.setPeca( pecaDB.consultar( new Pecas(rs.getInt("idPeca")) ) );
                objBusca.setOrdem( ordemDB.consultar( new OrdemDeServico(rs.getInt("idOrdem")) ) );
                objBusca.setQuantidade(rs.getInt("quantidade"));
                objBusca.setValorUnitario(rs.getString("valorUnitario"));
                objBusca.setValorTotal(rs.getString("valorTotal"));
                lista.add(objBusca);
            }
            return lista;
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw new Exception("Listar Persistencia: " + erro);
        }
    }
    
}
