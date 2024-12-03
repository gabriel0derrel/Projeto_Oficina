/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import conexao.ConexaoBD;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelos.Cliente;
import modelos.Endereco;
import modelos.ICrud;
import modelos.Telefone;

/**
 *
 * @author misuka
 */
public class ClienteDAO implements ICrud<Cliente>{
    
    private Connection conexao = null;

    public ClienteDAO() throws Exception{
        conexao = ConexaoBD.getConexao();
        if(conexao == null) throw new Exception("ERRO DE CONEXAO");
    }
    
    
    @Override
    public void incluir(Cliente objeto) throws Exception {
        try{
            String sql = "insert into cliente(nome, ddi1, ddd1, numeroTelefone1,"
                    + "ddi2,ddd2,numeroTelefone2,email,logradouro,numeroEndereco,"
                    + "cep,bairro,complemento,cidade,estado,tipoCliente,cpf,cnpj,"
                    + "contato,inscricaoEstadual) "
                    +"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, ?::tipoclienteenum ,?,?,?,?);";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, objeto.getNome());
            preparedStatement.setInt(2, objeto.getTelefone1().getDdi());
            preparedStatement.setInt(3, objeto.getTelefone1().getDdd());
            preparedStatement.setInt(4, objeto.getTelefone1().getNumero());
            if(objeto.getTelefone2() != null){
                preparedStatement.setInt(5, objeto.getTelefone2().getDdi());
                preparedStatement.setInt(6, objeto.getTelefone2().getDdd());
                preparedStatement.setInt(7, objeto.getTelefone2().getNumero());
            }
            else{
                preparedStatement.setNull(5, java.sql.Types.INTEGER);
                preparedStatement.setNull(6, java.sql.Types.INTEGER);
                preparedStatement.setNull(7, java.sql.Types.INTEGER);
            }
            preparedStatement.setString(8, objeto.getEmail());
            preparedStatement.setString(9, objeto.getEndereco().getLogradouro());
            preparedStatement.setInt(10, objeto.getEndereco().getNumeroEndereco());
            preparedStatement.setString(11, objeto.getEndereco().getCep());
            preparedStatement.setString(12, objeto.getEndereco().getBairro());
            preparedStatement.setString(13, objeto.getEndereco().getComplemento());
            preparedStatement.setString(14, objeto.getEndereco().getCidade());
            preparedStatement.setString(15, objeto.getEndereco().getEstado());
            preparedStatement.setString(16, objeto.getTipoCliente());
            if(objeto.getTipoCliente().equals("Pessoa Física")){
                preparedStatement.setString(17, objeto.getCpf());
                preparedStatement.setNull(18, java.sql.Types.VARCHAR);
                preparedStatement.setNull(19, java.sql.Types.VARCHAR);
                preparedStatement.setNull(20, java.sql.Types.VARCHAR);
            }
            else{
                preparedStatement.setNull(17, java.sql.Types.VARCHAR);
                preparedStatement.setString(18, objeto.getCnpj());
                preparedStatement.setString(19, objeto.getContato());
                preparedStatement.setString(20, objeto.getInscricaoEstadual());
            }
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro Incluir: "+ erro.getMessage());
        } catch(Exception erro){
              throw new Exception("Incluir Cliente Persistencia: " + erro.getMessage());
        } 
    }

    @Override
    public void alterar(Cliente objeto) throws Exception {
        try{
            String sql = "update cliente set nome = ?, ddi1 = ?, ddd1 = ?, numeroTelefone1 = ?,"
                    + "ddi2 = ?,ddd2 = ?,numeroTelefone2 = ?,email = ?,logradouro = ?,numeroEndereco = ?,"
                    + "cep = ?,bairro = ?,complemento = ?,cidade = ?,estado = ?,tipoCliente = ?::tipoclienteenum ,cpf = ?,cnpj = ?,"
                    + "contato = ?,inscricaoEstadual = ? where (idCliente = ?)";
            consultar(objeto);
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, objeto.getNome());
            preparedStatement.setInt(2, objeto.getTelefone1().getDdi());
            preparedStatement.setInt(3, objeto.getTelefone1().getDdd());
            preparedStatement.setInt(4, objeto.getTelefone1().getNumero());
            if(objeto.getTelefone2() != null){
                preparedStatement.setInt(5, objeto.getTelefone2().getDdi());
                preparedStatement.setInt(6, objeto.getTelefone2().getDdd());
                preparedStatement.setInt(7, objeto.getTelefone2().getNumero());
            }
            else{
                preparedStatement.setNull(5, java.sql.Types.INTEGER);
                preparedStatement.setNull(6, java.sql.Types.INTEGER);
                preparedStatement.setNull(7, java.sql.Types.INTEGER);
            }
            preparedStatement.setString(8, objeto.getEmail());
            preparedStatement.setString(9, objeto.getEndereco().getLogradouro());
            preparedStatement.setInt(10, objeto.getEndereco().getNumeroEndereco());
            preparedStatement.setString(11, objeto.getEndereco().getCep());
            preparedStatement.setString(12, objeto.getEndereco().getBairro());
            preparedStatement.setString(13, objeto.getEndereco().getComplemento());
            preparedStatement.setString(14, objeto.getEndereco().getCidade());
            preparedStatement.setString(15, objeto.getEndereco().getEstado());
            preparedStatement.setString(16, objeto.getTipoCliente());
            if(objeto.getTipoCliente().equals("Pessoa Física")){
                preparedStatement.setString(17, objeto.getCpf());
                preparedStatement.setNull(18, java.sql.Types.VARCHAR);
                preparedStatement.setNull(19, java.sql.Types.VARCHAR);
                preparedStatement.setNull(20, java.sql.Types.VARCHAR);
            }
            else{
                preparedStatement.setNull(17, java.sql.Types.VARCHAR);
                preparedStatement.setString(18, objeto.getCnpj());
                preparedStatement.setString(19, objeto.getContato());
                preparedStatement.setString(20, objeto.getInscricaoEstadual());
            }
            preparedStatement.setInt(21, objeto.getIdCliente());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro Alterar: "+ erro.getMessage());
        } catch(Exception erro){
              throw new Exception("Alterar Cliente Persistencia: " + erro.getMessage());
        } 
    }

    @Override
    public Cliente consultar(Cliente objeto) throws Exception {
        try {
            String sql = "select * from cliente where idCliente = ?";
            Cliente resultado = new Cliente();
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, objeto.getIdCliente());
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                
                int idCliente = rs.getInt("idCliente");
                String nome = rs.getString("nome");
                
                Telefone telefone1 = new Telefone(rs.getInt("ddi1"),rs.getInt("ddd1"),rs.getInt("numeroTelefone1"));
                Telefone telefone2;
                
                Integer ddi2 = (Integer) rs.getObject("ddi2");
                Integer ddd2 = (Integer) rs.getObject("ddd2");
                Integer numeroTelefone2 = (Integer) rs.getObject("numeroTelefone2");
                if(ddi2 != null && ddd2 != null && numeroTelefone2 != null){
                    telefone2 = new Telefone(ddi2, ddd2, numeroTelefone2);
                }else{
                    telefone2 = null;
                }
                
                String email = rs.getString("email");
                
                Endereco endereco = new Endereco(rs.getString("logradouro"), rs.getInt("numeroEndereco"), rs.getString("cep"), 
                        rs.getString("bairro"), rs.getString("complemento"), rs.getString("cidade"), rs.getString("estado"));
                
                String tipoCliente = rs.getString("tipoCliente");

                String cpf;
                String cnpj;
                String contato;
                String inscricaoEstadual;
                if(tipoCliente.equals("Pessoa Física")){
                    cpf = rs.getString("cpf");
                    cnpj = "";
                    contato = "";
                    inscricaoEstadual = "";
                } else{
                    cpf = "";
                    cnpj = rs.getString("cnpj");
                    contato = rs.getString("contato");
                    inscricaoEstadual = rs.getString("inscricaoEstadual");
                }
                
                resultado = new Cliente(idCliente, nome, telefone1, telefone2, email, endereco, tipoCliente, cpf, cnpj, contato, inscricaoEstadual);
            }
            
            return resultado;
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro Consultar: "+ erro.getMessage());
        } catch(Exception erro){
              throw new Exception("Consultar Clientes Persistencia: " + erro.getMessage());
        }  
    }

    @Override
    public List<Cliente> listar() throws Exception {
        try {
            String sql = "select * from cliente order by idCliente";
            List<Cliente> lista = new ArrayList<>();
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                
                int idCliente = rs.getInt("idCliente");
                String nome = rs.getString("nome");
                
                Telefone telefone1 = new Telefone(rs.getInt("ddi1"),rs.getInt("ddd1"),rs.getInt("numeroTelefone1"));
                Telefone telefone2;
                
                Integer ddi2 = (Integer) rs.getObject("ddi2");
                Integer ddd2 = (Integer) rs.getObject("ddd2");
                Integer numeroTelefone2 = (Integer) rs.getObject("numeroTelefone2");
                if(ddi2 != null && ddd2 != null && numeroTelefone2 != null){
                    telefone2 = new Telefone(ddi2, ddd2, numeroTelefone2);
                }else{
                    telefone2 = null;
                }
                
                String email = rs.getString("email");
                
                Endereco endereco; 
                endereco = new Endereco(rs.getString("logradouro"), rs.getInt("numeroEndereco"), rs.getString("cep"), 
                        rs.getString("bairro"), rs.getString("complemento"), rs.getString("cidade"), rs.getString("estado"));
                
                String tipoCliente = rs.getString("tipoCliente");

                String cpf;
                String cnpj;
                String contato;
                String inscricaoEstadual;
                if(tipoCliente.equals("Pessoa Física")){
                    cpf = rs.getString("cpf");
                    cnpj = "";
                    contato = "";
                    inscricaoEstadual = "";
                } else{
                    cpf = "";
                    cnpj = rs.getString("cnpj");
                    contato = rs.getString("contato");
                    inscricaoEstadual = rs.getString("inscricaoEstadual");
                }
                
                Cliente objetoAuxiliar = new Cliente(idCliente, nome, telefone1, telefone2, email, endereco, tipoCliente, cpf, cnpj, contato, inscricaoEstadual);
                lista.add(objetoAuxiliar);
            }
            
            return lista;
        } catch (SQLException erro) {
            //Erro do comando SQL - chave, coluna, nome da tabela, ...
            throw new Exception("SQL Erro Listar: "+ erro.getMessage());
        } catch(Exception erro){
              throw new Exception("Listar Clientes Persistencia: " + erro.getMessage());
        } 
    }
    
}
