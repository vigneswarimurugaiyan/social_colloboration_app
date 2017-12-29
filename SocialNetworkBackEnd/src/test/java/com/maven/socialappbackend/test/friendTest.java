package com.maven.socialappbackend.test;

import static org.junit.Assert.*;
import java.util.Date;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.maven.socialappbackend.dao.frienddao;
import com.maven.socialappbackend.model.friend;
public class friendTest {
static frienddao frienddao1;
	
	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.maven.socialappbackend");
		context.refresh();
		
		frienddao1=(frienddao)context.getBean("friendDAO");
	}
	
	@Test
	public void addfriendTest()
	{
		friend f=new friend();
		f.setStatus("online");
		f.setUserId(20);
		assertTrue("Problem in Inserting friend",frienddao1.addfriend(f));
	
	}
	
	@Test
	public void updatefriendTest()
	{
		friend f=frienddao1.getfriendbyid(37);
		f.setStatus("offline");
		f.setUserId(21);
		assertTrue("Problem in updating friend",frienddao1.updatefriend(f));
	}
	@Test
	public void getfriendbyidTest()
	{
		friend f=frienddao1.getfriendbyid(36);
		System.out.println("getfriendbyid() method invoked");
		System.out.println("friend status = "+f.getStatus());
	}
	@Ignore
	@Test
	public void deletefriendTest()
	{
		friend f=frienddao1.getfriendbyid(37);
		assertTrue("problem in deleting friend",frienddao1.deletefriend(f));
	}
	@Test
	public void getallfriendTest()
	{
		List<friend> l=frienddao1.getallfriends();
		for(friend b:l)
		{
			System.out.println(b.getFriendId());
		}
	}
	
	
}
