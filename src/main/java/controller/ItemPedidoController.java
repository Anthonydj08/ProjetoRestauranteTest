package controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import controller.util.FacesUtil;
import model.ItemPedidoModel;
import model.Entidades.ItemPedido;
import model.Exception.NullException;

@ManagedBean(name = "itemPedidoController")
@RequestScoped
public class ItemPedidoController {
	private ItemPedido itemPedido;
	private List<ItemPedido> listaItemPedido;
	ItemPedidoModel ipm = new ItemPedidoModel();

	public ItemPedido getItemPedido() {
		return itemPedido;
	}

	public void setItemPedido(ItemPedido itemPedido) {
		this.itemPedido = itemPedido;
	}

	public List<ItemPedido> getListaItemPedido() {
		listaItemPedido = ipm.listarTodos();
		return listaItemPedido;
	}

	public void setListaItemPedido(List<ItemPedido> listaItemPedido) {
		this.listaItemPedido = listaItemPedido;
	}

	@SuppressWarnings("unchecked")
	public void salvar(ItemPedido ip) throws Exception {
		try {
			ipm.registraItemPedido((List<ItemPedido>) ip);
			FacesUtil.adicionarMsgInfo("Item pedido Salvo com Sucesso.");
		} catch (NullException ne) {
			FacesUtil.adicionarMsgErro(ne.getMessage());
		}
	}

	public void excluir(ItemPedido ip) {
		ipm.removeItemPedido(ip);
		FacesUtil.adicionarMsgInfo("Item pedido excluido.");
	}

	public String editar(ItemPedido ip) {
		try {
			ipm.atualizaItemPedido(ip);
			FacesUtil.adicionarMsgInfo("Item pedido alterado.");
		} catch (NullException ne) {
			FacesUtil.adicionarMsgErro(ne.getMessage());
		}
		return "";
	}

}
