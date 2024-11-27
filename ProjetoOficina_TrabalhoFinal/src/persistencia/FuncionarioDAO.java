/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;
import java.sql.Connection;
import conexao.ConexaoBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelos.Funcionario;
import modelos.ICrud;
import modelos.Telefone;
/**
 *
 * @author ejmcc
 */
public class FuncionarioDAO implements ICrud<Funcionario>{
    //Conexao com o banco
    private Connection conexao = null;

    public FuncionarioDAO() throws Exception{
      conexao = ConexaoBD.getConexao();
      if(conexao == null) throw new Exception("ERRO DE CONEXAO");
    }

    @Override
    public void incluir(Funcionario objeto) throws Exception {
        try {
            String sql = "insert into funcionario(nome,email,ddi,ddd,numero)"
                  + "values(?,?,?,?,?);";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, objeto.getNome());
            preparedStatement.setString(2, objeto.getEmail());
            preparedStatement.setInt(3, objeto.getTelefone().getDdi());
            preparedStatement.setInt(4, objeto.getTelefone().getDdd());
            preparedStatement.setInt(5, objeto.getTelefone().getNumero());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
              throw new Exception("Incluir Persistencia: " + erro);
        } 
    }

    @Override
    public void alterar(Funcionario objeto) throws Exception {
        try {
            String sql = "update funcionario set nome = ?,email = ?,ddi = ?,ddd = ?,numero = ? "
                     + "where (idFuncionario = ?)";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, objeto.getNome());
            preparedStatement.setString(2, objeto.getEmail());
            preparedStatement.setInt(3, objeto.getTelefone().getDdi());
            preparedStatement.setInt(4, objeto.getTelefone().getDdd());
            preparedStatement.setInt(5, objeto.getTelefone().getNumero());
            preparedStatement.setInt(6, objeto.getIdFuncionario());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
              throw new Exception("Alterar Persistencia: " + erro);
        } 
    }

    @Override
    public Funcionario consultar(Funcionario objeto) throws Exception {
        String sql = "select * from funcionario where idFuncionario = ?";
        Funcionario objFuncionarioBusca = new Funcionario();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, objeto.getIdFuncionario());
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                Telefone Telefone = new Telefone(rs.getInt("ddi"),rs.getInt("ddd"),rs.getInt("numero"));
                objFuncionarioBusca = new Funcionario(rs.getInt("idFuncionario"),rs.getString("nome"), rs.getString("email"), Telefone);
            }
            return objFuncionarioBusca;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Funcionario> listar() throws Exception {
        List<Funcionario> listaDeFuncionario = new ArrayList<>();
        String sql = "select * from funcionario order by nome";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                Telefone Telefone = new Telefone(rs.getInt("ddi"),rs.getInt("ddd"),rs.getInt("numero"));
                Funcionario objFuncionario = new Funcionario(rs.getInt("idfuncionario"), rs.getString("nome"), rs.getString("email"), Telefone);
                listaDeFuncionario.add(objFuncionario);
            }
            return listaDeFuncionario;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
  
}
