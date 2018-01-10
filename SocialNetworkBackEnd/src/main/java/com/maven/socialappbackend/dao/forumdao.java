package com.maven.socialappbackend.dao;

import java.util.List;

import com.maven.socialappbackend.model.forum;
public interface forumdao {
	public boolean addforum(forum b);
	public boolean updateforum(forum b);
	public boolean deleteforum(forum b);
	public forum getforumbyid(int forumId);
	public List<forum> getallforums1(int userId);
public boolean approveforum(forum b);
public boolean rejectforum(forum b);
}
