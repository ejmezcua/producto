package com.prueba.comercio.service;

import java.time.format.DateTimeParseException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.comercio.dao.ProductoDao;
import com.prueba.comercio.dto.ProductoDTO;
import com.prueba.comercio.exception.NoFormatBrandException;
import com.prueba.comercio.exception.NoFormatDateException;
import com.prueba.comercio.exception.ProductoNotFoundException;
import com.prueba.comercio.model.Producto;
import com.prueba.comercio.utils.Utilities;

@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoDao productoDao;

    public void setProductoDao(ProductoDao productoDao) {
        this.productoDao = productoDao;
    }

	@Override
	@Transactional
	public ProductoDTO findByFechaProductoIdCadenaId(String fechaAplicacion, String productoId, String brandId) {
		try {
			Optional<Producto> optional = productoDao.findByFechaProductoIdCadenaId(Utilities.toLocalDateTime(fechaAplicacion), productoId, Long.parseLong(brandId));
			return new ProductoDTO(optional.orElseThrow(()-> new ProductoNotFoundException(fechaAplicacion, productoId, brandId))); 
		} catch (NumberFormatException e) {
			throw new NoFormatBrandException(brandId);
		} catch (DateTimeParseException e) {
			throw new NoFormatDateException(fechaAplicacion);
		}		
	}
}
