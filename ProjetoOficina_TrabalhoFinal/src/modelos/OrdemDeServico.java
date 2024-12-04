/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.util.Date;
import modelos.enums.StatusEnum;

/**
 *
 * @author misuka
 */
public class OrdemDeServico {
    
    private int idOrdem = 0;
    private StatusEnum status = null;
    private Date dataInicio = null;
    private Date dataFim = null;
    private String valorTotal = "";
    private String valorPago = "";
    private String diferenca = "";
    private Veiculo veiculo = null;

    public OrdemDeServico(int idOrdem, String status, Date dataInicio, Date dataFim, 
            String valorTotal, String valorPago, String diferenca, Veiculo veiculo) {
        this.idOrdem = idOrdem;
        this.status = StatusEnum.converterDescricao(status);
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.valorTotal = valorTotal;
        this.valorPago = valorPago;
        this.diferenca = diferenca;
        this.veiculo = veiculo;
    }

    public OrdemDeServico(int idOrdem) {
        this.idOrdem = idOrdem;
    }

    public OrdemDeServico() {
    }
    
    public int getIdOrdem() {
        return idOrdem;
    }

    public void setIdOrdem(int idOrdem) {
        this.idOrdem = idOrdem;
    }

    public String getStatus() {
        return status.getDescricao();
    }

    public void setStatus(String status) {
        this.status = StatusEnum.converterDescricao(status);
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

    public String getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(String valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getValorPago() {
        return valorPago;
    }

    public void setValorPago(String valorPago) {
        this.valorPago = valorPago;
    }

    public String getDiferenca() {
        return diferenca;
    }

    public void setDiferenca(String diferenca) {
        this.diferenca = diferenca;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    /*@Override
    public String toString() {
        return idOrdem + "-" + status + "=>" + dataInicio + "=>" + dataFim + "=>" + valorTotal + "=>" + valorPago + "=>" + diferenca + "=>" + veiculo;
    }*/

    @Override
    public String toString() {
        return idOrdem + "-" + status + "=>" + veiculo;
    }
    
}
