-- Tables
CREATE TABLE IF NOT EXISTS prices (
	brand_id INT,
	price_list INT,
	start_date TIMESTAMP,
	end_date TIMESTAMP,
	product_id VARCHAR(50),
	priority INT,
	price DOUBLE,
	curr VARCHAR(5),
	PRIMARY KEY (brand_id, price_list)
);

-- Inserts
INSERT INTO prices (brand_id, price_list, start_date, end_date, product_id, priority, price, curr)
 	SELECT 	1, 
			1, 
			PARSEDATETIME('2020-06-14-00.00.00', 'yyyy-MM-dd-HH.mm.ss'), 
			PARSEDATETIME('2020-12-31-23.59.59', 'yyyy-MM-dd-HH.mm.ss'), 
			35455, 
			0, 
			35.50, 
			'EUR'
	WHERE NOT EXISTS (
		SELECT 1
		FROM prices
		WHERE brand_id = 1 AND price_list = 1);			
			
			
INSERT INTO prices (brand_id, price_list, start_date, end_date, product_id, priority, price, curr)
	SELECT	1, 
			2, 
			PARSEDATETIME('2020-06-14-15.00.00', 'yyyy-MM-dd-HH.mm.ss'), 
			PARSEDATETIME('2020-06-14-18.30.00', 'yyyy-MM-dd-HH.mm.ss'), 
			35455, 
			1, 
			25.45, 
			'EUR'
	WHERE NOT EXISTS (
		SELECT 1
		FROM prices
		WHERE brand_id = 1 AND price_list = 2);
		

		
INSERT INTO prices (brand_id, price_list, start_date, end_date, product_id, priority, price, curr)
	SELECT 	1, 
			3, 
			PARSEDATETIME('2020-06-15-00.00.00', 'yyyy-MM-dd-HH.mm.ss'), 
			PARSEDATETIME('2020-06-15-11.00.00', 'yyyy-MM-dd-HH.mm.ss'), 
			35455, 
			1, 
			30.50, 
			'EUR'
	WHERE NOT EXISTS (
		SELECT 1
		FROM prices
		WHERE brand_id = 1 AND price_list = 3);


		
INSERT INTO prices (brand_id, price_list, start_date, end_date, product_id, priority, price, curr)
	SELECT	1, 
			4, 
			PARSEDATETIME('2020-06-15-16.00.00', 'yyyy-MM-dd-HH.mm.ss'), 
			PARSEDATETIME('2020-12-31-23.59.59', 'yyyy-MM-dd-HH.mm.ss'), 
			35455, 
			1, 
			38.95, 
			'EUR'	WHERE NOT EXISTS (
		SELECT 1
		FROM prices
		WHERE brand_id = 1 AND price_list = 4);