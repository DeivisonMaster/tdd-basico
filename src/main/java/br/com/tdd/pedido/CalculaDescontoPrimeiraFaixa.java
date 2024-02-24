package br.com.tdd.pedido;

import java.math.BigDecimal;

public class CalculaDescontoPrimeiraFaixa extends CalculadoraFaixaDesconto{

	public CalculaDescontoPrimeiraFaixa(CalculadoraFaixaDesconto proximo) {
		super(proximo);
	}

	@Override
	protected BigDecimal calcular(BigDecimal valorTotal) {
		if(valorTotal.compareTo(new BigDecimal("300.00")) > 0 && valorTotal.compareTo(new BigDecimal("800.00")) < 0)
			return BigDecimal.valueOf(valorTotal.doubleValue() * 0.04);
		
		return new BigDecimal("-1");
	}

}
