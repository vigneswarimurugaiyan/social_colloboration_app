package com.product.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.product.DAO.UserDAO;
import com.product.model.User;
import com.product.model.error;

@RestController
public class UserController {
	
	private UserDAO userdao;
	@RequestMapping(value = "/saveuser", method = RequestMethod.POST)
	public ResponseEntity<User> savedata(@RequestBody User user)
	{
		userdao.adduser(user);
			//response.data is user
			//response.status is 200 OK
			return new ResponseEntity<User>(user,HttpStatus.OK);
	    	
		}
		
	@RequestMapping(value="/getuser",method=RequestMethod.GET)
	public ResponseEntity<?> getUserlist(HttpSession session)
	{
		//if(session.getAttribute("Id")==null){
    		//error err=new error(5,"UnAuthorized User");
    		//return new ResponseEntity<error>(err,HttpStatus.UNAUTHORIZED);
    	//}

		//String Id=(String)session.getAttribute("Id");
    	User user=(User) userdao.getList();
    	return new ResponseEntity<User>(user,HttpStatus.OK);
		
	}
}
