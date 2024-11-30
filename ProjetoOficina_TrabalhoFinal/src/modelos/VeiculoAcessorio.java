/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author Cliente
 */
public class VeiculoAcessorio {
    private Veiculo veiculo = null;
    private Acessorio acessorio =  null;

    public VeiculoAcessorio(Veiculo veiculo,Acessorio acessorio) {
        this.veiculo = veiculo;
        this.acessorio = acessorio;
    }

    public VeiculoAcessorio() {
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Acessorio getAcessorio() {
        return acessorio;
    }

    public void setAcessorio(Acessorio acessorio) {
        this.acessorio = acessorio;
    }

    @Override
    public String toString() {
        return veiculo + "-" + acessorio;
    }
    
    
}
