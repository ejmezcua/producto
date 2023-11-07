package com.prueba.comercio.exception;

import com.prueba.comercio.constants.AppConstants;
import com.prueba.comercio.utils.Properties;

public class NoDataFoundException extends RuntimeException {
	private static final long serialVersionUID = -1569152274021022534L;

	public NoDataFoundException() {
		super(Properties.obtenerInstancia().getString(AppConstants.NO_DATA));
	}
}
