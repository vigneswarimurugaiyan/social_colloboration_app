package com.maven.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.maven.socialappbackend.dao.forumdao;
import com.maven.socialappbackend.model.forum;

@RestController
public class ForumController {
	@Autowired
   forumdao forumDAO;
	@PostMapping(value="/addforum")
    public ResponseEntity<forum> saveforum(@RequestBody forum b)
    {
      if(forumDAO.addforum(b))
      {
	    System.out.println(b);
	    
        return new ResponseEntity<forum>(b,HttpStatus.OK);
      }
      else
      {
	   return new ResponseEntity<forum>(b,HttpStatus.INTERNAL_SERVER_ERROR);	
      }
    }
   
	
	@GetMapping(value="/getAllforums/{userId}")
	public ResponseEntity<ArrayList<forum>> getAllforums(@PathVariable("userId") int userId)
	{
		System.out.println("obj**"+forumDAO);
		ArrayList listforums = (ArrayList) forumDAO.getallforums1(userId);
	    //for(forum b:listforums)
	   // {
	    	System.out.println("check list %%%%%%%%%%%%%%%%%%%"+listforums);
	    //}
		return new ResponseEntity<ArrayList<forum>>(listforums,HttpStatus.OK);
	}
	
		@GetMapping(value="/acceptforum/{forumid}")
		public ResponseEntity<forum> acceptforum(@PathVariable("forumid") int forumid)
		{
			forum b=forumDAO.getforumbyid(forumid);
			b.setStatus("A");
			if(forumDAO.approveforum(b))
			 {
			    System.out.println(b);
			    
		        return new ResponseEntity<forum>(b,HttpStatus.OK);
		      }
			 else
		      {
			   return new ResponseEntity<forum>(b,HttpStatus.INTERNAL_SERVER_ERROR);	
		      }
		    
	}
		@GetMapping(value="/rejectforum/{forumid}")
		public ResponseEntity<forum> rejectforum(@PathVariable("forumid") int forumid)
		{
			forum b=forumDAO.getforumbyid(forumid);
			b.setStatus("NA");
			if(forumDAO.rejectforum(b))
			 {
			    System.out.println(b);
			    
		        return new ResponseEntity<forum>(b,HttpStatus.OK);
		      }
			 else
		      {
			   return new ResponseEntity<forum>(b,HttpStatus.INTERNAL_SERVER_ERROR);	
		      }
		    
	}
		
	@PostMapping(value="/editforum")
	public  ResponseEntity<forum> updateforum(@RequestBody forum b)
	{		
		if(forumDAO.updateforum(b))
		 {
		    System.out.println(b);
		    
	        return new ResponseEntity<forum>(b,HttpStatus.OK);
	      }
		 else
	      {
		   return new ResponseEntity<forum>(b,HttpStatus.INTERNAL_SERVER_ERROR);	
	      }
	}
	@PostMapping(value ="/deleteforum")
	public ResponseEntity<forum> deleteforum(@RequestBody forum b)
	{
		if(forumDAO.deleteforum(b))
		{
			return new ResponseEntity<forum>(b,HttpStatus.OK);
		}
		else{
			return new ResponseEntity<forum>(b,HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

}
