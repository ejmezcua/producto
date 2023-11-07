package com.prueba.comercio;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductoApplicationTests {

	@Test
	void contextLoads() {
		ProductoApplication productoApplication = new ProductoApplication();
		assertNotEquals(null, productoApplication);
	}
}
