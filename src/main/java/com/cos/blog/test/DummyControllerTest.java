package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import com.cos.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cos.blog.model.RoleType;
import com.cos.blog.repository.UserRepository;

import net.bytebuddy.asm.Advice.OffsetMapping.Sort;

@RestController
public class DummyControllerTest {
	
	@Autowired //의존성 주입(DI)
	private UserRepository userRepository;
	
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	@DeleteMapping("/dummy/user/{id}")
	public String deleteUser(@PathVariable int id) {
		try {
			userRepository.deleteById(id);	
		} catch(EmptyResultDataAccessException e) {
			return "아이디가 존재하지 않음";
		}
		return "삭제 완료" + id;
	}
	
	//save함수는 id를 전달하지 않으면 insert
	//id를 전달하면 해당 id에 대한 데이터가 있으면 update
	//id를 전달하면 해당 id에 대한 데이터가 없으면 insert
	@Transactional //함수 종료시에 자동 commit된다
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) { // json 데이터 요청 => java object(Message Converter의 Jackson 라이브러리가 변환)
		System.out.println(id);
		System.out.println(requestUser.getPassword());
		System.out.println(requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패함");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
//		requestUser.setId(id);
//		requestUser.setUsername("ssar");
//		userRepository.save(user);
		
//		더티체킹 => 업데이트에 주료 사용 @Transactional
//		영속성 컨텍스트
//		영속화된 객체를 데이터에 넣는것 flush
		
		return user; 
	}
	
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2, sort = "id", direction=Direction.DESC) org.springframework.data.domain.Pageable pageable){
		Page<User> pagingUser = userRepository.findAll(pageable);
		List<User> users = pagingUser.getContent();
		return users;
	}
	//{id} 주소로 파라미터를 전달 받을 수 있음
	//http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
		@Override
		public IllegalArgumentException get() {
			return new IllegalArgumentException("해당 유저는 없습니다 id : " + id);
		}
		});
		//user = 자바 오브젝트
		//요청 = 웹브라우저
		//변환 웹 브라우저가 이해할 수 있는 데이터 -> json
		//springboot = MessageConverter가 응답시에 자동 작동
		//만약 자바 오브젝트를 retrun => MessageConverter 가 Jackson 라이브러리 호출
		//user 오브젝트를 json으로 변환
		return user;
	}
	// http://localhost:8000/blog/dummy/join (request)
	// http의 body 에데이터를 가지고 (request)
	@PostMapping("/dummy/join")
	public String join(com.cos.blog.model.User user) {
		System.out.println("id : " + user.getId());
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		System.out.println("email : " + user.getEmail());
		System.out.println("role : " + user.getRole());
		System.out.println("createDate : " + user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입 완료";
	}
}
