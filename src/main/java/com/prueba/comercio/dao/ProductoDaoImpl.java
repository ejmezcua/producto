package com.prueba.comercio.dao;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prueba.comercio.model.Producto;
import com.prueba.comercio.repositories.ProductoRepository;

@Component
public class ProductoDaoImpl implements ProductoDao {

	@Autowired
	private ProductoRepository productoRepository;

	@Override
	public Optional<Producto> findByFechaProductoIdCadenaId(LocalDateTime fechaAplicacion, String productId,
			Long brandId) {
		return productoRepository
				.findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
						productId, brandId, fechaAplicacion, fechaAplicacion);
	}
}
