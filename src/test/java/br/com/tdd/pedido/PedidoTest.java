package br.com.tdd.pedido;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class PedidoTest {
	private Pedido pedido;
	
	@Before
	public void init() {
		CalculadoraFaixaDesconto calculadoraFaixaDesconto = 
				new CalculaDescontoTerceiraFaixa(
						new CalculaDescontoSegundaFaixa(
								new CalculaDescontoPrimeiraFaixa(
										new CalculaSemDesconto(null))));
		
		pedido = new Pedido(calculadoraFaixaDesconto);
	}

	@Test
	public void devePermitirAdicionarUmItemAoPedido() throws Exception {
		pedido.adicionaItem(new Item("Sabonete", new BigDecimal("3.50"), 3));
	}
	
	@Test
	public void deveCalcularValorTotalParaPedidoVazio() throws Exception {
		assertEquals(new BigDecimal("0.0"), pedido.valorTotal());
	}
	
	@Test
	public void deveCalcularDescontoParaPedidoVazio() throws Exception {
		assertEquals(BigDecimal.ZERO, pedido.desconto());
	}
	
	@Test
	public void deveCalcularPedidoParaUmItemSemDesconto() throws Exception {
		pedido.adicionaItem(new Item("Óleo", new BigDecimal("5.00"), 5));
		
		assertEquals(new BigDecimal("25.0"), pedido.valorTotal());
		assertEquals(BigDecimal.ZERO, pedido.desconto());
	}
	
	@Test
	public void deveCalcularPedidoParaDoisItensSemDesconto() throws Exception {
		pedido.adicionaItem(new Item("Café", new BigDecimal("7.50"), 3));
		pedido.adicionaItem(new Item("Filtro Café", new BigDecimal("4.50"), 3));
		
		assertEquals(new BigDecimal("36.0"), pedido.valorTotal());
		assertEquals(BigDecimal.ZERO, pedido.desconto());
	}
	
	@Test
	public void deveAplicarDescontoNaPrimeiraFaixaDeDesconto() throws Exception {
		pedido.adicionaItem(new Item("Creme", new BigDecimal("20.00"), 20));
		
		assertEquals(new BigDecimal("400.0"), pedido.valorTotal());
		assertEquals(new BigDecimal("16.0"), pedido.desconto());
	}
	
	@Test
	public void deveAplicarDescontoNaSegundaFaixaDeDesconto() throws Exception {
		pedido.adicionaItem(new Item("Shampoo", new BigDecimal("15.0"), 30));
		pedido.adicionaItem(new Item("Oleo", new BigDecimal("15.0"), 30));
		
		assertEquals(new BigDecimal("900.0"), pedido.valorTotal());
		assertEquals(new BigDecimal("54.0"), pedido.desconto());
	}
	
	@Test
	public void deveAplicarDescontoNaTerceiraFaixaDeDesconto() throws Exception {
		pedido.adicionaItem(new Item("Creme", new BigDecimal("15.00"), 30));
		pedido.adicionaItem(new Item("Shampoo", new BigDecimal("10.0"), 30));
		pedido.adicionaItem(new Item("Oleo", new BigDecimal("15.0"), 30));
		
		assertEquals(new BigDecimal("1200.0"), pedido.valorTotal());
		assertEquals(new BigDecimal("96.0"), pedido.desconto());
	}
	
}
