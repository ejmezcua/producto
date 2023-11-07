package com.prueba.comercio.servicio;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.prueba.comercio.dao.ProductoDao;
import com.prueba.comercio.exception.NoFormatDataException;
import com.prueba.comercio.model.Producto;
import com.prueba.comercio.service.ProductoServiceImpl;

class ProductoServiceImplTest {

	private ProductoServiceImpl servicio;
	private ProductoDao productoDao;
	private String fechaAplicacion;
	private String productoId;
	private String brandId;
	private Long brandIdParsed;
	private Producto producto;

	@BeforeEach
	public void setUp() {
		servicio = new ProductoServiceImpl();
		productoDao = mock(ProductoDao.class);

		servicio.setProductoDao(productoDao);

		fechaAplicacion = "2023-11-06-00.00.00";
		productoId = "35455";
		brandId = "456";
		brandIdParsed = 456L;

		producto = new Producto();
		producto.setPrice(123.12);
	}

	@Test
	void testFindByFechaProductoIdCadenaId() {
        when(productoDao.findByFechaProductoIdCadenaId(fechaAplicacion, productoId, brandIdParsed))
            .thenReturn(Arrays.asList(producto));

        List<Producto> productos = servicio.findByFechaProductoIdCadenaId(fechaAplicacion, productoId, brandId);

        assertNotNull(productos);
        assertFalse(productos.isEmpty());
    }

	@Test
	void testFindByFechaProductoIdCadenaIdWithNumberFormatException() {
		brandId = "invalid_brand_id";

		when(productoDao.findByFechaProductoIdCadenaId(anyString(), anyString(), anyLong()))
				.thenThrow(NumberFormatException.class);

		assertThrows(NoFormatDataException.class, () -> {
			servicio.findByFechaProductoIdCadenaId(fechaAplicacion, productoId, brandId);
		});
	}
}
