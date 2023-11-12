package com.prueba.comercio.service;

import com.prueba.comercio.dto.ProductoDTO;

public interface ProductoService {

	public ProductoDTO findByFechaProductoIdCadenaId(String fechaAplicacion, String productoId, String brandId);
}
