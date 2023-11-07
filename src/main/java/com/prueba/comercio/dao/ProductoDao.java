package com.prueba.comercio.dao;

import java.util.List;

import com.prueba.comercio.model.Producto;

public interface ProductoDao {

	public List<Producto> findByFechaProductoIdCadenaId(String fechaAplicacion, String productoId, Long brandId);
}
