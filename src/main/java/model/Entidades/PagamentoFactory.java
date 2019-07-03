package model.Entidades;

public class PagamentoFactory {
	public Pagamento build(String tipo) {
		Pagamento p = null;
		if (tipo == PagamentoI.tipo_c) {
			p = new PagamentoCartao();
		} else if (tipo == PagamentoI.tipo_d) {
			p = new PagamentoDinheiro();
		}
		return p;
	}
}
