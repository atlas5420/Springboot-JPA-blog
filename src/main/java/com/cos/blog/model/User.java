package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//ORM - > object를 table로 mapping 해준다
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@DynamicInsert // insert시에 null값을 제외해준다
@Entity // user 클래스가 mysql에서 테이블이 생성된다
public class User {
	
	@Id //primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //project에 연결된 db의 넘버링 전략을 따라간다
	private int id; //시퀸스, auto_incerement
	
	@Column(nullable = false, length = 100, unique = true) //null값이 올수없고 길이는 30
	private String username;
	  
	@Column(nullable = false, length = 100) // 해쉬로 변경 => 암호화
	private String password;
	  
	@Column(nullable = false, length = 50)
	private String email;
	
//	@ColumnDefault("user")
	//DB에는 RoleType이 없다
	@Enumerated(EnumType.STRING)
	private RoleType role; //Enum을 쓰는게 좋다 => data의 도메인을 만들수 있기때문(admin, manager,user etc) 권한
											//도메인을 정의하는건 범위를 설정한다는것
	@CreationTimestamp // 시간이 자동으로 입력된다
	private Timestamp createDate;
	
	private String oauth; //회원가입 구분
	
}
