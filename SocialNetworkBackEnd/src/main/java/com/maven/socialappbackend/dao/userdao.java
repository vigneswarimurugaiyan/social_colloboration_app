package com.maven.socialappbackend.dao;

import java.util.List;

import com.maven.socialappbackend.model.userdetail;
public interface userdao {
	
public boolean adduser(userdetail user);
public boolean updateuser(userdetail user);
public boolean deleteuser(userdetail user);
public List<userdetail> getalluser();
public userdetail getuserbyid(int userId);
public userdetail getuser(String userName);
public boolean checklogin(userdetail user);
public boolean changeonlinestatus(userdetail user);

}
