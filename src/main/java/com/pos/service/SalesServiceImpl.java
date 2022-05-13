package com.pos.service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.pos.dto.Sales;

public class SalesServiceImpl implements SalesService {
	// ----------- stock 파일 읽어들일 때, 각각의 재고 정보를 저장할 ArrayList ----------
	ArrayList<String> stockIdList = new ArrayList<>();	
	ArrayList<String> stockNameList = new ArrayList<>(); // 제품 이름
	ArrayList<Integer> priceList = new ArrayList<>(); 
	ArrayList<Integer> stockCountList = new ArrayList<>();
	//------------------------------------------------------------
	ArrayList<Sales> salesList = new ArrayList<>();   // 판매 정보 저장을 위한.
	private String [] str = null;
	private String src = "E:\\SpringMidTerm\\stockInfo.txt"; // 파일 기본경로
	private File file = new File(src);
	private Sales sales;
	private StockServiceImpl stockService;
	
	@Autowired 
	public SalesServiceImpl(Sales sales, StockServiceImpl stockService) {
		this.sales = sales;
		this.stockService = stockService;
	}
	
	@Override
	public void stockInfo() {  //제품 정보 읽기 (제품 코드, 제품명, 가격만 읽기) 
		try { //salesInfo.txt 파일 읽기
			
            String line;
            BufferedReader br = new BufferedReader(new FileReader(file));
            
            while((line = br.readLine()) != null) {
            	str = line.split("\t");
            	stockIdList.add(str[0]);  
            	stockNameList.add(str[1]);
            	priceList.add(Integer.parseInt(str[2]));
            	stockCountList.add(Integer.parseInt(str[3]));
            }
            
            br.close();
		}catch(IOException e) {
			e.getStackTrace();
		}
	}
	
	//판매한 제품 기록,
	public void salesWrite(String stockName, int count, int total) {
		try {
			String salesSrc = "E:\\SpringMidTerm\\salesInfo.txt";
			File salesFile = new File(salesSrc);
	        BufferedWriter bw = new BufferedWriter(new FileWriter(salesFile,true));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
			Date now = new Date();
			String nowTime = sdf.format(now);
			
			bw.write(stockName+"\t"+count+"\t"+total+"\t"+nowTime+"\n");
	        bw.flush();
	        bw.close();
	        
		}catch(IOException e) {
			e.getStackTrace();	
		}
	}
	
	// 판매내역 정보 읽어오기.
	public void salesRead() {
		try { 
			String line;
            BufferedReader br = new BufferedReader(new FileReader("E:\\SpringMidTerm\\salesInfo.txt"));
            
            while((line = br.readLine()) != null) {
            	str = line.split("\t");
            	sales = new Sales();
            	sales.setStockName(str[0]);
            	sales.setCount(Integer.parseInt(str[1]));
            	sales.setTotalPrice(Integer.parseInt(str[2]));
            	sales.setDate(str[3]);
            	salesList.add(sales);
            }
            br.close();
		}catch(IOException e) {
			e.getStackTrace();
		}
	}
	
	// 현재 총 매출액.
	public void currentTotal() {
		int sum=0;
		if(salesList.isEmpty()) {
			salesRead();
			for(int i=0; i<salesList.size(); i++) {
				sum += salesList.get(i).getTotalPrice();
			}
			System.out.println("현재 총 매출액: " + sum);
		}else {
			for(int i=0; i<salesList.size(); i++) {
				sum += salesList.get(i).getTotalPrice();
			}
			System.out.println("현재 총 매출액: " + sum);
		}
	}
	
	// 사용자가 내야할 금액 계산.
	public int salesTotal(String stockName, int count) {
		int total = 0;
		
		for(int i=0; i<stockNameList.size(); i++) {
			if(stockName.equals(stockNameList.get(i))) {
				total += count * priceList.get(i);		
			}
		}
		return total;
	}
	
	// 제품이 팔리고 나면 제품의 수량이 줄어들기 때문에 업데이트.
	public void updateStock(String stockName, int stockCount)  {
		try {
			 String line="";
			 String changeStr ="";
			 int total = 0;
			 BufferedReader br = new BufferedReader(new FileReader(file));
			 
			 while((line = br.readLine()) != null) {
	            	str = line.split("\t");
		            if(str[1].equals(stockName)) {
		            	total = Integer.parseInt(str[3]) - stockCount;
		            	str[3] = Integer.toString(total);
		            }
		           changeStr += str[0]+"\t"+str[1]+"\t"+str[2]+"\t"+str[3]+"\n";
	         }
			 
			 for(int i=0; i<stockNameList.size(); i++) {
				 if(stockNameList.get(i).equals(stockName)) {
					 stockCountList.set(i, total);    // 저장된 arrayLsit에도 업데이트.
				 }
			 }
	            
			 FileWriter fw = new FileWriter(src);
	         fw.write(changeStr);
	         fw.flush();
	         fw.close();
	         br.close();
		}catch(IOException e) {
			e.getStackTrace();
		}
	}
	
	 // 판매된 제품 화면에 출력
		public void salesPrint() {
			System.out.println("\n=======================판매 내역=========================");
			System.out.println("제품이름\t\t판매수량\t금액\t판매시간");
			for(int i=0; i<salesList.size(); i++) {
					System.out.println(salesList.get(i).getStockName() + "\t\t" +salesList.get(i).getCount() + "\t" + salesList.get(i).getTotalPrice() + "\t" + salesList.get(i).getDate());
			}
			System.out.println("=========================================================");
			
		}
	// 판매하고 있는 제품 목록 출력
	public void printStock() {
		System.out.println("==================판매====================");
		System.out.println("제품번호\t제품이름\t\t제품가격\t현재제품수량" );
		if(stockIdList.isEmpty()) {
			stockInfo();
			for(int i=0; i<stockIdList.size(); i++) {
				System.out.println(stockIdList.get(i) + "\t" + stockNameList.get(i) + "\t\t" + priceList.get(i) + "\t" + stockCountList.get(i));
			}
		}else {
			for(int i=0; i<stockIdList.size(); i++) {
				System.out.println(stockIdList.get(i) + "\t" + stockNameList.get(i) + "\t\t" + priceList.get(i) + "\t" + stockCountList.get(i));
			}
		}
		System.out.println("=========================================\n");
	}
	
	// 판매 가능 여부.
	public boolean isSales(String stockName, int count) {
		for(int i=0; i<stockNameList.size(); i++) {
			if(stockNameList.get(i).equals(stockName)) 
				if(stockCountList.get(i) >= count)
					return true;
		}
		return false;
	}
}
