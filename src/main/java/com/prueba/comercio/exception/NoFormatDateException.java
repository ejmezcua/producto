package com.prueba.comercio.exception;

import java.text.MessageFormat;

import com.prueba.comercio.constants.AppConstants;
import com.prueba.comercio.utils.Properties;

public class NoFormatDateException extends RuntimeException {
	private static final long serialVersionUID = -5615633593581446889L;

	public NoFormatDateException(String date) {
		super(MessageFormat.format(Properties.obtenerInstancia().getString(AppConstants.NO_FORMAT_DATE), date));
	}
}
