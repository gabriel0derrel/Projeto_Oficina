/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import conexao.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import modelos.ICrud;
import modelos.Modelo;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import modelos.Marca;

/**
 *
 * @author Cliente
 */
public class ModeloDAO implements ICrud<Modelo>{
    
        //Conexao com o banco
    private Connection conexao = null;

    public ModeloDAO() throws Exception{
      conexao = ConexaoBD.getConexao();
      if(conexao == null) throw new Exception("ERRO DE CONEXAO");
    }

    @Override
    public void incluir(Modelo objeto) throws Exception {
        try {
            String sql = "insert into modelo(descricao,idMarca)"
                  + "values(?,?);";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, objeto.getDescricao());
            preparedStatement.setInt(2, objeto.getMarca().getIdMarca());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
              throw new Exception("Incluir Persistencia: " + erro);
        } 
    }

    @Override
    public void alterar(Modelo objeto) throws Exception {
        try {
            String sql = "update modelo set descricao = ?,idMarca = ? "
                     + "where (idModelo = ?)";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, objeto.getDescricao());
            preparedStatement.setInt(2, objeto.getMarca().getIdMarca());
            preparedStatement.setInt(3, objeto.getIdModelo());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
              throw new Exception("Alterar Persistencia: " + erro);
        } 
    }

    @Override
    public Modelo consultar(Modelo objeto) throws Exception {
        String sql = "select * from modelo where idModelo = ?";
        Modelo objModeloBusca = new Modelo();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, objeto.getIdModelo());
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                Marca marca = new Marca(rs.getInt("idMarca"));
                objModeloBusca = new Modelo(rs.getInt("idModelo"), rs.getString("descricao"),marca);
            }
            return objModeloBusca;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;   
}

    @Override
    public List<Modelo> listar() throws Exception {
        List<Modelo> listaDeMarca = new ArrayList<>();
        String sql = "select * from modelo order by idModelo";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                Marca marca = new Marca(rs.getInt("idMarca"));
                Modelo objModelo = new Modelo(rs.getInt("idModelo"), rs.getString("descricao"),marca);
                listaDeMarca.add(objModelo);
            }
            return listaDeMarca;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
