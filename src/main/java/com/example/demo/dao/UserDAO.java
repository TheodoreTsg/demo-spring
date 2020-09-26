package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.SpringDemoEntity;


public interface UserDAO extends JpaRepository<SpringDemoEntity, Integer> {
	
	@Query(value = "SELECT * FROM userregistration WHERE username= ?1 AND password = ?2 ", nativeQuery = true)
	SpringDemoEntity findUser(String username, String password);
	
	SpringDemoEntity findUserByEmail(String email);
}