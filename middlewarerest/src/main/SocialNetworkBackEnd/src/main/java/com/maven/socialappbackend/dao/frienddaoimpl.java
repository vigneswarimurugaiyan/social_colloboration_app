package com.maven.socialappbackend.dao;
import java.util.List;

import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.maven.socialappbackend.model.friend;

@Repository("frienddao")
public class frienddaoimpl implements frienddao{
	@Autowired
	SessionFactory sessionFactory;
	
	public frienddaoimpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}
	
	@Transactional

	public boolean addfriend(friend f) 
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
	public friend getfriendbyid(int friendId) 
	{
		
	    Session session=sessionFactory.openSession();
	    friend f=(friend) session.get(friend.class,new Integer(friendId));
	    session.flush();
	    session.close();
		return f;
		 
	}
@Transactional
public boolean updatefriend(friend f)
{
try
	{
	 Session session=sessionFactory.openSession();
	session.saveOrUpdate(f);
	System.out.println("friend updated successfully");
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
public boolean deletefriend(friend f)
{
try
	{
	sessionFactory.getCurrentSession().delete(f);
	 System.out.println("friend deleted successfully");
	return true;
	}
	catch(Exception e)
	{
	System.out.println(e);
	return false;	
}
}
@Transactional
public List<friend> getallfriends()
{
	Session session=sessionFactory.openSession();
	String hql="from friend";
	Query query=session.createQuery(hql);
	return query.list();
}
}