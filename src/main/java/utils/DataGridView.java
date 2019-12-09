package utils;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import Service.ProdutoService;
import exception.ValidacaoException;
import model.entity.Produto;

@Named
@SessionScoped
public class DataGridView implements Serializable {

	private static final long serialVersionUID = 1L;

	
	private List<Produto> produtos;


	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
}