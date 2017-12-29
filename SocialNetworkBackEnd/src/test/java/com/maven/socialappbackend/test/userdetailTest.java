package com.maven.socialappbackend.test;
import static org.junit.Assert.*;
import java.util.Date;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.maven.socialappbackend.dao.userdao;
import com.maven.socialappbackend.model.userdetail;

import org.junit.Test;

public class userdetailTest {
static userdao userdao1;
	
	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.maven.socialappbackend");
		context.refresh();
		
		userdao1=(userdao)context.getBean("userDAO");
	}
	
	@Test
	public void adduserTest()
	{
		userdetail user=new userdetail();
		
		user.setUserName("unique");
		user.setPassword("unique");
		user.setEmailId("a@gmail.com");
		user.setRole("user");
		user.setIsOnline("N");
		user.setStatus("N");
		assertTrue("Problem in Inserting user",userdao1.adduser(user));
	
	}
	@Ignore
	@Test
	public void updateuserTest()
	{
		userdetail user=userdao1.getuserbyid(20);
		user.setUserName("maha");
		user.setPassword("mylife100");
        user.setEmailId("maha@gmail.com");
	    user.setRole("user");
	    user.setIsOnline("N");
	    user.setStatus("N");
		assertTrue("Problem in updating user",userdao1.updateuser(user));
	}
	@Ignore
	@Test
	public void getuserbyidTest()
	{
		userdetail ud=userdao1.getuserbyid(1);
		System.out.println("getuserbyid() method invoked");
		System.out.println("username = "+ud.getUserName());
	}
	@Ignore
	@Test
	public void deleteuserTest()
	{
		userdetail ud=userdao1.getuserbyid(4);
		assertTrue("problem in deleting user",userdao1.deleteuser(ud));
	}
	@Ignore
	@Test
	public void getalluserTest()
	{
		List<userdetail> l=userdao1.getalluser();
		for(userdetail ud:l)
		{
			System.out.println(ud.getUserId());
		}
	}
	@Ignore
	@Test
	public void changeonlinestatus()
	{
		userdetail u=userdao1.getuserbyid(24);
		u.setStatus("y");
		assertTrue("problem in changing the  user online status",userdao1.changeonlinestatus(u));
		
	}
	@Test
	public void getuserTest()
	{
		userdetail u=userdao1.getuser("nn");
		System.out.println(u.getPassword()+"this is my password");
		
		
	}
	@Test
	public void checkloginTest()
	{
		userdetail u=new userdetail();
		u.setUserName("hello");
		u.setPassword("hellogiri");
		assertTrue("problem in logging the  user ",userdao1.checklogin(u));
		
	}
	

}
