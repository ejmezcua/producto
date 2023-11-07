package com.prueba.comercio.dao;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.prueba.comercio.exception.NoDataFoundException;
import com.prueba.comercio.exception.ProductoNotFoundException;
import com.prueba.comercio.repositories.DatabaseInitializer;

class ProductoDaoImplTest {

	private ProductoDaoImpl productoDao;

	@BeforeEach
	void setUp() {
		DatabaseInitializer.initDB();
		productoDao = new ProductoDaoImpl();
	}

	@Test
	void testFindByFechaProductoIdCadenaId_ProductoNotFoundException() {
		String fecha = "2020-06-14-12.00.00";
		String productoId = "35455_Not_Exists";
		Long brandId = 1L;

		assertThrows(ProductoNotFoundException.class, () -> {
			productoDao.findByFechaProductoIdCadenaId(fecha, productoId, brandId);
		});
	}

	@Test
	void testFindByFechaProductoIdCadenaId_NoDataFoundException() {
		String fecha = "2020-06-14-12.00.00_Data_Error";
		String productoId = "35455";
		Long brandId = 1L;

		assertThrows(NoDataFoundException.class, () -> {
			productoDao.findByFechaProductoIdCadenaId(fecha, productoId, brandId);
		});
	}
}
