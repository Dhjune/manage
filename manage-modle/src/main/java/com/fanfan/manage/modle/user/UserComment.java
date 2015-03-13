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
@Table(name="APP_USER_COMMENT")
@DynamicInsert(value=true)
@DynamicUpdate(value=true)
@SequenceGenerator(name="SEQ_COMMENT_ID",sequenceName="SEQ_COMMENT_ID",allocationSize=1)
public class UserComment {
	
	@Id
	@Column(name="COMMENT_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_COMMENT_ID")
	private int id ;
	
	@Column(name="USER_ID")
	private int userId;
	
	@Column(name="NICK_NAME")
	private String nickName;
	
	@Column(name="CONTENT")
	private String content;
	
	@Column(name="COMMENT_TIME",updatable=false)
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date commentTime;
	
	@Column(name="COMMENT_KIND")
	private String commentKind;
	
	@Column(name="REF_OBJECT_ID")
	private int refObjId;
	
	@Column(name="REF_OBJECT_SUBID")
	private int refObjSubId;
	
	@Column(name="COMMENT_SOURCE")
	private String commentSourse;
	
	@Column(name="SOURCE_IPADDR")
	private String scourseIpAddr;
	
	@Column(name="DEVICE_ID")
	private String deviceId;
	
	@Column(name="ORG_COMMENT_ID")
	private int orgCommentId;
	
	@Column(name="PRAISE_NUM")
	private int praiseNum;
	
	@Column(name="TREAD_NUM")
	private int treadNum;
	
	@Column(name="HAS_MODIFY")
	private int hasModify;
	
	@Column(name="MODIFY_TIME")
	@Temporal(TemporalType.TIME)
	private Date modifyTime;
	
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

	public String getCommentKind() {
		return commentKind;
	}

	public void setCommentKind(String commentKind) {
		this.commentKind = commentKind;
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

	public String getCommentSourse() {
		return commentSourse;
	}

	public void setCommentSourse(String commentSourse) {
		this.commentSourse = commentSourse;
	}

	public String getScourseIpAddr() {
		return scourseIpAddr;
	}

	public void setScourseIpAddr(String scourseIpAddr) {
		this.scourseIpAddr = scourseIpAddr;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public int getOrgCommentId() {
		return orgCommentId;
	}

	public void setOrgCommentId(int orgCommentId) {
		this.orgCommentId = orgCommentId;
	}

	public int getPraiseNum() {
		return praiseNum;
	}

	public void setPraiseNum(int praiseNum) {
		this.praiseNum = praiseNum;
	}

	public int getTreadNum() {
		return treadNum;
	}

	public void setTreadNum(int treadNum) {
		this.treadNum = treadNum;
	}

	public int getHasModify() {
		return hasModify;
	}

	public void setHasModify(int hasModify) {
		this.hasModify = hasModify;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
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
