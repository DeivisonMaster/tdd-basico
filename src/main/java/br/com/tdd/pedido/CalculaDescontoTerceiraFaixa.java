package br.com.tdd.pedido;

import java.math.BigDecimal;

public class CalculaDescontoTerceiraFaixa extends CalculadoraFaixaDesconto{

	public CalculaDescontoTerceiraFaixa(CalculadoraFaixaDesconto proximo) {
		super(proximo);
	}

	@Override
	protected double calcular(BigDecimal valorTotal) {
		if(valorTotal.compareTo(new BigDecimal("1000.0")) > 0)
			return valorTotal.doubleValue() * 0.08;
		
		return -1;
	}

}
