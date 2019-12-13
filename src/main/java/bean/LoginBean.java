package bean;

import java.io.Serializable;


import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import Service.LoginService;
import model.entity.Cliente;
import model.entity.Farmacia;


@Named
@RequestScoped
public class LoginBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private LoginService loginService;
	
	@Inject
	private Cliente cliente;
	
	@Inject
	private Farmacia farmacia;
	
	private FacesContext sessao;
	
	private	ClienteBean clinteBean;
	
	private String login;


	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Farmacia getFarmacia() {
		return farmacia;
	}

	public void setFarmacia(Farmacia farmacia) {
		this.farmacia = farmacia;
	}

	private String senha;

	

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

  public String logar() {
		try {
			Object user = loginService.logar(senha, login);
			sessao = FacesContext.getCurrentInstance();
			if (user instanceof Cliente) {
				sessao.getExternalContext().getSessionMap().put("Perfil", cliente);
				cliente = (Cliente) user;
				return "pagina_inicial";
              
			} else if (user instanceof Farmacia) {
				sessao.getExternalContext().getSessionMap().put("Perfil", farmacia);
				farmacia = (Farmacia) user;
				return "pagina_inicial";
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Email ou Senha Incorretos."));
			}

		} catch (Exception v) {
			v.printStackTrace();
		}
		return null;

	}
  
	public String Deslogar() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "pagina_inicial";
	}
}
