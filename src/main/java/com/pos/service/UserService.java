package com.pos.service;

import com.pos.dto.User;

public interface UserService{
	boolean login(String userId, String userPw); // 로그인  
	boolean isManager(String userId, String userPw);  // 관리자 확인
	int register(User user); // 회원가입
	void chanagePw(String userId, String userPw, String changePw);	// 비밀번호 변경
	void userInfo(); // 초기 저장된 아이디 비밀번호 읽기
}



