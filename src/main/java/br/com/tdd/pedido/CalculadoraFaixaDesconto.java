package br.com.tdd.pedido;

import java.math.BigDecimal;

public abstract class CalculadoraFaixaDesconto {
	private CalculadoraFaixaDesconto proximo;

	protected CalculadoraFaixaDesconto(CalculadoraFaixaDesconto proximo) {
		this.proximo = proximo;
	}
	
	public BigDecimal desconto(BigDecimal valorTotal) {
		BigDecimal desconto = calcular(valorTotal);
		
		if(valorTotal.doubleValue() == -1) 
			return proximo.desconto(valorTotal);
		
		return desconto;
	}

	protected abstract BigDecimal calcular(BigDecimal valorTotal);
	
	
}
