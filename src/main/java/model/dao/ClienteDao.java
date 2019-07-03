package model.dao;

import java.util.List;

import model.Entidades.Cliente;

public interface ClienteDao {
	public List<Cliente> ListarTodos();
	public Cliente consultarPorCpf(String cpf);
}
