package com.prueba.comercio.constants;

public final class AppConstants {
	
	public static final String JDBC_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
	public static final String SCHEMA_SQL = "sql/schema.sql";

	public static final String NOT_FOUND = "error.request.notfound";
	public static final String NO_FORMAT = "error.request.noformat";
	public static final String NO_DATA = "error.request.nodata";
	public static final String ERR_SQL = "error.sql";

	public static final String PRODUCTO_EXCEPTION = "producto.exception";

	public static final String DATE_FORMAT = "yyyy-MM-dd-HH.mm.ss";
	
	public static final String FILE_ID = "id";
	public static final String FILE_PRICE = "price";
	public static final String FILE_END_DATE = "end_date";
	public static final String FILE_START_DATE = "start_date";
	public static final String FILE_PRICE_LIST = "price_list";
	public static final String FILE_BRAND_ID = "brand_id";
	public static final String FILE_PRODUCT_ID = "product_id";
	
	private AppConstants() { }	
}
