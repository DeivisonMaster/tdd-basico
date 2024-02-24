package br.com.tdd.pedido;

import java.math.BigDecimal;

public class CalculaSemDesconto extends CalculadoraFaixaDesconto{

	public CalculaSemDesconto(CalculadoraFaixaDesconto proximo) {
		super(proximo);
	}

	@Override
	protected BigDecimal calcular(BigDecimal valorTotal) {
		return BigDecimal.ZERO;
	}

}
