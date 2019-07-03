package controller;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import controller.util.FacesUtil;
import model.ClienteModel;
import model.ContaModel;
import model.ItemCardapioModel;
import model.ItemPedidoModel;
import model.PedidoModel;
import model.Entidades.Cliente;
import model.Entidades.Conta;
import model.Entidades.ItemCardapio;
import model.Entidades.ItemPedido;
import model.Entidades.Pedido;
import model.Entidades.Usuario;
import model.Exception.JaExisteException;
import model.Exception.NullException;
import model.Exception.StringException;
import model.Exception.ValorException;

@ManagedBean(name = "vendaController")
@SessionScoped
public class VendaController implements Serializable {

	private static final long serialVersionUID = -1898794228698382029L;
	private ItemCardapio itemCardapio;
	private Conta contaCadastro;
	private Pedido pedido;
	private Cliente cliente;
	private Usuario usuario;
	private ItemPedido itempedido = new ItemPedido();
	private List<ItemPedido> itensPedido = new ArrayList<ItemPedido>();
	private List<ItemCardapio> listaCardapio;
	private List<Cliente> listaCliente;
	private Integer quantidade = 0;
	private ItemCardapioModel icm = new ItemCardapioModel();
	private ItemPedidoModel ipm = new ItemPedidoModel();
	private PedidoModel pm = new PedidoModel();
	private ClienteModel clm = new ClienteModel();
	private ContaModel cm = ContaModel.getInstance();

	public VendaController() {
		itemCardapio = new ItemCardapio();
		contaCadastro = new Conta();
		pedido = new Pedido();
		cliente = new Cliente();
	}

	public ItemCardapio getItemCardapio() {
		return itemCardapio;
	}

	public void setItemCardapio(ItemCardapio itemCardapio) {
		this.itemCardapio = itemCardapio;
	}

	public Conta getContaCadastro() {
		if (contaCadastro == null) {
			contaCadastro = new Conta();
			contaCadastro.setValorTotal(0.0);
		}
		return contaCadastro;
	}

	public void setContaCadastro(Conta contaCadastro) {
		this.contaCadastro = contaCadastro;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ItemPedido getItempedido() {
		return itempedido;
	}

	public void setItempedido(ItemPedido itempedido) {
		this.itempedido = itempedido;
	}

	public List<ItemPedido> getItensPedido() {
		return itensPedido;
	}

	public void setItensPedido(List<ItemPedido> itensPedido) {
		this.itensPedido = itensPedido;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public List<ItemCardapio> getListaCardapio() {
		listaCardapio = icm.listarTodos();
		return listaCardapio;
	}

	public void setListaCardapio(List<ItemCardapio> listaCardapio) {
		this.listaCardapio = listaCardapio;
	}

	public List<Cliente> getListaCliente() {
		listaCliente = clm.ListarTodos();
		return listaCliente;
	}

	public void setListaCliente(List<Cliente> listaCliente) {
		this.listaCliente = listaCliente;
	}

	public void adicionar(ItemCardapio itemcardapio) {

		int posicaoEncontrada = -1;

		for (int pos = 0; pos < itensPedido.size() && posicaoEncontrada < 0; pos++) {
			ItemPedido itemTemp = itensPedido.get(pos);

			if (itemTemp.getItemCardapio().equals(itemcardapio)) {
				posicaoEncontrada = pos;
			}
		}

		ItemPedido item = new ItemPedido();
		item.setItemCardapio(itemcardapio);

		if (posicaoEncontrada < 0) {
			quantidade++;
			item.setQuantidade(itempedido.getQuantidade());
			item.setValorParcial(itemcardapio.getPreco());
			itensPedido.add(item);
			FacesUtil.adicionarMsgInfo("Item adicionado ao carrinho");
		} else {
			ItemPedido itemTemp = itensPedido.get(posicaoEncontrada);
			item.setQuantidade(itemTemp.getQuantidade() + 1);
			item.setValorParcial(itemcardapio.getPreco());
			itensPedido.set(posicaoEncontrada, item);
		}
		contaCadastro
				.setValorTotal(contaCadastro.getValorTotal() + (itemcardapio.getPreco() * itempedido.getQuantidade()));

	}

	public void remover(ItemPedido item) {

		int posicaoEncontrada = -1;

		for (int pos = 0; pos < itensPedido.size() && posicaoEncontrada < 0; pos++) {
			ItemPedido itemTemp = itensPedido.get(pos);

			if (itemTemp.getItemCardapio().equals(item.getItemCardapio())) {
				posicaoEncontrada = pos;
			}
		}

		if (posicaoEncontrada > -1) {
			itensPedido.remove(posicaoEncontrada);
			FacesUtil.adicionarMsgInfo("Item removido do carrinho");
			contaCadastro.setValorTotal(contaCadastro.getValorTotal() - (item.getValorParcial() * item.getQuantidade()));
		}
		quantidade--;
	}

	java.util.Date d = new Date();
	String data = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);

	public void Salvar() {
		try {
			contaCadastro.setStatus("Aberta");
			cm.registraConta(contaCadastro);

			pedido.setConta(contaCadastro);
			pedido.setData(d);
			pedido.setStatus("Realizado");
			pm.registraPedido(pedido);

			for (int j = 0; j < itensPedido.size(); j++) {
				itensPedido.get(j).setPedido(pedido);
			}
			ipm.registraItemPedido(itensPedido);

			FacesUtil.adicionarMsgInfo("Compra realizada com Sucesso.");
		} catch (NullException ne) {
			FacesUtil.adicionarMsgErro(ne.getMessage());
		} catch (JaExisteException je) {
			FacesUtil.adicionarMsgErro(je.getMessage());
		} catch (StringException se) {
			FacesUtil.adicionarMsgErro(se.getMessage());
		} catch (ValorException ve) {
			FacesUtil.adicionarMsgErro(ve.getMessage());
		} catch (Exception e) {
			FacesUtil.adicionarMsgErro(e.getMessage());
		}
	}

	public String Carrinho() {
		return "Carrinho";
	}

	public String Pagamento() {
		return "Pagamento";
	}

}
