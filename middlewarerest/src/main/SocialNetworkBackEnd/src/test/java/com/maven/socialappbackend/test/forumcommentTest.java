package com.maven.socialappbackend.test;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Date;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.maven.socialappbackend.dao.forumcommentdao;
import com.maven.socialappbackend.model.forumcomment;
public class forumcommentTest {
static forumcommentdao forumcommentdao1;
	
	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.maven.socialappbackend");
		context.refresh();
		
		forumcommentdao1=(forumcommentdao)context.getBean("forumcommentDAO");
	}
	
	@Test
	public void addforumcommentTest()
	{
		forumcomment fc=new forumcomment();
		fc.setForumCommentdate(new Date());
		fc.setForumComment("good conversation");
		fc.setUserId(20);
		fc.setUserName("maha");
		assertTrue("Problem in Inserting forumcomment",forumcommentdao1.addforumcomment(fc));
	
	}
	
	@Test
	public void updateforumTest()
	{
		forumcomment fc=forumcommentdao1.getforumcommentbyid(33);
		fc.setForumId(30);
		fc.setForumCommentdate(new Date());
		fc.setForumComment("amazing questions");
		fc.setUserId(19);
		fc.setUserName("vicky");
		assertTrue("Problem in updating forumcomment",forumcommentdao1.updateforumcomment(fc));
	}
	@Ignore
	@Test
	public void getforumcommentbyidTest()
	{
		forumcomment fc=forumcommentdao1.getforumcommentbyid(33);
		System.out.println("getforumcommentbyid() method invoked");
		System.out.println("forumcomment = "+fc.getForumComment());
	}
	@Ignore
	@Test
	public void deleteforumcommentTest()
	{
		forumcomment fc=forumcommentdao1.getforumcommentbyid(32);
		assertTrue("problem in deleting forumcomment",forumcommentdao1.deleteforumcomment(fc));
	}
	@Ignore
	@Test
	public void getallforumcommentTest()
	{
		List<forumcomment> l=forumcommentdao1.getallforumcomments();
		for(forumcomment b:l)
		{
			System.out.println(b.getForumCommentid());
		}
	}
	
	
}
