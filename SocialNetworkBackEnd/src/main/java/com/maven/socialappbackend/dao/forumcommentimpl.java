package com.maven.socialappbackend.dao;
import java.util.List;

import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.maven.socialappbackend.model.forumcomment;
@Repository("forumcommentdao")
public class forumcommentimpl implements forumcommentdao {
	@Autowired
	SessionFactory sessionFactory;
	public forumcommentimpl(SessionFactory sessionFactory)
	{
         this.sessionFactory=sessionFactory;
	}
	
	@Transactional

	public boolean addforumcomment(forumcomment fc) 
	{
		try
		{
		sessionFactory.getCurrentSession().save(fc);
		System.out.println("forumcomment added successfully");
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
		return false;
		}
	}
	@Transactional
	public forumcomment getforumcommentbyid(int forumCommentid) 
	{
		
	    Session session=sessionFactory.openSession();
	    forumcomment fc=(forumcomment) session.get(forumcomment.class,new Integer(forumCommentid));
	    session.flush();
	    session.close();
		return fc;
		 
	}
@Transactional
public boolean updateforumcomment(forumcomment fc)
{
try
	{
	 Session session=sessionFactory.openSession();
	session.saveOrUpdate(fc);
	System.out.println("forumcomment updated successfully");
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
public boolean deleteforumcomment(forumcomment fc)
{
try
	{
	sessionFactory.getCurrentSession().delete(fc);
	 System.out.println("forum comment deleted successfully");
	return true;
	}
	catch(Exception e)
	{
	System.out.println(e);
	return false;	
}
}
@Transactional
public List<forumcomment> getallforumcomments()
{
	Session session=sessionFactory.openSession();
	String hql="from forumcomment";
	Query query=session.createQuery(hql);
	return query.list();
}


}