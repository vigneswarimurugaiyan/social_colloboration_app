package com.maven.socialappbackend.dao;

import java.util.List;

import com.maven.socialappbackend.model.forumcomment;

public interface forumcommentdao {
	public boolean addforumcomment(forumcomment fc);
	public boolean deleteforumcomment(forumcomment fc);
	public boolean updateforumcomment(forumcomment fc);
	public forumcomment getforumcommentbyid(int forumCommentid);
public List<forumcomment> getallforumcomments();
}
