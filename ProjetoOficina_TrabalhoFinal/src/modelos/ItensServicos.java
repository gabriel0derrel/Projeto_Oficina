/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author misuka
 */
public class ItensServicos {
    
    private int idItensServico = 0;
    private Servicos servico = null; 
    private OrdemDeServico ordem = null;
    private Funcionario funcionario = null;
    private int quantidade = 0;
    private String precoUnitario = "";
    private String precoFinal = "";

    public ItensServicos() {
    }
    
    public ItensServicos(int idItensServico) {
        this.idItensServico = idItensServico;
    }
    
    public ItensServicos(int idItensServico, Servicos servico, OrdemDeServico ordem, 
            Funcionario funcionario, int quantidade, String precoUnitario, String precoFinal) {
        this.idItensServico = idItensServico;
        this.servico = servico;
        this.ordem = ordem;
        this.funcionario = funcionario;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.precoFinal = precoFinal;
    }
    
    public int getIdItensServico() {
        return idItensServico;
    }

    public void setIdItensServico(int idItensServico) {
        this.idItensServico = idItensServico;
    }

    public Servicos getServico() {
        return servico;
    }

    public void setServico(Servicos servico) {
        this.servico = servico;
    }

    public OrdemDeServico getOrdem() {
        return ordem;
    }

    public void setOrdem(OrdemDeServico ordem) {
        this.ordem = ordem;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(String precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public String getPrecoFinal() {
        return precoFinal;
    }

    public void setPrecoFinal(String precoFinal) {
        this.precoFinal = precoFinal;
    }

    /*@Override
    public String toString() {
        return idItensServico + "-" + servico.toString() + "=>" + ordem.toString() + "=>" + funcionario.toString() + "=>" + quantidade + "=>" + precoUnitario + "=>" + precoFinal;
    }*/
    
    @Override
    public String toString() {
        return idItensServico + "-" + servico.toString() + "=>" + ordem.toString() + "=>" + funcionario.toString();
    }
    
}
