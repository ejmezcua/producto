package com.prueba.comercio.controllers;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.prueba.comercio.model.Producto;
import com.prueba.comercio.repositories.DatabaseInitializer;
import com.prueba.comercio.service.ProductoService;

@WebMvcTest(ProductoController.class)
@AutoConfigureMockMvc
class ProductoControllerTest {
	
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ProductoService productoService;

    @BeforeEach
    void setUp() {
    	DatabaseInitializer.initDB();
    }
    
	@Test
    void testGetProductos() throws Exception {
        String fecha = "2020-06-14-12.00.00";
        String productoId = "35455";
        String cadenaId = "1";

        Producto producto = new Producto();
        producto.setBrandId(1L);
        producto.setPriceList(1L);
        producto.setStartDate("2020-06-14-00.00.00");
        producto.setEndDate("2020-12-31-23.59.59");
        producto.setProductId("35455");
        producto.setPrice(35.5);
        
        List<Producto> productos = Arrays.asList(new Producto());
        when(productoService.findByFechaProductoIdCadenaId(fecha, productoId, cadenaId))
            .thenReturn(productos);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/producto/{fecha}/{productoId}/{cadenaId}", fecha, productoId, cadenaId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].brandId").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").isNumber());
    }
}
