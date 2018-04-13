package com.gts.entity;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gts.util.jsondateserializer;

@NamedNativeQueries({
	@NamedNativeQuery(
			name="callgtslogincheck",
			query="CALL gtslogincheck(:user_name,:pass_word)",resultClass=users.class),
})

@NamedStoredProcedureQuery(
		name = "updatelastlogintime",
		procedureName = "updatelastlogintime",
		resultClasses={gts_useraccount.class},
		parameters={
				@StoredProcedureParameter(mode=ParameterMode.IN,type=Integer.class,name="user_id"),
				@StoredProcedureParameter(mode=ParameterMode.IN,type=Date.class,name="last_successfull_login_time"),
				@StoredProcedureParameter(mode=ParameterMode.IN,type=String.class,name="userloginstatus"),
				@StoredProcedureParameter(mode=ParameterMode.IN,type=String.class,name="userstatus"),
				
		})





@Entity
@Table
public class users {
private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private int user_id;
@Column
private String user_name;
@Column
private String user_pwd;
@Column
private Date user_lastsuccessfulllogin;
@Column
private int user_tokenid;
@Column
private String user_loginstatus;
@Column 
private String user_status;
@JsonSerialize(using=jsondateserializer.class)
public Date getUser_lastsuccessfulllogin() {
	return user_lastsuccessfulllogin;
}
public void setUser_lastsuccessfulllogin(Date user_lastsuccessfulllogin) {
	this.user_lastsuccessfulllogin = user_lastsuccessfulllogin;
}
public int getUser_id() {
	return user_id;
}
public void setUser_id(int user_id) {
	this.user_id = user_id;
}
public String getUser_name() {
	return user_name;
}
public void setUser_name(String user_name) {
	this.user_name = user_name;
}
public String getUser_pwd() {
	return user_pwd;
}
public void setUser_pwd(String user_pwd) {
	this.user_pwd = user_pwd;
}

public int getUser_tokenid() {
	return user_tokenid;
}
public void setUser_tokenid(int user_tokenid) {
	this.user_tokenid = user_tokenid;
}
public String getUser_loginstatus() {
	return user_loginstatus;
}
public void setUser_loginstatus(String user_loginstatus) {
	this.user_loginstatus = user_loginstatus;
}
public String getUser_status() {
	return user_status;
}
public void setUser_status(String user_status) {
	this.user_status = user_status;
}
}