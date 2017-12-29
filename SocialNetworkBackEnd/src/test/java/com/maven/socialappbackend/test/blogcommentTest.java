package com.maven.socialappbackend.test;
import static org.junit.Assert.*;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.maven.socialappbackend.dao.blogcommentdao;
import com.maven.socialappbackend.dao.blogdao;
import com.maven.socialappbackend.dao.userdao;
import com.maven.socialappbackend.model.blog;
import com.maven.socialappbackend.model.blogcomment;
import com.maven.socialappbackend.model.userdetail;

import org.junit.Test;
public class blogcommentTest {
static blogcommentdao blogcommentdao1;
	
	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.maven.socialappbackend");
		context.refresh();
		
		blogcommentdao1=(blogcommentdao)context.getBean("blogcommentDAO");
	}
	
	@Test
	public void addblogcommentTest()
	{
		blogcomment bc=new blogcomment();
		bc.setBlogId(1);
		bc.setBlogCommentdate(new Date());
		bc.setBlogComment("good");
		bc.setUserId(20);
		bc.setUserName("maha");
		assertTrue("Problem in Inserting blogcomment",blogcommentdao1.addblogcomment(bc));
	
	}
	@Ignore
	@Test
	public void updateblogTest()
	{
		blogcomment bc=blogcommentdao1.getblogcommentbyid(2);
		bc.setBlogId(3);
		bc.setBlogCommentdate(new Date());
		bc.setBlogComment("super");
		bc.setUserId(19);
		bc.setUserName("vicky");
		assertTrue("Problem in updating blogcomment",blogcommentdao1.updateblogcomment(bc));
	}
	@Ignore
	@Test
	public void getblogcommentbyidTest()
	{
		blogcomment bc=blogcommentdao1.getblogcommentbyid(1);
		System.out.println("getblogcommentbyid() method invoked");
		System.out.println("blogcomment = "+bc.getBlogComment());
	}
	@Ignore
	@Test
	public void deleteblogcommentTest()
	{
		blogcomment bc=blogcommentdao1.getblogcommentbyid(3);
		assertTrue("problem in deleting blogcomment",blogcommentdao1.deleteblogcomment(bc));
	}
	@Ignore
	@Test
	public void getallblogcommentTest()
	{
		List<blogcomment> l=blogcommentdao1.getallblogcomments();
		for(blogcomment b:l)
		{
			System.out.println(b.getBlogCommentid());
		}
	}
	
	
}
