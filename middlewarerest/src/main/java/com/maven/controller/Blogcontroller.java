package com.maven.controller;

import java.util.ArrayList;
import java.util.List;

//import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maven.socialappbackend.dao.blogdao;
import com.maven.socialappbackend.model.blog;
@RestController
public class Blogcontroller {

	@Autowired
    blogdao blogDAO;
	@PostMapping(value="/addblog")
    public ResponseEntity<blog> saveblog(@RequestBody blog b)
    {
      if(blogDAO.addblog(b))
      {
	    System.out.println(b);
	    
        return new ResponseEntity<blog>(b,HttpStatus.OK);
      }
      else
      {
	   return new ResponseEntity<blog>(b,HttpStatus.INTERNAL_SERVER_ERROR);	
      }
    }
	
	@GetMapping(value="/getAllBlogs/{userId}")
	public ResponseEntity<ArrayList<blog>> getAllBlogs(@PathVariable("userId") int userId)
	{
		System.out.println("obj**"+blogDAO);
		ArrayList listBlogs = (ArrayList) blogDAO.getallblogs1(userId);
	    //for(blog b:listBlogs)
	   // {
	    	System.out.println("check list %%%%%%%%%%%%%%%%%%%"+listBlogs);
	    //}
		return new ResponseEntity<ArrayList<blog>>(listBlogs,HttpStatus.OK);
	}
	
	@GetMapping(value="/increaselike/{blogid}")
		public ResponseEntity<blog> increselike(@PathVariable("blogid") int blogid)
		{
			blog b=blogDAO.getblogbyid(blogid);
			b.setLikes(b.getLikes()+1);
			if(blogDAO.updateblog(b))
			 {
			    System.out.println(b);
			    
		        return new ResponseEntity<blog>(b,HttpStatus.OK);
		      }
			 else
		      {
			   return new ResponseEntity<blog>(b,HttpStatus.INTERNAL_SERVER_ERROR);	
		      }
		    
	}
		@GetMapping(value="/acceptblog/{blogid}")
		public ResponseEntity<blog> acceptBlog(@PathVariable("blogid") int blogid)
		{
			blog b=blogDAO.getblogbyid(blogid);
			b.setStatus("A");
			if(blogDAO.approveblog(b))
			 {
			    System.out.println(b);
			    
		        return new ResponseEntity<blog>(b,HttpStatus.OK);
		      }
			 else
		      {
			   return new ResponseEntity<blog>(b,HttpStatus.INTERNAL_SERVER_ERROR);	
		      }
		    
	}
		@GetMapping(value="/rejectblog/{blogid}")
		public ResponseEntity<blog> rejectBlog(@PathVariable("blogid") int blogid)
		{
			blog b=blogDAO.getblogbyid(blogid);
			b.setStatus("NA");
			if(blogDAO.rejectblog(b))
			 {
			    System.out.println(b);
			    
		        return new ResponseEntity<blog>(b,HttpStatus.OK);
		      }
			 else
		      {
			   return new ResponseEntity<blog>(b,HttpStatus.INTERNAL_SERVER_ERROR);	
		      }
		    
	}
		
	@PostMapping(value="/edit")
	public  ResponseEntity<blog> updateBlog(@RequestBody blog b)
	{
		System.out.println("impl"+b.getBlogName());
		System.out.println("impl  "+b.getBlogId());
		
		if(blogDAO.updateblog(b))
		 {
		    System.out.println(b);
		    
	        return new ResponseEntity<blog>(b,HttpStatus.OK);
	      }
		 else
	      {
		   return new ResponseEntity<blog>(b,HttpStatus.INTERNAL_SERVER_ERROR);	
	      }
	}
	@PostMapping(value = "/delete")
	public ResponseEntity<blog> deleteBlog(@RequestBody blog b)
	{
		if(blogDAO.deleteblog(b))
		{
			return new ResponseEntity<blog>(b,HttpStatus.OK);
		}
		else{
			return new ResponseEntity<blog>(b,HttpStatus.SERVICE_UNAVAILABLE);
		}
	}


}

	

