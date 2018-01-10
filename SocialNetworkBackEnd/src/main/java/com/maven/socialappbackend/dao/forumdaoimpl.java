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

	public boolean addforum(forum b) 
	{
		try
		{
			System.out.println("inside forum check user"+b.getUser().getRole());
		sessionFactory.getCurrentSession().save(b);
		System.out.println("forum successfully added in backend");
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
	    forum b=(forum) session.get(forum.class,new Integer(forumId));
	    session.flush();
	    session.close();
		return b;
		 
	}
@Transactional
public boolean updateforum(forum b)
{
try
	{
	 Session session=sessionFactory.openSession();
	session.saveOrUpdate(b);
	System.out.println(" forum updated successfully");
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
public boolean deleteforum(forum b)
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
//@Transactional
//public List<forum> getallforums()
//{
//Session session = sessionFactory.openSession();
//Transaction tx = session.beginTransaction();
//Query query = session.createQuery("from forum where status = 'A'");
//List result = query.list();
//tx.commit();
//session.close();
//return result;
//}
@Transactional
public boolean approveforum(forum b)
{
	try
	{
	sessionFactory.getCurrentSession().saveOrUpdate(b);
	System.out.println("appoved forum");
	return true;
}
	catch(Exception e)
	{
		System.out.println(e);
		return false;	
	}
}
@Transactional
public boolean rejectforum(forum b) {
	try
	{
	sessionFactory.getCurrentSession().saveOrUpdate(b);
	System.out.println("rejected forum");
	return true;
}
	catch(Exception e)
	{
		System.out.println(e);
		return false;	
	}
}

	@Transactional
	public List<forum> getallforums1(int userId)
	{
		System.out.println("inside the getallforums in impl");
		int flag=0;
		Session session=sessionFactory.openSession();
		String hql="from forum";
		
		Query query=session.createQuery(hql);
		List<forum> l=query.list();
		for(forum b1:l)
		{
			System.out.println("forumuserid"+b1.getUser().getUserId()+b1.getForumName());
		}
		System.out.println("list elements"+l);
		for(forum b:l)
		{
			System.out.println("forum pbj"+b);
			if(userId==b.getUser().getUserId())
			{
				System.out.println("inside if in getallforums impl"+b.getUser().getUserId())
;				flag=1;
				break;
				
			}
			
		}
		if(flag==1)
		{
			System.out.println("flag==1");
;			Query query1 = session.createQuery("from forum where status = 'A'");
			List result = query1.list();
			session.close();
			return result;
		}
		else
		{
			System.out.println("flag==0");
			
			return query.list();
		}
		
	}





}