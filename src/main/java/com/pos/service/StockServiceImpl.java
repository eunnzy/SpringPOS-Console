package com.pos.service;

import java.io.*;
import java.util.*;
import com.pos.dto.Stock;

public class StockServiceImpl implements StockService {
	// -- 파일에 저장된 재고 정보들 (제품 코드, 제품 이름, 제품 현재 수량)을 각각의 ArrayList에 저장 --
	private ArrayList<String> stockIdList = new ArrayList<>();
	private ArrayList<String> stockNameList = new ArrayList<>();
	private ArrayList<Integer> stockCount = new ArrayList<>();
	private String [] str = null; // 파일을 읽은 문자열 저장.
	private String src = "E:\\SpringMidTerm\\stockInfo.txt"; // 파일 경로
	private File file = new File(src); 
	
	// stockInfo.txt 파일에서 제품 정보 읽어옴.
	@Override
	public void stockInfo() {
		try {
		 String line;
         BufferedReader br = new BufferedReader(new FileReader(file));
         
         while((line = br.readLine()) != null) {
         	str = line.split("\t");
         	stockIdList.add(str[0]);  
         	stockNameList.add(str[1]);
         	stockCount.add(Integer.parseInt(str[3]));
         }
       
         br.close();
		}catch(IOException e) {
			e.getStackTrace();
		}
	}	
	// stockInfo.txt 파일에 새로운 제품 추가.
	@Override
	public int addNewStock(Stock stock) { // 새로운 재고 추가
		 if(isNotSuccess(stock.getStockId())) {
			 System.out.println("동일한 제품코드가 이미 존재합니다.");
			 return -1;
		 }
		try {
			 //새로운 재고 --> 각각의 ArrayList에도 삽입.
			 stockIdList.add(stock.getStockId());			
			 stockNameList.add(stock.getStockName());
			 stockCount.add(stock.getStockCount());
	         
			 BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));
		     bw.write(stock.getStockId() +  "\t" +stock.getStockName() + "\t" + stock.getPrice() + "\t" + stock.getStockCount() +"\n");
		     bw.flush();
		     bw.close();
		     System.out.println("제품이 추가 되었습니다.\n");
		     return 0;
			
		}catch(IOException e) {
			e.getStackTrace();	
		}
		return -1;
	}

	// stockInfo.txt 파일에 입고된 제품 수량 업데이트.
	@Override
	public void updateStock(String stockId, int addCount) {
		try { // userInfo.txt 파일 읽기
            String line="";
            String changeStr="";  // 변경될 문자열 저장.
            int total = 0;
            BufferedReader br = new BufferedReader(new FileReader(file)); 
            
            while((line = br.readLine()) != null) {
            	str = line.split("\t");
	            if(str[0].equals(stockId)) {
	            	total = Integer.parseInt(str[3]) + addCount;
	            	str[3] = Integer.toString(total);
	            }
	           changeStr += str[0]+"\t"+str[1]+"\t"+str[2]+"\t"+str[3]+"\n";
            }
            
            for(int i=0; i<stockIdList.size(); i++) {
            	if(stockIdList.get(i).equals(stockId)) {
            		stockCount.set(i, total);
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

	// 제품 추가할 때 중복된 제품코드 있는지 확인. 중복 x -> true 반환, 중복
	@Override 
	public boolean isNotSuccess(String stockId) {
		if(stockIdList.isEmpty()) {
			 stockInfo();
		 }
		for(int i=0; i<stockIdList.size(); i++) {
			if(stockIdList.get(i).equals(stockId)) 
				return true;
		}
		return false;
	}
	
	// 재고현황을 화면에 출력.
	@Override
	public void printStock() {
		System.out.println("================== 재고 현황 ==================");
	    System.out.println("제품코드\t\t제품이름\t\t현재재고수량");
		if(stockIdList.isEmpty()) {
			stockInfo();
			for(int i=0; i<stockIdList.size(); i++)
				System.out.println(stockIdList.get(i) + "\t\t" + stockNameList.get(i) + "\t\t" + stockCount.get(i));
		}else {
			for(int i=0; i<stockIdList.size(); i++)
				System.out.println(stockIdList.get(i) + "\t\t" + stockNameList.get(i) + "\t\t" + stockCount.get(i));
		}
		System.out.println("=============================================\n");
	}
	
}
