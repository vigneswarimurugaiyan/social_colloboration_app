package com.maven.socialappbackend.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class forum {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private int forumId;
private String forumName;
private String forumContent;
private int userId;
private Date createDate;
private String status;
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
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
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
