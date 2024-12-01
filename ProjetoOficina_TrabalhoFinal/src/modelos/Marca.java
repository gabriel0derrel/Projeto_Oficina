/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author lusra
 */
public class Marca {
    private int idMarca = 0;
    private String descricao = "";
    
    public Marca(){
        
    }
    
    public Marca(int idMarca){
        this.idMarca = idMarca;
    }
    
    public Marca(int idMarca, String descricao){
        this.idMarca = idMarca;
        this.descricao = descricao;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return idMarca + "-" + descricao;
    }
    
    
}
