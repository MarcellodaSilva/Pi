package bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import Service.UsuarioService;

@Named
@ViewScoped
public class UsuarioBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Inject
	private	UsuarioService usuarioService;
	
	private String senha;
	private String login;
	
	public UsuarioService getUsuarioService() {
		return usuarioService;
	}
	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
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
	
	public String excluirConta() {
		try {
			usuarioService.excluirConta(senha, login);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Conta Excluida com sucesso"));
			return "pagina_inicial";
		}catch(Exception v) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "erro", "Erro ao excluir conta"));
		}
		return null;
		
	}
	
}
