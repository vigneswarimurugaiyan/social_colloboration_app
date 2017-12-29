package com.maven.socialappbackend.dao;

import java.util.List;

import com.maven.socialappbackend.model.friend;

public interface frienddao {
	public boolean addfriend(friend f);
	public boolean updatefriend(friend f);
	public boolean deletefriend(friend f);
	public friend getfriendbyid(int friendId);
	public List<friend> getallfriends();
}
