package com.fanfan.manage.modle.base.vote;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fanfan.manage.modle.base.PlatForm;

@Entity
@Table(name="APP_BASE_VOTE_PLAN")
@DynamicInsert(true)
@DynamicUpdate(true)
@SequenceGenerator(name="SEQ_VOTE_PLAN_ID",sequenceName="SEQ_VOTE_PLAN_ID",allocationSize=1)
public class VotePlan {
	
	@Id
	@Column(name="PLAN_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_VOTE_PLAN_ID")
	private int id ;	
	
	@ManyToOne
	@JoinColumn(name="VOTE_ID")
	private Vote vote;
	
	@ManyToOne
	@JoinColumn(name="PLATFORM_ID")
	private PlatForm platForm ;
	
	@Column(name="REF_TYPE")
	private String refType;
	
	@Column(name="REF_OBJECTID")
	private int refObjId;
	
	@Column(name="REF_SUBID")
	private int refSubId;
	
	@Column(name="STATUS")
	private int status;
	
	@Column(name="OPEN_TIME")
	@Temporal(TemporalType.DATE)
	private Date openTime;
	
	@Column(name="CLOSE_TIME")
	@Temporal(TemporalType.DATE)
	private Date closeTime;
	
	
	@Column(name="CREATE_TIME",updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	@Column(name="UPDATE_USER")
	private String updateUser;
	
	@Column(name="UPDATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Vote getVote() {
		return vote;
	}

	public void setVote(Vote vote) {
		this.vote = vote;
	}

	public PlatForm getPlatForm() {
		return platForm;
	}

	public void setPlatForm(PlatForm platForm) {
		this.platForm = platForm;
	}

	public String getRefType() {
		return refType;
	}

	public void setRefType(String refType) {
		this.refType = refType;
	}

	public int getRefObjId() {
		return refObjId;
	}

	public void setRefObjId(int refObjId) {
		this.refObjId = refObjId;
	}

	public int getRefSubId() {
		return refSubId;
	}

	public void setRefSubId(int refSubId) {
		this.refSubId = refSubId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public Date getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
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
