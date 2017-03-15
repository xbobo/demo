package com.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "app_user_base")
public class UserBase implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	
	@Column(name = "user_account", nullable = false, unique = true)
	private String userAccount;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "salt", nullable = false)
	private String salt;
	
	@Column(name = "real_name", nullable = true)
	private String realName;

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name = "create_time", nullable = false)
	private Date createTime;

	@Column(name = "create_user", nullable = true)
	private String createUser;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "update_time", nullable = false)
	private Date updateTime;

	@Column(name = "update_user", nullable = true)
	private String updateUser;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

}
