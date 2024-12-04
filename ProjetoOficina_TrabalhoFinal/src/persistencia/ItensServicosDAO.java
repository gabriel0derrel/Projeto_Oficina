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
import modelos.Funcionario;
import modelos.ICrud;
import modelos.ItensServicos;
import modelos.OrdemDeServico;
import modelos.Servicos;

/**
 *
 * @author misuka
 */
public class ItensServicosDAO implements ICrud<ItensServicos>{
    
    private Connection conexao = null;
    private ICrud<Servicos> servicoDB = new ServicoDAO();
    private ICrud<OrdemDeServico> ordemDB = new OrdemDeServicoDAO();
    private ICrud<Funcionario> funcionarioDB = new FuncionarioDAO();

    public ItensServicosDAO() throws Exception{
        conexao = ConexaoBD.getConexao();
        if(conexao == null) throw new Exception("ERRO DE CONEXAO");
    }

    @Override
    public void incluir(ItensServicos objeto) throws Exception {
        try{
            String sql = "insert into ItensServicos(idServico, idOrdem, idFuncionario, quantidade, precoUnitario, precoFinal) "
                  + "values(? ,? ,? ,? ,?::money ,?::money );";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, objeto.getServico().getIdServico());
            preparedStatement.setInt(2, objeto.getOrdem().getIdOrdem());
            preparedStatement.setInt(3, objeto.getFuncionario().getIdFuncionario());
            preparedStatement.setInt(4, objeto.getQuantidade());
            preparedStatement.setString(5, String.format("%.2f", Float.valueOf(objeto.getPrecoUnitario())));
            preparedStatement.setString(6, String.format("%.2f", Float.valueOf(objeto.getPrecoFinal())));
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw new Exception("Incluir Persistencia: " + erro);
        }
    }

    @Override
    public void alterar(ItensServicos objeto) throws Exception {
        try{
            consultar(objeto);
            String sql = "update ItensServicos set idServico = ?, idOrdem = ?, "
                    + "idFuncionario = ?, quantidade = ?, precoUnitario = ?::money, precoFinal = ?::money "
                     + "where (idItensServico = ?);";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, objeto.getServico().getIdServico());
            preparedStatement.setInt(2, objeto.getOrdem().getIdOrdem());
            preparedStatement.setInt(3, objeto.getFuncionario().getIdFuncionario());
            preparedStatement.setInt(4, objeto.getQuantidade());
            preparedStatement.setString(5, String.format("%.2f", Float.valueOf(objeto.getPrecoUnitario())));
            preparedStatement.setString(6, String.format("%.2f", Float.valueOf(objeto.getPrecoFinal())));
            preparedStatement.setInt(7, objeto.getIdItensServico());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw new Exception("Incluir Persistencia: " + erro);
        }
    }

    @Override
    public ItensServicos consultar(ItensServicos objeto) throws Exception {
        try{
            ItensServicos objBusca = new ItensServicos();
            String sql = "select * from ItensServicos where idItensServico = ?;";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, objeto.getIdItensServico());
            ResultSet rs = preparedStatement.executeQuery();
            if(!rs.isBeforeFirst()) throw new Exception("Item de Serviço nao encontrada");
            while(rs.next()){
                objBusca.setIdItensServico(rs.getInt("IdItensServico"));
                objBusca.setServico( servicoDB.consultar( new Servicos(rs.getInt("idServico")) ) );
                objBusca.setOrdem( ordemDB.consultar( new OrdemDeServico(rs.getInt("idOrdem")) ) );
                objBusca.setFuncionario( funcionarioDB.consultar( new Funcionario(rs.getInt("idFuncionario")) ) );
                objBusca.setQuantidade(rs.getInt("quantidade"));
                objBusca.setPrecoUnitario(rs.getString("precoUnitario"));
                objBusca.setPrecoFinal(rs.getString("precoFinal"));
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
    public List<ItensServicos> listar() throws Exception {
        try{
            List<ItensServicos> lista = new ArrayList<>();
            String sql = "select * from ItensServicos order by idItensServico;";
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                ItensServicos objBusca = new ItensServicos();
                objBusca.setIdItensServico(rs.getInt("IdItensServico"));
                objBusca.setServico( servicoDB.consultar( new Servicos(rs.getInt("idServico")) ) );
                objBusca.setOrdem( ordemDB.consultar( new OrdemDeServico(rs.getInt("idOrdem")) ) );
                objBusca.setFuncionario( funcionarioDB.consultar( new Funcionario(rs.getInt("idFuncionario")) ) );
                objBusca.setQuantidade(rs.getInt("quantidade"));
                objBusca.setPrecoUnitario(rs.getString("precoUnitario"));
                objBusca.setPrecoFinal(rs.getString("precoFinal"));
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
