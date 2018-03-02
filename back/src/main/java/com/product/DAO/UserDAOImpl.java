package com.product.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.product.model.User;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {
	@Autowired
	private SessionFactory sessionfactory;
	public void adduser(User user)
	{
		try
		{
			Session session=sessionfactory.openSession();
			session.save(user);
			session.flush();
			session.close();
		}
		
		catch(Exception ex)
		{
			System.out.println("Error="+ex);
		}
		
	}
	public List<User> getList()
	{
		Session session=sessionfactory.openSession();
		List<User> showuser=session.createQuery("from User").list();
		session.close();
		return showuser;
		
	}
}
