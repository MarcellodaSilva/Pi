package bean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import Service.CompraService;
import model.entity.Compra;

@Named
@ViewScoped

public class CompraBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Compra compra;
	
	@Inject
	private CompraService serviceCompra;
	
	public Compra getCompra() {
		return compra;
	}
	public void setCompra(Compra compra) {
		this.compra = compra;
	}
	public CompraService getServiceCompra() {
		return serviceCompra;
	}
	public void setServiceCompra(CompraService serviceCompra) {
		this.serviceCompra = serviceCompra;
	}
	
	
}
