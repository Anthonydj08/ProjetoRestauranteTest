package model;

import model.Entidades.PagamentoCartao;
import model.Exception.JaExisteException;
import model.Exception.NullException;
import model.Exception.StringException;
import model.dao.PagamentoCartaoDao;
import model.dao.PagamentoCartaoDaoImpl;
import model.util.Validacoes;

public class PagamentoCartaoModel {

	private PagamentoCartaoDaoImpl dao = new PagamentoCartaoDaoImpl();

	public void registraPagamentoCartao(PagamentoCartao pc) throws JaExisteException, StringException, NullException {
		if (pc != null) {
			if (!this.existe(pc)) {
				if (Validacoes.verificaString(pc.getNomeTitular())) {
					dao.insert(pc);
				} else {
					throw new StringException("Não digite números ou símbolos");
				}
			} else {
				throw new JaExisteException("Este pagamento já existe");
			}
		} else {
			throw new NullException("Nenhum item pode estar vazio");
		}
	}

	public void atualizaPagamentoCartao(PagamentoCartao pc) throws StringException, NullException {
		if  (!this.existe(pc)) {
			if (Validacoes.verificaString(pc.getNomeTitular())) {
				dao.update(pc);
			} else {
				throw new StringException("Nome inválido");
			}
		} else {
			throw new NullException("Nenhum item pode estar vazio");
		}
	}

	public void deletaPagamentoCartao(PagamentoCartao pc) {
		dao.remove(pc);
	}

	private boolean existe(PagamentoCartao pc) {
		boolean valida = false;
		if (((PagamentoCartaoDao) dao).buscarPorCodPagamento(pc.getCodPagamento()) != null) {
			valida = true;
		}
		return valida;
	}

}
