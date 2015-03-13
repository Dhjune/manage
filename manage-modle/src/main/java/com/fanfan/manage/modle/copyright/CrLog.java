package com.fanfan.manage.modle.copyright;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="APP_COPYRIGHT_LOG")
@DynamicInsert(true)
@DynamicUpdate(true)
@SequenceGenerator(name="SEQ_COPYRIGHT_LOG_ID",sequenceName="SEQ_COPYRIGHT_LOG_ID",allocationSize=1)
public class CrLog {
	
	@Id
	@Column(name="LOG_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_COPYRIGHT_LOG_ID")
	private int id ;
	
	@Column(name="ADMIN_ID")
	private int adminId;
	
	@Column(name="BOOK_ID")
	private int bookId;
	
	@Column(name="ADMIN_NAME")
	private String adminName;
	
	@Column(name="BEHAVIOUR")
	private String behaviour;
	
	@Column(name="LOG_INFO")
	private String logInfo;
		
	@Column(name="CREATE_TIME")
	private Date createTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getBehaviour() {
		return behaviour;
	}

	public void setBehaviour(String behaviour) {
		this.behaviour = behaviour;
	}

	public String getLogInfo() {
		return logInfo;
	}

	public void setLogInfo(String logInfo) {
		this.logInfo = logInfo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
	
}
