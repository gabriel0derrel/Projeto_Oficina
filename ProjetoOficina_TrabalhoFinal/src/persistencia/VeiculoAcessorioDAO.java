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
import modelos.Acessorio;
import modelos.ICrud;
import modelos.Veiculo;
import modelos.VeiculoAcessorio;

/**
 *
 * @author Cliente
 */
public class VeiculoAcessorioDAO implements ICrud<VeiculoAcessorio>{
    //Conexao com o banco
    private Connection conexao = null;

    public VeiculoAcessorioDAO() throws Exception{
      conexao = ConexaoBD.getConexao();
      if(conexao == null) throw new Exception("ERRO DE CONEXAO");
    }

    @Override
    public void incluir(VeiculoAcessorio objeto) throws Exception {
        try {
            String sql = "insert into veiculoAcessorio(placa,idAcessorio)"
                  + "values(?,?);";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, objeto.getVeiculo().getPlaca());
            preparedStatement.setInt(2, objeto.getAcessorio().getIdAcessorio());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
              throw new Exception("Incluir Persistencia: " + erro);
        }     }

    @Override
    public void alterar(VeiculoAcessorio objeto) throws Exception {
        try {
            String sql = "update veiculoAcessorio set placa = ?,idAcessorio = ? "
                     + "where (placa = ? AND idAcessorio = ?)";
            consultar(objeto);
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, objeto.getVeiculo().getPlaca());
            preparedStatement.setInt(2, objeto.getAcessorio().getIdAcessorio());
            preparedStatement.setString(3, objeto.getVeiculo().getPlaca());
            preparedStatement.setInt(4, objeto.getAcessorio().getIdAcessorio());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
              throw new Exception("Alterar Persistencia: " + erro);
        } 
    }

    @Override
    public VeiculoAcessorio consultar(VeiculoAcessorio objeto) throws Exception {
        String sql = "select * from veiculoAcessorio where (placa = ? AND idAcessorio = ?)";
        VeiculoAcessorio objVeiculoAcessorioBusca = new VeiculoAcessorio();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, objeto.getVeiculo().getPlaca());
            preparedStatement.setInt(2, objeto.getAcessorio().getIdAcessorio());
            ResultSet rs = preparedStatement.executeQuery();
            if(!rs.isBeforeFirst()) throw new Exception("Acessorio ou veiculo nao encontrada");
            while(rs.next()) {
                Veiculo veiculo = new Veiculo(rs.getString("placa"));
                Acessorio acessorio = new Acessorio(rs.getInt("idAcessorio"));
                objVeiculoAcessorioBusca = new VeiculoAcessorio(veiculo,acessorio);
            }
            return objVeiculoAcessorioBusca;
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
              throw new Exception("Comcultar Persistencia: " + erro);
        } 
    }

    @Override
    public List<VeiculoAcessorio> listar() throws Exception {
        List<VeiculoAcessorio> listaDeVeiculoAcessorio = new ArrayList<>();
        String sql = "select * from veiculoAcessorio order by idAcessorio";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                Veiculo veiculo = new Veiculo(rs.getString("placa"));
                Acessorio acessorio = new Acessorio(rs.getInt("idAcessorio"));
                VeiculoAcessorio objVeiculoAcessorioBusca = new VeiculoAcessorio(veiculo,acessorio);
                listaDeVeiculoAcessorio.add(objVeiculoAcessorioBusca);
            }
            return listaDeVeiculoAcessorio;
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
              throw new Exception("Listar Persistencia: " + erro);
        } 
    } 
}
