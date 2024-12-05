/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import conexao.ConexaoBD;
import java.util.ArrayList;
import java.util.List;
import modelos.Endereco;
import modelos.Oficina;
import modelos.ICrud;
import modelos.Telefone;
/**
 *
 * @author ejmcc
 */
public class OficinaDAO implements ICrud<Oficina>{
    //Conexao com o banco
    private Connection conexao = null;

    public OficinaDAO() throws Exception{
      conexao = ConexaoBD.getConexao();
      if(conexao == null) throw new Exception("ERRO DE CONEXAO");
    }

  @Override
  public void incluir(Oficina objOficina) throws Exception {
    try {
      String sql = "insert into oficina(email,nome,ddi1,ddd1,numeroTelefone1,ddi2,ddd2,numeroTelefone2,logradouro,numeroEndereco,cep,bairro,complemento,cidade,estado)"
              + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
      PreparedStatement preparedStatement = conexao.prepareStatement(sql);
      preparedStatement.setString(1, objOficina.getIdentificador_Email());
      preparedStatement.setString(2, objOficina.getNome());
      preparedStatement.setInt(3, objOficina.getTelefone1().getDdi());
      preparedStatement.setInt(4, objOficina.getTelefone1().getDdd());
      preparedStatement.setInt(5, objOficina.getTelefone1().getNumero());
      if(objOficina.getTelefone2()!=null){
        preparedStatement.setInt(6, objOficina.getTelefone2().getDdi());
        preparedStatement.setInt(7, objOficina.getTelefone2().getDdd());
        preparedStatement.setInt(8, objOficina.getTelefone2().getNumero());
      }else{
          preparedStatement.setNull(6, java.sql.Types.INTEGER);
          preparedStatement.setNull(7, java.sql.Types.INTEGER);
          preparedStatement.setNull(8, java.sql.Types.INTEGER);
      }
      preparedStatement.setString(9, objOficina.getEndereco().getLogradouro());
      preparedStatement.setInt(10, objOficina.getEndereco().getNumeroEndereco());
      preparedStatement.setString(11, objOficina.getEndereco().getCep());
      preparedStatement.setString(12, objOficina.getEndereco().getBairro());
      preparedStatement.setString(13, objOficina.getEndereco().getComplemento());
      preparedStatement.setString(14, objOficina.getEndereco().getCidade());
      preparedStatement.setString(15, objOficina.getEndereco().getEstado());
      preparedStatement.executeUpdate();
    } catch (SQLException erro) {
        //Erro do comando SQL - chave, coluna, nome da tabela, ...
        throw new Exception("SQL Erro: "+ erro.getMessage());
    } catch(Exception erro){
          throw new Exception("Incluir Persistencia: " + erro);
    } 
  }

  @Override
  public void alterar(Oficina objOficina) throws Exception {
    try {
      String sql = "update oficina set nome = ?, ddi1 = ?,ddd1 = ?,numeroTelefone1 = ?,ddi2 = ?,ddd2 = ?,numeroTelefone2 = ?,logradouro = ?,numeroEndereco = ?,cep = ?,bairro = ?,complemento = ?,cidade = ?,estado = ? "
              + "where (email = ?)";
      consultar(objOficina);
      PreparedStatement preparedStatement = conexao.prepareStatement(sql);
      preparedStatement.setString(1, objOficina.getNome());
      preparedStatement.setInt(2, objOficina.getTelefone1().getDdi());
      preparedStatement.setInt(3, objOficina.getTelefone1().getDdd());
      preparedStatement.setInt(4, objOficina.getTelefone1().getNumero());
      if(objOficina.getTelefone2()!=null){
        preparedStatement.setInt(5, objOficina.getTelefone2().getDdi());
        preparedStatement.setInt(6, objOficina.getTelefone2().getDdd());
        preparedStatement.setInt(7, objOficina.getTelefone2().getNumero());
      }else{
          preparedStatement.setNull(5, java.sql.Types.INTEGER);
          preparedStatement.setNull(6, java.sql.Types.INTEGER);
          preparedStatement.setNull(7, java.sql.Types.INTEGER);
      }
      preparedStatement.setString(8, objOficina.getEndereco().getLogradouro());
      preparedStatement.setInt(9, objOficina.getEndereco().getNumeroEndereco());
      preparedStatement.setString(10, objOficina.getEndereco().getCep());
      preparedStatement.setString(11, objOficina.getEndereco().getBairro());
      preparedStatement.setString(12, objOficina.getEndereco().getComplemento());
      preparedStatement.setString(13, objOficina.getEndereco().getCidade());
      preparedStatement.setString(14, objOficina.getEndereco().getEstado());
      preparedStatement.setString(15, objOficina.getIdentificador_Email());
      preparedStatement.executeUpdate();
    } catch (SQLException erro) {
        //Erro do comando SQL - chave, coluna, nome da tabela, ...
        throw new Exception("SQL Erro: "+ erro.getMessage());
    } catch(Exception erro){
          throw new Exception("Alterar Servicos Persistencia: " + erro);
    }   
  }
   @Override
    public Oficina consultar(Oficina objOficina) throws Exception {
        String sql = "select * from oficina where email = ?";
        Oficina objOficinaBusca = new Oficina();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, objOficina.getIdentificador_Email());
            ResultSet rs = preparedStatement.executeQuery();
            if(!rs.isBeforeFirst()) throw new Exception("Oficina nao encontrado");
            while(rs.next()) {
                Telefone Telefone1 = new Telefone(rs.getInt("ddi1"),rs.getInt("ddd1"),rs.getInt("numeroTelefone1"));
                Telefone telefone2;
                
                Integer ddi2 = (Integer) rs.getObject("ddi2");
                Integer ddd2 = (Integer) rs.getObject("ddd2");
                Integer numeroTelefone2 = (Integer) rs.getObject("numeroTelefone2");
                if(ddi2 != null && ddd2 != null && numeroTelefone2 != null){
                    telefone2 = new Telefone(ddi2, ddd2, numeroTelefone2);
                }else{
                    telefone2 = null;
                }
                Endereco endereco = new Endereco(rs.getString("logradouro"), rs.getInt("numeroEndereco"), rs.getString("cep"), rs.getString("bairro"), rs.getString("complemento"), rs.getString("cidade"), rs.getString("estado"));
                objOficinaBusca = new Oficina(rs.getString("email"),rs.getString("nome"), Telefone1, telefone2, endereco);
            }
            return objOficinaBusca;
    } catch (SQLException erro) {
        //Erro do comando SQL - chave, coluna, nome da tabela, ...
        throw new Exception("SQL Erro: "+ erro.getMessage());
    } catch(Exception erro){
          throw new Exception("Consultar Oficina Persistencia: " + erro);
    }
    }

    @Override
    public List<Oficina> listar() throws Exception {
    List<Oficina> listaDeOficina = new ArrayList<>();
        String sql = "select * from oficina order by nome";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                Telefone Telefone1 = new Telefone(rs.getInt("ddi1"),rs.getInt("ddd1"),rs.getInt("numeroTelefone1"));
                Telefone telefone2;
                
                Integer ddi2 = (Integer) rs.getObject("ddi2");
                Integer ddd2 = (Integer) rs.getObject("ddd2");
                Integer numeroTelefone2 = (Integer) rs.getObject("numeroTelefone2");
                if(ddi2 != null && ddd2 != null && numeroTelefone2 != null){
                    telefone2 = new Telefone(ddi2, ddd2, numeroTelefone2);
                }else{
                    telefone2 = null;
                }
                Endereco endereco = new Endereco(rs.getString("logradouro"), rs.getInt("numeroEndereco"), rs.getString("cep"), rs.getString("bairro"), rs.getString("complemento"), rs.getString("cidade"), rs.getString("estado"));
                Oficina objOficina = new Oficina(rs.getString("email"), rs.getString("nome"), Telefone1, telefone2, endereco);
                listaDeOficina.add(objOficina);
            }
            return listaDeOficina;
    } catch (SQLException erro) {
        //Erro do comando SQL - chave, coluna, nome da tabela, ...
        throw new Exception("SQL Erro: "+ erro.getMessage());
    } catch(Exception erro){
          throw new Exception("Listar Oficina Persistencia: " + erro);
    }
    }
  
}
