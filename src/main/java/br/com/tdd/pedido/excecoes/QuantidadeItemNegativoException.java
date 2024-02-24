package br.com.tdd.pedido.excecoes;

public class QuantidadeItemNegativoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public QuantidadeItemNegativoException(String msg) {
		super(msg);
	}
}
