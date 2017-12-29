package com.maven.socialappbackend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class friend {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private int friendId;
private int userId;
private String status;
public int getFriendId() {
	return friendId;
}
public void setFriendId(int friendId) {
	this.friendId = friendId;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}

}
