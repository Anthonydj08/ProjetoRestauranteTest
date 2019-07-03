package model.Entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "item_pedido")
public class ItemPedido implements Serializable {

	private static final long serialVersionUID = 2653789344701536455L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_itemPedido", nullable = false)
	private Integer codItemPedido;
	@Column(name = "quantidade", nullable = false)
	private Integer quantidade;
	@Transient
	@Column(name = "valor_parcial")
	private double valorParcial;

	@ManyToOne
	@JoinColumn(name = "cod_item", referencedColumnName = "cod_item")
	private ItemCardapio itemCardapio;

	@ManyToOne
	@JoinColumn(name = "cod_pedido", referencedColumnName = "cod_pedido")
	private Pedido pedido;

	public Integer getCodItemPedido() {
		return codItemPedido;
	}

	public void setCodItemPedido(Integer codItemPedido) {
		this.codItemPedido = codItemPedido;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public double getValorParcial() {
		return valorParcial;
	}

	public void setValorParcial(double valorParcial) {
		this.valorParcial = valorParcial;
	}

	public ItemCardapio getItemCardapio() {
		return itemCardapio;
	}

	public void setItemCardapio(ItemCardapio itemdardapio) {
		this.itemCardapio = itemdardapio;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codItemPedido == null) ? 0 : codItemPedido.hashCode());
		result = prime * result + ((itemCardapio == null) ? 0 : itemCardapio.hashCode());
		result = prime * result + ((pedido == null) ? 0 : pedido.hashCode());
		result = prime * result + ((quantidade == null) ? 0 : quantidade.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valorParcial);
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
		ItemPedido other = (ItemPedido) obj;
		if (codItemPedido == null) {
			if (other.codItemPedido != null)
				return false;
		} else if (!codItemPedido.equals(other.codItemPedido))
			return false;
		if (itemCardapio == null) {
			if (other.itemCardapio != null)
				return false;
		} else if (!itemCardapio.equals(other.itemCardapio))
			return false;
		if (pedido == null) {
			if (other.pedido != null)
				return false;
		} else if (!pedido.equals(other.pedido))
			return false;
		if (quantidade == null) {
			if (other.quantidade != null)
				return false;
		} else if (!quantidade.equals(other.quantidade))
			return false;
		if (Double.doubleToLongBits(valorParcial) != Double.doubleToLongBits(other.valorParcial))
			return false;
		return true;
	}

}
