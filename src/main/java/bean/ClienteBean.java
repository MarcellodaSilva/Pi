package bean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import Service.ClienteService;
import exception.ValidacaoException;
import model.entity.Cliente;

@Named
@RequestScoped
public class ClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteService clienteService;
	@Inject
	private Cliente cliente;
	private boolean booleanoForm;
	
	public boolean isBooleanoForm() {
		return booleanoForm;
	}

	public void setBooleanoForm(boolean booleanoForm) {
		this.booleanoForm = booleanoForm;
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
		} catch (ValidacaoException v) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "erro", "Erro no Cadastro"));
		}

	}
	
	public void atualizar(Cliente c) {
		this.cliente = c;
	}
	
	public String atualizarC() throws Exception {
		clienteService.atualizarCliente(this.cliente);
		return "pagina_inicial";
	}

}
