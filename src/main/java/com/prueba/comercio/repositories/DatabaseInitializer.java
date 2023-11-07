package com.prueba.comercio.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import com.prueba.comercio.constants.AppConstants;

public class DatabaseInitializer {

	private static Connection conexion;

	DatabaseInitializer() {
		super();
	}

	public static void initDB() {
		if (conexion == null) {
			try {
				conexion = DriverManager.getConnection(AppConstants.JDBC_URL);

				ClassPathResource resource = new ClassPathResource(AppConstants.SCHEMA_SQL);
				ScriptUtils.executeSqlScript(conexion, resource);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static Connection getConnection() {
		if (conexion == null) {
			try {
				conexion = DriverManager.getConnection(AppConstants.JDBC_URL);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conexion;
	}

	public static void closeConnection() {
		if (conexion != null) {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
