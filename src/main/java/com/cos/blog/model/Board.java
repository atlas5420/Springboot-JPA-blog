package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob // 대용량 데이터에 사용
	private String content; // tool 사용 썸머노트 라이브러리 <html>태그가 섞여서 디자인 됨
	
	@ColumnDefault("0")
	private int count; // 조회수
	//ManytoMany는 쉽게보면 두 테이블의 중간값을 만들어주는것
	//단점은 primary key로만 만들기때문에 다른 content는 들어갈 수 없다
	@ManyToOne // Many = Board, One = user 한명의 유저는 여러개의 개시글을 쓸수있다
	@JoinColumn(name="userId")
	private User user; //FK
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
