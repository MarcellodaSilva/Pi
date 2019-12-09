package bean;

import java.io.Serializable;


import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import Service.FarmaciaService;
import model.entity.Farmacia;

@Named
@SessionScoped
public class LoginFarmaciaBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private Farmacia farmacia;
	
	@Inject
	private FarmaciaService farmaciaService;
	
	private String senha;
	private String login;
	
	
	public Farmacia getFarmacia() {
		return farmacia;
	}
	public void setFarmacia(Farmacia farmacia) {
		this.farmacia = farmacia;
	}
	public FarmaciaService getFarmaciaService() {
		return farmaciaService;
	}
	public void setFarmaciaService(FarmaciaService farmaciaService) {
		this.farmaciaService = farmaciaService;
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
	
	/*public String loginFarmacia() {
		if(senha.trim().isEmpty() != true && login.trim().isEmpty() != true) {
		     farmacia = farmaciaService.loginFarmacia(senha, login);
			if(farmacia != null) {
			FacesContext session = FacesContext.getCurrentInstance();
			session.getExternalContext().getSessionMap().put("email",farmacia);
			return "Carrinho.xhtml?faces-redirect=true";
			}
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "erro!", "Email ou Senha Incorretos!"));
		}
		return null;
	}*/
	
	public String Deslogar() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Deslogado com sucesso!"));
		return"CadastroCliente.xhtml?faces-redirect=true";
	}
}
