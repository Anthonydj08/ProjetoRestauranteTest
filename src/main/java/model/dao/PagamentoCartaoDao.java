package model.dao;

import model.Entidades.PagamentoCartao;

public interface PagamentoCartaoDao {
	public PagamentoCartao buscarPorCodPagamento(int codPagamento);
}
