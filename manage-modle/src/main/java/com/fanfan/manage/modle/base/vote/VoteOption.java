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

@Entity
@Table(name="APP_BASE_VOTE_OPTION")
@DynamicInsert(true)
@DynamicUpdate(true)
@SequenceGenerator(name="SEQ_VOTE_OPTION_ID",sequenceName="SEQ_VOTE_OPTION_ID",allocationSize=1)
public class VoteOption {

	@Id
	@Column(name="OPTION_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_VOTE_OPTION_ID")
	private int id ;
	
	@ManyToOne
	@JoinColumn(name="VOTE_ID")
	private Vote vote;
	
	@Column(name="OPTION_SUBJECT")
	private String subject;
	
	@Column(name="OPTION_KEY")
	private String key;
	
	@Column(name="DISP_ORDER")
	private int dispOrder;
	
	@Column(name="STATUS")
	private int status ;
	
	@Column(name="CREATE_TIME",updatable=false)
	@Temporal(TemporalType.TIME)
	private Date createTime;
	
	@Column(name="UPDATE_USER")
	private String updateUser;
	
	@Column(name="UPDATE_TIME")
	@Temporal(TemporalType.TIME)
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

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getDispOrder() {
		return dispOrder;
	}

	public void setDispOrder(int dispOrder) {
		this.dispOrder = dispOrder;
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
