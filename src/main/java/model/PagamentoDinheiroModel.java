package model;

import model.Entidades.PagamentoDinheiro;
import model.Exception.JaExisteException;
import model.Exception.NullException;
import model.Exception.ValorException;
import model.dao.PagamentoDinheiroDao;
import model.dao.PagamentoDinheiroDaoImpl;
import model.util.Validacoes;

public class PagamentoDinheiroModel {

	private PagamentoDinheiroDaoImpl dao = new PagamentoDinheiroDaoImpl();

	public void registraPagamentoDinheiro(PagamentoDinheiro pd) throws JaExisteException, ValorException, NullException {
		if (pd != null) {
			if (!this.existe(pd)) {
				if (Validacoes.verificaValor(pd.getValor())) {
					dao.insert(pd);
				} else {
					throw new ValorException("Valor inválido");
				}
			} else {
				throw new JaExisteException("Este pagamento já existe");
			}
		} else {
			throw new NullException("Nenhum item pode estar vazio");
		}
	}

	public void atualizaPagamentoDinheiro(PagamentoDinheiro pd) throws ValorException, NullException {
		if (((PagamentoDinheiroDao) dao).buscarPorCodPagamento(pd.getCodPagamento()) != null) {
			if (Validacoes.verificaValor(pd.getValor())) {
				dao.update(pd);
			} else {
				throw new ValorException("Valor inválido");
			}
		} else {
			throw new NullException("Nenhum item pode estar vazio");
		}
	}

	public void deletaPagamentoDinheiro(PagamentoDinheiro pd) {
		dao.remove(pd);
	}

	private boolean existe(PagamentoDinheiro pd) {
		boolean valida = false;
		if (((PagamentoDinheiroDao) dao).buscarPorCodPagamento(pd.getCodPagamento()) != null) {
			valida = true;
		}
		return valida;
	}
	
}
