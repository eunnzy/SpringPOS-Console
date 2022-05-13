package com.pos.service;

import com.pos.dto.Stock;

public interface StockService {
	void stockInfo(); // 제품 정보 파일에서 읽기
	int addNewStock(Stock stock); // 제품 추가
	void updateStock(String stockId, int addCount);	// 제품 업데이트 (입고)
	boolean isNotSuccess(String stockId);  // 제품코드가 동일한지 검사
	void printStock(); // 화면에 재고 출력
}
