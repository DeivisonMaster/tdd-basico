package br.com.tdd.pedido;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import br.com.tdd.pedido.excecoes.QuantidadeItemNegativoException;

public class PedidoTest {
	private PedidoBuilder pedidoBuilder;
	
	@Before
	public void init() {
		pedidoBuilder = new PedidoBuilder();
	}

	@Test
	public void devePermitirAdicionarUmItemAoPedido() throws Exception {
		pedidoBuilder.comItem("Sabonete", new BigDecimal("3.50"), 3);
	}
	
	@Test
	public void deveCalcularValorTotalParaPedidoVazio() throws Exception {
		assertEquals(new BigDecimal("0.0"), pedidoBuilder.valorTotal());
	}
	
	@Test
	public void deveCalcularDescontoParaPedidoVazio() throws Exception {
		assertEquals(BigDecimal.ZERO, pedidoBuilder.desconto());
	}
	
	@Test
	public void deveCalcularPedidoParaUmItemSemDesconto() throws Exception {
		pedidoBuilder.comItem("Óleo", new BigDecimal("5.00"), 5);
		
		assertEquals(new BigDecimal("25.0"), pedidoBuilder.valorTotal());
		assertEquals(BigDecimal.ZERO, pedidoBuilder.desconto());
	}
	
	@Test
	public void deveCalcularPedidoParaDoisItensSemDesconto() throws Exception {
		pedidoBuilder
			.comItem("Café", new BigDecimal("7.50"), 3)
			.comItem("Filtro Café", new BigDecimal("4.50"), 3);
		
		assertEquals(new BigDecimal("36.0"), pedidoBuilder.valorTotal());
		assertEquals(BigDecimal.ZERO, pedidoBuilder.desconto());
	}
	
	@Test
	public void deveAplicarDescontoNaPrimeiraFaixaDeDesconto() throws Exception {
		pedidoBuilder.comItem("Creme", new BigDecimal("20.00"), 20);
		
		assertEquals(new BigDecimal("400.0"), pedidoBuilder.valorTotal());
		assertEquals(new BigDecimal("16.0"), pedidoBuilder.desconto());
	}
	
	@Test
	public void deveAplicarDescontoNaSegundaFaixaDeDesconto() throws Exception {
		pedidoBuilder
			.comItem("Shampoo", new BigDecimal("15.0"), 30)
			.comItem("Oleo", new BigDecimal("15.0"), 30);
		
		assertEquals(new BigDecimal("900.0"), pedidoBuilder.valorTotal());
		assertEquals(new BigDecimal("54.0"), pedidoBuilder.desconto());
	}
	
	@Test
	public void deveAplicarDescontoNaTerceiraFaixaDeDesconto() throws Exception {
		pedidoBuilder
			.comItem("Creme", new BigDecimal("15.00"), 30)
			.comItem("Shampoo", new BigDecimal("10.0"), 30)
			.comItem("Oleo", new BigDecimal("15.0"), 30);
		
		assertEquals(new BigDecimal("1200.0"), pedidoBuilder.valorTotal());
		assertEquals(new BigDecimal("96.0"), pedidoBuilder.desconto());
	}
	
	@Test(expected = QuantidadeItemNegativoException.class)
	public void naoDeveAceitarPedidosComItensComQuantidadesNegativas() {
		pedidoBuilder.comItem("Desodorante", new BigDecimal("4.00"), -11);
	}
	
	@Test
	public void deveValidarMensagemErroDeItemComQuantidadeNegativa() {
		try {
			pedidoBuilder.comItem("Esponja", new BigDecimal("3.50"), -3);
			fail("Deveria ter lançado a exception QuantidadeItensInvalidaException");
			
		} catch (QuantidadeItemNegativoException e) {
			assertEquals("Não é possivel adicionar item com quantidade negativa!", e.getMessage());
		}
	}
}
