package com.maven.socialappbackend.dao;
import java.util.List;

import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.maven.socialappbackend.model.job;
@Repository("jobdao")
public class jobdaoimpl implements jobdao {
	@Autowired
	SessionFactory sessionFactory;
	
	public jobdaoimpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}
	
	@Transactional

	public boolean addjob(job j) 
	{
		try
		{
		sessionFactory.getCurrentSession().save(j);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
		return false;
		}
	}
	@Transactional
	public job getjobbyid(int jobId) 
	{
		
	    Session session=sessionFactory.openSession();
	    job j=(job) session.get(job.class,new Integer(jobId));
	    session.flush();
	    session.close();
		return j;
		 
	}
@Transactional
public boolean updatejob(job j)
{
try
	{
	 Session session=sessionFactory.openSession();
	session.saveOrUpdate(j);
	System.out.println("updated job successfully");
	session.flush();
	session.close();
	return true;
	}
	catch(Exception e)
	{
	System.out.println(e);
	return false;	
}
}
@Transactional
public boolean deletejob(job j)
{
try
	{
	sessionFactory.getCurrentSession().delete(j);
	 System.out.println("deleted job successfully");
	return true;
	}
	catch(Exception e)
	{
	System.out.println(e);
	return false;	
}
}
@Transactional
public List<job> getalljobs()
{
	Session session=sessionFactory.openSession();
	String hql="from job";
	Query query=session.createQuery(hql);
	return query.list();
}

}