package bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import Service.ClienteService;
import Service.UsuarioService;
import exception.ValidacaoException;
import model.entity.Cliente;
import model.entity.Usuario;

@Named
@ViewScoped
public class ClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteService clienteService;
	@Inject
	private Cliente cliente;
	@Inject
	private Usuario usuario;
	@Inject
	private UsuarioService usuarioService;
	private boolean booleanoForm;

	public boolean isBooleanoForm() {
		return booleanoForm;
	}

	public void setBooleanoForm(boolean booleanoForm) {
		this.booleanoForm = booleanoForm;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ClienteService getClienteService() {
		return clienteService;
	}

	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	public void adicionarCliente() throws Exception {
		try {
			clienteService.cadastrarCliente(cliente);
			usuarioService.cadastrarUsuario(usuario);
		} catch (ValidacaoException v) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "erro", "Erro no Cadastro"));
		}

	}

}
