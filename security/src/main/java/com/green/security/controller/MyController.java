package com.green.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.security.dto.MemberDto;
import com.green.security.entity.Member;
import com.green.security.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MyController {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder; // 암호화 매서드를 가진 객체

	@RequestMapping("/")
	public String root() {
		log.info(".................................");
		return "index";
	}

	@RequestMapping("/registForm")
	public String registForm() {
		log.info("registForm..............");
		return "registForm";
	}

	@RequestMapping("/registProc")
	public @ResponseBody String registProc(MemberDto memberDto) { // 커멘드 객체로 파라미터 받음. 폼 파라미터의 이름과 ? 가 동일해야한다.

		log.info("registProc........................" + memberDto);

		// memberRepository의 매개변수를 멤버dto인 상태로 넣으면 안되고 멤버entity를 넣어야 한다.
		// dto-> entity 옮길때, 패스워드 복호화 & role setting 하기
		Member member = new Member();
		member.setUsername(memberDto.getUsername()); // memberDto로 받아둔 username을 member엔티티에 set

		// 암호화된 패스워드를 set해준다.
		String newfw = bCryptPasswordEncoder.encode(memberDto.getPassword());// newfw = 암호화된 패스워드
		// security관련된 설정파일을 만든다(패스워드 암호화를 위해서)
		member.setPassword(newfw);
		
		member.setName(memberDto.getName());
		member.setRole("ROLE_MEMBER"); // DTO의 ROLE은 NULL이니까 여기서 String으로 직접 set 해준다.

		memberRepository.save(member);

		return "회원가입 완료됨.";
	}

	@RequestMapping("/login")
	public String loginForm() {
		
		return "loginForm";
	}
	
	@RequestMapping("/success")
	public String loginSuccess() {
		
		return "success";
	}
	
	
	
	
	
	
	
}
