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
@Table(name="APP_USER_FOLLOW")
@DynamicInsert(value=true)
@DynamicUpdate(value=true)
@SequenceGenerator(name="SEQ_FOLLOW_ID",sequenceName="SEQ_FOLLOW_ID",allocationSize=1)
public class UserFollow {

	@Id
	@Column(name="FOLLOW_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_FOLLOW_ID")
	private int id;
	
	@Column(name="USER_ID")
	private int userId;
	
	@Column(name="DEVICE_ID")
	private String deviceId;
	
	@Column(name="FOLLOW_TYPE")
	private String type;
	
	@Column(name="REF_OBJECT_ID")
	private int refObjId;
	
	@Column(name="REF_OBJECT_SUBID")
	private int refObjSubId;
	
	@Column(name="FOLLOW_TIME")
	@Temporal(TemporalType.TIME)
	private Date followTime;
	
	@Column(name="CANCEL_TIME")
	@Temporal(TemporalType.TIME)
	private Date cancelTime;
	
	@Column(name="STATUS")
	private int status;
	
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

	public int getRefObjSubId() {
		return refObjSubId;
	}

	public void setRefObjSubId(int refObjSubId) {
		this.refObjSubId = refObjSubId;
	}

	public Date getFollowTime() {
		return followTime;
	}

	public void setFollowTime(Date followTime) {
		this.followTime = followTime;
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
