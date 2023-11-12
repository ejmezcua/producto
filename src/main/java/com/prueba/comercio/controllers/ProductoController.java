package com.prueba.comercio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.comercio.dto.ProductoDTO;
import com.prueba.comercio.service.ProductoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class ProductoController {

	@Autowired
	private ProductoService productoService;

	@GetMapping("/producto/{fecha}/{productoId}/{cadenaId}")
	public ProductoDTO getProductos(@PathVariable String fecha, @PathVariable @Valid String productoId,
			@PathVariable @Valid String cadenaId) {
		return productoService.findByFechaProductoIdCadenaId(fecha, productoId, cadenaId);
	}
}
