package com.gts.DAO;

import java.util.List;

import com.gts.entity.user_token;


public interface usertokendao {
	void savetokeninfo(user_token u);
	user_token gettokeninfo(int inuser_id,int inuser_tokenid);
	 //List<user_token> getallusertoken();
}
