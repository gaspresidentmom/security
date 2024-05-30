package com.green.security.dto;

import lombok.Data;

@Data
public class MemberDto {
	// --> MemberDto는 사용자가 입력한 파라미터를 받는 용도
	private Integer mno; //--> db에서 넘어오는 자료를 처리하는게 아니니까 mno는 주석처리 
	private String username;
	private String password;
	private String name;
	private String role;
}
