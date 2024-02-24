package br.com.tdd.pedido;

import java.math.BigDecimal;

public class PedidoBuilder {
	private Pedido pedido;
	
	public PedidoBuilder() {
		CalculadoraFaixaDesconto calculadoraFaixaDesconto = 
				new CalculaDescontoTerceiraFaixa(
						new CalculaDescontoSegundaFaixa(
								new CalculaDescontoPrimeiraFaixa(
										new CalculaSemDesconto(null))));
		
		pedido = new Pedido(calculadoraFaixaDesconto);
	}

	public PedidoBuilder comItem(String descricao, BigDecimal valor, Integer quantidade) {
		pedido.adicionaItem(new Item(descricao, valor, quantidade));
		return this;
	}

	public BigDecimal valorTotal() {
		return pedido.valorTotal();
	}

	public BigDecimal desconto() {
		return pedido.desconto();
	}
}
