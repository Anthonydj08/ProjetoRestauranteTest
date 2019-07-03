package model.Entidades;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", length = 1, discriminatorType = DiscriminatorType.STRING)
@Table(name = "pagamento")
public abstract class Pagamento implements Serializable{

	private static final long serialVersionUID = 8169779687507933860L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "cod_pagamento")
	private Integer codPagamento;
	@Column(name = "data", nullable = false)
	private String data;
	
	@OneToOne(mappedBy = "pagamento")
	@JoinColumn(name = "cod_pagamento_cartao", referencedColumnName = "cod_pagamento")
	private PagamentoCartao pagamentocartao;
	
	@OneToOne(mappedBy = "pagamento")
	@JoinColumn(name = "cod_pagamento_dinheiro", referencedColumnName = "cod_pagamento")
	private PagamentoDinheiro pagamentodinheiro;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Conta conta;

	public Pagamento() {}
	
	public Integer getCodPagamento() {
		return codPagamento;
	}
	public void setCodPagamento(Integer codPagamento) {
		this.codPagamento = codPagamento;
	}
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public PagamentoCartao getPagamentocartao() {
		return pagamentocartao;
	}

	public void setPagamentocartao(PagamentoCartao pagamentocartao) {
		this.pagamentocartao = pagamentocartao;
	}

	public PagamentoDinheiro getPagamentodinheiro() {
		return pagamentodinheiro;
	}

	public void setPagamentodinheiro(PagamentoDinheiro pagamentodinheiro) {
		this.pagamentodinheiro = pagamentodinheiro;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codPagamento == null) ? 0 : codPagamento.hashCode());
		result = prime * result + ((conta == null) ? 0 : conta.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((pagamentocartao == null) ? 0 : pagamentocartao.hashCode());
		result = prime * result + ((pagamentodinheiro == null) ? 0 : pagamentodinheiro.hashCode());
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
		Pagamento other = (Pagamento) obj;
		if (codPagamento == null) {
			if (other.codPagamento != null)
				return false;
		} else if (!codPagamento.equals(other.codPagamento))
			return false;
		if (conta == null) {
			if (other.conta != null)
				return false;
		} else if (!conta.equals(other.conta))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (pagamentocartao == null) {
			if (other.pagamentocartao != null)
				return false;
		} else if (!pagamentocartao.equals(other.pagamentocartao))
			return false;
		if (pagamentodinheiro == null) {
			if (other.pagamentodinheiro != null)
				return false;
		} else if (!pagamentodinheiro.equals(other.pagamentodinheiro))
			return false;
		return true;
	}
	
	
}
