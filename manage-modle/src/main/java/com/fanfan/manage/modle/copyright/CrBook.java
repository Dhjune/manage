package com.fanfan.manage.modle.copyright;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="APP_COPYRIGHT_BOOK")
@DynamicInsert(true)
@DynamicUpdate(true)
@SequenceGenerator(name="SEQ_COPYRIGHT_BOOK_ID",sequenceName="SEQ_COPYRIGHT_BOOK_ID",allocationSize=1)
public class CrBook {
	
	@Id
	@Column(name="BOOK_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_COPYRIGHT_BOOK_ID")
	private int id;
	
	@Column(name="AUTHOR_ID")
	private int authorId;
	
	@Column(name="PEN_NAME")
	private String penName;
	
	@Column(name="BOOK_TYPE")
	private String bookType;
	
	@Column(name="BOOK_NAME")
	private String bookName;
	
	@Column(name="COUNTRY")
	private String country;
	
	@Column(name="BOOK_LENGTH")
	private String length ;
	
	@Column(name="BOOK_COLOR")
	private String color;
	//创作时间
	@Column(name="PRODUCE_TIME")
	private String produceTime;
	
	@Column(name="COVER_IMAGEONE")
	private String coverImageOne;
	
	@Column(name="COVER_IMAGETWO")
	private String coverImageTwo;
	
	//更新状态
	@Column(name="UPDATE_STATUS")
	private String updateStatus;
	
	//FTP存储位置
	@Column(name="FTP_PATH")
	private String ftpPath;
	
	@Column(name="STATUS")
	private int status ;
	
	@Column(name="CREATE_TIME",updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	@Column(name="UPDATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime ;
	
	@Column(name="REMARKS")
	private String remarks;
	
	@Column(name="BRIEFINTRO")
	private String  briefIntro;
	
	@Column(name="UPDATE_USER")
	private String updateUser;
	
	/**
	@OneToMany
	@JoinColumn(name="BOOK_ID")
	private Set<CrInfo> infos = new HashSet<CrInfo>();
	*/
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getPenName() {
		return penName;
	}

	public void setPenName(String penName) {
		this.penName = penName;
	}

	public String getBookType() {
		return bookType;
	}

	public void setBookType(String book_type) {
		this.bookType = book_type;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getProduceTime() {
		return produceTime;
	}

	public void setProduceTime(String produceTime) {
		this.produceTime = produceTime;
	}

	public String getUpdateStatus() {
		return updateStatus;
	}

	public void setUpdateStatus(String updateStatus) {
		this.updateStatus = updateStatus;
	}

	public String getFtpPath() {
		return ftpPath;
	}

	public void setFtpPath(String ftpPath) {
		this.ftpPath = ftpPath;
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

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	

	public String getBriefIntro() {
		return briefIntro;
	}

	public void setBriefIntro(String briefIntro) {
		this.briefIntro = briefIntro;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getCoverImageTwo() {
		return coverImageTwo;
	}

	public void setCoverImageTwo(String covreImageTwo) {
		this.coverImageTwo = covreImageTwo;
	}

	public String getCoverImageOne() {
		return coverImageOne;
	}

	public void setCoverImageOne(String coverImageOne) {
		this.coverImageOne = coverImageOne;
	}
	/**
	public Set<CrInfo> getInfos() {
		return infos;
	}

	public void setInfos(Set<CrInfo> infos) {
		this.infos = infos;
	}
	*/
	
	
	
	
}
