/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author misuka
 */
public class ItensPeca {
    
    private int idItensPeca = 0;
    private OrdemDeServico ordem = null;
    private Pecas peca = null; 
    private String valorTotal = "";
    private String valorUnitario = "";
    private int quantidade = 0;

    public ItensPeca(int idItensPeca) {
        this.idItensPeca = idItensPeca;
    }

    public ItensPeca(int idItensPeca, OrdemDeServico ordem, Pecas peca, String valorTotal, String precoUnitario, int quantidade) {
        this.idItensPeca = idItensPeca;
        this.ordem = ordem;
        this.peca = peca;
        this.valorTotal = valorTotal;
        this.valorUnitario = precoUnitario;
        this.quantidade = quantidade;
    }

    public ItensPeca() {
    }
    
    public int getIdItensPeca() {
        return idItensPeca;
    }

    public void setIdItensPeca(int idItensPeca) {
        this.idItensPeca = idItensPeca;
    }

    public OrdemDeServico getOrdem() {
        return ordem;
    }

    public void setOrdem(OrdemDeServico ordem) {
        this.ordem = ordem;
    }

    public Pecas getPeca() {
        return peca;
    }

    public void setPeca(Pecas peca) {
        this.peca = peca;
    }

    public String getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(String valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(String valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return  idItensPeca + "-" + ordem + "=>" + peca + "=>" + valorTotal + "=>" + valorUnitario + "=>" + quantidade;
    }
    
    
}
