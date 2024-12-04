/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package modelos.enums;

/**
 *
 * @author misuka
 */
public enum StatusEnum {
    //'Orçamento','Aprovado','Execução','Finalizado','Pago'
    ORCAMENTO("Orçamento"),
    APROVADO("Aprovado"),
    EXECUCAO("Execução"),
    FINALIZADO("Finalizado"),
    PAGO("Pago");

    private String descricao;

    private StatusEnum(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }
    
    public static StatusEnum converterDescricao(String descricao){
        for (StatusEnum tipoDeStatus : StatusEnum.values()) {
            if (tipoDeStatus.getDescricao().equalsIgnoreCase(descricao)) {
                return StatusEnum.valueOf(tipoDeStatus.name()); // Retorna o valor do enum usando o nome
            }
        }
        throw new IllegalArgumentException("Nenhuma titulação encontrada para a descrição: " + descricao);
    }
}
