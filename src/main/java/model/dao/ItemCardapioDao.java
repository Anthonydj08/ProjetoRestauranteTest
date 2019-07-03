package model.dao;

import java.util.List;

import model.Entidades.ItemCardapio;

public interface ItemCardapioDao {
	public List<ItemCardapio> listarTodos();
	public ItemCardapio consultarPorNome(String nome);
}
