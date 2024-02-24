package br.com.tdd.pedido;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
	private BigDecimal valorTotal = BigDecimal.ZERO;
	private BigDecimal desconto = BigDecimal.ZERO;
	private List<Item> itens = new ArrayList<>();
	private CalculadoraFaixaDesconto calculadoraFaixaDesconto;
	
	public Pedido(CalculadoraFaixaDesconto calculadoraFaixaDesconto) {
		this.calculadoraFaixaDesconto = calculadoraFaixaDesconto;
	}

	public void adicionaItem(Item item) {
		itens.add(item);
	}

	private BigDecimal obtemValorTotal(Item item) {
		return item.getValorUnitario().multiply(new BigDecimal(item.getQuantidade()));
	}

	public BigDecimal valorTotal() {
		valorTotal = BigDecimal.valueOf(itens.stream().mapToDouble(item -> obtemValorTotal(item).doubleValue()).sum());
		return valorTotal;
	}

	public BigDecimal desconto() {
		if(valorTotal.compareTo(new BigDecimal("300.00")) > 0 && valorTotal.compareTo(new BigDecimal("800.00")) < 0) {
			desconto = BigDecimal.valueOf(valorTotal.doubleValue() * 0.04);
		} else if(valorTotal.compareTo(new BigDecimal("800.0")) > 0 && valorTotal.compareTo(new BigDecimal("1000.0")) < 0) {
			desconto = BigDecimal.valueOf(valorTotal.doubleValue() * 0.06);
		} else if(valorTotal.compareTo(new BigDecimal("1000.0")) > 0) {
			desconto = BigDecimal.valueOf(valorTotal.doubleValue() * 0.08);
		} else {
			desconto = BigDecimal.ZERO;
		}
		return desconto;
		
//		return BigDecimal.valueOf(calculadoraFaixaDesconto.calcular(valorTotal));
	}

}
