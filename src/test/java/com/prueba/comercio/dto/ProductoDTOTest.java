package com.prueba.comercio.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.prueba.comercio.model.Producto;
import com.prueba.comercio.utils.Utilities;

class ProductoDTOTest {
	
	private Producto producto;
	
	@Test
	void testGettersAndSetters() {
		ProductoDTO producto = new ProductoDTO();

		producto.setBrandId(1L);
		producto.setPriceList(2L);
		producto.setStartDate(Utilities.toLocalDateTime("2023-11-06-00.00.00"));
		producto.setEndDate(Utilities.toLocalDateTime("2023-11-06-23.59.59"));
		producto.setProductId("35455");
		producto.setPrice(33.33);

		assertEquals(1L, producto.getBrandId());
		assertEquals(2L, producto.getPriceList());
		assertEquals(Utilities.toLocalDateTime("2023-11-06-00.00.00"), producto.getStartDate());
		assertEquals(Utilities.toLocalDateTime("2023-11-06-23.59.59"), producto.getEndDate());
		assertEquals("35455", producto.getProductId());
		assertEquals(33.33, producto.getPrice(), 0.001);
	}
	
	@Test
	void testConstructorWithProducto() {
		producto = mock(Producto.class);

		when(producto.getId()).thenReturn(1L);
		when(producto.getBrandId()).thenReturn(1L);
		when(producto.getPriceList()).thenReturn(2L);
		LocalDateTime startDate = Utilities.toLocalDateTime("2020-06-14-00.00.00");
		when(producto.getStartDate()).thenReturn(startDate);
		LocalDateTime endDate = Utilities.toLocalDateTime("2020-06-14-23.59.59");
		when(producto.getEndDate()).thenReturn(endDate);
		when(producto.getProductId()).thenReturn("35455");
		when(producto.getPriority()).thenReturn(1);
		when(producto.getPrice()).thenReturn(33.33);
		when(producto.getCurr()).thenReturn("EUR");

		ProductoDTO productoDTO = new ProductoDTO(producto);

		assertEquals(Long.valueOf(1L), productoDTO.getBrandId());
		assertEquals(Long.valueOf(2L), productoDTO.getPriceList());
		assertEquals(startDate, productoDTO.getStartDate());
		assertEquals(endDate, productoDTO.getEndDate());
		assertEquals("35455", productoDTO.getProductId());
		assertEquals(Double.valueOf(33.33), productoDTO.getPrice());
	}
}
