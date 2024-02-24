package br.com.tdd.pedido;

import java.math.BigDecimal;

public abstract class CalculadoraFaixaDesconto {
	private CalculadoraFaixaDesconto proximo;

	protected CalculadoraFaixaDesconto(CalculadoraFaixaDesconto proximo) {
		this.proximo = proximo;
	}
	
	public double desconto(BigDecimal valorTotal) {
		double desconto = calcular(valorTotal);
		
		if(desconto == -1.0) {
			return proximo.desconto(valorTotal);
		}
		
		return desconto;
	}

	protected abstract double calcular(BigDecimal valorTotal);
	
	
}
