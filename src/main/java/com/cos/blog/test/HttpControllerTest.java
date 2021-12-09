package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//client request -> response(html file) @Controller
//client request -> response(Data) @RestController

@RestController
public class HttpControllerTest {
	
	private static final String TAG = "HttpControllerTest : ";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = Member.builder().password("1234").username("asd").id(33).email("asdasd").build();
		System.out.println(TAG + "getter : " + m.getId());
		m.setId(5000);
		System.out.println(TAG + "setter : " + m.getId());
		return "lombokTest 완료";
	}
	
	//인터넷 브라우저 요청은 무조건 get만 가능
	@GetMapping("/http/get")
	public String getTest(Member m) {
		return "get request" + m.getId() + " ," + m.getUsername() + " ," + m.getPassword() + " ," +m.getEmail();
	}
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) {
		return "post request" + m.getId() + " ," + m.getUsername() + " ," + m.getPassword() + " ," +m.getEmail();
	}
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put request"+ m.getId() + " ," + m.getUsername() + " ," + m.getPassword() + " ," +m.getEmail();
	}
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete request";
	}
}
