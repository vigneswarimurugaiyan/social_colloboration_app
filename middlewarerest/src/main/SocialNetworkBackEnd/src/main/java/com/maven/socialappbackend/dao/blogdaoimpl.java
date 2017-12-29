package com.maven.socialappbackend.dao;
import java.util.List;

import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.maven.socialappbackend.model.blog;
import com.maven.socialappbackend.model.userdetail;
@Repository("blogdao")
public class blogdaoimpl implements blogdao {
	@Autowired
	SessionFactory sessionFactory;
	
	public blogdaoimpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}
	
	@Transactional

	public boolean addblog(blog b) 
	{
		try
		{
		sessionFactory.getCurrentSession().save(b);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
		return false;
		}
	}
	@Transactional
	public blog getblogbyid(int blogId) 
	{
		
	    Session session=sessionFactory.openSession();
	    blog b=(blog) session.get(blog.class,new Integer(blogId));
	    session.flush();
	    session.close();
		return b;
		 
	}
@Transactional
public boolean updateblog(blog b)
{
try
	{
	 Session session=sessionFactory.openSession();
	session.saveOrUpdate(b);
	System.out.println("updated successfully");
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
public boolean deleteblog(blog b)
{
try
	{
	sessionFactory.getCurrentSession().delete(b);
	 System.out.println("deleted successfully");
	return true;
	}
	catch(Exception e)
	{
	System.out.println(e);
	return false;	
}
}
@Transactional
public List<blog> getallblogs()
{
	Session session=sessionFactory.openSession();
	String hql="from blog";
	Query query=session.createQuery(hql);
	return query.list();
}
@Transactional
public boolean approveblog(blog b)
{
	try
	{
	sessionFactory.getCurrentSession().saveOrUpdate(b);
	System.out.println("appoved blog");
	return true;
}
	catch(Exception e)
	{
		System.out.println(e);
		return false;	
	}
}
@Transactional
public boolean rejectblog(blog b) {
	try
	{
	sessionFactory.getCurrentSession().saveOrUpdate(b);
	System.out.println("rejected blog");
	return true;
}
	catch(Exception e)
	{
		System.out.println(e);
		return false;	
	}
}

}