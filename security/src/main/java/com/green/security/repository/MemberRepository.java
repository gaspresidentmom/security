package com.green.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.green.security.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer>{

	// jpa리포지토리를 상속받음 (인터페이스끼리 '상속'할때는 extends 사용)
	
	public Member findByUsername(String username);
	
	
}
