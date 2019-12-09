

package model.entity;

import java.io.Serializable;


import javax.persistence.ManyToOne;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "carrinho")
public class Carrinho implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrinho")
    private Integer idCarrinho;
  
    @NotNull(message="{carrinho.quantidade.blank.msg}")
    @PositiveOrZero(message="{carrinho.quantidade.positiveorzero.msg}")
    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;
   
    @ManyToOne
    @JoinColumn(name = "id_historico", referencedColumnName = "id_historico")
    private Historico historico;
   
    @NotEmpty
    @ManyToMany(mappedBy="carrinho")
    private List<Produto> produto;
    
    @NotEmpty
    @ManyToMany(mappedBy="carrinho")
	private List<Estoque> estoque;
    
    
    public Integer getIdCarrinho() {
        return idCarrinho;
    }

    public void setIdCarrinho(Integer idCarrinho) {
        this.idCarrinho = idCarrinho;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }


    public Historico getHistorico() {
        return historico;
    }

    public void setHistorico(Historico historico) {
        this.historico = historico;
    }

    public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}

	public List<Estoque> getEstoque() {
		return estoque;
	}

	public void setEstoque(List<Estoque> estoque) {
		this.estoque = estoque;
	}
}
