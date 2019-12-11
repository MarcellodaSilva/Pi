
package model.entity;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;

@Entity(name = "Farmacia")
@Table(name = "farmacia")
@DiscriminatorValue(value="Pj")
public class Farmacia extends Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "{farmacia.nome.blank.msg}")
	@Column(name = "nome", nullable = false, length = 170)
	private String nome;

	@NotBlank(message = "{farmacia.cnpj.blank.msg}")
	@CNPJ(message = "{farmacia.cnpj.cnpj.msg}")
	@Column(name = "cnpj", nullable = false, unique = true, length = 40)
	private String cnpj;

	@NotBlank(message = "{farmacia.telefone.blank.msg}")
	@Size(min = 11, message = "{farmacia.telefone.size.msg}")
	@Column(name = "telefone01", nullable = false, length = 11)
	private String telefone01;

	@Size(min = 11, message = "{farmacia.telefone.size.msg}")
	@Column(name = "telefone02", length = 11)
	private String telefone02;

	@OneToOne(mappedBy = "Farmacia")
	private EnderecoFarmacia enderecoFarmacia;

	@OneToOne(mappedBy = "farmacia")
	private Estoque estoque;

	public Farmacia() {}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getTelefone01() {
		return telefone01;
	}

	public void setTelefone01(String telefone01) {
		this.telefone01 = telefone01;
	}

	public String getTelefone02() {
		return telefone02;
	}

	public void setTelefone02(String telefone02) {
		this.telefone02 = telefone02;
	}

	public EnderecoFarmacia getEnderecoFarmacia() {
		return enderecoFarmacia;
	}

	public void setEnderecoFarmacia(EnderecoFarmacia enderecoFarmacia) {
		this.enderecoFarmacia = enderecoFarmacia;
	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

}
