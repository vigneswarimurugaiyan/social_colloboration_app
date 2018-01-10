package com.maven.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maven.socialappbackend.dao.frienddao;
import com.maven.socialappbackend.dao.userdao;
import com.maven.socialappbackend.model.friend;
import com.maven.socialappbackend.model.job;
import com.maven.socialappbackend.model.userdetail;
@RestController
public class Friend_Controller {
	@Autowired
    frienddao friendDAO;
	@Autowired
	userdao userDAO;
	@PostMapping(value="/addfriend")
    public ResponseEntity<friend> savefriend(@RequestBody friend f)
    {
    	
    	f.setStatus("R");
    	
      if(friendDAO.addfriend(f))
      {
	    System.out.println(f);
	    
        return new ResponseEntity<friend>(f,HttpStatus.OK);
      }
      else
      {
	   return new ResponseEntity<friend>(f,HttpStatus.INTERNAL_SERVER_ERROR);	
      }
    }
	
	@GetMapping(value="/getallfriends/{userName}")
	public ResponseEntity<List<friend>> getAllfriends(@PathVariable("userName") String userName)
	{
		System.out.println("obj**"+friendDAO);
		List<friend> listfriends = (ArrayList) friendDAO.getallfriends(userName);
		return new ResponseEntity<List<friend>>(listfriends,HttpStatus.OK);
	}
	@GetMapping(value="/getapprovefriends/{userName}")
	public ResponseEntity<List<friend>> getapprovefriends(@PathVariable("userName") String userName)
	{
		System.out.println("obj**"+friendDAO);
		List<friend> listfriends = (ArrayList) friendDAO.getapprovedfriends(userName);
		return new ResponseEntity<List<friend>>(listfriends,HttpStatus.OK);
	}
	@GetMapping(value="/getapprove/{friendId}")
	public ResponseEntity<friend> getapprove(@PathVariable("friendId") int friendId)
	{
		//System.out.println("obj**"+friendDAO);
		friend f=friendDAO.getfriendbyid(friendId);
		f.setStatus("A");
		if(friendDAO.updatefriend(f))
		{
		return new ResponseEntity<friend>(f,HttpStatus.OK);
	    }
		else
		{
			return new ResponseEntity<friend>(f,HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
		
	
		@GetMapping(value="/getfriend/{friendid}")
		public ResponseEntity<friend> acceptfriend(@PathVariable("friendid") int friendid)
		{
			friend b=friendDAO.getfriendbyid(friendid);
			if(b!=null)
			 {
			    System.out.println(b);
			    
		        return new ResponseEntity<friend>(b,HttpStatus.OK);
		      }
			 else
		      {
			   return new ResponseEntity<friend>(b,HttpStatus.INTERNAL_SERVER_ERROR);	
		      }
		    
	}
		@GetMapping(value="/getAllusers/{userId}")
		public ResponseEntity<List> getalluser(@PathVariable("userId") int userId)
		{
			userdetail u=userDAO.getuserbyid(userId);
			System.out.println("obj**"+friendDAO);
			List listfriends = (ArrayList) userDAO.getalluser1(u);
			return new ResponseEntity<List>(listfriends,HttpStatus.OK);
		}
		
		
		
		
		@GetMapping(value="/deletefriend/{friendId}")
		public ResponseEntity<friend> deletefriend(@PathVariable("friendId") int friendId)
		{
			//System.out.println("obj**"+friendDAO);
			friend f=friendDAO.getfriendbyid(friendId);
			f.setStatus("R");
			if(friendDAO.deletefriend(f))
			{
			return new ResponseEntity<friend>(f,HttpStatus.OK);
		    }
			else
			{
				return new ResponseEntity<friend>(f,HttpStatus.INTERNAL_SERVER_ERROR);	
			}
		}
		
		
		
		
		}
