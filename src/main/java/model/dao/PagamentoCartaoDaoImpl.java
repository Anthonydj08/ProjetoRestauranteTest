package model.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Entidades.PagamentoCartao;
import model.dao.util.JPAManager;

public class PagamentoCartaoDaoImpl extends DAOImpl implements PagamentoCartaoDao{

	public PagamentoCartao buscarPorCodPagamento(int codPagamento) {
		PagamentoCartao pagamentoCartao = null;
		EntityManager mng = JPAManager.getInstance().getEntityManager();
		try {
			Query query = mng.createQuery("FROM PagamentoCartao where cod_pagamento = :codPagamento");
			pagamentoCartao = (PagamentoCartao) query.getSingleResult();
		} finally {
			mng.close();
		}
		return pagamentoCartao;
	}
	
}
