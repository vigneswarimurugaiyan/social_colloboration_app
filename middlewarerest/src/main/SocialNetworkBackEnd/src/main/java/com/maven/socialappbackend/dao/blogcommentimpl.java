package com.maven.socialappbackend.dao;
import java.util.List;

import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.maven.socialappbackend.model.blog;
import com.maven.socialappbackend.model.blogcomment;
import com.maven.socialappbackend.model.userdetail;
@Repository("blogcommentdao")
public class blogcommentimpl implements blogcommentdao{
	@Autowired
	SessionFactory sessionFactory;
	public blogcommentimpl(SessionFactory sessionFactory)
	{
         this.sessionFactory=sessionFactory;
	}
	
	@Transactional

	public boolean addblogcomment(blogcomment bc) 
	{
		try
		{
		sessionFactory.getCurrentSession().save(bc);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
		return false;
		}
	}
	@Transactional
	public blogcomment getblogcommentbyid(int blogCommentid) 
	{
		
	    Session session=sessionFactory.openSession();
	    blogcomment bc=(blogcomment) session.get(blogcomment.class,new Integer(blogCommentid));
	    session.flush();
	    session.close();
		return bc;
		 
	}
@Transactional
public boolean updateblogcomment(blogcomment bc)
{
try
	{
	 Session session=sessionFactory.openSession();
	session.saveOrUpdate(bc);
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
public boolean deleteblogcomment(blogcomment bc)
{
try
	{
	sessionFactory.getCurrentSession().delete(bc);
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
public List<blogcomment> getallblogcomments()
{
	Session session=sessionFactory.openSession();
	String hql="from blogcomment";
	Query query=session.createQuery(hql);
	return query.list();
}


}