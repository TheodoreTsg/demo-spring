package com.example.demo.util;

import org.springframework.stereotype.Component;

import com.example.demo.entity.PasswordResetToken;
import com.example.demo.entity.SpringDemoEntity;

@Component
public class CreatePasswordResetToken {

	public void createPasswordResetTokenForUser(SpringDemoEntity user, String token) {
	    PasswordResetToken myToken = new PasswordResetToken();
//	    passwordTokenRepository.save(myToken);
	}

}