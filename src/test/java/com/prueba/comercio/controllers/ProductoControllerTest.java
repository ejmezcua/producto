package com.prueba.comercio.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.prueba.comercio.dto.ProductoDTO;
import com.prueba.comercio.service.ProductoService;

@ExtendWith(MockitoExtension.class)
class ProductoControllerTest {

    @Mock
    private ProductoService productoService;

    @InjectMocks
    private ProductoController productoController;

    
    @Test
    void testGetProductos() throws Exception {
        String fecha = "2020-06-14-12.00.00";
        String productoId = "35455";
        String cadenaId = "1";
        
        ProductoDTO producto = new ProductoDTO();
        producto.setPrice(33.33);
        when(productoService.findByFechaProductoIdCadenaId(fecha, productoId, cadenaId)).thenReturn(producto);

        ProductoDTO response = productoController.getProductos(fecha, productoId, cadenaId);
        assertEquals(33.33, response.getPrice());

        verify(productoService, times(1)).findByFechaProductoIdCadenaId(fecha, productoId, cadenaId);
    }
}
