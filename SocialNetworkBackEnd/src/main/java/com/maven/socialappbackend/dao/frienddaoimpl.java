package com.maven.socialappbackend.dao;
import java.util.List;

import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.maven.socialappbackend.model.friend;
import com.maven.socialappbackend.model.userdetail;

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
		System.out.println("updated friend successfully");
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
public boolean approvefriendrequest(friend f) {
	// TODO Auto-generated method stub
	return false;
}

public boolean rejectfriendrequest(friend f) {
	// TODO Auto-generated method stub
	return false;
}

public List<friend> getallfriends(String userName) {
	Session session=sessionFactory.openSession();
	Query q=session.createQuery("from friend where userName=:uname and status=:status");
	q.setParameter("uname",userName);
	q.setParameter("status","R");
	List<friend> l=q.list();
	return l;
}

public List<friend> getapprovedfriends(String userName) {
	Session session=sessionFactory.openSession();
	Query q=session.createQuery("from friend where userName=:uname and status='A'");
	q.setParameter("uname",userName);
	List<friend> l=q.list();
	return l;
}

public boolean checklogin(userdetail user) {
	Session session=sessionFactory.openSession();
	Query q=session.createQuery("from userdetail where userName=:uname and password=:passwd");
	q.setParameter("uname",user.getUserName());
	q.setParameter("passwd",user.getPassword());
	userdetail u=(userdetail)q.list().get(0);
	session.close();
	if(u==null)
		return false;
	else
		System.out.println(" inside the checklogin logged in successfully");
		return true;
	
}



}