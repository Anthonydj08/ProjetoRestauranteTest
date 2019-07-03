package model.Entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="cliente")
@PrimaryKeyJoinColumn(name = "cod_cliente")
@DiscriminatorValue("C")
public class Cliente extends Pessoa implements Serializable{

	private static final long serialVersionUID = 7126712513942445970L;
	
	@OneToMany(mappedBy = "cliente" )
	private List<Pedido> Pedido;
	
	public Cliente() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((Pedido == null) ? 0 : Pedido.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (Pedido == null) {
			if (other.Pedido != null)
				return false;
		} else if (!Pedido.equals(other.Pedido))
			return false;
		return true;
	}
	
	
}
