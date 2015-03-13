package com.fanfan.manage.modle.admin;

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
@Table(name="APP_ADMIN_LOG")
@DynamicInsert(true)
@DynamicUpdate(true)
@SequenceGenerator(name="SEQ_ADMIN_LOG_ID",sequenceName="SEQ_ADMIN_LOG_ID",allocationSize=1)
public class AdminLog {
	
	@Id
	@Column(name="ADMIN_LOG_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_ADMIN_LOG_ID")
	private int id;	
	
	@Column(name="ADMIN_ID")
	private int adminId;
	
	@Column(name="LOG_TYPE")
	private int logType;
	
	@Column(name="LOG_LEVEL")
	private int logLevel;
	
	@Column(name="LOG_TABLE")
	private String logTable;
	
	@Column(name="ROW_ID")
	private String rowId;
	
	@Column(name="LOG_INFO")
	private String logInfo;
	
	@Column(name="ADDITIONAL_INFO")
	private String additionalInfo;
	
	@Column(name="IP_ADDR")
	private String ipAddr;
	
	@Column(name="CREATE_TIME",updatable=false)
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

	public int getLogType() {
		return logType;
	}

	public void setLogType(int logType) {
		this.logType = logType;
	}

	public int getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(int logLevel) {
		this.logLevel = logLevel;
	}

	public String getLogTable() {
		return logTable;
	}

	public void setLogTable(String logTable) {
		this.logTable = logTable;
	}

	public String getRowId() {
		return rowId;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

	public String getLogInfo() {
		return logInfo;
	}

	public void setLogInfo(String logInfo) {
		this.logInfo = logInfo;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
	
	
}
