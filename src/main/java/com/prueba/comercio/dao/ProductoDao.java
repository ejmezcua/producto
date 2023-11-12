package com.prueba.comercio.dao;

import java.time.LocalDateTime;
import java.util.Optional;

import com.prueba.comercio.model.Producto;

public interface ProductoDao {

	public Optional<Producto> findByFechaProductoIdCadenaId(LocalDateTime fechaAplicacion, String productId,
			Long brandId);
}
