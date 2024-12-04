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
import modelos.ICrud;
import modelos.Veiculo;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import modelos.Modelo;


/**
 *
 * @author Cliente
 */
public class VeiculoDAO implements ICrud<Veiculo>{
    
        //Conexao com o banco
    private Connection conexao = null;
    private final ICrud<Modelo> modeloDB = new ModeloDAO();

    public VeiculoDAO() throws Exception{
      conexao = ConexaoBD.getConexao();
      if(conexao == null) throw new Exception("ERRO DE CONEXAO");
    }

    @Override
    public void incluir(Veiculo objeto) throws Exception {
        try{
            String sql = "insert into veiculo(placa,anoFabricacao,dataRegistro,chassi,patrimonio,kilometragem,anoModelo,idModelo)"
                  + "values(?,?,?,?,?,?,?,?);";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, objeto.getPlaca());
            preparedStatement.setDate(2, new Date(objeto.getAnoFabricacao().getTime()));
            preparedStatement.setDate(3, new Date(objeto.getDataRegistro().getTime()));
            preparedStatement.setString(4, objeto.getChassi());
            if(objeto.getPatrimonio() == -1)
                preparedStatement.setNull(5, java.sql.Types.INTEGER);
            else 
                preparedStatement.setInt(5, objeto.getPatrimonio());
            
            preparedStatement.setInt(6, objeto.getKilometragem());
            preparedStatement.setDate(7, new Date(objeto.getAnoModelo().getTime()));
            preparedStatement.setInt(8, objeto.getModelo().getIdModelo());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
              throw new Exception("Incluir Persistencia: " + erro);
        }
    }

    @Override
    public void alterar(Veiculo objeto) throws Exception {
        try{
            String sql = "update veiculo set anoFabricacao = ?,dataRegistro = ?,chassi = ?,patrimonio = ?,kilometragem = ?,anoModelo = ?,idModelo = ? "
                     + "where (placa = ?)";
            consultar(objeto);
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setDate(1, new Date(objeto.getAnoFabricacao().getTime()));
            preparedStatement.setDate(2, new Date(objeto.getDataRegistro().getTime()));
            preparedStatement.setString(3, objeto.getChassi());
            if(objeto.getPatrimonio() == -1)
                preparedStatement.setNull(4, java.sql.Types.INTEGER);
            else 
                preparedStatement.setInt(4, objeto.getPatrimonio());
            
            preparedStatement.setInt(5, objeto.getKilometragem());
            preparedStatement.setDate(6, new Date(objeto.getAnoModelo().getTime()));
            preparedStatement.setInt(7, objeto.getModelo().getIdModelo());
            preparedStatement.setString(8, objeto.getPlaca());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
              throw new Exception("Alterar Persistencia: " + erro);
        } 
}
    @Override
    public Veiculo consultar(Veiculo objeto) throws Exception {
        String sql = "select * from veiculo where placa = ?";
        Veiculo objAcessorioBusca = new Veiculo();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, objeto.getPlaca());
            ResultSet rs = preparedStatement.executeQuery();
            if(!rs.isBeforeFirst()) throw new Exception("Veiculo nao encontrado");
            while(rs.next()) {
                Modelo modelo = new Modelo(rs.getInt("idModelo"));
                modelo = modeloDB.consultar(modelo);
                Integer patrimonio = (Integer) rs.getInt("patrimonio");
                if(rs.wasNull()){
                    patrimonio = null;
                }
                objAcessorioBusca = new Veiculo(rs.getString("placa"), rs.getDate("anoFabricacao"), rs.getDate("dataRegistro"),rs.getString("chassi"),patrimonio,rs.getInt("kilometragem"),rs.getDate("anoModelo"),modelo);
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
    public List<Veiculo> listar() throws Exception {
        List<Veiculo> listaDeVeiculo = new ArrayList<>();
        String sql = "select * from veiculo order by placa";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {   
                Modelo modelo = new Modelo(rs.getInt("idModelo"));
                modelo = modeloDB.consultar(modelo);
                Integer patrimonio = (Integer) rs.getInt("patrimonio");
                if(rs.wasNull()){
                    patrimonio = null;
                }
                Veiculo objVeiculo = new Veiculo(rs.getString("placa"), rs.getDate("anoFabricacao"), rs.getDate("dataRegistro"),rs.getString("chassi"),patrimonio,rs.getInt("kilometragem"),rs.getDate("anoModelo"),modelo);
                listaDeVeiculo.add(objVeiculo);
            }
            return listaDeVeiculo;
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
              throw new Exception("Listar Persistencia: " + erro);
        } 
    }
}
