package com.fanfan.manage.modle.admin;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


@Entity
@Table(name="APP_ADMIN_USER")
@DynamicInsert(true)
@DynamicUpdate(true)
@SequenceGenerator(name="SEQ_ADMIN_ID",sequenceName="SEQ_ADMIN_ID",allocationSize=1)
public class AdminUser {
	
	@Id
	@Column(name="ADMIN_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_ADMIN_ID")
	private int id;	
	
	@Column(name="LOGIN_NAME")
	private String name;
	
	@Column(name="PASSWORD")
	private String pwd;
	
	@Column(name="REAL_NAME")
	private String realName;
	
	@Column(name="DEPARTMENT")
	private String department;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="MOBILE_NO")
	private String mobileNo;
	
	@Column(name="STATUS")
	private int status ;
	
	@Column(name="CREATE_TIME",updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	@Column(name="UPDATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;
	
	@Column(name="LASTEST_LOGIN_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastTime;
	
	@ManyToMany
    @JoinTable(name="APP_ADMIN_USER_ROLE",
    joinColumns=@JoinColumn(name="ADMIN_ID", referencedColumnName="ADMIN_ID"),
    inverseJoinColumns=@JoinColumn(name="ADMIN_ROLE_ID", referencedColumnName="ADMIN_ROLE_ID"))
	private Set<AdminRole> roles = new HashSet<AdminRole>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
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

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Set<AdminRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<AdminRole> roles) {
		this.roles = roles;
	}
	
	
	
}
