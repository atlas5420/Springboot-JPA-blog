package com.cos.blog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service
public class PrincipalDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	//스플링이 로그인 요청할떄 username password 변수 2개를 가로채는데
	//password 부분 처리는 알아서 username은 DB에 있는지 확인해주면 된다 loadUserByUsername 함수에서 처리
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User principal = userRepository.findByUsername(username)
				.orElseThrow(()->{
					return new UsernameNotFoundException("해당하는 유저아이디 없음"+username);
				});
		return new PrincipalDetail(principal); // 시큐리티 세션에 유저정보가 저장됨
	}

}
