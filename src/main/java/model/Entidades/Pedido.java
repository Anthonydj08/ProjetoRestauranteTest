package model.Entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {

	private static final long serialVersionUID = -4639976277352565939L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_pedido")
	private Integer codPedido;
	@Column(name = "mesa", nullable = false)
	private Integer mesa;
	@Temporal(TemporalType.DATE)
	@Column(name = "data", nullable = false)
	private Date data;
	@Column(name = "status", length = 10, nullable = false)
	private String status;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cod_pessoa", referencedColumnName = "cod_pessoa")
	private Cliente cliente;

	@ManyToOne // onetomany
	@JoinColumn(name = "cod_conta", referencedColumnName = "cod_conta")
	private Conta conta;

	@OneToOne(cascade = CascadeType.ALL)
	private Entrega entrega;

	@JoinTable(name = "pedido_itemcardapio", joinColumns = {
			@JoinColumn(name = "cod_pedido", referencedColumnName = "cod_pedido") }, inverseJoinColumns = {
					@JoinColumn(name = "cod_item", referencedColumnName = "cod_item") })
	@ManyToMany
	private List<ItemCardapio> itemcardapio;

	@JoinTable(name = "pedido_funcionario", joinColumns = {
			@JoinColumn(name = "cod_pedido", referencedColumnName = "cod_pedido") }, inverseJoinColumns = {
					@JoinColumn(name = "cod_pessoa", referencedColumnName = "cod_pessoa") })
	@ManyToOne
	@JoinColumn(name="cod_pessoa" , referencedColumnName = "cod_pessoa")
	private Funcionario funcionario;

	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itempedido;

	public Pedido() {
	}

	public Integer getCodPedido() {
		return codPedido;
	}

	public void setCodPedido(Integer codPedido) {
		this.codPedido = codPedido;
	}

	public Integer getMesa() {
		return mesa;
	}

	public void setMesa(Integer mesa) {
		this.mesa = mesa;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Entrega getEntrega() {
		return entrega;
	}

	public void setEntrega(Entrega entrega) {
		this.entrega = entrega;
	}

	public List<ItemCardapio> getItemcardapio() {
		return itemcardapio;
	}

	public void setItemcardapio(List<ItemCardapio> itemcardapio) {
		this.itemcardapio = itemcardapio;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<ItemPedido> getItempedido() {
		return itempedido;
	}

	public void setItempedido(List<ItemPedido> itempedido) {
		this.itempedido = itempedido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((codPedido == null) ? 0 : codPedido.hashCode());
		result = prime * result + ((conta == null) ? 0 : conta.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((entrega == null) ? 0 : entrega.hashCode());
		result = prime * result + ((funcionario == null) ? 0 : funcionario.hashCode());
		result = prime * result + ((itemcardapio == null) ? 0 : itemcardapio.hashCode());
		result = prime * result + ((itempedido == null) ? 0 : itempedido.hashCode());
		result = prime * result + ((mesa == null) ? 0 : mesa.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Pedido other = (Pedido) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (codPedido == null) {
			if (other.codPedido != null)
				return false;
		} else if (!codPedido.equals(other.codPedido))
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
		if (entrega == null) {
			if (other.entrega != null)
				return false;
		} else if (!entrega.equals(other.entrega))
			return false;
		if (funcionario == null) {
			if (other.funcionario != null)
				return false;
		} else if (!funcionario.equals(other.funcionario))
			return false;
		if (itemcardapio == null) {
			if (other.itemcardapio != null)
				return false;
		} else if (!itemcardapio.equals(other.itemcardapio))
			return false;
		if (itempedido == null) {
			if (other.itempedido != null)
				return false;
		} else if (!itempedido.equals(other.itempedido))
			return false;
		if (mesa == null) {
			if (other.mesa != null)
				return false;
		} else if (!mesa.equals(other.mesa))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

}
