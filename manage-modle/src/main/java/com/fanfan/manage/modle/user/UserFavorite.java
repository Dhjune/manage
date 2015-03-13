package com.fanfan.manage.modle.user;

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
@Table(name="APP_USER_FAVORITES")
@DynamicInsert(value=true)
@DynamicUpdate(value=true)
@SequenceGenerator(name="SEQ_FAVORITE_ID",sequenceName="SEQ_FAVORITE_ID",allocationSize=1)
public class UserFavorite {
	
	@Id
	@Column(name="FAVORITE_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_FAVORITE_ID")
	private int id;
	
	@Column(name="USER_ID")
	private int userId ;
	
	@Column(name="DEVICE_ID")
	private String deviceId;
	
	@Column(name="FAVORITE_TYPE")
	private String type;
	
	@Column(name="REF_OBJECT_ID")
	private int refObjId;
	
	@Column(name="SUBJECT")
	private String subject;
	
	@Column(name="FAVORITE_TIME")
	@Temporal(TemporalType.TIME)
	private Date  favoTime;
	
	@Column(name="CANCEL_TIME",updatable=false)
	@Temporal(TemporalType.TIME)
	private Date cancelTime;
	
	@Column(name="STATUS")
	private int status ;
	
	@Column(name="UPDATE_USER")
	private String updateUser;
	
	@Column(name="UPDATE_TIME")
	@Temporal(TemporalType.TIME)
	private Date updateTime;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getRefObjId() {
		return refObjId;
	}

	public void setRefObjId(int refObjId) {
		this.refObjId = refObjId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getFavoTime() {
		return favoTime;
	}

	public void setFavoTime(Date favoTime) {
		this.favoTime = favoTime;
	}

	public Date getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
