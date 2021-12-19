package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cos.blog.model.User;

//DAO jsp에서의
//자동으로 bean등록이 된다
@Repository // 생략 가능하다
public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUsername(String username);
}

// JPA Naming 전략
// select * from user where username = ? AND password = ?;
//User findByUsernameAndPassword(String username, String password);

//@Query(value="select * from user where username = ?1 AND password = ?2", nativeQuery = true)
//User login(String username, String password);