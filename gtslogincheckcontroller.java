package com.gts.controller;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.header.Header;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import com.gts.DAO.usersdao;
import com.gts.DAO.usertokendao;
import com.gts.entity.gts_user;
import com.gts.entity.user_token;
import com.gts.entity.users;
@RestController
public class gtslogincheckcontroller {
	
	@Autowired
	usersdao usersDAO;
	@Autowired 
	usertokendao utdao;

	@PostMapping("logincheck")
	public ResponseEntity<?> logincheck(@RequestBody users us)
	{
		System.out.println("inside logincheck controller ()");

		String username=us.getUser_name();
		String password=us.getUser_pwd();
		
		int length=username.length()+password.length();
		users ut=usersDAO.gtslogincheck(username,password,length);
		
		int userid=ut.getUser_id();
		Date d=new Date();
		usersDAO.changelogintime(userid,d,"success","login");
		
		user_token utobj=new user_token();
		utobj.setUser_id(userid);
		utobj.setUser_tokenvalue("gjhgsdkakjjksagk");
		utobj.setToken_createdon(new Date());
		utobj.setToken_expireon(new Date());
		utobj.setToken_status("active");
		utdao.savetokeninfo(utobj);

		user_token ut1=new user_token();
		//ut1.setUser_tokenid(2);
		user_token utoken=(user_token)utdao.gettokeninfo(1,2);
		return new ResponseEntity<user_token>(utobj,HttpStatus.OK);

	}
	@PostMapping("logout")
	
		public boolean logout(@RequestHeader(value="X-Auth-Token",required=false) String myheader)
		{
			System.out.println("my header  "+myheader);
			return true;
		}
	
	
	
	
	

}





