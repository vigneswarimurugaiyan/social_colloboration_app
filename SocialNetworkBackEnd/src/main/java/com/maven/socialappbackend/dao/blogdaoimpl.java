package com.maven.socialappbackend.dao;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
			System.out.println("inside blog check user"+b.getUser().getRole());
		sessionFactory.getCurrentSession().save(b);
		System.out.println("blog successfully added in backend");
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
	System.out.println(" blog updated successfully");
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
//@Transactional
//public List<blog> getallblogs()
//{
//Session session = sessionFactory.openSession();
//Transaction tx = session.beginTransaction();
//Query query = session.createQuery("from blog where status = 'A'");
//List result = query.list();
//tx.commit();
//session.close();
//return result;
//}
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

	@Transactional
	public List<blog> getallblogs1(int userId)
	{
		System.out.println("inside the getallblogs in impl");
		int flag=0;
		Session session=sessionFactory.openSession();
		String hql="from blog";
		
		Query query=session.createQuery(hql);
		List<blog> l=query.list();
		for(blog b1:l)
		{
			System.out.println("bloguserid"+b1.getUser().getUserId()+b1.getBlogName());
		}
		System.out.println("list elements"+l);
		for(blog b:l)
		{
			System.out.println("blog pbj"+b);
			if(userId==b.getUser().getUserId())
			{
				System.out.println("inside if in getallblogs impl"+b.getUser().getUserId())
;				flag=1;
				break;
				
			}
			
		}
		if(flag==1)
		{
			System.out.println("flag==1");
;			Query query1 = session.createQuery("from blog where status = 'A'");
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