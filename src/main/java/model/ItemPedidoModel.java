package model;

import java.util.List;

import model.Entidades.ItemPedido;
import model.Exception.NullException;
import model.dao.ItemPedidoDaoImpl;

public class ItemPedidoModel {

	private ItemPedidoDaoImpl dao = new ItemPedidoDaoImpl();

	public void registraItemPedido(List<ItemPedido> itensPedido) throws NullException, Exception{
		if (itensPedido != null) {
			dao.insertItens(itensPedido);
		} else {
			throw new NullException("Nenhum item pode estar vazio");
		}
	}

	public void atualizaItemPedido(ItemPedido ip) throws NullException {
		if (ip != null) {
			dao.update(ip);
		} else {
			throw new NullException("Nenhum item pode estar vazio");
		}
	}

	public void removeItemPedido(ItemPedido ip) {
		dao.remove(ip);
	}

	public List<ItemPedido> listarTodos() {
		return dao.listarTodos();
	}
}
