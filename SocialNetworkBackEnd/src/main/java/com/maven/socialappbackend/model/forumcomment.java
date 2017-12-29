package com.maven.socialappbackend.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table
public class forumcomment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int forumCommentid;
	private int forumId;
	private String forumComment;
	private Date forumCommentdate;
	private int userId;
	private String userName;
	public int getForumCommentid() {
		return forumCommentid;
	}
	public void setForumCommentid(int forumCommentid) {
		this.forumCommentid = forumCommentid;
	}
	public int getForumId() {
		return forumId;
	}
	public void setForumId(int forumId) {
		this.forumId = forumId;
	}
	public String getForumComment() {
		return forumComment;
	}
	public void setForumComment(String forumComment) {
		this.forumComment = forumComment;
	}
	public Date getForumCommentdate() {
		return forumCommentdate;
	}
	public void setForumCommentdate(Date forumCommentdate) {
		this.forumCommentdate = forumCommentdate;
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
