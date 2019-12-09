package bean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import Service.EnderecoFarmaciaService;
import exception.ValidacaoException;
import model.entity.EnderecoFarmacia;

@Named
@RequestScoped
public class EnderecoFarmaciaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EnderecoFarmacia EnderecoFarmacia;

	@Inject
	private EnderecoFarmaciaService endFarmaciaService;

	public EnderecoFarmaciaService getEndfarmaciaService() {
		return endFarmaciaService;
	}

	public void setEndfarmaciaService(EnderecoFarmaciaService endfarmaciaService) {
		endFarmaciaService = endfarmaciaService;
	}

	public EnderecoFarmacia getEnderecoFarmacia() {
		return EnderecoFarmacia;
	}

	public void setEnderecoFarmacia(EnderecoFarmacia enderecoFarmacia) {
		EnderecoFarmacia = enderecoFarmacia;
	}

	public void cadastrarEdereco() throws Exception {
		try {
			endFarmaciaService.cadastrarEndereco(EnderecoFarmacia);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cadastro Realizado Com Sucesso!"));
		} catch (ValidacaoException v) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "erro", "Erro no Cadastro"));
		}

	}

}
