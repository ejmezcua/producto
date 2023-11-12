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
	private NoFormatBrandException noFormatBrandException;
	private NoFormatDateException noFormatDateException;
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
		noFormatBrandException = new NoFormatBrandException(brandId);
		noFormatDateException = new NoFormatDateException(fechaAplicacion);
		
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
	void testHandleNoFormatBrandException() {
		ResponseEntity<Object> responseEntity = controllerAdvisor
				.handleNoFormatBrandException(noFormatBrandException, webRequest);

		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

		Map<String, Object> responseBody = (Map<String, Object>) responseEntity.getBody();
		assertEquals(MessageFormat.format(Properties.obtenerInstancia().getString(AppConstants.NO_FORMAT_BRAND), brandId), responseBody.get(MESSAGE));
	}
	
	@Test
	void testHandleNoFormatDateException() {
		ResponseEntity<Object> responseEntity = controllerAdvisor
				.handleNoFormatDateException(noFormatDateException, webRequest);
		
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
		
		Map<String, Object> responseBody = (Map<String, Object>) responseEntity.getBody();
		assertEquals(MessageFormat.format(Properties.obtenerInstancia().getString(AppConstants.NO_FORMAT_DATE), fechaAplicacion), responseBody.get(MESSAGE));
	}

	@Test
	void testHandleNoDataFoundException() {
		ResponseEntity<Object> responseEntity = controllerAdvisor.handleNoDataFoundException(new NoDataFoundException(),
				webRequest);

		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

		Map<String, Object> responseBody = (Map<String, Object>) responseEntity.getBody();
		assertEquals(Properties.obtenerInstancia().getString(AppConstants.NO_DATA), responseBody.get(MESSAGE));
	}

}
