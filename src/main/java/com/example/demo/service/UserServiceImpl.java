package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.Credentials;
import com.example.demo.bean.Email;
import com.example.demo.bean.SignUpCredentials;
import com.example.demo.dao.UserDAO;
import com.example.demo.entity.SpringDemoEntity;
import com.example.demo.util.JWTHandling;

@Service
public class UserServiceImpl {
	
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private JWTHandling jwtHandling;
	
	public List<Credentials> getAllUsers() {
        List<SpringDemoEntity> conEntity = userDao.findAll();

        List<Credentials> list = new ArrayList<>();
        for (SpringDemoEntity obj : conEntity) {
            	Credentials ud = new Credentials();
                BeanUtils.copyProperties(obj, ud);
                list.add(ud);
        }

        return list;
    }
	
	public String userAuthenticate(Credentials request) {
		
		SpringDemoEntity conEntity = userDao.findUser(request.getUsername(), request.getPassword());
		if(conEntity != null) {
			String jwt = jwtHandling.createJWT(conEntity.getId(), conEntity.getUsername());
			System.out.println("Token is " + jwt);
			return jwt;
		}
		else {
			return "false";
		}
		
	}
	
	public String signUpUser(SignUpCredentials request) {
		SpringDemoEntity conEntity = userDao.findUserByEmail(request.getEmail());
		if(conEntity != null) {
			return "User already exists!";
		} else {
			conEntity = new SpringDemoEntity();
			conEntity.setUsername(request.getUsername());
			conEntity.setEmail(request.getEmail());
			conEntity.setPassword(request.getPassword());
			userDao.save(conEntity);
			return "User registration was successful!";
		}
	}
	
	public String forgotPassword(Email email) {
		SpringDemoEntity entity = userDao.findUserByEmail(email.getEmail());
		if(entity != null) {
			String token = UUID.randomUUID().toString();
			return token;
		} else {
			return "false";
		}
	}

}