package com.prueba.comercio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.comercio.dao.ProductoDao;
import com.prueba.comercio.exception.NoFormatDataException;
import com.prueba.comercio.model.Producto;

@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoDao productoDao;

	public void setProductoDao(ProductoDao productoDao) {
		this.productoDao = productoDao;
	}

	@Override
	public List<Producto> findByFechaProductoIdCadenaId(String fechaAplicacion, String productoId, String brandId) {
		try {
			return productoDao.findByFechaProductoIdCadenaId(fechaAplicacion, productoId, Long.parseLong(brandId));
		} catch (NumberFormatException e) {
			throw new NoFormatDataException();
		}
	}
}
