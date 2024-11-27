/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author Cliente
 */
public class Servicos {
    private int IdServico = 0;
    private String descricaoServico = "";
    private String precoServico = "";

    public Servicos(int IdServico) {
        this.IdServico = IdServico;
    }
    
    public Servicos(int IdServico, String descricaoServico,String precoServico) {
    this.IdServico = IdServico;
    this.descricaoServico = descricaoServico;
    this.precoServico = precoServico;
    }

    public Servicos() {
    }

    public int getIdServico() {
        return IdServico;
    }

    public void setIdServico(int IdServico) {
        this.IdServico = IdServico;
    }

    public String getDescricaoServico() {
        return descricaoServico;
    }

    public void setDescricaoServico(String descricaoServico) {
        this.descricaoServico = descricaoServico;
    }

    public String getPrecoServico() {
        return precoServico;
    }

    public void setPrecoServico(String precoServico) {
        this.precoServico = precoServico;
    }

    @Override
    public String toString() {
        return IdServico + "-" + descricaoServico + " => " + precoServico;
    }
    
    
    
}

