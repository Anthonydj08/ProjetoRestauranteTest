package model.Entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cardapio")
@NamedQueries({ @NamedQuery(name = "Cardapio.listar", query = "SELECT cardapios FROM Cardapio cardapios") })
public class Cardapio implements Serializable {

	private static final long serialVersionUID = -4664430664985731932L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_cardapio")
	private Integer codCardapio;

	@Column(name = "categoria", length = 20, nullable = false)
	private String categoria;

	@OneToMany(mappedBy = "cardapio")
	private List<ItemCardapio> ItemCardapio;

	public Cardapio() {
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Integer getCodCardapio() {
		return codCardapio;
	}

	public void setCodCardapio(Integer codCardapio) {
		this.codCardapio = codCardapio;
	}

	public List<ItemCardapio> getItemCardapio() {
		return ItemCardapio;
	}

	public void setItemCardapio(List<ItemCardapio> itemCardapio) {
		ItemCardapio = itemCardapio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ItemCardapio == null) ? 0 : ItemCardapio.hashCode());
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((codCardapio == null) ? 0 : codCardapio.hashCode());
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
		Cardapio other = (Cardapio) obj;
		if (ItemCardapio == null) {
			if (other.ItemCardapio != null)
				return false;
		} else if (!ItemCardapio.equals(other.ItemCardapio))
			return false;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (codCardapio == null) {
			if (other.codCardapio != null)
				return false;
		} else if (!codCardapio.equals(other.codCardapio))
			return false;
		return true;
	}

	
}
