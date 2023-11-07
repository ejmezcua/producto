package com.prueba.comercio.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.text.MessageFormat;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import com.prueba.comercio.constants.AppConstants;
import com.prueba.comercio.utils.Properties;

@SuppressWarnings("unchecked")
class ControllerAdvisorTest {

	private static final String MESSAGE = "message";

	private ControllerAdvisor controllerAdvisor;
	private ProductoNotFoundException productoNotFoundException;
	private String fechaAplicacion;
	private String productoId;
	private String brandId;
	private WebRequest webRequest;

	@BeforeEach
	public void setUp() {
		fechaAplicacion = "2020-06-14-00.00.00";
		productoId = "35455";
		brandId = "1";

		controllerAdvisor = new ControllerAdvisor();
		productoNotFoundException = new ProductoNotFoundException(fechaAplicacion, productoId, brandId);

		webRequest = mock(WebRequest.class);
	}

	@Test
	void testHandleProductoNotFoundException() {
		ResponseEntity<Object> responseEntity = controllerAdvisor
				.handleProductoNotFoundException(productoNotFoundException, webRequest);

		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

		Map<String, Object> responseBody = (Map<String, Object>) responseEntity.getBody();
		assertEquals(MessageFormat.format(Properties.obtenerInstancia().getString(AppConstants.NOT_FOUND), productoId,
				fechaAplicacion, brandId), responseBody.get(MESSAGE));
	}

	@Test
	void testHandleNoFormatDataException() {
		ResponseEntity<Object> responseEntity = controllerAdvisor
				.handleNoFormatDataException(new NoFormatDataException(), webRequest);

		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

		Map<String, Object> responseBody = (Map<String, Object>) responseEntity.getBody();
		assertEquals(Properties.obtenerInstancia().getString(AppConstants.NO_FORMAT), responseBody.get(MESSAGE));
	}

	@Test
	void testHandleNodataFoundException() {
		ResponseEntity<Object> responseEntity = controllerAdvisor.handleNodataFoundException(new NoDataFoundException(),
				webRequest);

		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

		Map<String, Object> responseBody = (Map<String, Object>) responseEntity.getBody();
		assertEquals(Properties.obtenerInstancia().getString(AppConstants.NO_DATA), responseBody.get(MESSAGE));
	}

}
