package com.maven.socialappbackend.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class forum {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private int forumId;
private String forumName;
private String forumContent;
@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
@JoinColumn(name="userId")
private userdetail user;
private Date createDate;
private String status;
private String forumComment;

public userdetail getUser() {
	return user;
}
public void setUser(userdetail user) {
	this.user = user;
}
public String getForumComment() {
	return forumComment;
}
public void setForumComment(String forumComment) {
	this.forumComment = forumComment;
}
public int getForumId() {
	return forumId;
}
public void setForumId(int forumId) {
	this.forumId = forumId;
}
public String getForumName() {
	return forumName;
}
public void setForumName(String forumName) {
	this.forumName = forumName;
}
public String getForumContent() {
	return forumContent;
}
public void setForumContent(String forumContent) {
	this.forumContent = forumContent;
}

public Date getCreateDate() {
	return createDate;
}
public void setCreateDate(Date createDate) {
	this.createDate = createDate;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}

}
