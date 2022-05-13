package com.pos.dto;

public class User {
	private String userId;  // 사용자 ID
	private String userPw;	 // 사용자 비밀번호
	private String userName;	// 사용자 이름
	private String userType;	// 회원 유형 (관리자 / 사용자)
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userPw=" + userPw + ", userName=" + userName + ", userType=" + userType
				+ "]";
	}

}
