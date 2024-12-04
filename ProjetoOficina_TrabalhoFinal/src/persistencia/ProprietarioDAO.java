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
import modelos.Cliente;
import modelos.ICrud;
import modelos.Proprietario;
import modelos.Veiculo;

/**
 *
 * @author misuka
 */
public class ProprietarioDAO implements ICrud<Proprietario>{
    
    private Connection conexao = null;
    private final ICrud<Cliente> clienteDB = new ClienteDAO();
    private final ICrud<Veiculo> veiculoDB = new VeiculoDAO();

    public ProprietarioDAO() throws Exception{
        conexao = ConexaoBD.getConexao();
        if(conexao == null) throw new Exception("ERRO DE CONEXAO");
    }
    
    
    @Override
    public void incluir(Proprietario objeto) throws Exception {
        try{
            verificarSeHaUmProprietárioEmAberto(objeto);
            String sql = "insert into proprietario(dataInicio, dataFim, idCliente, placa)"
                  + "values(?,?,?,?);";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setDate(1, new Date(objeto.getDataInicio().getTime()));
            if(objeto.getDataFim() != null){
                preparedStatement.setDate(2, new Date(objeto.getDataFim().getTime()));
            }else{
                preparedStatement.setNull(2, java.sql.Types.DATE);
            }
            preparedStatement.setInt(3, objeto.getCliente().getIdCliente());
            preparedStatement.setString(4, objeto.getVeiculo().getPlaca());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw new Exception("Incluir Persistencia: " + erro);
        }
    }

    @Override
    public void alterar(Proprietario objeto) throws Exception {
        try{
            consultar(objeto);
            String sql = "update proprietario set dataInicio = ?, dataFim = ?, idCliente = ?, placa = ? "
                     + "where (idProprietario = ?);";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setDate(1, new Date(objeto.getDataInicio().getTime()));
            if(objeto.getDataFim() != null){
                preparedStatement.setDate(2, new Date(objeto.getDataFim().getTime()));
            }else{
                preparedStatement.setNull(2, java.sql.Types.DATE);
            }
            preparedStatement.setInt(3, objeto.getCliente().getIdCliente());
            preparedStatement.setString(4, objeto.getVeiculo().getPlaca());
            preparedStatement.setInt(5, objeto.getIdProprietario());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw new Exception("Alterar Persistencia: " + erro);
        }
    }

    @Override
    public Proprietario consultar(Proprietario objeto) throws Exception {
        try{
            Proprietario objBusca = new Proprietario();
            String sql = "select * from proprietario where idProprietario = ?;";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, objeto.getIdProprietario());
            ResultSet rs = preparedStatement.executeQuery();
            if(!rs.isBeforeFirst()) throw new Exception("Proprietario nao encontrado");
            while(rs.next()){
                objBusca.setIdProprietario(rs.getInt("idProprietario"));
                objBusca.setDataInicio(rs.getDate("dataInicio"));
                Date dataFim = rs.getDate("dataFim");
                if(rs.wasNull()){
                    dataFim = null;
                }
                objBusca.setDataFim(dataFim);
                Cliente cliente = new Cliente(rs.getInt("idCliente"));
                objBusca.setCliente(clienteDB.consultar(cliente));
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
    public List<Proprietario> listar() throws Exception {
        try{
            List<Proprietario> lista = new ArrayList<>();
            String sql = "select * from proprietario order by idProprietario;";
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                Proprietario objBusca = new Proprietario();
                objBusca.setIdProprietario(rs.getInt("idProprietario"));
                objBusca.setDataInicio(rs.getDate("dataInicio"));
                Date dataFim = rs.getDate("dataFim");
                if(rs.wasNull()){
                    dataFim = null;
                }
                objBusca.setDataFim(dataFim);
                Cliente cliente = new Cliente(rs.getInt("idCliente"));
                objBusca.setCliente(clienteDB.consultar(cliente));
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
    
    private void verificarSeHaUmProprietárioEmAberto(Proprietario objeto)throws Exception{
        try {
            List<Proprietario> lista = listar();
            for(Proprietario elemento : lista){
                if(elemento.getCliente().getIdCliente()==objeto.getCliente().getIdCliente()){
                    if(elemento.getVeiculo().getPlaca().equals(objeto.getVeiculo().getPlaca())){
                        if(elemento.getDataFim() == null) throw new Exception("Para o mesmo cliente ter o mesmo carro de novo, a data do fim da apropriação anterior deve ser definida");
                    }
                }
            }
        } catch (Exception erro) {
            throw erro;
        }
    }
    
}
