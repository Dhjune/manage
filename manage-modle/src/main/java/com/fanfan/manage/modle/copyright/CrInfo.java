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
@Table(name="APP_COPYRIGHT_INFO")
@DynamicInsert(true)
@DynamicUpdate(true)
@SequenceGenerator(name="SEQ_COPYRIGHT_INFO_ID",sequenceName="SEQ_COPYRIGHT_INFO_ID",allocationSize=1)
public class CrInfo {
	
	@Id
	@Column(name="INFO_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_COPYRIGHT_INFO_ID")
	private int  id ;
	
	@Column(name="BOOK_ID")
	private int  bookId ;
	
	@Column(name="INFO_TYPE")
	private String type;
	
	//版权期限
	@Column(name="DEADLINE")
	private String deadline;
	
	//版权性质
	@Column(name="CR_NATURE")
	private String nature;
	
	//范围
	@Column(name="CR_SCOPE")
	private String scope;
	
	//备注
	@Column(name="REMARKS")
	private String remarks;
	//情况
	@Column(name="CR_SITUATION")
	private String situation ;
	
	//合同编号
	@Column(name="CONTRACT_NO")
	private String contractNo;
	
	@Column(name="STATUS")
	private int status ;
	
	@Column(name="CREATE_TIME",updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	@Column(name="UPDATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime ;

	@Column(name="UPDATE_USER")
	private String udpateUser;
	
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

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getSituation() {
		return situation;
	}

	public void setSituation(String situation) {
		this.situation = situation;
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

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getUdpateUser() {
		return udpateUser;
	}

	public void setUdpateUser(String udpateUser) {
		this.udpateUser = udpateUser;
	}
	
	
}
