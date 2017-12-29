package com.maven.socialappbackend.dao;
import java.util.List;

import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.maven.socialappbackend.model.forum;
import com.maven.socialappbackend.model.userdetail;
@Repository("forumdao")
public class forumdaoimpl implements forumdao{
	@Autowired
	SessionFactory sessionFactory;
	
	public forumdaoimpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}
	
	@Transactional

	public boolean addforum(forum f) 
	{
		try
		{
		sessionFactory.getCurrentSession().save(f);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
		return false;
		}
	}
	@Transactional
	public forum getforumbyid(int forumId) 
	{
		
	    Session session=sessionFactory.openSession();
	    forum f=(forum) session.get(forum.class,new Integer(forumId));
	    session.flush();
	    session.close();
		return f;
		 
	}
@Transactional
public boolean updateforum(forum f)
{
try
	{
	 Session session=sessionFactory.openSession();
	session.saveOrUpdate(f);
	System.out.println("forum updated successfully");
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
public boolean deleteforum(forum f)
{
try
	{
	sessionFactory.getCurrentSession().delete(f);
	 System.out.println("forum deleted successfully");
	return true;
	}
	catch(Exception e)
	{
	System.out.println(e);
	return false;	
}
}
@Transactional
public List<forum> getallforums()
{
	Session session=sessionFactory.openSession();
	String hql="from forum";
	Query query=session.createQuery(hql);
	return query.list();
}
}