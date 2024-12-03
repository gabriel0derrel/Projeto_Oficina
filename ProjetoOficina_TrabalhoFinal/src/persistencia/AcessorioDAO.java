/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import conexao.ConexaoBD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import modelos.Acessorio;
import modelos.ICrud;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Statement;

/**
 *
 * @author lusra
 */
public class AcessorioDAO implements ICrud<Acessorio> {
            //Conexao com o banco
    private Connection conexao = null;

    public AcessorioDAO() throws Exception{
      conexao = ConexaoBD.getConexao();
      if(conexao == null) throw new Exception("ERRO DE CONEXAO");
    }

    @Override
    public void incluir(Acessorio objeto) throws Exception {
        try {
            String sql = "insert into acessorio(anoFabricacao,descricao)"
                  + "values(?,?);";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setDate(1, new Date(objeto.getAno().getTime()));
            preparedStatement.setString(2, objeto.getDescricao());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
              throw new Exception("Incluir Persistencia: " + erro);
        } 
    }

    @Override
    public void alterar(Acessorio objeto) throws Exception {
        try {
            String sql = "update acessorio set anoFabricacao = ?,descricao = ? "
                     + "where (idAcessorio = ?)";
            consultar(objeto);
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setDate(1, new Date(objeto.getAno().getTime()));
            preparedStatement.setString(2, objeto.getDescricao());
            preparedStatement.setInt(3, objeto.getIdAcessorio());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
              throw new Exception("Alterar Persistencia: " + erro);
        } 
    }

    @Override
    public Acessorio consultar(Acessorio objeto) throws Exception {
        String sql = "select * from acessorio where idAcessorio = ?";
        Acessorio objAcessorioBusca = new Acessorio();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, objeto.getIdAcessorio());
            ResultSet rs = preparedStatement.executeQuery();
            if(!rs.isBeforeFirst()) throw new Exception("Acessorio nao encontrada");
            while(rs.next()) {
                objAcessorioBusca = new Acessorio(rs.getInt("idAcessorio"), rs.getDate("anoFabricacao"), rs.getString("descricao"));
            }
            return objAcessorioBusca;
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
              throw new Exception("Consultar Persistencia: " + erro);
        } 
    }

    @Override
    public List<Acessorio> listar() throws Exception {
        List<Acessorio> listaDeAcessorio = new ArrayList<>();
        String sql = "select * from acessorio order by idAcessorio";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                Acessorio objAcessorio = new Acessorio(rs.getInt("idAcessorio"), rs.getDate("anoFabricacao"), rs.getString("descricao"));
                listaDeAcessorio.add(objAcessorio);
            }
            return listaDeAcessorio;
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
              throw new Exception("Listar Persistencia: " + erro);
        } 
    }
}
