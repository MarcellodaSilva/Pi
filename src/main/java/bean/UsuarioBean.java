package bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import Service.ClienteService;
import Service.FarmaciaService;
import Service.UsuarioService;
import exception.ValidacaoException;
import model.entity.Cliente;

@Named
@ViewScoped
public class UsuarioBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Inject
	private	UsuarioService usuarioService;
	private String senha;
	private String login;
	@Inject
	private LoginBean loginBean;
	@Inject
	private ClienteService clienteService;
	@Inject
	private FarmaciaService farmaciaService;
	@Inject
	private Cliente cliente;
	
	
	
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
	public LoginBean getLoginBean() {
		return loginBean;
	}
	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}
	public ClienteService getClienteService() {
		return clienteService;
	}
	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	public FarmaciaService getFarmaciaService() {
		return farmaciaService;
	}
	public void setFarmaciaService(FarmaciaService farmaciaService) {
		this.farmaciaService = farmaciaService;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
	public void atualizar() throws Exception {
		try {
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
			Cliente usuario = (Cliente) session.getAttribute("perfil");
		if(cliente.getNome() == null || cliente.getNome().length() == 0) {
			cliente.setNome(usuario.getNome());
		}
		if(cliente.getCpf()== null || cliente.getCpf().length() == 0) {
			cliente.setCpf(usuario.getCpf());
		}
		if(cliente.getLogin()== null || cliente.getLogin().length() == 0) {
			cliente.setNome(usuario.getLogin());
		}
		if(cliente.getSenha()== null || cliente.getSenha().length() == 0) {
			cliente.setSenha(usuario.getSenha());
		}
		if(cliente.getTelefone01()== null || cliente.getTelefone01().length() == 0) {
			cliente.setTelefone01(usuario.getTelefone01());
		}
		if(cliente.getTelefone02()== null || cliente.getTelefone02().length() == 0) {
			cliente.setTelefone02(usuario.getTelefone02());
		}
		
		cliente.setIdUsuario(usuario.getIdUsuario());
		
		System.out.println(cliente.getNome() + cliente.getLogin() + cliente.getCpf() + cliente.getSenha()
		+ cliente.getTelefone01() + cliente.getTelefone02() + cliente.getIdUsuario());
		clienteService.atualizarCliente(cliente);
		} catch (ValidacaoException v) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "erro", "Erro de atualizar"));
		}
	}
	
}
