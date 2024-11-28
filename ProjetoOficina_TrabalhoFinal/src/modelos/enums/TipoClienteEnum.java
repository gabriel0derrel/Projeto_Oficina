/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package modelos.enums;

/**
 *
 * @author misuka
 */
public enum TipoClienteEnum {
    PESSOAFISICA("Pessoa Física"),
    PESSOAJURIDICA("Pessoa Jurídica");

    private String descricao;

    private TipoClienteEnum(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }
    
    public static TipoClienteEnum converterDescricao(String descricao){
        for (TipoClienteEnum tipoDeCliente : TipoClienteEnum.values()) {
            if (tipoDeCliente.getDescricao().equalsIgnoreCase(descricao)) {
                return TipoClienteEnum.valueOf(tipoDeCliente.name()); // Retorna o valor do enum usando o nome
            }
        }
        throw new IllegalArgumentException("Nenhuma titulação encontrada para a descrição: " + descricao);
    }
}
