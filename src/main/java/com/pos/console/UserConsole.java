package com.pos.console;

import java.util.*;
import org.springframework.beans.factory.annotation.*;

import com.pos.service.StockServiceImpl;
import com.pos.service.UserServiceImpl;
import com.pos.dto.User;

public class UserConsole {
	private String userId, userPw, userName, userType;
	private int menu;  // 메뉴 입력 받기 위한 
	private boolean state=false; // 로그인을 위한
	private User user;
	private UserServiceImpl userService;  
	
	// 회원가입과 로그인 기능을 위한.
	@Autowired
	public UserConsole(User user, UserServiceImpl userService) {
		this.user = user;
		this.userService = userService;
	}
	@Autowired
	private SalesConsole salesConsole;
	@Autowired
	private StockConsole stockConsole;
	
	public void start() {  // 사용자의 첫 화면(메뉴) 호출
		while(true) {
			System.out.println("================== Menu ==================");
			System.out.println("1. 로그인");
			System.out.println("2. 회원가입");
			System.out.println("3. 비밀번호 변경");
			System.out.println("4. 재고관리");
			System.out.println("5. 프로그램 종료");
			System.out.println("==========================================");
			System.out.print("원하시는 메뉴를 선택해주세요: ");
			Scanner sc = new Scanner(System.in);
			menu = sc.nextInt();
			userService.userInfo();  // userInfo(회원정보)에 저장된 파일 읽기.
			
			switch(menu) {
				case 1: // 로그인
					while(true) {
						System.out.println("\n===============로그인================");
						System.out.print("아이디를 입력하세요: ");
						userId = sc.next();
						System.out.print("비밀번호를 입력하세요: ");
						userPw = sc.next();
						state = userService.login(userId, userPw);  
							
						if(state == true) {   // 로그인 성공
							System.out.println(userId +"님 환영합니다.\n");
							System.out.println("====================================");
							salesConsole.selling();  // 판매 화면으로 넘어감
							break;
						}else {
							System.out.println("아이디와 비밀번호가 일치하지 않습니다.");
							System.out.println("======================================\n");
							continue;
						}
					}	
					break;
				case 2:  // 회원가입 
					while(true) {
						System.out.println("\n===============회원등록================");
						System.out.print("이름: ");
						userName =sc.next();
						System.out.print("아이디: ");
						userId = sc.next();
						System.out.print("비밀번호: ");
						userPw = sc.next();
						System.out.print("회원 유형(사용자 or 관리자): ");
						userType = sc.next();
						
						user = new User();   // user 객체 생성
						user.setUserId(userId);
						user.setUserName(userName);
						user.setUserPw(userPw);
						user.setUserType(userType);
						
						// 동일한 아이디 검사하여 회원가입 진행 
						if(userService.register(user) == 0) {  // 동일한 ID 없음
							System.out.println("회원가입 축하드립니다!");
							System.out.println("======================================\n");
							break;
						}
						else {  // 동일한 ID 있으면 회원가입 불가
							System.out.println("아이디가 이미 존재합니다. 아이디 비밀번호를 다시 입력해주세요.");
							System.out.println("======================================\n");
							continue;
						}
					}
					break;
				case 3: // 비밀번호 변경
					while(true) {
						System.out.println("\n=============비밀번호 변경===============");
						System.out.print("아이디를 입력하세요: ");
						userId = sc.next();
						System.out.print("비밀번호를 입력하세요: ");
						userPw = sc.next();
						state = userService.login(userId, userPw);    // 아이디와 비밀번호가 유효한지 확인.
						
						if(state == true) {
							System.out.print("변경할 비밀번호를 입력해주세요: ");
							String changePw = sc.next();
							userService.chanagePw(userId, userPw, changePw);
							System.out.println("비밀번호 변경이 완료됐습니다!");
							System.out.println("=======================================\n");
							break;
						}else {
							System.out.println("아이디와 비밀번호가 일치하지 않습니다.");
							System.out.println("=======================================\n");
							continue;
						}
					}
					break;
				case 4:  // 재고 관리
					while(true) {
						System.out.println("\n=============관리자 로그인===============");
						System.out.print("아이디를 입력하세요: ");
						userId = sc.next();
						System.out.print("비밀번호를 입력하세요: ");
						userPw = sc.next();
						state = userService.isManager(userId, userPw);    // 관리자가 맞는지 확인
						if(state == true) { // 관리자 맞으면 
							System.out.println(userId +"님 환영합니다!");
							System.out.println("=======================================\n");
							stockConsole.stocks();  // 재고관리 화면 이동
							break;
						}else {  // 관리자 아니면 재고관리 화면 접근 불가
							System.out.println("권한이 없습니다. 관리자 아이디로 로그인 해주세요.\n");
							System.out.println("=======================================\n");
							continue;
						}
					}
					break;
				case 5:
					System.out.println("프로그램을 종료합니다.");
					System.exit(0);
					break;
				default:
					System.out.println("메뉴를 잘못 입력하셨습니다.");
					break;
			}
			
		}
	}
}
