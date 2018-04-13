package com.gts.DAO;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gts.entity.user_token;
import com.gts.entity.users;

@Transactional
@Repository
public class usersdaoimpl implements usersdao {
	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	usersdao ud;
	@Autowired 
	usertokendao utdao;
//	public void saveuserinfo(users u)
//	{
	//	StoredProcedureQuery storeprocedure=entityManager.createNamedStoredProcedureQuery("saveuserinfo");
		//storeprocedure.setParameter("user_name", u.getUsername());
		//storeprocedure.setParameter("pass_word",u.getPassword());
		//storeprocedure.setParameter("last_loggedin",u.getLastlogin());
			//	storeprocedure.execute();
//	}
	
	
	public users gtslogincheck(String username,String password,int length)
	{
		try{
	System.out.println("entered the impl login check()");
	users us=(users) entityManager.createNativeQuery("CALL gtslogincheck(:user_name,:pass_word)",users.class).setParameter("user_name",username).setParameter("pass_word",password).getSingleResult();
	//int userid=us.getUser_id();
	//Date d=new Date();
	//System.out.println(d);
	
	//ud.changelogintime(userid,d,"success","login");
		//System.out.println("after the called gtslogincheck sttored procedure call  ");
		//System.out.println("787613982689689689");
	//String token=getToken(length);
	//System.out.println("my token inside :!!!!!!"+token+"*********");
	//user_token ut=(user_token)utdao.gettokeninfo(us.getUser_id());
	//System.out.println("my usser_token obj"+ut);

	//user_token ut = new user_token();
	//user_token utobj=new user_token();
	
//	utobj.setUser_id(us.getUser_id());
	//utobj.setUser_tokenvalue(token);
	//utobj.setToken_createdon(new Date());
	//utobj.setToken_expireon(new Date());
	
	//utobj.setToken_status("active");
	//utdao.savetokeninfo(utobj);
return us;
		}
		catch(NoResultException e) {
	        return null;
	    }
		
		
}

	public void changelogintime(int user_id,Date last_successfull_login_time,String userloginstatus,String userstatus)
	{
		try
		{
			StoredProcedureQuery storeprocedure=entityManager.createNamedStoredProcedureQuery("updatelastlogintime");
			storeprocedure.setParameter("user_id",user_id);
			storeprocedure.setParameter("last_successfull_login_time",last_successfull_login_time);
			storeprocedure.setParameter("userloginstatus",userloginstatus);
			storeprocedure.setParameter("userstatus",userstatus);
			
			
			System.out.println("before update procedure call");
			storeprocedure.getUpdateCount();
			storeprocedure.execute();	
			System.out.println("after update procedure call");
		}
		catch(Exception e)
		{
			System.out.println("I am in error:" + e);
		}
	
	}
	
	
	private final Random random = new Random();
		private final String CHARS = "abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ234567890!@#$";
		public String getToken(int length) {
		    StringBuilder token = new StringBuilder(length);
		    for (int i = 0; i < length; i++) {
		        token.append(CHARS.charAt
		        		(random.nextInt(CHARS.length())));
		    }
		    return token.toString();
		}

}

