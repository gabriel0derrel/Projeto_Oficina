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
import modelos.Servicos;


/**
 *
 * @author Cliente
 */
public class ServicoDAO implements ICrud<Servicos>{
        //Conexao com o banco
    private Connection conexao = null;

    public ServicoDAO() throws Exception{
      conexao = ConexaoBD.getConexao();
      if(conexao == null) throw new Exception("ERRO DE CONEXAO");
    }

    @Override
    public void incluir(Servicos objServicos) throws Exception {
    try {
      String sql = "insert into servico(descricao,preco)"
              + "values(?,?::money);";
      String money = String.format("%.2f", Float.valueOf(objServicos.getPrecoServico()));
      PreparedStatement preparedStatement = conexao.prepareStatement(sql);
      preparedStatement.setString(1, objServicos.getDescricaoServico());
      preparedStatement.setString(2, money);
      preparedStatement.executeUpdate();
    } catch (SQLException erro) {
        //Erro do comando SQL - chave, coluna, nome da tabela, ...
        throw new Exception("SQL Erro: "+ erro.getMessage());
    } catch(Exception erro){
          throw new Exception("Incluir Persistencia: " + erro);
    } 
    }

    @Override
    public void alterar(Servicos objServicos) throws Exception {
    try {
      String sql = "update servico set descricao = ?, preco = ?::money "
              + "where (idServico = ?)";
      String money = String.format("%.2f", Float.valueOf(objServicos.getPrecoServico()));
      PreparedStatement preparedStatement = conexao.prepareStatement(sql);
      preparedStatement.setString(1, objServicos.getDescricaoServico());
      preparedStatement.setString(2, money);
      preparedStatement.setInt(3, objServicos.getIdServico());
      preparedStatement.executeUpdate(); 
          } catch (SQLException erro) {
        //Erro do comando SQL - chave, coluna, nome da tabela, ...
        throw new Exception("SQL Erro: "+ erro.getMessage());
    } catch(Exception erro){
          throw new Exception("Incluir Persistencia: " + erro);
    } 
    }

    @Override
    public Servicos consultar(Servicos objServicos) throws Exception {
        String sql = "select * from servico where idServico = ?";
        Servicos servicoBusca = new Servicos();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, objServicos.getIdServico());
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
               servicoBusca = new Servicos(rs.getInt("idServico"), rs.getString("descricao"), rs.getString("preco"));
            }
            return servicoBusca;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Servicos> listar() throws Exception {
    List<Servicos> listaDeServico = new ArrayList<>();
    String sql = "select * from servico order by idServico";
    try {
        Statement statement = conexao.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while(rs.next()) {
           Servicos servicoLista = new Servicos(rs.getInt("idServico"), rs.getString("descricao"), rs.getString("preco"));
            listaDeServico.add(servicoLista);
        }
        return listaDeServico;
    } catch (SQLException e) {
        e.printStackTrace();
    }     
    return null;
    }
}
