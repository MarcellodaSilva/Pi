package bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import Service.ProdutoService;
import exception.ValidacaoException;
import model.entity.Produto;

@Named
@RequestScoped
public class PesquisaProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProdutoService produtoService;
	private String nomeProduto;
	List<Produto> produtos;

	public ProdutoService getProdutoService() {
		return produtoService;
	}

	public void setProdutoService(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String listarProdutos() throws ValidacaoException {
		if (!nomeProduto.trim().isEmpty()) {
			produtos = produtoService.pesquisar(nomeProduto);
			return "resultado";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Digite o nome do produto."));
		}
		return null;

	}
}
