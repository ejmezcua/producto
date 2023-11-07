# Proyecto
producto 

# Descripción
	Proyecto que almacena, en una base de datos en memoria del tipo H2, datos de tarifas y precios de un artículos de una cadena, junto con el período de validez de estas tarifas, con prioridad de aplicación si hay solapamientos de intervalos de tiempo de validez.
	Con estos datos se habilita un endpoint para recibir consultas de estos datos con el formato:

	api/producto/{tiempo}/{identificador_del_producto}/{identificador_de_cadena}
	donde:	{tiempo} tiene el formato	yyyy-MM-dd-HH.mm.ss	(Ejemplo: 2020-06-14-10.00.00)
			{identificador_del_producto} es el identificador código de producto (Ejemplo: 35455)
			{identificador_de_cadena} es el identificador de la cadena del grupo (Ejemplo: 1)

	y envía como respuesta una cadena en formato json con un conjunto de elementos conteniendo:
		identificador de producto, identificador de cadena, tarifa a aplicar, fechas de aplicación y precio final.

 # Especificaciones técnicas y dependencias
	Java 11.
	Maven 3.8.7.
	Spring Boot 3.1.5.
	Base de datos H2.
	Test de integración JUnit 5 con Mockito.
	Monitorización de cobertura de código de test con Jacoco.
	(Para más info ver fichero pom.xml)

  # Instrucciones para instalación y configuración.
	El código está publicado en la URL: 
	Al ejecutar mvn package, se obtendrá el fichero:
		producto-1.0.0.jar
	Para ejecutar el fichero jar: 
		java -jar producto-1.0.0.jar

  # Ejemplos de uso:
  	## Casos de prueba desde el navegador o Postman:
     
  	*TEST 1: Petición a las 10:00 del día 14 del producto 35455, para la brand 1 (ZARA)
  		Petición: 
  			http://localhost:8080/api/producto/2020-06-14-10.00.00/35455/1
  		Respuesta:	(Status: 200 OK)
  		```java
  			[
  				{
  					"brandId": 1,
  					"priceList": 1,
  					"startDate": "2020-06-14-00.00.00",
  					"endDate": "2020-12-31-23.59.59",
  					"productId": "35455",
  					"price": 35.5
  				}
  			]
  		```
  	
  	*TEST 2: Petición a las 16:00 del día 14 del producto 35455, para la brand 1 (ZARA)
  		Petición: 
  			http://localhost:8080/api/producto/2020-06-14-16.00.00/35455/1
  		Respuesta:	(Status: 200 OK)
  		```java
  			[
  				{
  					"brandId": 1,
  					"priceList": 2,
  					"startDate": "2020-06-14-15.00.00",
  					"endDate": "2020-06-14-18.30.00",
  					"productId": "35455",
  					"price": 25.45
  				}
  			]
  		```
  
  	*TEST 3: Petición a las 21:00 del día 14 del producto 35455, para la brand 1 (ZARA)
  		Petición: 
  			http://localhost:8080/api/producto/2020-06-14-21.00.00/35455/1
  		Respuesta:	(Status: 200 OK)
  		```java
  			[
  				{
  					"brandId": 1,
  					"priceList": 1,
  					"startDate": "2020-06-14-00.00.00",
  					"endDate": "2020-12-31-23.59.59",
  					"productId": "35455",
  					"price": 35.5
  				}
  			]
  		```
  
  	*TEST 4: Petición a las 10:00 del día 15 del producto 35455, para la brand 1 (ZARA)
  		Petición: 
  			http://localhost:8080/api/producto/2020-06-15-10.00.00/35455/1
  		Respuesta:	(Status: 200 OK)
  		```java
  			[
  				{
  					"brandId": 1,
  					"priceList": 3,
  					"startDate": "2020-06-15-00.00.00",
  					"endDate": "2020-06-15-11.00.00",
  					"productId": "35455",
  					"price": 30.5
  				}
  			]
  		```
  
  	*TEST 5: Petición a las 21:00 del día 16 del producto 35455, para la brand 1 (ZARA)
  		Petición: 
  			http://localhost:8080/api/producto/2020-06-16-21.00.00/35455/1
  		Respuesta:	(Status: 200 OK)
  		```java
  			[
  				{
  					"brandId": 1,
  					"priceList": 4,
  					"startDate": "2020-06-15-16.00.00",
  					"endDate": "2020-12-31-23.59.59",
  					"productId": "35455",
  					"price": 38.95
  				}
  			]
  		```
  
  
  	*TEST 6: Fallo de petición a una fecha incorrecta del producto 35455, para la brand 1 (ZARA)
  		Petición: 
  			http://localhost:8080/api/producto/XYZ2020-06-16-21.00.00/35455/1
  		Respuesta:	(Status: 404-Not Found)
  		```java
  			{
  				"timestamp": "2023-11-06T18:11:12.3252427",
  				"message": "No se encontró el producto 35455 para la fecha XYZ2020-06-16-21.00.00 y brand 1."
  			}
  		```
  
  
  	*TEST 7: Fallo de petición a las 21:00 del día 16 del producto 3545544, para la brand 1 (ZARA)
  		Petición: 
  			http://localhost:8080/api/producto/2020-06-16-21.00.00/3545544/1
  		Respuesta:	(Status: 404-Not Found)
  		```java
  			{
  				"timestamp": "2023-11-06T18:14:24.282736",
  				"message": "No se encontró el producto 3545544 para la fecha 2020-06-16-21.00.00 y brand 1."
  			}
  		```
  			
  			
  	*TEST 8: Fallo de petición a las 21:00 del día 16 del producto 35455, para la brand 133 (????)
  		Petición: 
  			http://localhost:8080/api/producto/2020-06-16-21.00.00/35455/133
  		Respuesta:	(Status: 404-Not Found)
  		```java
  			{
  				"timestamp": "2023-11-06T18:13:03.4715305",
  				"message": "No se encontró el producto 35455 para la fecha 2020-06-16-21.00.00 y brand 133."
  			}
  		```
  
  
  	*TEST 9: Fallo de petición a las 21:00 del día 16 del producto 3545544, para la brand 1XYZ (----)
  		Petición: 
  			http://localhost:8080/api/producto/2020-06-16-21.00.00/3545544/1XYZ
  		Respuesta:	(Status: 400-Bad Request)
  		```java
  			{
  				"timestamp": "2023-11-06T18:16:28.9606437",
  				"message": "Formato inválido de la cadena: 'brand_id'"
  			}
  		```
  

# Datos de la base de datos en memoria:
<code>brand_id	price_list	start_date		end_date		product_id	priority	price	curr
1		  1		2020-06-14-00.00.00	2020-12-31-23.59.59	35455		0		35.50	EUR 
1		  2		2020-06-14-15.00.00	2020-06-14-18.30.00	35455		1		25.45	EUR 
1		  3		2020-06-15-00.00.00	2020-06-15-11.00.00	35455		1		30.50	EUR 
1		  4		2020-06-15-16.00.00	2020-12-31-23.59.59	35455		1		38.95	EUR 
</code>

