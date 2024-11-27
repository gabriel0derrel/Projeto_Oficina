/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author misuka
 */
public class Cliente {
    private int idCliente = 0;
    private String nome = "";
    private Telefone telefone1 = null;
    private Telefone telefone2 = null;
    private String email = "";
    private Endereco endereco = null;
    private String tipoCliente = "";
    private String cpf = ""; 
    private String cnpj = "";
    private String contato = "";
    private String inscricaoEstadual = "";

    public Cliente() {
    }
    
    public Cliente(int idCliente) {
        this.idCliente = idCliente;
        nome = "";
        telefone1 = null;
        telefone2 = null;
        email = "";
        endereco = null;
        tipoCliente = "";
        cpf = ""; 
        cnpj = "";
        contato = "";
        inscricaoEstadual = "";
    }
    
    public Cliente(int idCliente, String nome, Telefone telefone1, Telefone telefone2, 
            String email, Endereco endereco, String tipoCliente, String cpf, String cnpj, 
            String contato, String inscricaoEstadual) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.telefone1 = telefone1;
        this.telefone2 = telefone2;
        this.email = email;
        this.endereco = endereco;
        this.tipoCliente = tipoCliente;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.contato = contato;
        this.inscricaoEstadual = inscricaoEstadual;
    }

    
    
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Telefone getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(Telefone telefone1) {
        this.telefone1 = telefone1;
    }

    public Telefone getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(Telefone telefone2) {
        this.telefone2 = telefone2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    @Override
    public String toString() {
        return idCliente + " - " + nome + " => " + telefone1.toString() + " => " + telefone2.toString() + " => " + email;
    }
    
    
}
