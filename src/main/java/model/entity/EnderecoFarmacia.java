
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
@Table(name = "endereco_farmacia")
public class EnderecoFarmacia implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
    private Integer idEndereco;

    @NotBlank(message="{endereco.cidade.blank.msg}")
    @Column(name = "cidade", nullable = false, length=160)
    private String cidade;
 
    @NotBlank(message="{endereco.bairro.blank.msg}")
    @Column(name = "bairro", nullable = false, length=100)
    private String bairro;
  
    @NotBlank(message="{endereco.cep.blank.msg}")
    @MCPE
    @Column(name = "cep", nullable = false, length=9)
    private String cep;
  
    @NotBlank(message="{endereco.rua.blank.msg}")
    @Column(name = "rua", nullable = false, length=200)
    private String rua;
  
    @NotBlank(message="{endereco.numero.blank.msg}")
    @Column(name = "numero", nullable = false, length=7)
    private String numero;
    
    @JoinColumn(name = "id_farmacia", referencedColumnName = "id")
    @OneToOne
    private Farmacia Farmacia;

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

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Farmacia getFarmacia() {
        return Farmacia;
    }

    public void setFarmacia(Farmacia Farmacia) {
        this.Farmacia = Farmacia;
    }

    public void atualizarDados(EnderecoFarmacia enderecoFarmacia) {
		if(enderecoFarmacia == null) return;
		
		if(enderecoFarmacia.getBairro() != null) {
			this.setBairro(enderecoFarmacia.getBairro());
		}
		
		if(enderecoFarmacia.getCep() != null) {
			this.setCep(enderecoFarmacia.getCep());
		}
		
		if(enderecoFarmacia.getCidade() != null) {
			this.setCidade(enderecoFarmacia.getCidade());
		}
		
		if(enderecoFarmacia.getNumero() != null) {
			this.setNumero(enderecoFarmacia.getNumero());
		}
		
		if(enderecoFarmacia.getRua() != null) {
			this.setRua(enderecoFarmacia.getRua());
		}	
	}
}
