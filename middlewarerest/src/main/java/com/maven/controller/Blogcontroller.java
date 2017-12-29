package com.maven.controller;

import java.util.ArrayList;

//import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maven.socialappbackend.dao.blogdao;
import com.maven.socialappbackend.model.blog;
@RestController
public class Blogcontroller {

	@Autowired
    blogdao blogDAO;
   
	@PostMapping(value="/addBlog")
    public ResponseEntity<String> saveblog(@RequestBody blog b)
    {
      if(blogDAO.addblog(b))
      {
	    System.out.println(b);
	    
        return new ResponseEntity<String>("blog added",HttpStatus.OK);
      }
      else
      {
	   return new ResponseEntity<String>(" error blog added",HttpStatus.INTERNAL_SERVER_ERROR);	
      }
    }
	@PostMapping(value="/getAllBlogs")
	public ResponseEntity<ArrayList<blog>> getAllBlogs()
	{
		ArrayList listBlogs = (ArrayList)blogDAO.getallblogs();
		return new ResponseEntity<ArrayList<blog>>(listBlogs,HttpStatus.OK);
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

	

