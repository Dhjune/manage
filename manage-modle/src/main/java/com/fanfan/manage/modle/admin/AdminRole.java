package com.fanfan.manage.modle.admin;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fanfan.manage.modle.book.Book;

@Entity
@Table(name="APP_ADMIN_ROLE")
@DynamicInsert(true)
@DynamicUpdate(true)
@SequenceGenerator(name="SEQ_ADMIN_ROLE_ID",sequenceName="SEQ_ADMIN_ROLE_ID",allocationSize=1)
public class AdminRole {
	
	@Id
	@Column(name="ADMIN_ROLE_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_ADMIN_ROLE_ID")
	private int id;	
	
	@Column(name="PARENT_ROLEID")
	private int pid;
	
	@Column(name="ROLE_NAME")
	private String name;
	
	@Column(name="STATUS")
	private int status;
	
	@Column(name="CREATE_TIME",updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	@Column(name="UPDATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;
	
	@ManyToMany(mappedBy="roles")
	private Set<AdminUser> users =  new HashSet<AdminUser>();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Set<AdminUser> getUsers() {
		return users;
	}

	public void setUsers(Set<AdminUser> users) {
		this.users = users;
	}
	
	
	
		
}
