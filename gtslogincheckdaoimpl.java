package com.gts.DAO;

import java.sql.SQLException;
import java.util.Date;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.gts.entity.gtsregister;
import com.gts.entity.user_token;
import com.gts.entity.users;
@Transactional
@Repository
public class gtslogincheckdaoimpl implements gtslogincheckdao {
	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	usersdao usersDAO;
	@Autowired
	usertokendao utdao;
	@Autowired
	gtslogincheckdao logindao;
	//public boolean gtslogincheck(String username,String password,int length)
	//{
	//	try{
	//System.out.println("entered the impl login check()");
	//users us=(users) entityManager.createNativeQuery("CALL gtslogincheck(:user_name,:pass_word)",users.class).setParameter("user_name",username).setParameter("pass_word",password).getSingleResult();
	//int userid=us.getUser_id();
	//Date d=new Date();
	//System.out.println(d);
	//ud.changelogintime(userid, d);
	//int id=gts.getGts_reg_id();
	//logindao.changelogintime(id, d);
	//System.out.println("last******"+gtsid);
//	System.out.println("after the called gtslogincheck sttored procedure call  "+gtsreg.getEmail());
	//String token=getToken(length);
	//System.out.println("my token inside :!!!!!!"+token+"*********");
//	users u=new users();
	//u.setUsername(username);
	//u.setPassword(password);
	//u.setLastlogin(d);
	//ud.saveuserinfo(u);
	//user_token utobj=new user_token();
	//utobj.setUser(u);
	//utobj.setToken(token);
	//utdao.savetokeninfo(utobj);
//return true;
//		}
	//	catch(NoResultException e) {
	  
	
	//return false;
	  //  }
		
		
}
	
	//public void changelogintime(int uid,Date lastloggedin)
	//{
		//System.out.println("enter the change logintime");
		//entityManager.createNativeQuery("CALL updatelastlogintime(:myid,:last_login_time)",gtsregister.class).setParameter("myid",uid).setParameter("last_login_time", lastloggedin);
		//System.out.println("successfully updated last time");
	//}
	
	
	//private final Random random = new Random();
	//private final String CHARS = "abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ234567890!@#$";
	//public String getToken(int length) {
	  //  StringBuilder token = new StringBuilder(length);
	    //for (int i = 0; i < length; i++) {
	      //  token.append(CHARS.charAt
	        //		(random.nextInt(CHARS.length())));
	    //}
	   // return token.toString();
	//}
//}