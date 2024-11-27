/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author eduar
 */
public class Endereco {
	private String logradouro = "";
	private int numeroEndereco = 0;
	private String complemento = "";
	private String cep = "";
        private String cidade = "";
        private String estado = "";
        private String bairro = "";
	public Endereco(String logradouro, int numeroEndereco,String cep,String bairro, String complemento, String cidade, String estado) throws Exception{
            if(logradouro.isEmpty())throw new Exception("o logradouro nao pode estar vazio");
            this.logradouro = logradouro;
            if(numeroEndereco<=0)throw new Exception("o numero nao pode ser menor ou igual a 0");
            this.numeroEndereco = numeroEndereco;
            if(complemento.isEmpty())throw new Exception("o complemento nao pode estar vazio");
            this.complemento = complemento;
            if(cep.isEmpty())throw new Exception("o cep ao pode estar vazio");
            this.cep = cep;
            if(cidade.isEmpty())throw new Exception("a cidade nao pode estar vazia");
            this.cidade = cidade;
            if(estado.isEmpty())throw new Exception("o estado nao pode estar vazia");
            this.estado = estado; 
            if(bairro.isEmpty())throw new Exception("o bairro nao pode estar vazia");
            this.bairro = bairro; 
	}
	public Endereco(){
	}
	public String getLogradouro() {
            return logradouro;
	}
	public void setLogradouro(String logradouro) throws Exception{
            if(logradouro.isEmpty())throw new Exception("o logradouro nao pode estar vazio");
            this.logradouro = logradouro;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) throws Exception{
            if(complemento.isEmpty())throw new Exception("o complemento nao pode estar vazio");
            this.complemento = complemento;
	}
	public String getCep() {
            return cep;
	}
	public void setCep(String cep) throws Exception{
            if(cep.isEmpty())throw new Exception("o cep nao pode estar vazio");
            this.cep = cep;
	}
        public String getCidade() {
            return cidade;
        }
        public void setCidade(String cidade) throws Exception{
            if(cidade.isEmpty())throw new Exception("a cidade nao pode estar vazia");
            this.cidade = cidade;
        }
        public String getEstado() {
            return estado;
        }
        public void setEstado(String estado) throws Exception{
            if(estado.isEmpty())throw new Exception("o estado nao pode estar vazia");
            this.estado = estado;
        }

    public int getNumeroEndereco() {
        return numeroEndereco;
    }

    public void setNumeroEndereco(int numeroEndereco) {
        this.numeroEndereco = numeroEndereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    @Override
    public String toString() {
        return "logradouro=" + logradouro + "\n numeroEndereco=" + numeroEndereco + "\n complemento=" + complemento + "\n cep=" + cep + "\n cidade=" + cidade + "\n estado=" + estado + "\n bairro=" + bairro;
    }


        
 
}
