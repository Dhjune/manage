package com.fanfan.manage.modle.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name="APP_BASE_DEVICE")
@DynamicInsert(true)
public class Device {
	
	@Id
	@Column(name="DEVICE_KEY")
	private String key;
	
	@Column(name="DEVICE_NAME")
	private String name;
	
	@Column(name="DEVICE_OS")
	private String os;
	
	@Column(name="OS_VERSION")
	private String osVersion;
	
	@Column(name="MEMORY_SIZE")
	private int memorySize;
	
	@Column(name="HAS_CRACK")
	private int hasCrack;
	
	@Column(name="SCREEN_WIDTH")
	private int screenWidth;
	
	@Column(name="SCREEN_HEIGHT")
	private int screenHeight;
	
	@Column(name="CREATE_TIME",updatable=false)
	@Temporal(TemporalType.TIME)
	private Date createTime;

	
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public int getMemorySize() {
		return memorySize;
	}

	public void setMemorySize(int memorySize) {
		this.memorySize = memorySize;
	}

	public int getHasCrack() {
		return hasCrack;
	}

	public void setHasCrack(int hasCrack) {
		this.hasCrack = hasCrack;
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
