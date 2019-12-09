package bean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import Service.EstoqueService;
import model.entity.Estoque;

@Named
@ViewScoped
public class EstoqueBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Estoque estoque;
	
	@Inject
	private EstoqueService service;
	
	public Estoque getEstoque() {
		return estoque;
	}
	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}
	public EstoqueService getService() {
		return service;
	}
	public void setService(EstoqueService service) {
		this.service = service;
	}
	
	

}
