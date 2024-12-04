/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.util.Date;

/**
 *
 * @author misuka
 */
public class Proprietario {
    
    private int idProprietario = 0;
    private Date dataInicio = null;
    private Date dataFim = null;
    private Cliente cliente = null;
    private Veiculo veiculo = null;

    public Proprietario(int idProprietario) {
        this.idProprietario = idProprietario;
    }

    public Proprietario(int idProprietario, Date dataInicio, Date dataFim, Cliente cliente, Veiculo veiculo) {
        this.idProprietario = idProprietario;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.cliente = cliente;
        this.veiculo = veiculo;
    }

    public Proprietario() {
    }

    public int getIdProprietario() {
        return idProprietario;
    }

    public void setIdProprietario(int idProprietario) {
        this.idProprietario = idProprietario;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    /*@Override
    public String toString() {
        return idProprietario + "-" + dataInicio + "=>" + dataFim + "=>" + cliente.toString() + "=>" + veiculo.toString();
    }*/
    
    @Override
    public String toString() {
        return idProprietario + "-" + "=>" + cliente.toString() + "=>" + veiculo.toString();
    }
}
