package com.maven.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> saveblog(@RequestBody forum b)
    {
      if(forumDAO.addforum(b))
      {
	    System.out.println(b);
        return new ResponseEntity<String>("Forum added",HttpStatus.OK);
      }
      else
      {
	   return new ResponseEntity<String>(" error Forum added",HttpStatus.INTERNAL_SERVER_ERROR);	
      }
    }
}
