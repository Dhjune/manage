package com.fanfan.manage.modle.base.vote;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="APP_BASE_VOTE_RESULT")
@DynamicUpdate(true)
@DynamicInsert(true)
public class VoteResult {
	
	@EmbeddedId
	private VoteResultPK pk;
	
	@Column(name="VOTE_NUM")
	private int voteNum;
	
	@Column(name="CREATE_TIME",updatable=false)
	@Temporal(TemporalType.TIME)
	private Date createTime;
	
	@Column(name="UPDATE_TIME")
	@Temporal(TemporalType.TIME)
	private Date updateTime;
	
	@Column(name="MODI_COUNT")
	private int modiCount;
	
	@Column(name="MODI_USER")
	private String updateUser;
	
	@Column(name="MODI_TIME")
	@Temporal(TemporalType.TIME)
	private Date modiTime;

	public VoteResultPK getPk() {
		return pk;
	}

	public void setPk(VoteResultPK pk) {
		this.pk = pk;
	}

	public int getVoteNum() {
		return voteNum;
	}

	public void setVoteNum(int voteNum) {
		this.voteNum = voteNum;
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

	public int getModiCount() {
		return modiCount;
	}

	public void setModiCount(int modiCount) {
		this.modiCount = modiCount;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getModiTime() {
		return modiTime;
	}

	public void setModiTime(Date modiTime) {
		this.modiTime = modiTime;
	}
	
	

}
