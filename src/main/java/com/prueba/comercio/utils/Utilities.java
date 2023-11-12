package com.prueba.comercio.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.prueba.comercio.constants.AppConstants;

public class Utilities {
	
	private Utilities() {
		super();
	}

	public static LocalDateTime toLocalDateTime(String fecha) {
		return LocalDateTime.parse(fecha, DateTimeFormatter.ofPattern(AppConstants.DATE_FORMAT));
	}
}
