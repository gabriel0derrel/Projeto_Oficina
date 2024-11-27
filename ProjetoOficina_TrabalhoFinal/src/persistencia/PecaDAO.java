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
import modelos.Pecas;


/**
 *
 * @author Cliente
 */
public class PecaDAO implements ICrud<Pecas>{
        //Conexao com o banco
    private Connection conexao = null;

    public PecaDAO() throws Exception{
      conexao = ConexaoBD.getConexao();
      if(conexao == null) throw new Exception("ERRO DE CONEXAO");
    }

    @Override
    public void incluir(Pecas objPeca) throws Exception {
    try {
      String sql = "insert into peca(idPeca,descricao,codigoFabricante,valorUnitario,quantidade)"
              + "values(?,?,?,?::money,?);";
      String money = String.format("%.2f", Float.valueOf(objPeca.getValorUnitarioPeca()));
      PreparedStatement preparedStatement = conexao.prepareStatement(sql);
      preparedStatement.setInt(1, objPeca.getIdPeca());
      preparedStatement.setString(2, objPeca.getDescricaoPeca());
      preparedStatement.setInt(3, objPeca.getCodigoFabricante());
      preparedStatement.setString(4, money);
      preparedStatement.setInt(5, objPeca.getQuantidadePeca());
      preparedStatement.executeUpdate();
    } catch (SQLException erro) {
        //Erro do comando SQL - chave, coluna, nome da tabela, ...
        throw new Exception("SQL Erro: "+ erro.getMessage());
    } catch(Exception erro){
          throw new Exception("Incluir Persistencia: " + erro);
    } 
    }

    @Override
    public void alterar(Pecas objPeca) throws Exception {
    try {
      String sql = "update peca set descricao = ?,codigoFabricante = ?, valorUnitario = ?::money,quantidade = ? "
              + "where (idPeca = ?)";
      String money = String.format("%.2f", Float.valueOf(objPeca.getValorUnitarioPeca()));
      PreparedStatement preparedStatement = conexao.prepareStatement(sql);
      preparedStatement.setString(1, objPeca.getDescricaoPeca());
      preparedStatement.setInt(2, objPeca.getCodigoFabricante());
      preparedStatement.setString(3, money);
      preparedStatement.setInt(4, objPeca.getQuantidadePeca());
      preparedStatement.setInt(5, objPeca.getIdPeca());
      preparedStatement.executeUpdate(); 
          } catch (SQLException erro) {
        //Erro do comando SQL - chave, coluna, nome da tabela, ...
        throw new Exception("SQL Erro: "+ erro.getMessage());
    } catch(Exception erro){
          throw new Exception("Alterar Persistencia: " + erro);
    } 
}

    @Override
    public Pecas consultar(Pecas objPeca) throws Exception {
        String sql = "select * from peca where idPeca = ?";
        Pecas pecaBusca = new Pecas();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, objPeca.getIdPeca());
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
               pecaBusca = new Pecas(rs.getInt("idPeca"), rs.getString("descricao"), rs.getInt("codigoFabricante"), rs.getString("valorUnitario"), rs.getInt("quantidade"));
            }
            return pecaBusca;
        } catch (SQLException e) {
            e.printStackTrace();
        }   
        return null;
    }

    @Override
    public List<Pecas> listar() throws Exception {
    List<Pecas> listaDePeca = new ArrayList<>();
    String sql = "select * from peca order by idPeca";
    try {
        Statement statement = conexao.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while(rs.next()) {
           Pecas pecaLista = new Pecas(rs.getInt("idPeca"), rs.getString("descricao"), rs.getInt("codigoFabricante"), rs.getString("valorUnitario"), rs.getInt("quantidade"));
            listaDePeca.add(pecaLista);
        }
        return listaDePeca;
    } catch (SQLException e) {
        e.printStackTrace();
    }     
    return null;
    }

}
