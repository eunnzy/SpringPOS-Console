package com.pos.service;

public interface SalesService {
	void stockInfo(); // 제품 정보 읽기
	void updateStock(String stockName, int stockCount);		// 판매 수량 업데이트.
	int salesTotal(String stockName, int count);	// 상품 총 금액
	void salesWrite(String stockName, int count, int total);	// 판매한 재퓸 쓰기
	void printStock();	// 화면에 재고 출력	
	boolean isSales(String stockName, int count);	// 판매 가능 한지 확인 (제품수량)
	void salesPrint();	    // 판매내역 출력
	void currentTotal();	// 현재 총 매출액
	void salesRead();	// 판매내역 파일 읽기.
}
