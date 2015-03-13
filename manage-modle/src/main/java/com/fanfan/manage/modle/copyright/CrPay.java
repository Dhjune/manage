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
@Table(name="APP_COPYRIGHT_PAY")
@DynamicInsert(true)
@DynamicUpdate(true)
@SequenceGenerator(name="SEQ_COPYRIGHT_PAY_ID",sequenceName="SEQ_COPYRIGHT_PAY_ID",allocationSize=1)
public class CrPay {
	
	@Id
	@Column(name="PAY_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_COPYRIGHT_PAY_ID")
	private int id ;
	
	@Column(name="BOOK_ID")
	private int bookId ;
	
	@Column(name="UPDATE_DATE")
	private String updateDate;
	
	@Column(name="UPDATE_NUMBER")
	private String updateNumber;
	
	@Column(name="REMARKS")
	private String remarks;
	
	@Column(name="EDITOR_NAME")
	private String editorName;
	//验收人
	@Column(name="ACCEPTER")
	private String accepter;
	
	@Column(name="PAY_PRICE")
	private String price;
	
	@Column(name="PAY_TOTAL")
	private String total;
	
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

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateNumber() {
		return updateNumber;
	}

	public void setUpdateNumber(String updateNumber) {
		
		this.updateNumber = updateNumber;
		
	}

	public String getRemarks() {
		
		return remarks;
		
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getEditorName() {
		return editorName;
	}

	public void setEditorName(String editorName) {
		this.editorName = editorName;
	}

	public String getAccepter() {
		return accepter;
	}

	public void setAccepter(String accepter) {
		this.accepter = accepter;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
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
