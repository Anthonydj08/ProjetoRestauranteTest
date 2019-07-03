package model.Entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="funcionario")
@PrimaryKeyJoinColumn(name = "cod_funcionario")
@DiscriminatorValue("F")
public class Funcionario extends Pessoa  implements Serializable {
	
	private static final long serialVersionUID = -2112511687324669107L;
	
	@Column(name = "num_carteira", length = 20)
	private Integer numCarteira;
	@Column(name = "funcao", length = 30)
	private String funcao;
	@Column(name = "salario", precision = 6, scale = 2)
	private double salario;
	
	@OneToMany(mappedBy="funcionario")
	private List<Pedido> pedido;
	
	public Funcionario() {}
	
	public Integer getNumCarteira() {
		return numCarteira;
	}

	public void setNumCarteira(Integer numCarteira) {
		this.numCarteira = numCarteira;
	}

	public String getFuncao() {
		return funcao;
	}
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}

	public List<Pedido> getPedido() {
		return pedido;
	}

	public void setPedido(List<Pedido> pedido) {
		this.pedido = pedido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((funcao == null) ? 0 : funcao.hashCode());
		result = prime * result + ((numCarteira == null) ? 0 : numCarteira.hashCode());
		result = prime * result + ((pedido == null) ? 0 : pedido.hashCode());
		long temp;
		temp = Double.doubleToLongBits(salario);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Funcionario other = (Funcionario) obj;
		if (funcao == null) {
			if (other.funcao != null)
				return false;
		} else if (!funcao.equals(other.funcao))
			return false;
		if (numCarteira == null) {
			if (other.numCarteira != null)
				return false;
		} else if (!numCarteira.equals(other.numCarteira))
			return false;
		if (pedido == null) {
			if (other.pedido != null)
				return false;
		} else if (!pedido.equals(other.pedido))
			return false;
		if (Double.doubleToLongBits(salario) != Double.doubleToLongBits(other.salario))
			return false;
		return true;
	}


}
