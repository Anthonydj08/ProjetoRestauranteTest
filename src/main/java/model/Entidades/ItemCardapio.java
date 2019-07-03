package model.Entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "item_cardapio")
public class ItemCardapio implements Serializable{
	
	private static final long serialVersionUID = -8066196187289652098L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "cod_item")
	private Integer codItem;
	@Column(name = "preco", precision = 6, scale = 2, nullable = false)
	private double preco;
	/**/
	@ManyToOne
	@JoinColumn(name = "cod_cardapio", referencedColumnName = "cod_cardapio")
	private Cardapio cardapio = new Cardapio();
	
	@ManyToMany
	private List<Pedido> pedido;
	
	@OneToMany(mappedBy = "itemCardapio")
	private List<ItemPedido> ItemPedido;
	
	@OneToOne
	private Estoque estoque = new Estoque();
	
	
	public ItemCardapio() {}
	
	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}	
	
	public Integer getCodItem() {
		return codItem;
	}
	public void setCodItem(Integer codCardapio) {
		this.codItem = codCardapio;
	}

	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Cardapio getCardapio() {
		return cardapio;
	}

	public void setCardapio(Cardapio cardapio) {
		this.cardapio = cardapio;
	}

	public List<Pedido> getPedido() {
		return pedido;
	}

	public void setPedido(List<Pedido> pedido) {
		this.pedido = pedido;
	}

	public List<ItemPedido> getItemPedido() {
		return ItemPedido;
	}

	public void setItemPedido(List<ItemPedido> itemPedido) {
		ItemPedido = itemPedido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ItemPedido == null) ? 0 : ItemPedido.hashCode());
		result = prime * result + ((cardapio == null) ? 0 : cardapio.hashCode());
		result = prime * result + ((codItem == null) ? 0 : codItem.hashCode());
		result = prime * result + ((estoque == null) ? 0 : estoque.hashCode());
		result = prime * result + ((pedido == null) ? 0 : pedido.hashCode());
		long temp;
		temp = Double.doubleToLongBits(preco);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemCardapio other = (ItemCardapio) obj;
		if (ItemPedido == null) {
			if (other.ItemPedido != null)
				return false;
		} else if (!ItemPedido.equals(other.ItemPedido))
			return false;
		if (cardapio == null) {
			if (other.cardapio != null)
				return false;
		} else if (!cardapio.equals(other.cardapio))
			return false;
		if (codItem == null) {
			if (other.codItem != null)
				return false;
		} else if (!codItem.equals(other.codItem))
			return false;
		if (estoque == null) {
			if (other.estoque != null)
				return false;
		} else if (!estoque.equals(other.estoque))
			return false;
		if (pedido == null) {
			if (other.pedido != null)
				return false;
		} else if (!pedido.equals(other.pedido))
			return false;
		if (Double.doubleToLongBits(preco) != Double.doubleToLongBits(other.preco))
			return false;
		return true;
	}

}
