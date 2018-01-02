package com.maven.controller;

import java.util.ArrayList;

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
	@GetMapping(value="/getAllBlogs")
	public ResponseEntity<ArrayList<blog>> getAllBlogs()
	{
		ArrayList listBlogs = (ArrayList) blogDAO.getallblogs();
	    //for(blog b:listBlogs)
	   // {
	    	System.out.println("check list"+listBlogs);
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
	@PostMapping(value="/updateBlog")
	public  ResponseEntity<String> updateBlog(@RequestBody blog blog)
	{
		blog tempBlog=blogDAO.getblogbyid(blog.getBlogId());	
		tempBlog.setBlogName(blog.getBlogName());
		tempBlog.setBlogContent(blog.getBlogContent());
		if(blogDAO.updateblog(tempBlog))
		{
			return new ResponseEntity<String>("Blog Update",HttpStatus.OK);
			
		}
		else
		{
			return new ResponseEntity<String>("Error in Blog updation",HttpStatus.SERVICE_UNAVAILABLE);
			
		}
	}
	@PostMapping(value = "deleteBlog")
	public ResponseEntity<String> deleteBlog(@RequestBody blog blog)
	{
		blog tempblog=blogDAO.getblogbyid(blog.getBlogId());
		if(blogDAO.deleteblog(tempblog))
		{
			return new ResponseEntity<String>("Blog Deleted",HttpStatus.OK);
		}
		else{
			return new ResponseEntity<String>("Error in blog deletion",HttpStatus.SERVICE_UNAVAILABLE);
		}
	}


}

	

