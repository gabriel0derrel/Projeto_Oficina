/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author lusra
 */
public class Funcionario {
    private int idFuncionario = 0;
    private String nome = "";
    private String email = "";
    private Telefone telefone = null;
    
    public Funcionario(){
        
    }
    
    public Funcionario(int idFuncionario){
        this.idFuncionario = idFuncionario;
    }
    
    public Funcionario(int idFuncionario, String nome, String email, Telefone telefone){
        this.idFuncionario = idFuncionario;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return idFuncionario + "-" + nome + "=>" + email + "=>" + telefone;
    }
    
    
}
