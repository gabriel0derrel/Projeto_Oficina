/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.util.Date;

/**
 *
 * @author Cliente
 */
public class Veiculo {
    private String placa = "";
    private Date anoFabricacao = null;
    private Date dataRegistro = null;
    private String chassi = "";
    private int patrimanio = 0;
    private int kilometragem = 0;
    private Date anoModelo = null;
    private Modelo modelo = null;

    public Veiculo(String placa) {
        this.placa = placa;
    }

    public Veiculo(String placa,Date anoFabricacao,Date dataRegistro,String chassi,int patrimanio,int kilometragem,Date anoModelo,Modelo modelo) {
    this.placa = placa;
    this.anoFabricacao = anoFabricacao;
    this.dataRegistro = dataRegistro;
    this.chassi = chassi;
    this.patrimanio = patrimanio;
    this.kilometragem = kilometragem;
    this.anoModelo = anoModelo;
    this.modelo = modelo;
    }

    public Veiculo() {
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Date getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(Date anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public int getPatrimanio() {
        return patrimanio;
    }

    public void setPatrimanio(int patrimanio) {
        this.patrimanio = patrimanio;
    }

    public int getKilometragem() {
        return kilometragem;
    }

    public void setKilometragem(int kilometragem) {
        this.kilometragem = kilometragem;
    }

    public Date getAnoModelo() {
        return anoModelo;
    }

    public void setAnoModelo(Date anoModelo) {
        this.anoModelo = anoModelo;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return placa + "-" + anoFabricacao + "=>" + dataRegistro + "=>" + chassi + "=>" + patrimanio + "=>" + kilometragem + "=>" + anoModelo + "=>" + modelo.getIdModelo();
    }
    
    
}
