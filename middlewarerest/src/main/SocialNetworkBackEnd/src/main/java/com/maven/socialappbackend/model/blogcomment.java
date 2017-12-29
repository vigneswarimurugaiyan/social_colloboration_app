package com.maven.socialappbackend.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class blogcomment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int blogCommentid;
	private int blogId;
	private String blogComment;
	private Date blogCommentdate;
	private int userId;
	private String userName;
	public int getBlogCommentid() {
		return blogCommentid;
	}
	public void setBlogCommentid(int blogCommentid) {
		this.blogCommentid = blogCommentid;
	}
	public int getBlogId() {
		return blogId;
	}
	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}
	public String getBlogComment() {
		return blogComment;
	}
	public void setBlogComment(String blogComment) {
		this.blogComment = blogComment;
	}
	public Date getBlogCommentdate() {
		return blogCommentdate;
	}
	public void setBlogCommentdate(Date blogCommentdate) {
		this.blogCommentdate = blogCommentdate;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	

}
