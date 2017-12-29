package com.maven.socialappbackend.test;


import static org.junit.Assert.*;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.maven.socialappbackend.dao.jobdao;
import com.maven.socialappbackend.model.job;

public class jobTest {

	
static jobdao jobdao1;
	
	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.maven.socialappbackend");
		context.refresh();
		
		jobdao1=(jobdao)context.getBean("jobDAO");
	}
	
	@Test
	public void addjobTest()
	{
		job j=new job();
		j.setJobProfile("vigneswari");
		j.setJobDesc("good");
		j.setJobQualification("B-Tech,IT");
		j.setStatus("online");
		j.setPostDate(new Date());
		assertTrue("Problem in Inserting job",jobdao1.addjob(j));
	
	}
	@Ignore
	@Test
	public void updatejobTest()
	{
		job j=jobdao1.getjobbyid(36);
		j.setJobProfile("maha");
		j.setJobDesc("super");
		j.setJobQualification("Bsc-cse");
		j.setStatus("online");
		j.setPostDate(new Date());
		assertTrue("Problem in updating job",jobdao1.updatejob(j));
	}
	@Ignore
	@Test
	public void getjobbyidTest()
	{
		job j=jobdao1.getjobbyid(35);
		System.out.println("getjobbyid() method invoked");
		System.out.println("jobname = "+j.getJobQualification());
	}
	@Ignore
	@Test
	public void deletejobTest()
	{
		job j=jobdao1.getjobbyid(37);
		assertTrue("problem in deleting job",jobdao1.deletejob(j));
	}
	@Ignore
	@Test
	public void getalljobTest()
	{
		List<job> l=jobdao1.getalljobs();
		for(job b:l)
		{
			System.out.println(b.getJobId());
		}
	}
	
	
}
