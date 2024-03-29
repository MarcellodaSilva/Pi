package bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import Service.ProdutoService;
import exception.ValidacaoException;
import model.entity.Marca;
import model.entity.Produto;
import model.entity.Tipo;

@Named
@RequestScoped
public class ProdutoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Produto produto;
	
	@Inject
	private ProdutoService produtoService;
	
	private List<Produto> produtos;
	
	@Inject
	private List<Marca> marcas;
	
	public List<Marca> getMarcas() {
		return marcas;
	}

	public void setMarcas(List<Marca> marcas) {
		this.marcas = marcas;
	}

	public List<Tipo> getTipos() {
		return tipos;
	}

	public void setTipos(List<Tipo> tipos) {
		this.tipos = tipos;
	}

	@Inject
	private List<Tipo> tipos;
	
	@PostConstruct
	public void Int() throws ValidacaoException {
		produtos = produtoService.listar();
	}
	
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
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public void adicionarProduto() throws Exception {
		try{
			produtoService.cadastrarProduto(produto);
		  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cadastro Realizado Com Sucesso!"));
		}catch(ValidacaoException v){
		  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "erro", "Erro no Cadastro"));
		}
	}
	
	public void listar() throws ValidacaoException {
		produtos = produtoService.listar();
	}
 
	public String atualizar(Produto p) {
		this.produto = p;
		return "editar_produto";
	}
	public String atualizarP() throws Exception {
		produtoService.atualizarProduto(this.produto);
		return "listar";
   }
   
   public void deletar(Integer id) throws Exception {
	  produtoService.removerProduto(id);
   }
}
