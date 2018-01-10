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
import com.maven.socialappbackend.dao.userdao;
import com.maven.socialappbackend.model.blog;
import com.maven.socialappbackend.model.job;
import com.maven.socialappbackend.model.userdetail;
@RestController
public class JobController {

	@Autowired
   jobdao jobDAO;
	@Autowired
   userdao userDAO;
	@PostMapping(value="/addjob")
    public ResponseEntity<job> saveblog(@RequestBody job b)
    {
    //	List<userdetail> l=new ArrayList<userdetail>();
      //  l.add(u);
       // b.setUd(l);
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
    
    
    @GetMapping(value="/getAllJobsapply/{userId}")
   	public ResponseEntity<List<job>> getAllBlogsapply(@PathVariable("userId") int userId)
   	{
   		userdetail u=userDAO.getuserbyid(userId);
   		List listjobs = jobDAO.getalljobsapply(u);
   	    
   	    	System.out.println("check list apply job "+listjobs);
   		return new ResponseEntity<List<job>>(listjobs,HttpStatus.OK);
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
 
@GetMapping(value="/applyjob/{jobId}/{userId}")
	public ResponseEntity<job> approvejob(@PathVariable("jobId") int jobId,@PathVariable("userId") int userId)
	{
	
	job b=jobDAO.getjobbyid(jobId);
	userdetail ud=userDAO.getuserbyid(userId);
	//b.getUd().add(ud);
	
	ud.getJ().add(b);
	
	System.out.println("ssss"+ud.getJ());
	for(job j:ud.getJ())
	{
		
		System.out.println("jjjj"+j.getJobName());
	}
	
	//userDAO.updateuser(ud);
		if(userDAO.updateuser(ud))
		 {
			
		   System.out.println(b);
		    
	        return new ResponseEntity<job>(b,HttpStatus.OK);
	      }
		 else
	      {
		   return new ResponseEntity<job>(b,HttpStatus.INTERNAL_SERVER_ERROR);	
	      }	 	
	      
	}
	
	    

}
