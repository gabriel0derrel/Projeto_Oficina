/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author ejmcc
 */
public class Oficina {
  //Atributos
  private String identificador_Email = "";
  private String nome = "";
  private Telefone Telefone1 = null;
  private Telefone Telefone2 = null;
  private Endereco endereco = null; 

  public Oficina(String identificador_Email) {
      this.identificador_Email = identificador_Email;
  }

    public Oficina() {
    }
  
  public Oficina( String identificador_Email, String nome, Telefone Telefone1,
                             Telefone Telefone2,Endereco endereco) {
    this.identificador_Email = identificador_Email;
    this.nome = nome;
    this.Telefone1 = Telefone1;
    this.Telefone2 = Telefone2;
    this.endereco = endereco;
  }

    public String getIdentificador_Email() {
        return identificador_Email;
    }

    public void setIdentificador_Email(String identificador_Email) {
        this.identificador_Email = identificador_Email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Telefone getTelefone1() {
        return Telefone1;
    }

    public void setTelefone1(Telefone Telefone1) {
        this.Telefone1 = Telefone1;
    }

    public Telefone getTelefone2() {
        return Telefone2;
    }

    public void setTelefone2(Telefone Telefone2) {
        this.Telefone2 = Telefone2;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }


  @Override
  public String toString() {
    return identificador_Email + "-" + nome + " => " + Telefone1.toString() + " => " + Telefone2.toString()+ " => " + Telefone2.toString();
  }
  
  
}
