package com.prueba.comercio.exception;

import com.prueba.comercio.constants.AppConstants;
import com.prueba.comercio.utils.Properties;

public class NoFormatDataException extends RuntimeException {
	private static final long serialVersionUID = 8525648065827306011L;

	public NoFormatDataException() {
		super(Properties.obtenerInstancia().getString(AppConstants.NO_FORMAT));
	}
}
