package com.fanfan.manage.modle.client;

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
@Table(name="APP_CLIENT_BOOK_RECOMMEND")
@DynamicInsert(value=true)
@DynamicUpdate(value=true)
@SequenceGenerator(name="SEQ_RECOMMEND_ID",sequenceName="SEQ_RECOMMEND_ID",allocationSize=1)
public class Recommend {
	
	@Id
    @Column(name = "RECOMMEND_ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_RECOMMEND_ID")
	private Integer id;
	
	@Column(name="RECOMMEND_KIND")
	private Integer kind;
	
	@Column(name="BOOK_ID")
	private Integer bookId;
	
	@Column(name="PLATFORM_ID")
	private Integer platformId;
	
	@Column(name="BOOK_TYPE")
	private Integer bookType;
	
	@Column(name="DISP_NAME")
	private String dispName;
	
	@Column(name="DISP_TAGS")
	private String dispTags;
	
	
	@Column(name="DISP_ORDER")
	private Integer dispOrder;
	
	@Column(name="STATUS")
	private Integer status;
		
	@Column(name="CREATE_TIME",updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	@Column(name="update_user")
	private String updateUser;
	
	@Column(name="update_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getKind() {
		return kind;
	}

	public void setKind(Integer kind) {
		this.kind = kind;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public Integer getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Integer platformId) {
		this.platformId = platformId;
	}

	public Integer getBookType() {
		return bookType;
	}

	public void setBookType(Integer bookType) {
		this.bookType = bookType;
	}

	public String getDispName() {
		return dispName;
	}

	public void setDispName(String dispName) {
		this.dispName = dispName;
	}

	public String getDispTags() {
		return dispTags;
	}

	public void setDispTags(String dispTags) {
		this.dispTags = dispTags;
	}

	public Integer getDispOrder() {
		return dispOrder;
	}

	public void setDispOrder(Integer dispOrder) {
		this.dispOrder = dispOrder;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
