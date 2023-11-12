package com.prueba.comercio.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.prueba.comercio.utils.Utilities;

class ProductoTest {

	@Test
	void testGettersAndSetters() {
		Producto producto = new Producto();

		producto.setId(1L);
		producto.setBrandId(1111L);
		producto.setPriceList(2222L);
		producto.setStartDate(Utilities.toLocalDateTime("2023-11-06-00.00.00"));
		producto.setEndDate(Utilities.toLocalDateTime("2023-11-06-23.59.59"));
		producto.setProductId("35455");
		producto.setPriority(1);
		producto.setPrice(100.0);
		producto.setCurr("EUR");

		assertEquals(1L, producto.getId());
		assertEquals(1111L, producto.getBrandId());
		assertEquals(2222L, producto.getPriceList());
		assertEquals(Utilities.toLocalDateTime("2023-11-06-00.00.00"), producto.getStartDate());
		assertEquals(Utilities.toLocalDateTime("2023-11-06-23.59.59"), producto.getEndDate());
		assertEquals("35455", producto.getProductId());
		assertEquals(1, producto.getPriority());
		assertEquals(100.0, producto.getPrice(), 0.001);
		assertEquals("EUR", producto.getCurr());
	}
}
