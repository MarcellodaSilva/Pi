
package model.entity;

import java.io.Serializable;



import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

@Entity()
@Table(name = "cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer idCliente;
	
	@NotBlank(message = "{cliente.cpf.blank.msg}")
	@CPF(message = "{cliente.cpf.cpf.msg}")
	@Column(name = "cpf", nullable = false, unique = true, length = 14)
	private String cpf;

	@NotBlank(message = "{clinte.nome.blank.msg}")
	@Column(name = "nome", nullable = false, length = 120)
	private String nome;

	@NotBlank(message = "{cliente.telefone.blank.msg}")
	@Size(min = 14, message = "{cliente.telefone.size.msg}")
	@Column(name = "telefone01", nullable = false, length = 14)
	private String telefone01;

	@Column(name = "telefone02", length = 14)
	private String telefone02;

	@NotBlank(message = "{cliente.senha.blank.msg}")
	@Column(name = "senha", nullable = false, length = 30)
	private String senha;

	@NotBlank(message = "{cliente.email.blank.msg}")
	@Email(message = "{cliente.email.email.msg}")
	@Column(name = "login", nullable = false, length = 130)
	private String login;
	
	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@OneToOne(mappedBy = "Cliente")
	private EnderecoCliente enderecoCliente;

	@OneToOne(mappedBy = "cliente")
	private Historico historico;

	@ManyToMany(mappedBy = "clientelist")
	private List<Compra> compralist;

	public Cliente() {
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public EnderecoCliente getEnderecoCliente() {
		return enderecoCliente;
	}

	public void setEnderecoCliente(EnderecoCliente enderecoCliente) {
		this.enderecoCliente = enderecoCliente;
	}

	public Historico getHistorico() {
		return historico;
	}

	public void setHistorico(Historico historico) {
		this.historico = historico;
	}

	public List<Compra> getCompralist() {
		return compralist;
	}

	public void setCompralist(List<Compra> compralist) {
		this.compralist = compralist;
	}
}
