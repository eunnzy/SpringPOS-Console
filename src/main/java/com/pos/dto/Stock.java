package com.pos.dto;

public class Stock {
	private String stockId;   // 제품 코드
	private String stockName;	// 제품 이름
	private int stockCount;	 // 제품 개수
	private int price;	 // 제품 가격
	
	public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public int getStockCount() {
		return stockCount;
	}
	public void setStockCount(int stockCount) {
		this.stockCount = stockCount;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Stock [stockId=" + stockId + ", stockName=" + stockName + ", stockCount=" + stockCount + ", price="
				+ price + "]";
	}
	

}
