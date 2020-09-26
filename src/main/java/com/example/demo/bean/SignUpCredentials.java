package com.example.demo.bean;

public class SignUpCredentials {
	
	private String username;
	private String email;
	private String password;
	private String confpassword;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getConfpassword() {
		return confpassword;
	}
	public void setConfpassword(String confpassword) {
		this.confpassword = confpassword;
	}
	
	@Override
	public String toString() {
		return "SignUpCredentials [username=" + username + ", email=" + email + ", password=" + password
				+ ", confpassword=" + confpassword + "]";
	}
}