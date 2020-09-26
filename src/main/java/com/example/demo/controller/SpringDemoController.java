package com.example.demo.controller;

import java.util.List;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.Credentials;
import com.example.demo.bean.Email;
import com.example.demo.bean.SignUpCredentials;
import com.example.demo.service.UserServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class SpringDemoController {
	
//	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserServiceImpl userService;
	
    @RequestMapping("/")
    public String index() {
        return "Congratulations on creating your first controller!";
    }
    
    @RequestMapping(value = "/getAllUsers", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<List<Credentials>> getUsers() {

        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/login", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity <String> userAuthenticate(@RequestBody Credentials requestobject) {
    	System.out.println("Credentials are " + requestobject);
    	String getResponse = userService.userAuthenticate(requestobject);
    	
    	return new ResponseEntity<>(getResponse, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/signup", produces = "application/json", method = RequestMethod.PUT)
    public ResponseEntity <String> signUpUser(@RequestBody SignUpCredentials requestobject) {
    	
    	ResponseEntity<String> response;
    	try {
    		String getResponse = userService.signUpUser(requestobject);
            response = new ResponseEntity<>(getResponse,HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }
    
    @RequestMapping(value = "/forgot-password", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity <String> forgotUserPassword(@RequestBody Email userEmail) {
    	ResponseEntity<String> response;
    	try {
    		String getResponse = userService.forgotPassword(userEmail);
    		response = new ResponseEntity<>(getResponse,HttpStatus.ACCEPTED);
    	} catch (Exception e) {
    		response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    	return response;
	}
}