/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author Cliente
 */
public class Modelo {
    private int idModelo = 0;
    private String descricao = "";
    private Marca marca = null;

    public Modelo(int idModelo, String descricao,Marca marca) {
        this.idModelo = idModelo;
        this.descricao = descricao;
        this.marca = marca;
    }

    public Modelo(int idModelo) {
        this.idModelo = idModelo;
    }

    public Modelo() {
    }

    public int getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(int idModelo) {
        this.idModelo = idModelo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return idModelo + "-" + descricao + "=>" + marca.getIdMarca();
    }
    
    
}
