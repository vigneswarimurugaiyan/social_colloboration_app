package com.maven.socialappbackend.test;

import static org.junit.Assert.*;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.maven.socialappbackend.dao.blogdao;
import com.maven.socialappbackend.dao.userdao;
import com.maven.socialappbackend.model.blog;
import com.maven.socialappbackend.model.userdetail;

import org.junit.Test;

public class blogTest {

static blogdao blogdao1;
	
	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.maven.socialappbackend");
		context.refresh();
		
		blogdao1=(blogdao)context.getBean("blogDAO");
	}
	@Ignore
	@Test
	public void addblogTest()
	{
		blog b=new blog();
		
		b.setBlogName("my java article");
		b.setBlogContent("explaining oops concept");
		b.setStatus("N");
		b.setLikes(100);
		b.setUserId(20);
		b.setCreateDate(new Date());
		assertTrue("Problem in Inserting blog",blogdao1.addblog(b));
	
	}
	@Ignore
	@Test
	public void updateblogTest()
	{
		blog b=blogdao1.getblogbyid(1);
		b.setBlogName("About angular framework");
		b.setBlogContent("explaining angular concept");
		b.setStatus("A");
		b.setLikes(500);
		b.setUserId(21);
		b.setCreateDate(new Date());
		assertTrue("Problem in updating blog",blogdao1.updateblog(b));
	}
	@Test
	public void getblogbyidTest()
	{
		blog b=blogdao1.getblogbyid(27);
		System.out.println("getblogbyid() method invoked");
		System.out.println("blogstatus = "+b.getStatus());
	}
	@Ignore
	@Test
	public void deleteblogTest()
	{
		blog b=blogdao1.getblogbyid(4);
		assertTrue("problem in deleting blog",blogdao1.deleteblog(b));
	}
	@Ignore
	@Test
	public void getallblogTest()
	{
		List<blog> l=blogdao1.getallblogs();
		for(blog b:l)
		{
			System.out.println(b.getBlogId());
		}
	}
	
	@Test
	public void approveblogTest()
	{
	blog b=blogdao1.getblogbyid(26);
	System.out.println("status before change"+b.getStatus());
	b.setStatus("A");
	assertTrue("problem in approving blog id",blogdao1.approveblog(b));
	}
	
	@Test
	public void rejectblogTest()
	{
		blog b=blogdao1.getblogbyid(27);
		b.setStatus("N");
		assertTrue("problem in rejecting  blog id",blogdao1.rejectblog(b));	
	}
}
