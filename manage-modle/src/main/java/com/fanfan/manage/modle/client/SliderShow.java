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
@Table(name="APP_client_slidershow")
@DynamicInsert(value=true)
@DynamicUpdate(value=true)
@SequenceGenerator(name="SEQ_SHOW_ID",sequenceName="SEQ_SHOW_ID",allocationSize=1)
public class SliderShow {
	
	@Id
    @Column(name = "SHOW_ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_SHOW_ID")
	private Integer id;
	
	
	@Column(name="PLATFORM_ID")
	private Integer platformId;
	
	@Column(name="SHOW_NAME")
	private String showName;
		
	@Column(name="SHOW_KIND")
	private Integer showKind;   // 1入口2首页   
	
	@Column(name="SHOW_TYPE")
	private Integer showType;   //0无 1-活动信息公告  2-漫画作品推广 3-点卷兑换推荐 4-APP推广 
	
	@Column(name="IMAGE_URL")
	private String imageUrl;
	
	@Column(name="SHOW_URL")
	private String showUrl;
	
	@Column(name="REF_ID")
	private Integer refId;
	
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

	public Integer getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Integer platformId) {
		this.platformId = platformId;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public Integer getShowKind() {
		return showKind;
	}

	public void setShowKind(Integer showKind) {
		this.showKind = showKind;
	}

	public Integer getShowType() {
		return showType;
	}

	public void setShowType(Integer showType) {
		this.showType = showType;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getShowUrl() {
		return showUrl;
	}

	public void setShowUrl(String showUrl) {
		this.showUrl = showUrl;
	}

	public Integer getRefId() {
		return refId;
	}

	public void setRefId(Integer refId) {
		this.refId = refId;
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

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
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
	
	
}
