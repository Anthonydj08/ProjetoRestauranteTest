package model;

import java.util.List;

import model.Entidades.ItemCardapio;
import model.Exception.JaExisteException;
import model.Exception.NullException;
import model.Exception.ValorException;
import model.dao.ItemCardapioDaoImpl;
import model.util.Validacoes;

public class ItemCardapioModel {

	private ItemCardapioDaoImpl dao = new ItemCardapioDaoImpl();

	public void registraItemCardapio(ItemCardapio ic)
			throws JaExisteException, ValorException, NullException {
		if (ic != null) {
			if (!this.existe(ic)) {
				if (Validacoes.verificaValor(ic.getPreco())) {
					dao.insert(ic);
				} else {
					throw new ValorException("Valor inválido");
				}
			} else {
				throw new JaExisteException("Este item do cardapio já existe");
			}
		} else {
			throw new NullException("Nenhum item pode estar vazio");
		}
	}

	public void atualizaItemCardapio(ItemCardapio ic) throws ValorException, NullException {
		if (Validacoes.verificaValor(ic.getPreco())) {
			dao.update(ic);
		} else {
			throw new ValorException("Valor inválido");
		}
	}

	public void removeItemCardapio(ItemCardapio ic) {
		dao.remove(ic);
	}

	private boolean existe(ItemCardapio ic) {
		boolean existe = false;
		try{
			dao.consultarPorNome(ic.getEstoque().getNome());
			existe = true;
		} catch (Exception e) {
			
		}
		return existe;
	}

	public List<ItemCardapio> listarTodos() {
		return dao.listarTodos();
	}
}
