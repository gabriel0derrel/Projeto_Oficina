/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.sql.Connection;
import conexao.ConexaoBD;
import java.util.List;
import modelos.Exemplo;
import modelos.ICrud;

/**
 *
 * @author misuka
 */
public class ExemploDAO implements ICrud<Exemplo>{
    
    //Conexao com o banco
    private Connection conexao = null;
    public ExemploDAO()throws Exception{
      conexao = ConexaoBD.getConexao();
      if(conexao == null) throw new Exception("ERRO DE CONEXAO");
    }
    
    @Override
    public void incluir(Exemplo objeto) throws Exception {
        //Incluir o objeto
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void alterar(Exemplo objeto) throws Exception {
        //Alterar o objeto com base na primary key dele
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Exemplo consultar(Exemplo objeto) throws Exception {
        //Recebe um objeto com apenas a Primary Key preenchida
        //Caso encontre, retorna um objeto com os dados encontrados
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Exemplo> listar() throws Exception {
        //Retorna os dados como uma list
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
