package com.maven.socialappbackend.dao;

import java.util.List;

import com.maven.socialappbackend.model.blog;

public interface blogdao {
	public boolean addblog(blog b);
	public boolean updateblog(blog b);
	public boolean deleteblog(blog b);
	public blog getblogbyid(int blogId);
	public List<blog> getallblogs();
public boolean approveblog(blog b);
public boolean rejectblog(blog b);
}
