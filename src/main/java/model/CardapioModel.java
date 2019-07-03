package model;

import java.util.List;

import model.Entidades.Cardapio;
import model.Exception.JaExisteException;
import model.Exception.NullException;
import model.Exception.StringException;
import model.dao.CardapioDaoImpl;
import model.util.Validacoes;

public class CardapioModel {

	private CardapioDaoImpl dao = new CardapioDaoImpl();

	public void registraCategoria(Cardapio c) throws JaExisteException, NullException, StringException {
		if (c != null) {
			if (!this.existe(c)) {
				if (Validacoes.verificaString(c.getCategoria())) {
					dao.insert(c);
				} else {
					throw new StringException("Categoria inválida");
				}
			} else {
				throw new JaExisteException("Este Cardapio já existe");
			}
		} else {
			throw new NullException("Nenhum item pode estar vazio");
		}
	}

	public void atualizarCategoria(Cardapio c) throws StringException, NullException, JaExisteException {
		if (c != null) {
			if (!this.existe(c)) {
				if (Validacoes.verificaString(c.getCategoria())) {
					dao.update(c);
				} else {
					throw new StringException("Categoria inválida");
				}
			} else {
				throw new JaExisteException("Este Cardapio já existe");
			}
		} else {
			throw new NullException("Nenhum item pode estar vazio");
		}
	}

	public void removeCategoria(Cardapio c) {
		dao.remove(c);
	}

	private boolean existe(Cardapio c) {
		boolean existe = false;
		try{
			dao.consultarPorId(c.getCategoria());
			existe = true;
		} catch (Exception e) {
			
		}
		return existe;
	}

	public List<Cardapio> listarTodos() {
		return dao.listarTodos();
	}
}
