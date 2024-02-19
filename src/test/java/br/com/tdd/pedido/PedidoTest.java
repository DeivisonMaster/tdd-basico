package br.com.tdd.pedido;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class PedidoTest {
	private Pedido pedido;
	
	@Before
	public void init() {
		pedido = new Pedido();
	}

	@Test
	public void devePermitirAdicionarUmItemAoPedido() throws Exception {
		pedido.adicionaItem(new Item("Sabonete", new BigDecimal("3.50"), 3));
	}
	
	@Test
	public void deveCalcularValorTotalParaPedidoVazio() throws Exception {
		assertEquals(BigDecimal.ZERO, pedido.valorTotal());
	}
	
	@Test
	public void deveCalcularDescontoParaPedidoVazio() throws Exception {
		assertEquals(BigDecimal.ZERO, pedido.desconto());
	}
	
	@Test
	public void deveCalcularPedidoParaUmItemSemDesconto() throws Exception {
		pedido.adicionaItem(new Item("Óleo", new BigDecimal("5.00"), 5));
		
		assertEquals(new BigDecimal("25.00"), pedido.valorTotal());
		assertEquals(BigDecimal.ZERO, pedido.desconto());
	}
}
