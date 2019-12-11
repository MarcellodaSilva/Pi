package model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "usuario")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	protected Integer idUsuario;

	@NotBlank(message = "{cliente.senha.blank.msg}")
	@Column(name = "senha", nullable = false, length = 30)
	protected String senha;

	@NotBlank(message = "{cliente.email.blank.msg}")
	@Email(message = "{cliente.email.email.msg}")
	@Column(name = "login", nullable = false, length = 130)
	protected String login;
	
	@Column(name = "tipo", length = 2)
	protected String Tipo;

	public String getTipo() {
		return Tipo;
	}

	public void setTipo(String tipo) {
		Tipo = tipo;
	}

	public Usuario() {
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

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
}
