package com.prueba.comercio.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class DatabaseInitializerTest {

	@Test
	void testGetConnection() throws SQLException {
		Connection connection = DatabaseInitializer.getConnection();
		assertNotEquals(null, connection);
	}

	@Test
	void testCloseConnection() throws SQLException {
		Connection connection = DatabaseInitializer.getConnection();
		DatabaseInitializer.closeConnection();
		assertEquals(true, connection.isClosed());
	}
}
