package com.prueba.comercio.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.prueba.comercio.service.ProductoException;

class ProductoExceptionTest {
	private static final String TESTING_CAUSE = "testing cause";
	private static final String TESTING_MESSAGES = "testing messages";

	@Test
	void testConstructorWithoutMessage() {
		ProductoException exception = new ProductoException();

		assertEquals(null, exception.getMessage());
		assertEquals(null, exception.getCause());
	}

	@Test
	void testConstructorWithMessage() {
		ProductoException exception = new ProductoException(TESTING_MESSAGES);

		assertEquals(TESTING_MESSAGES, exception.getMessage());
	}

	@Test
	void testConstructorWithMessageAndCause() {
		Exception causa = new Exception(TESTING_CAUSE);
		ProductoException exception = new ProductoException(TESTING_MESSAGES, causa);

		assertEquals(TESTING_MESSAGES, exception.getMessage());
		assertEquals(causa, exception.getCause());
	}
}
