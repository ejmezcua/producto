package com.prueba.comercio.dao;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.prueba.comercio.repositories.DatabaseInitializer;
import com.prueba.comercio.repositories.ProductoRepository;
import com.prueba.comercio.utils.Utilities;

@ExtendWith(MockitoExtension.class)
class ProductoDaoImplTest {

	@InjectMocks
	private ProductoDaoImpl productoDao;

	@Mock
	private ProductoRepository productoRepository;

	@BeforeEach
	void setUp() {
		DatabaseInitializer.initDB();
	}

	@Test
	void testFindByFechaProductoIdCadenaId_ProductoNotFoundException() {
		String fecha = "2020-06-14-12.00.00";
		String productoId = "35455_Not_Exists";
		Long brandId = 1L;

		when(productoRepository
				.findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
						productoId, brandId, Utilities.toLocalDateTime(fecha), Utilities.toLocalDateTime(fecha)))
				.thenThrow(NumberFormatException.class);

		Exception exc = assertThrows(NumberFormatException.class, () -> {
			productoDao.findByFechaProductoIdCadenaId(Utilities.toLocalDateTime(fecha), productoId, brandId);
		});
		
		assertTrue(exc instanceof NumberFormatException);
	}
}
