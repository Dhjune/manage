package com.fanfan.manage.modle.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="APP_USER_INFO",uniqueConstraints={@UniqueConstraint(columnNames={"USER_ID","PLATFORM_ID"})})
@DynamicInsert(value=true)
@DynamicUpdate(value=true)
public class UserInfo {
	
	@EmbeddedId
	private UserInfoUK uk;
	
	@Column(name="SCORE")
	private int score;
	
	@Column(name="USED_SCORE")
	private int usedScore;
	
	@Column(name="ALL_SCORE")
	private int allScore;
	
	@Column(name="TICKET")
	private int ticket;
	
	@Column(name="USED_TICKET")
	private int usedTicket;
	
	@Column(name="ALL_TICKET")
	private int allTicket;
	
	@Column(name="LOGIN_TIME")
	private int loginTime;
	
	@Column(name="LASTED_LOGIN_TIME")
	@Temporal(TemporalType.TIME)
	private Date lastLogin;
	
	@Column(name="PWD_ERROR_COUNT")	
	private int pwdErrorCount;
	
	@Column(name="LASTED_SIGN_TIME")
	@Temporal(TemporalType.TIME)
	private Date lastSignTime;
	
	@Column(name="LASTED_SIGN_DAYS")
	private int lastSignDays;
	
	
	@Column(name="UPDATE_TIME")
	@Temporal(TemporalType.TIME)
	private Date updateTime;
	

	public UserInfoUK getUk() {
		return uk;
	}

	public void setUk(UserInfoUK uk) {
		this.uk = uk;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getUsedScore() {
		return usedScore;
	}

	public void setUsedScore(int usedScore) {
		this.usedScore = usedScore;
	}

	public int getAllScore() {
		return allScore;
	}

	public void setAllScore(int allScore) {
		this.allScore = allScore;
	}

	public int getTicket() {
		return ticket;
	}

	public void setTicket(int ticket) {
		this.ticket = ticket;
	}

	public int getUsedTicket() {
		return usedTicket;
	}

	public void setUsedTicket(int usedTicket) {
		this.usedTicket = usedTicket;
	}

	public int getAllTicket() {
		return allTicket;
	}

	public void setAllTicket(int allTicket) {
		this.allTicket = allTicket;
	}

	public int getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(int loginTime) {
		this.loginTime = loginTime;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public int getPwdErrorCount() {
		return pwdErrorCount;
	}

	public void setPwdErrorCount(int pwdErrorCount) {
		this.pwdErrorCount = pwdErrorCount;
	}

	public Date getLastSignTime() {
		return lastSignTime;
	}

	public void setLastSignTime(Date lastSignTime) {
		this.lastSignTime = lastSignTime;
	}

	public int getLastSignDays() {
		return lastSignDays;
	}

	public void setLastSignDays(int lastSignDays) {
		this.lastSignDays = lastSignDays;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
			
}
