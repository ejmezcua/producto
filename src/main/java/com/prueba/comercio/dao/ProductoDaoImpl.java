package com.prueba.comercio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.prueba.comercio.constants.AppConstants;
import com.prueba.comercio.exception.NoDataFoundException;
import com.prueba.comercio.exception.ProductoNotFoundException;
import com.prueba.comercio.model.Producto;
import com.prueba.comercio.repositories.DatabaseInitializer;
import com.prueba.comercio.utils.Properties;

@Repository
public class ProductoDaoImpl implements ProductoDao {

	private static final Logger LOG = LoggerFactory.getLogger(ProductoDaoImpl.class);

	private static final String SQL_QUERY = "SELECT pr1.* FROM prices AS pr1 " //
			+ " WHERE PARSEDATETIME(?, 'yyyy-MM-dd-HH.mm.ss') BETWEEN pr1.start_date AND pr1.end_date " //
			+ "  AND pr1.product_id = ? " //
			+ "  AND pr1.brand_id = ? " //
			+ "  AND pr1.priority = (SELECT MAX(pr2.priority) " //
			+ "                  FROM prices AS pr2" //
			+ "                  WHERE PARSEDATETIME(?, 'yyyy-MM-dd-HH.mm.ss') BETWEEN pr2.start_date AND pr2.end_date " //
			+ "                        AND pr2.product_id = ? " //
			+ "                        AND pr2.brand_id = ?)"; //

	@Override
	public List<Producto> findByFechaProductoIdCadenaId(String fechaAplicacion, String productoId, Long brandId) {
		Connection conexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Producto> resultados = new ArrayList<>();

		try {
			conexion = DatabaseInitializer.getConnection();
			preparedStatement = conexion.prepareStatement(SQL_QUERY);
			setParameters(preparedStatement, fechaAplicacion, productoId, brandId);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				resultados.add(getProducto(resultSet));
			}

			if (resultados.isEmpty()) {
				throw new ProductoNotFoundException(fechaAplicacion, productoId, brandId.toString());
			}

		} catch (SQLException e) {
			throw new NoDataFoundException();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				LOG.error(Properties.obtenerInstancia().getString(AppConstants.ERR_SQL));
				resultados = new ArrayList<>();
			}
		}

		return resultados;
	}

	private Producto getProducto(ResultSet resultSet) throws SQLException {
		Producto producto;
		producto = new Producto();
		producto.setProductId(resultSet.getString(AppConstants.FILE_PRODUCT_ID));
		producto.setBrandId(resultSet.getLong(AppConstants.FILE_BRAND_ID));
		producto.setPriceList(resultSet.getLong(AppConstants.FILE_PRICE_LIST));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(AppConstants.DATE_FORMAT);
		LocalDateTime localTime = resultSet.getTimestamp(AppConstants.FILE_START_DATE).toLocalDateTime();
		producto.setStartDate(localTime.format(formatter));
		localTime = resultSet.getTimestamp(AppConstants.FILE_END_DATE).toLocalDateTime();
		producto.setEndDate(localTime.format(formatter));
		producto.setPrice(resultSet.getDouble(AppConstants.FILE_PRICE));
		return producto;
	}

	private void setParameters(PreparedStatement preparedStatement, String fechaAplicacion, String productoId,
			Long brandId) throws SQLException {
		preparedStatement.setString(1, fechaAplicacion);
		preparedStatement.setString(2, productoId);
		preparedStatement.setLong(3, brandId);
		preparedStatement.setString(4, fechaAplicacion);
		preparedStatement.setString(5, productoId);
		preparedStatement.setLong(6, brandId);
	}
}
