package com.pos.console;

import java.util.Scanner;
import org.springframework.beans.factory.annotation.*;
import com.pos.dto.Stock;
import com.pos.service.StockServiceImpl;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;

public class StockConsole {
	Scanner sc = new Scanner(System.in);
	private String stockId;  // 제품코드
	private String stockName;  // 제품이름
	private int price;	// 제품 가격
	private int stockCount; 	// 현재 제품수량
	private Stock stock;
	private StockServiceImpl stockService;
	
	@Autowired
	public StockConsole(Stock stock, StockServiceImpl stockService) {
		this.stock = stock;
		this.stockService = stockService;
	}
	
	@Autowired
	private UserConsole userConsole;
	
	public void stocks() {   // 재고관리 화면
		while(true) {
			System.out.println("\n\n================ 재고 관리 화면 ================");
			System.out.println("1. 제품 입고");
			System.out.println("2. 새로운 제품 추가");
			System.out.println("3. 뒤로 가기");
			System.out.println("4. 프로그램 종료");
			System.out.println("============================================");
			System.out.print("원하시는 메뉴 선택해주세요: ");
			int menu = sc.nextInt(); 
			
			switch(menu) {
				case 1:   // 제품 입고
					stockService.printStock();
					System.out.print("입고할 제품 코드: ");
					stockId= sc.next();
					System.out.print("입고할 수량: ");
					int addCount = sc.nextInt();
					stockService.updateStock(stockId, addCount);  // 제품 입고 후 파일 업데이트.
					stockService.printStock();
					break;
				case 2:   // 새로운 제품 추가
					System.out.print("제품 코드: ");
					stockId = sc.next();
					System.out.print("제품 이름: ");
					stockName = sc.next();
					System.out.print("제품 가격: ");
					price = sc.nextInt();
					System.out.print("제품 수량: ");
					stockCount = sc.nextInt();
					
					stock.setStockId(stockId);  
					stock.setPrice(price);
					stock.setStockName(stockName);
					stock.setStockCount(stockCount);
					stockService.addNewStock(stock); // 재고 추가
					stockService.printStock();
					break;
				case 3:
					userConsole.start();  // 뒤로가기
					break;
				case 4:
					System.exit(0);
				default:
					break;
			}
		}
	}
}
