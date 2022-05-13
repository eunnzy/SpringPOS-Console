package com.pos.dto;

public class Sales {
	private String stockId; // 제품 코드
	private String stockName; // 제품 이름
	private int price; // 제품 가격
	private int count;	 // 제품 개수
	private int totalPrice;	// 총 금액
	private String date;  // 판매 날짜
	
	public Sales() {
		
	}
	
	public Sales(String stockName, int count, int totalPrice, String date) {
		this.stockName = stockName;
		this.count = count;
		this.totalPrice = totalPrice;
		this.date = date;
	}

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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Sales [stockId=" + stockId + ", stockName=" + stockName + ", price=" + price + ", count=" + count
				+ ", totalPrice=" + totalPrice + ", date=" + date + "]";
	}
	

	
}
