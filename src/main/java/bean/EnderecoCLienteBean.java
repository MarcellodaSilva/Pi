package bean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import Service.EnderecoClienteService;
import exception.ValidacaoException;
import model.entity.EnderecoCliente;

@Named
@RequestScoped
public class EnderecoCLienteBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EnderecoCliente enderecoCliente;
	
	@Inject
	private EnderecoClienteService endClienteservice;
	
	public EnderecoCliente getEnderecoCliente() {
		return enderecoCliente;
	}
	public void setEnderecoCliente(EnderecoCliente enderecoCliente) {
		this.enderecoCliente = enderecoCliente;
	}
	
	public EnderecoClienteService getEndClienteservice() {
		return endClienteservice;
	}
	public void setEndClienteservice(EnderecoClienteService endClienteservice) {
		this.endClienteservice = endClienteservice;
	}
	public void cadastrarEdereco() throws Exception {
		try {
			endClienteservice.cadastrarEndereco(enderecoCliente);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cadastro Realizado Com Sucesso!"));
		} catch (ValidacaoException v) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "erro", "Erro no Cadastro"));
		}

	}
}
