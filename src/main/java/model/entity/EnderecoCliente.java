
package model.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import ValidationCustom.MCPE;

@Entity
@Table(name = "endereco_cliente")

public class EnderecoCliente implements Serializable {

    private static final long serialVersionUID = 1L;
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
    private Integer idEndereco;

    @NotBlank(message="{endereco.cidade.blank.msg}")
    @Column(name = "cidade", nullable = false, length=160)
    private String cidade;
  
    @NotBlank(message="{endereco.rua.blank.msg}")
    @Column(name = "rua", nullable = false, length=200)
    private String rua;
    
    @NotBlank(message="{endereco.cep.blank.msg}")
    @MCPE
    @Column(name = "cep", nullable = false, length=12)
    private String cep;
   
    @NotBlank(message="{endereco.bairro.blank.msg}")
    @Column(name = "bairro", nullable = false, length=100)
    private String bairro;
   
    @NotBlank(message="{endereco.numero.blank.msg}")
    @Column(name = "numero", nullable = false, length=7)
    private String numero;
    
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    @OneToOne
    private Cliente Cliente;

    public Integer getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Cliente geCliente() {
        return Cliente;
    }

    public void setCliente(Cliente Cliente) {
        this.Cliente = Cliente;
    }
    
    public void atualizarDados(EnderecoCliente enderecoCliente) {
		if(enderecoCliente == null) return;
		
		if(enderecoCliente.getBairro() != null) {
			this.setBairro(enderecoCliente.getBairro());
		}
		
		if(enderecoCliente.getCep() != null) {
			this.setCep(enderecoCliente.getCep());
		}
		
		if(enderecoCliente.getCidade() != null) {
			this.setCidade(enderecoCliente.getCidade());
		}
		
		if(enderecoCliente.getNumero() != null) {
			this.setNumero(enderecoCliente.getNumero());
		}
		
		if(enderecoCliente.getRua() != null) {
			this.setRua(enderecoCliente.getRua());
		}	
	}

}
