package com.maven.socialappbackend.dao;

import java.util.List;

import com.maven.socialappbackend.model.job;
import com.maven.socialappbackend.model.userdetail;

public interface jobdao {
	public boolean addjob(job b);
	public boolean updatejob(job b);
	public boolean deletejob(job b);
	public job getjobbyid(int jobId);
	public List<job> getalljobs();
	public List<job> getalljobsapply(userdetail u);
}
