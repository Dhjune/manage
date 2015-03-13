package com.fanfan.manage.modle.base;

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
@Table(name = "APP_BASE_APPLICATION")
@DynamicInsert(value=true)
@DynamicUpdate(true)
@SequenceGenerator(name="SEQ_APP_ID",sequenceName="SEQ_APP_ID",allocationSize=1)
public class Application {
	
	@Id
    @Column(name = "APP_ID")   
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_APP_ID")
	private int id  ;
	
	@Column(name="PLATFORM_ID")
	private int platId ;
	
	@Column(name="APP_NAME")
	private String appName ;
	
	@Column(name="APP_KEY")
	private String appKey;
	
	@Column(name="APP_VERSION")
	private String appVersion;
	
	@Column(name="APP_FILESIZE")
	private int appFileSize;
	
	@Column(name="VERSION_TYPE")
	private int versionType;
	
	@Column(name="PUBLISH_TIME")
	@Temporal(TemporalType.DATE)
	private Date pubTime; //发布时间
	
	
	@Column(name="PUBLISH_PLATFORM")
	private String pubPlat;  //发布平台
	
	@Column(name="PUBLISH_ID")
	private String  pubId ;
	
	@Column(name="APP_URL")
	private String appUrl ;
	
	@Column(name="UPDATE_TYPE")
	private int updateType;
	
	@Column(name="CREATE_TIME",updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date CreateTime ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPlatId() {
		return platId;
	}

	public void setPlatId(int platId) {
		this.platId = platId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public int getAppFileSize() {
		return appFileSize;
	}

	public void setAppFileSize(int appFileSize) {
		this.appFileSize = appFileSize;
	}

	public int getVersionType() {
		return versionType;
	}

	public void setVersionType(int versionType) {
		this.versionType = versionType;
	}

	public Date getPubTime() {
		return pubTime;
	}

	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}

	public String getPubPlat() {
		return pubPlat;
	}

	public void setPubPlat(String pubPlat) {
		this.pubPlat = pubPlat;
	}

	public String getPubId() {
		return pubId;
	}

	public void setPubId(String pubId) {
		this.pubId = pubId;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public int getUpdateType() {
		return updateType;
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	public Date getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(Date createTime) {
		CreateTime = createTime;
	}
	

}
