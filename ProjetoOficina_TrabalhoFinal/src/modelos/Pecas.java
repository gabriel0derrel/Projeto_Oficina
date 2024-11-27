/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author Cliente
 */
public class Pecas {
    private int IdPeca = 0;
    private String descricaoPeca = "";
    private int codigoFabricante = 0;
    private String valorUnitarioPeca = "";
    private int quantidadePeca = 0;
    
    public Pecas(int IdServico) {
        this.IdPeca = IdServico;
    }
    
    public Pecas(int IdServico, String descricaoPeca,int codigoFabricante,String valorUnitarioPeca,int quantidadePeca) {
    this.IdPeca = IdServico;
    this.descricaoPeca = descricaoPeca;
    this.codigoFabricante = codigoFabricante;
    this.valorUnitarioPeca = valorUnitarioPeca;
    this.quantidadePeca = quantidadePeca;
    }

    public Pecas() {
    }

    public int getIdPeca() {
        return IdPeca;
    }

    public void setIdPeca(int IdPeca) {
        this.IdPeca = IdPeca;
    }

    public String getDescricaoPeca() {
        return descricaoPeca;
    }

    public void setDescricaoPeca(String descricaoPeca) {
        this.descricaoPeca = descricaoPeca;
    }

    public int getCodigoFabricante() {
        return codigoFabricante;
    }

    public void setCodigoFabricante(int codigoFabricante) {
        this.codigoFabricante = codigoFabricante;
    }

    public String getValorUnitarioPeca() {
        return valorUnitarioPeca;
    }

    public void setValorUnitarioPeca(String valorUnitarioPeca) {
        this.valorUnitarioPeca = valorUnitarioPeca;
    }

    public int getQuantidadePeca() {
        return quantidadePeca;
    }

    public void setQuantidadePeca(int quantidadePeca) {
        this.quantidadePeca = quantidadePeca;
    }

    @Override
    public String toString() {
        return IdPeca + "-" + descricaoPeca + "=>" + codigoFabricante + "=>" + valorUnitarioPeca + "=>" + quantidadePeca;
    }

}

