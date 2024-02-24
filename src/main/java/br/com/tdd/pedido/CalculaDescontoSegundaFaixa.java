package br.com.tdd.pedido;

import java.math.BigDecimal;

public class CalculaDescontoSegundaFaixa extends CalculadoraFaixaDesconto{

	public CalculaDescontoSegundaFaixa(CalculadoraFaixaDesconto proximo) {
		super(proximo);
	}

	@Override
	protected BigDecimal calcular(BigDecimal valorTotal) {
		if(valorTotal.compareTo(new BigDecimal("800.0")) > 0 && valorTotal.compareTo(new BigDecimal("1000.0")) < 0)
			return BigDecimal.valueOf(valorTotal.doubleValue() * 0.06);
		
		return new BigDecimal("-1");
	}

}
