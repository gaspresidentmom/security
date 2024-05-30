package com.green.security.config;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.green.security.entity.Member;

public class CustomUserDetails implements UserDetails{
	// security session에 들어가는 객체가 정해져 있기때문에 복잡하다(?) - UserDetails라는 객체를 통해서 들어가야 함(얘를 이용해 구현해야함)

	private Member member;
	
	
	public CustomUserDetails(Member member) {
		this.member = member;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new ArrayList<>();
		collection.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				
				return member.getRole();
			}
		});
		return collection;		// 자동으로 생성시킬 때 return null;로 표시되는데 null이 되면 안됨. 바꾸어주어야 함.
	}

	@Override
	public String getPassword() {
		return member.getPassword();
	}

	@Override
	public String getUsername() {
		return member.getUsername();
	}
	
	public String getName() {
		return member.getName();
	}
	
	public String getRole() {
		return member.getRole();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
