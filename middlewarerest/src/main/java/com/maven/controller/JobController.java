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

import com.maven.socialappbackend.dao.jobdao;
import com.maven.socialappbackend.model.blog;
import com.maven.socialappbackend.model.job;
import com.maven.socialappbackend.model.userdetail;
@RestController
public class JobController {

	@Autowired
   jobdao jobDAO;
   
	@PostMapping(value="/addjob")
    public ResponseEntity<job> saveblog(@RequestBody job b,userdetail u)
    {
    	List<userdetail> l=new ArrayList<userdetail>();
        l.add(u);
        b.setUd(l);
        if(jobDAO.addjob(b))
      {
	    System.out.println(b);
        return new ResponseEntity<job>(b,HttpStatus.OK);
      }
      else
      {
	   return new ResponseEntity<job>(b,HttpStatus.INTERNAL_SERVER_ERROR);	
      }
    }
    
    
    @GetMapping(value="/getAllJobs")
	public ResponseEntity<ArrayList<job>> getAllBlogs()
	{
		//System.out.println("obj**"+blogDAO);
		ArrayList listjobs = (ArrayList) jobDAO.getalljobs();
	    
	    	System.out.println("check list %%%%%%%%%%%%%%%%%%%"+listjobs);
		return new ResponseEntity<ArrayList<job>>(listjobs,HttpStatus.OK);
	}
    @PostMapping(value="/editjob")
	public  ResponseEntity<job> updateBlog(@RequestBody job b)
	{
		//System.out.println("impl"+b.getBlogName());
		//System.out.println("impl  "+b.getBlogId());
		
		if(jobDAO.updatejob(b))
		 {
		    System.out.println(b);
		    
	        return new ResponseEntity<job>(b,HttpStatus.OK);
	      }
		 else
	      {
		   return new ResponseEntity<job>(b,HttpStatus.INTERNAL_SERVER_ERROR);	
	      }
	}
	@PostMapping(value ="/deletejob")
	public ResponseEntity<job> deleteBlog(@RequestBody job b)
	{
		if(jobDAO.deletejob(b))
		{
			return new ResponseEntity<job>(b,HttpStatus.OK);
		}
		else{
			return new ResponseEntity<job>(b,HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
 
    
}
