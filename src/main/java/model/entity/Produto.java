
package model.entity;

import java.io.Serializable;


import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "produto")
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto", nullable=false)
    private Integer idProduto;

    @NotNull(message="{produto.valor.blank.msg}")
    @PositiveOrZero(message="{produto.valor.blank.msg}")
    @Column(name = "valor", nullable=false)
    private double valor;

    @NotBlank(message="{produto.nome.blank.msg}")
    @Column(name = "nome", nullable=false, length=160)
    private String nome;

    @NotBlank(message="{produto.descricao.blank}")
    @Column(name = "descricao", nullable=false, length=800)
    private String descricao;

    @NotNull(message="{produto.validade.blank.msg}")
    @Future(message="{produto.validadde.future.msg}")
    @Column(name = "validade")
    @Temporal(TemporalType.DATE)
    private Date validade;
  
    @NotBlank(message="{produto.tipo.blank.msg}")
    @Column(name = "tipo", nullable=false, length=60)
    private String tipo;

    @NotBlank(message="{produto.marca.blank.msg}")
    @Column(name = "marca", nullable=false, length=120)
    private String marca;
    
 
    @PositiveOrZero(message="{produto.quatidade.positiveorzero.msg}")
    @Column(name = "quantidade", nullable = false)
    private int quantidade;

	@OneToOne(mappedBy = "Produto")
    private Compra compra;
    
    @ManyToMany
    @JoinTable(name= "Carrinho_produto",
    joinColumns = @JoinColumn(name="id_produto"),
    inverseJoinColumns = @JoinColumn(name="id_carrinho"))
    private List<Carrinho> carrinho;
    
    @ManyToMany
    @JoinTable(name="Estoque_produto",
    joinColumns = @JoinColumn(name="id_produto"),
    inverseJoinColumns = @JoinColumn(name="id_estoque"))
	private List<Estoque> estoque;
    
    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }
    
    public List<Carrinho> getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(List<Carrinho> carrinho) {
		this.carrinho = carrinho;
	}

	public List<Estoque> getEstoque() {
		return estoque;
	}

	public void setEstoque(List<Estoque> estoque) {
		this.estoque = estoque;
	}
	
	public void atualizarDados(Produto produto) {
		if(produto == null) return;
		
		if(produto.getDescricao() != null) {
			this.setDescricao(produto.getDescricao());
		}
		
		if(produto.getMarca() != null) {
			this.setMarca(produto.getMarca());
		}
		
		if(produto.getNome() != null) {
			this.setNome(produto.getNome());
		}
		
		if(produto.getQuantidade() > 0) {
			this.setQuantidade(produto.getQuantidade());
		}
		
		if(produto.getTipo() != null) {
			this.setTipo(produto.getTipo());
		}
		
		if(produto.getValidade() != null) {
		 	this.setValidade(produto.getValidade());
		}
		
		if(produto.getValor() > 0) {
			this.setValor(produto.getValor());
		}	
	}
}
