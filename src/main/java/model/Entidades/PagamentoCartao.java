package model.Entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@DiscriminatorValue(value = "C")
@Table(name = "pagamento_cartao")
public class PagamentoCartao extends Pagamento implements Serializable{
	
	private static final long serialVersionUID = -6480289674362625243L;
	
	@Column(name = "cod_pagamento")
	private Integer codPagamento;
	@Column(name = "nome_titular", length = 100, nullable = false)
	private String nomeTitular;
	@Column(name = "numero", nullable = false)
	private Integer numero;
	@Column(name = "data_validade", nullable = false)
	private Date dataValidade;
	@Column(name = "cod_seguranca", nullable = false)
	private Integer codSeguranca;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Pagamento pagamento;

	public PagamentoCartao() {}
	
	public Integer getCodPagamento() {
		return codPagamento;
	}
	public void setCodPagamento(Integer codPagamento) {
		this.codPagamento = codPagamento;
	}
	public String getNomeTitular() {
		return nomeTitular;
	}
	public void setNomeTitular(String nomeTitular) {
		this.nomeTitular = nomeTitular;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public Date getDataValidade() {
		return dataValidade;
	}
	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}
	public Integer getCodSeguranca() {
		return codSeguranca;
	}
	public void setCodSeguranca(Integer codSeguranca) {
		this.codSeguranca = codSeguranca;
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
		result = prime * result + ((codSeguranca == null) ? 0 : codSeguranca.hashCode());
		result = prime * result + ((dataValidade == null) ? 0 : dataValidade.hashCode());
		result = prime * result + ((nomeTitular == null) ? 0 : nomeTitular.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((pagamento == null) ? 0 : pagamento.hashCode());
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
		PagamentoCartao other = (PagamentoCartao) obj;
		if (codPagamento == null) {
			if (other.codPagamento != null)
				return false;
		} else if (!codPagamento.equals(other.codPagamento))
			return false;
		if (codSeguranca == null) {
			if (other.codSeguranca != null)
				return false;
		} else if (!codSeguranca.equals(other.codSeguranca))
			return false;
		if (dataValidade == null) {
			if (other.dataValidade != null)
				return false;
		} else if (!dataValidade.equals(other.dataValidade))
			return false;
		if (nomeTitular == null) {
			if (other.nomeTitular != null)
				return false;
		} else if (!nomeTitular.equals(other.nomeTitular))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (pagamento == null) {
			if (other.pagamento != null)
				return false;
		} else if (!pagamento.equals(other.pagamento))
			return false;
		return true;
	}
	
}
