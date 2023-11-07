package com.prueba.comercio.service;

import java.util.List;

import com.prueba.comercio.model.Producto;

public interface ProductoService {

	public List<Producto> findByFechaProductoIdCadenaId(String fechaAplicacion, String productoId, String brandId);
}
