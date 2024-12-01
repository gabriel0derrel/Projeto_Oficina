/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.util.Date;

/**
 *
 * @author lusra
 */
public class Acessorio {
    private int idAcessorio = 0;
    private Date ano = null;
    private String descricao = "";
    
    public Acessorio(){
        
    }
    
    public Acessorio(int idAcessorio){
        this.idAcessorio = idAcessorio;
    }
    
    public Acessorio(int idAcessorio, Date ano, String descricao){
        this.idAcessorio = idAcessorio;
        this.ano = ano;
        this.descricao = descricao;
    }

    public int getIdAcessorio() {
        return idAcessorio;
    }

    public void setIdAcessorio(int idAcessorio) {
        this.idAcessorio = idAcessorio;
    }

    public Date getAno() {
        return ano;
    }

    public void setAno(Date ano) {
        this.ano = ano;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return idAcessorio + "-" + ano + "=>" + descricao;
    }
    
    
}
