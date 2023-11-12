package com.prueba.comercio.dto;

import java.time.LocalDateTime;

import com.prueba.comercio.model.Producto;

public class ProductoDTO {
	private Long brandId;
	
	private Long priceList;
	
	private LocalDateTime startDate;
	
	private LocalDateTime endDate;
	
	private String productId;
	
	private Double price;

	
	public ProductoDTO() {
		super();
	}

	public ProductoDTO(Producto producto) {
		super();
		this.brandId = producto.getBrandId();
		this.priceList = producto.getPriceList();
		this.startDate = producto.getStartDate();
		this.endDate = producto.getEndDate();
		this.productId = producto.getProductId();
		this.price = producto.getPrice();
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public Long getPriceList() {
		return priceList;
	}

	public void setPriceList(Long priceList) {
		this.priceList = priceList;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
