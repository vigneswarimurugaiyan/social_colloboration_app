package com.maven.socialappbackend.dao;

import java.util.List;

import com.maven.socialappbackend.model.blogcomment;

public interface blogcommentdao {
	public boolean addblogcomment(blogcomment bc);
	public boolean deleteblogcomment(blogcomment bc);
	public boolean updateblogcomment(blogcomment bc);
	public blogcomment getblogcommentbyid(int blogCommentid);
public List<blogcomment> getallblogcomments();

}
