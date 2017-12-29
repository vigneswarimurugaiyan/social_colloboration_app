package com.maven.socialappbackend.test;
import static org.junit.Assert.*;
import java.util.Date;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.maven.socialappbackend.dao.forumdao;
import com.maven.socialappbackend.model.forum;

import org.junit.Test;
public class forumTest {
static forumdao forumdao1;
	
	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.maven.socialappbackend");
		context.refresh();
		
		forumdao1=(forumdao)context.getBean("forumDAO");
	}
	
	@Test
	public void addforumTest()
	{
		forum f=new forum();
		
		f.setForumName("programming discussion");
		f.setForumContent("oops knowledge");
		f.setStatus("online");
		f.setUserId(20);
		f.setCreateDate(new Date());
		assertTrue("Problem in Inserting forum",forumdao1.addforum(f));
	
	}
	@Test
	public void updateforumTest()
	{
		forum f=forumdao1.getforumbyid(30);
		f.setForumName("Leaders autobiography");
		f.setForumContent("APJ life history");
		f.setStatus("online");
		f.setUserId(21);
		f.setCreateDate(new Date());
		assertTrue("Problem in updating forum",forumdao1.updateforum(f));
	}
	@Test
	public void getforumbyidTest()
	{
		forum f=forumdao1.getforumbyid(1);
		System.out.println("getforumbyid() method invoked");
		System.out.println("forumname = "+f.getForumName());
	}
	@Ignore
	@Test
	public void deleteforumTest()
	{
		forum f=forumdao1.getforumbyid(4);
		assertTrue("problem in deleting forum",forumdao1.deleteforum(f));
	}
	@Test
	public void getallforumTest()
	{
		List<forum> l=forumdao1.getallforums();
		for(forum b:l)
		{
			System.out.println(b.getForumName());
		}
	}
	
	
}
