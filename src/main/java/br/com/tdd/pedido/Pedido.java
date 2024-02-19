package br.com.tdd.pedido;

import java.math.BigDecimal;

public class Pedido {
	private BigDecimal valorTotal = BigDecimal.ZERO;

	public void adicionaItem(Item item) {
		valorTotal = valorTotal.add(extracted(item));
	}

	private BigDecimal extracted(Item item) {
		return item.getValorUnitario().multiply(new BigDecimal(item.getQuantidade()));
	}

	public BigDecimal valorTotal() {
		return valorTotal;
	}

	public BigDecimal desconto() {
		return BigDecimal.ZERO;
	}

}
