package com.pos.console;

import java.util.*;
import org.springframework.beans.factory.annotation.*;
import com.pos.service.SalesServiceImpl;

public class SalesConsole {
	private int total = 0;	// 제품 총 금액
	private int menu; // 메뉴 선택
	Scanner sc = new Scanner(System.in);
	@Autowired
	private UserConsole userConsole;
	@Autowired
	private SalesServiceImpl salesService;
	
	public void selling() {  // 제품 판매 화면
		while(true) {
			System.out.println("\n============== 판매 화면 ===============");
			System.out.println("1. 제품판매");
			System.out.println("2. 현재 총 매출");
			System.out.println("3. 판매내역");
			System.out.println("4. 뒤로가기");
			System.out.println("5. 프로그램 종료");
			System.out.println("=======================================");
			System.out.print("원하시는 메뉴를 선택해 주세요.");
			menu = sc.nextInt();

			switch(menu) {
				case 1:
					print(); // 판매 화면.
					break;
				case 2:
					salesService.currentTotal(); // 현재 총 매출액
					break;
				case 3:
					salesService.salesPrint();	// 판매 내역
				case 4:
					userConsole.start();  // 뒤로 가기
				case 5:
					System.out.println("프로그램을 종료합니다.");
					System.exit(0);
					break;
				default: 
					System.out.println("메뉴를 잘못입력하셨습니다.");
					break;
			}
		}
	}
	
	public void print() {			// 판매
			while(true) {
				salesService.printStock();   // 제품 리스트 출력
				System.out.println("판매할 제품을 입력해주세요.");
				System.out.print("제품 이름: ");
				String stockName = sc.next();
				System.out.print("제품 수량: ");
				int count = sc.nextInt();
				if(salesService.isSales(stockName, count)) {
					total += salesService.salesTotal(stockName, count);
					System.out.println("총 " + total + "원 입니다.");
					System.out.print("내실 금액: ");
					int pay = sc.nextInt();
					int exchange = pay - total;
					System.out.println("거스름 돈: " + exchange +"원");
					salesService.updateStock(stockName, count);
					salesService.salesWrite(stockName, count, total);
					break;
				}else {
					System.out.println("현재 제품 수량이 구매하려는 수량보다 적어 구매하실 수 없습니다.");
				}
			}
	}
	
}