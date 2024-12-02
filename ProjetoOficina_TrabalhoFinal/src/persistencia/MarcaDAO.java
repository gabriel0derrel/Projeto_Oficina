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
import modelos.Marca;

/**
 *
 * @author lusra
 */
public class MarcaDAO implements ICrud<Marca> {

        //Conexao com o banco
    private Connection conexao = null;

    public MarcaDAO() throws Exception{
      conexao = ConexaoBD.getConexao();
      if(conexao == null) throw new Exception("ERRO DE CONEXAO");
    }
    
    @Override
    public void incluir(Marca objeto) throws Exception {
        try {
            String sql = "insert into marca(descricao)"
                  + "values(?);";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, objeto.getDescricao());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
              throw new Exception("Incluir Persistencia: " + erro);
        } 
    }

    @Override
    public void alterar(Marca objeto) throws Exception {
        try {
            String sql = "update marca set descricao = ? "
                     + "where (idMarca = ?)";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, objeto.getDescricao());
            preparedStatement.setInt(2, objeto.getIdMarca());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
              throw new Exception("Alterar Persistencia: " + erro);
        } 
    }

    @Override
    public Marca consultar(Marca objeto) throws Exception {
        String sql = "select * from marca where idMarca = ?";
        Marca objMarcaBusca = new Marca();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, objeto.getIdMarca());
            ResultSet rs = preparedStatement.executeQuery();
            if(!rs.isBeforeFirst()) throw new Exception("Marca nao encontrada");
            while(rs.next()) {
                objMarcaBusca = new Marca(rs.getInt("idMarca"), rs.getString("descricao"));
            }
            return objMarcaBusca;
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
              throw new Exception("Consultar Persistencia: " + erro);
        } 
    }

    @Override
    public List<Marca> listar() throws Exception {
        List<Marca> listaDeMarca = new ArrayList<>();
        String sql = "select * from marca order by idMarca";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                Marca objMarca = new Marca(rs.getInt("idMarca"), rs.getString("descricao"));
                listaDeMarca.add(objMarca);
            }
            return listaDeMarca;
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
              throw new Exception("Listar Persistencia: " + erro);
        } 
    }
}
