package com.prueba.comercio.service;

public class ProductoException extends Exception {
	private static final long serialVersionUID = 644409717294944983L;

	public ProductoException() {
		super();
	}

	public ProductoException(String mensaje) {
		super(mensaje);
	}

	public ProductoException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}
}
