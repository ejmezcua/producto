package com.prueba.comercio.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class Properties {

	private static ResourceBundle bundle;

	private Properties() { }

	public static ResourceBundle obtenerInstancia() {
		if (bundle == null) {
			bundle = ResourceBundle.getBundle("messages", new Locale("es"));
		}
		return bundle;
	}
}
