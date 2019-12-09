
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "compra")
public class Compra implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compra")
    private Integer idCompra;
   
    @NotNull(message="{compra.quantidade.blank.msg }")
    @PositiveOrZero(message="{compra.quantidade.positiveorzero.msg}")
    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;
    
    @Column(name = "data_hora", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHora;
    
    @NotNull
    @JoinColumn(name = "id_estoque", referencedColumnName = "id_estoque")
    @OneToOne
    private Estoque estoque;
    
    @NotNull
    @JoinColumn(name = "id_produto", referencedColumnName = "id_produto")
    @OneToOne
    private Produto Produto;
    
    @ManyToOne
    @JoinColumn(name = "id_historico", referencedColumnName = "id_historico")
    private Historico historico;
    
    @NotEmpty
    @ManyToMany
    @JoinTable(name = "Compra_cliente",
    joinColumns= @JoinColumn(name="Id_compra"),
    inverseJoinColumns= @JoinColumn(name="Id_cliente"))
    private List<Cliente> clientelist;
    
    public List<Cliente> getClientelist() {
		return clientelist;
	}

	public void setClientelist(List<Cliente> clientelist) {
		this.clientelist = clientelist;
	}

	
    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public Produto getProduto() {
        return Produto;
    }

    public void setProduto(Produto Produto) {
        this.Produto = Produto;
    }

    public Historico getHistorico() {
        return historico;
    }

    public void setHistoricoList(Historico historico) {
        this.historico = historico;
    }
    
}
