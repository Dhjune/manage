package com.fanfan.manage.modle.copyright;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


@Entity
@Table(name="APP_COPYRIGHT_BOOKFILE")
@DynamicInsert(true)
@DynamicUpdate(true)
@SequenceGenerator(name="SEQ_COPYRIGHT_BOOKFILE_ID",sequenceName="SEQ_COPYRIGHT_BOOKFILE_ID",allocationSize=1)
public class CrBookFile {
	
	@Id
	@Column(name="BOOKFILE_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_COPYRIGHT_BOOKFILE_ID")
	private int id ;
	
	@Column(name="BOOK_ID")
	private int bookId;
	
	@Column(name="FILE_TYPE")
	private String type;
	
	@Column(name="SAVE_PATH")
	private String savePath;
	
	@Column(name="STATUS")
	private int status ;
	
	@Column(name="CREATE_TIME",updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	@Column(name="UPDATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime ;

	@Column(name="UPDATE_USER")
	private String updateUser;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
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

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	
	
}
