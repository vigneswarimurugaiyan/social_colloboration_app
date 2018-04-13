package com.gts.entity;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToOne;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gts.util.jsondateserializer;
@NamedStoredProcedureQuery(
		name = "savetokeninfo",
		procedureName = "savetokeninfo",
		resultClasses={user_token.class},
		parameters={ 
				@StoredProcedureParameter(mode=ParameterMode.IN,type=Integer.class,name="myuserid"),
				@StoredProcedureParameter(mode=ParameterMode.IN,type=String.class,name="tokenvalue"),
				@StoredProcedureParameter(mode=ParameterMode.IN,type=Date.class,name="tokencreatedon"),
				@StoredProcedureParameter(mode=ParameterMode.IN,type=Date.class,name="tokenexpireon"),
				@StoredProcedureParameter(mode=ParameterMode.IN,type=String.class,name="tokenstatus"),
		})
@NamedNativeQueries({
	@NamedNativeQuery(
		name="callgettokeninfo",
			query="CALL gettokeninfo(:inuser_id,:inuser_tokenid)",resultClass=user_token.class),
})

//@NamedNativeQueries({
	//@NamedNativeQuery(
		//	name="gettokeninfolist",
			//query="CALL gettokeninfolist",resultClass=user_token.class)
//})
@Entity
@Table
public class user_token {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int user_tokenid;
	@OneToOne(targetEntity=users.class)
    @JoinColumn(name="user_id")
	private int user_id;
	@Column
	private String user_tokenvalue;
	@Column
	private Date token_createdon;
	@Column 
	private Date token_expireon;
	@Column
	private String token_status;
		public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	@JsonSerialize(using=jsondateserializer.class)
	public Date getToken_createdon() {
		return token_createdon;
	}
	public void setToken_createdon(Date token_createdon) {
		this.token_createdon = token_createdon;
	}
	@JsonSerialize(using=jsondateserializer.class)
	public Date getToken_expireon() {
		return token_expireon;
	}
	public void setToken_expireon(Date token_expireon) {
		this.token_expireon = token_expireon;
	}
	public int getUser_tokenid() {
		return user_tokenid;
	}
	public void setUser_tokenid(int user_tokenid) {
		this.user_tokenid = user_tokenid;
	}
	public String getUser_tokenvalue() {
		return user_tokenvalue;
	}
	public void setUser_tokenvalue(String user_tokenvalue) {
		this.user_tokenvalue = user_tokenvalue;
	}
	
	public String getToken_status() {
		return token_status;
	}
	public void setToken_status(String token_status) {
		this.token_status = token_status;
	}
	
	
}


