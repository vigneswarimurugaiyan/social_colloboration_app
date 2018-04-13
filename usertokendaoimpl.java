package com.gts.DAO;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gts.entity.gts_user;
import com.gts.entity.user_token;

@Transactional
@Repository
public class usertokendaoimpl implements usertokendao {
	@PersistenceContext
	private EntityManager entityManager;
	
	public void savetokeninfo(user_token u)
	{
		StoredProcedureQuery storeprocedure=entityManager.createNamedStoredProcedureQuery("savetokeninfo");
		//storeprocedure.setParameter("mytoken",u.getUser_tokenvalue());
		storeprocedure.setParameter("myuserid",u.getUser_id());
		storeprocedure.setParameter("tokenvalue",u.getUser_tokenvalue());
		storeprocedure.setParameter("tokencreatedon",u.getToken_createdon());
		storeprocedure.setParameter("tokenexpireon",u.getToken_expireon());
		storeprocedure.setParameter("tokenstatus",u.getToken_status());
		
		
		
		
				storeprocedure.execute();
	}
	
	public user_token gettokeninfo(int inuser_id, int inuser_tokenid)
	{
	user_token ut=(user_token) entityManager.createNativeQuery("CALL gettokeninfo(:inuser_id:,inuser_tokenid)",user_token.class).setParameter("inuser_id",1).setParameter("inuser_tokenid",2).getSingleResult();
	System.out.println("enter token impl $&*^*(^^&)(&&)_&_)");
	System.out.println(ut.getUser_tokenvalue());
	System.out.println(ut);
	return ut;
	}
	//public List<user_token> getallusertoken()
	//{
		//List<user_token> usertoken=entityManager.createNamedQuery("gettokeninfolist",user_token.class).getResultList();
		//return usertoken;
	//}
	
}


	