package br.com.tdd.pedido;

import java.math.BigDecimal;

public class CalculaDescontoTerceiraFaixa extends CalculadoraFaixaDesconto{

	public CalculaDescontoTerceiraFaixa(CalculadoraFaixaDesconto proximo) {
		super(proximo);
	}

	@Override
	protected BigDecimal calcular(BigDecimal valorTotal) {
		if(valorTotal.compareTo(new BigDecimal("1000.0")) > 0)
			return BigDecimal.valueOf(valorTotal.doubleValue() * 0.08);
		
		return new BigDecimal("-1");
	}

}
