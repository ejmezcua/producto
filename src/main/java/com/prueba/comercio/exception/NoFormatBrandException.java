package com.prueba.comercio.exception;

import java.text.MessageFormat;

import com.prueba.comercio.constants.AppConstants;
import com.prueba.comercio.utils.Properties;

public class NoFormatBrandException extends RuntimeException {
	private static final long serialVersionUID = 8525648065827306011L;

	public NoFormatBrandException(String brandId) {
		super(MessageFormat.format(Properties.obtenerInstancia().getString(AppConstants.NO_FORMAT_BRAND), brandId));
	}
}
