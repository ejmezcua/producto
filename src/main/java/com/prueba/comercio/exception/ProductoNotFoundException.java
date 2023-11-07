package com.prueba.comercio.exception;

import java.text.MessageFormat;

import com.prueba.comercio.constants.AppConstants;
import com.prueba.comercio.utils.Properties;

public class ProductoNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 8483823568590322440L;

	public ProductoNotFoundException(String fechaAplicacion, String productoId, String brandId) {
		super(MessageFormat.format(Properties.obtenerInstancia().getString(AppConstants.NOT_FOUND), productoId,
				fechaAplicacion, brandId));
	}
}
