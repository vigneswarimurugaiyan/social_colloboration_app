package com.gts.DAO;

import java.util.Date;

import com.gts.entity.user_token;
import com.gts.entity.users;

public interface usersdao {
	users gtslogincheck(String username,String password,int length);
//void changelogintime(int user_id,Date last_successfull_login_time);
	void changelogintime(int user_id,Date last_successfull_login_time,String userloginstatus,String userstatus);
	
}
