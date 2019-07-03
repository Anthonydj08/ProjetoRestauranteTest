package model;

import java.util.List;

import model.Entidades.Usuario;
import model.Exception.StringException;
import model.dao.UsuarioDaoImpl;
import model.util.Validacoes;

public class UsuarioModel {
	private UsuarioDaoImpl dao = new UsuarioDaoImpl();

	public void registraUsuario(Usuario u) throws StringException {
		if (u != null) {
			if (!this.existe(u)) {
				if (Validacoes.verificaString(u.getLogin())) {
					dao.insert(u);
				} else {
					throw new StringException("Login já cadastrado");
				}
			}
		}
	}

	public void atualizaUsuario(Usuario u) {
		dao.update(u);
	}

	public void removeCliente(Usuario u) {
		dao.remove(u);
	}

	private boolean existe(Usuario u) {
		boolean existe = false;
		try {
			dao.consultarPorLogin(u.getLogin());
			existe = true;
		} catch (Exception e) {

		}
		return existe;
	}
	
	public Usuario autenticar(String login, String senha) {
		if (login == null || senha == null) {
			return null;
		}
		return dao.consultarLogin(login, senha);
	}
	
	public List<Usuario> listarTodos() {
		return dao.listarTodos();
	}
}
