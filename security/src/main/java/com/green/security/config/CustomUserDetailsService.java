package com.green.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.green.security.entity.Member;
import com.green.security.repository.MemberRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
					// 사용자를 메모리로 가져오겠다. username을 통해서(사용자 정보 데이터)
		
			Member member = memberRepository.findByUsername(username);
			
			if(member != null) {
				return new CustomUserDetails(member);
			}
			
		return null;	
	}
		
}
