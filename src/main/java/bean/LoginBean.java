package bean;

import javax.enterprise.context.RequestScoped;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import Service.ClienteService;
import Service.LoginService;
import model.entity.Cliente;
import model.entity.Farmacia;
import model.entity.Usuario;

import java.io.Serializable;

@Named
@RequestScoped
public class LoginBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private LoginService loginService;
	private Cliente cliente;
	
	private Farmacia farmacia;
	
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
			Usuario user = loginService.logar(senha, login);
			 System.out.println(user.getLogin());
			FacesContext sessao = FacesContext.getCurrentInstance();
			if (user.getTipo().equals("Pf")) {
				sessao.getExternalContext().getSessionMap().put("Perfil", (Cliente) user);
				cliente = (Cliente) user;
				return "perfil_cliente";
              
			} else if (user.getTipo().equals("Pj")) {
				sessao.getExternalContext().getSessionMap().put("Perfil", (Farmacia) user);
				farmacia = (Farmacia) user;
				return "perfil_farmacia";
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
