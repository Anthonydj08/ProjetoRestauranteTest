package model.Entidades;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@DiscriminatorValue(value = "D")
@Table(name = "pagamento_dinheiro")
public class PagamentoDinheiro extends Pagamento implements Serializable{
	
	private static final long serialVersionUID = 3219357625625480437L;
	
	@Column(name = "cod_pagamento")
	private Integer codPagamento;
	@Column(name = "valor", precision = 6, scale = 2, nullable = false)
	private double valor;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Pagamento pagamento;
	
	public PagamentoDinheiro() {}
	
	public Integer getCodPagamento() {
		return codPagamento;
	}
	public void setCodPagamento(Integer codPagamento) {
		this.codPagamento = codPagamento;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((codPagamento == null) ? 0 : codPagamento.hashCode());
		result = prime * result + ((pagamento == null) ? 0 : pagamento.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valor);
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
		PagamentoDinheiro other = (PagamentoDinheiro) obj;
		if (codPagamento == null) {
			if (other.codPagamento != null)
				return false;
		} else if (!codPagamento.equals(other.codPagamento))
			return false;
		if (pagamento == null) {
			if (other.pagamento != null)
				return false;
		} else if (!pagamento.equals(other.pagamento))
			return false;
		if (Double.doubleToLongBits(valor) != Double.doubleToLongBits(other.valor))
			return false;
		return true;
	}
	
}
