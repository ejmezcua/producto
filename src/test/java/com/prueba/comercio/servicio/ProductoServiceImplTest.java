package com.prueba.comercio.servicio;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.format.DateTimeParseException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.prueba.comercio.dao.ProductoDao;
import com.prueba.comercio.dto.ProductoDTO;
import com.prueba.comercio.exception.NoFormatBrandException;
import com.prueba.comercio.exception.NoFormatDateException;
import com.prueba.comercio.model.Producto;
import com.prueba.comercio.service.ProductoServiceImpl;
import com.prueba.comercio.utils.Utilities;

class ProductoServiceImplTest {

	private ProductoServiceImpl productoService;
	private ProductoDao productoDao;
	private String fechaAplicacion;
	private String productoId;
	private String brandId;
	private Long brandIdParsed;
	private String brandIdError;
	private String fechaAplicacionError;
	private Producto producto;

	@BeforeEach
	public void setUp() {
		productoService = new ProductoServiceImpl();
		productoDao = mock(ProductoDao.class);

		productoService.setProductoDao(productoDao);

		fechaAplicacion = "2023-11-06-00.00.00";
		productoId = "35455";
		brandId = "456";
		brandIdParsed = 456L;
		
		brandIdError = "invalid_brand_id";
		fechaAplicacionError = "2023-11-0test6-00.00.00";

		producto = new Producto();
		producto.setPrice(123.12);
	}

	@Test
	void testFindByFechaProductoIdCadenaId() {
        when(productoDao.findByFechaProductoIdCadenaId(Utilities.toLocalDateTime(fechaAplicacion), productoId, brandIdParsed))
            .thenReturn(Optional.of(producto));

        ProductoDTO result = productoService.findByFechaProductoIdCadenaId(fechaAplicacion, productoId, brandId);

        assertNotNull(result);
        assertNotEquals(null, result);
    }

	@Test
	void testFindByFechaProductoIdCadenaIdWithNumberFormatException() {
		when(productoDao.findByFechaProductoIdCadenaId(any(), anyString(), anyLong()))
				.thenThrow(NumberFormatException.class);

		assertThrows(NoFormatBrandException.class, () -> {
			productoService.findByFechaProductoIdCadenaId(fechaAplicacion, productoId, brandIdError);
		});
	}

	@Test
	void testFindByFechaProductoIdCadenaIdWithDateTimeParseException() {
		//brandId = "invalid_brand_id";

		when(productoDao.findByFechaProductoIdCadenaId(any(), anyString(), anyLong()))
				.thenThrow(DateTimeParseException.class);

		assertThrows(NoFormatDateException.class, () -> {
			productoService.findByFechaProductoIdCadenaId(fechaAplicacionError, productoId, brandId);
		});
	}	
}
