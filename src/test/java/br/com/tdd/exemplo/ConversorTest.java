package br.com.tdd.exemplo;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Before;

import junit.framework.Assert;

public class ConversorTest {
	private CamelCaseConverter camelCase;
	
	@Before
	public void setup() {
		camelCase = new CamelCaseConverter();
	}

	@Test
	public void deveConverterNome() {
		assertEquals("Lionel", camelCase.converter("lionel"));
	}
	
	@Test
	public void deveConverterUmNomeDadoLetrasMaiusculasEMinusculasInformado() {
		assertEquals("Lionel", camelCase.converter("lIoNEl"));
	}
}
