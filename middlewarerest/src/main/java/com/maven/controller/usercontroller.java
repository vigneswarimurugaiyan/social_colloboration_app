package com.maven.controller;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.maven.socialappbackend.dao.userdao;
import com.maven.socialappbackend.model.userdetail;
@RestController
public class usercontroller {
@Autowired
userdao userDAO;
@PostMapping(value="/insertuser")
public ResponseEntity<?> saveuser(@RequestBody userdetail b)
{
	b.setRole("user");
	b.setIsOnline("N");
	b.setStatus("N");
if(userDAO.adduser(b))
{
return new ResponseEntity<String>("user registered",HttpStatus.OK);
}
else
{
	return new ResponseEntity<String>(" error user occured in user registration",HttpStatus.INTERNAL_SERVER_ERROR);	
}
}
@PostMapping(value="/loginuser")
public ResponseEntity<userdetail>login(@RequestBody userdetail b,HttpSession session)
{
	
if(userDAO.checklogin(b))
{
	b.setStatus("y");
	userDAO.changeonlinestatus(b);
	userdetail tempuser=userDAO.getuser(b.getUserName());
	session.setAttribute("currentuser",b.getUserName());
return new ResponseEntity<userdetail>(tempuser,HttpStatus.OK);
}
else
{
	return new ResponseEntity<userdetail>(b,HttpStatus.INTERNAL_SERVER_ERROR);	
}
}
@GetMapping(value="/logoutuser/{username}")
public ResponseEntity<String>login(@PathVariable("username") String username)
{
	userdetail u=userDAO.getuser(username);
	u.setStatus("N");
if(userDAO.changeonlinestatus(u))
{
return new ResponseEntity<String>("logged out successfully",HttpStatus.OK);
}
else
{
	return new ResponseEntity<String>("error occured in logout process",HttpStatus.INTERNAL_SERVER_ERROR);	
}
}

}