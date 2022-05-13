package com.pos.service;

import java.io.*;
import java.util.*;
import com.pos.dto.User;

// User file 읽기 service
public class UserServiceImpl implements UserService{
	ArrayList<String> saveId = new ArrayList<>();	// 저장된 ID
	ArrayList<String> savePw = new ArrayList<>();	// 저장된 PW
	ArrayList<String> saveName = new ArrayList<>(); // 저장된 회원 이름.
	ArrayList<String> saveType = new ArrayList<>(); // 저장된 회원 유형
	
	private String [] str = null;
	private String src = "E:\\SpringMidTerm\\userInfo.txt"; // 파일 기본경로
	private File file = new File(src);
	
	// 로그인 유효성 검사
	public boolean login(String userId, String userPw) {	//로그인
        String line;
        for(int i=0; i<saveId.size(); i++ ) {
        	if(saveId.get(i).equals(userId) && savePw.get(i).equals(userPw)) {
	    		return true;
	    	}
         }
        return false;
    }	
	
	// 관리자 로그인 (재고 관리 화면 접근 하기 위한)
	@Override
	public boolean isManager(String userId, String userPw) {
		 String line;
	        for(int i=0; i<saveId.size(); i++ ) {
	        	if(saveId.get(i).equals(userId) && savePw.get(i).equals(userPw) && saveType.get(i).equals("관리자")) {
		    		return true;
		    	}
	         }
	        return false;
	}

	
	@Override //회원가입
	public int register(User user) {
		// 아이디 중복 확인
        for(int i=0; i<saveId.size(); i++) {
        	if(saveId.get(i).equals(user.getUserId()))
        		return -1;  // -1 반환되면 중복 아아디
        }
		try {
			 saveId.add(user.getUserId());
			 savePw.add(user.getUserPw());
			 saveName.add(user.getUserName());
			 saveType.add(user.getUserType());
			 // 회원가입할 정보들을 arraylist에 저장
			 
	         BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));
	         bw.write(user.getUserId() + "\t" + user.getUserPw()+ "\t"+ user.getUserName()+"\t" +user.getUserType()+"\n");
	         bw.flush();
	         bw.close();
	         
	         return 0;
		}catch(IOException e) {
			e.getStackTrace();	
		}
		
		return -1;
	}

	// 비밀번호 변경
	@Override
	public void chanagePw(String userId, String userPw, String changePw) {
		try { // userInfo.txt 파일 읽기
            String line="";
            String changeStr="";
            BufferedReader br = new BufferedReader(new FileReader(file));
            
            while((line = br.readLine()) != null) {
            	str = line.split("\t");
	            if(str[0].equals(userId) && str[1].equals(userPw)) {   // 변경될 아이디와 비밀번호의 행을 찾아서
	            	str[1] = changePw;		// 변겨될 비밀번호로 덮어씌움.
	            }
	           changeStr += str[0]+"\t"+str[1]+"\t"+str[2]+"\t"+str[3]+"\n";  // 변경될 문자열을 포함해서 한 행의 문자열 이어 붙임.
            }
            
            for(int i=0; i<saveId.size(); i++) {
            	if(saveId.get(i).equals(userId)) 
            		savePw.set(i, changePw);
            }
            
            FileWriter fw = new FileWriter(src);
           	fw.write(changeStr);   // 변경된 문자열 쓰기.
          	fw.flush();
          	fw.close();
            br.close();
		}catch(IOException e) {
			e.getStackTrace();
		}
	}

	@Override
	public void userInfo() {    // user 정보 읽기.
		try { // userInfo.txt 파일 읽기
			 String line;
	         BufferedReader br = new BufferedReader(new FileReader(file));
            
	         while((line = br.readLine()) != null) {
            	str = line.split("\t");
            	saveId.add(str[0]);
            	savePw.add(str[1]);
            	saveName.add(str[2]);
            	saveType.add(str[3]);
            }
            br.close();
		}catch(IOException e) {
			e.getStackTrace();
		}
	}
 



}

