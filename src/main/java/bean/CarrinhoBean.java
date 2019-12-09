package bean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import Service.CarrinhoService;
import model.entity.Carrinho;

@Named
@ViewScoped
public class CarrinhoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Carrinho carrinho ;
	
	@Inject
	private CarrinhoService servidorCarrinho;
	
	
	public Carrinho getCarrinho() {
		return carrinho;
	}
	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}
	public CarrinhoService getServidorCarrinho() {
		return servidorCarrinho;
	}
	public void setServidorCarrinho(CarrinhoService servidorCarrinho) {
		this.servidorCarrinho = servidorCarrinho;
	}
}