package model.dao;

import java.util.List;

import model.Entidades.Estoque;

public interface EstoqueDao {
	public List<Estoque> listarTodos();
	public Estoque consultarPorNome(String nome);
}
