package com.fanfan.manage.modle.user;

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
@Table(name="APP_USER_SCORE_HIS")
@DynamicInsert(value=true)
@DynamicUpdate(value=true)
@SequenceGenerator(name="SEQ_SCORE_LOG_ID",sequenceName="SEQ_SCORE_LOG_ID",allocationSize=1)
public class ScoreHistory {

	@Id
    @Column(name = "SCORE_LOG_ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_SCORE_LOG_ID")
	private String id ;
	
	@Column(name="USER_ID")
	private int  userId ;
	
	@Column(name="SCORE_TYPE")
	private int scoreType; 
	
	@Column(name="SOURCE_TYPE")
	private String scourceType ;
	
	@Column(name="SOURE_ID")
	private String sourceId;
	
	@Column(name="SUBJECT")
	private String subject;
	
	@Column(name="ADD_SCORE")
	private int addScore;
	
	@Column(name="REDUCE_SCORE")
	private int reduceScore;
	
	@Column(name="DEST_ID")
	private int destId ;
	
	@Column(name="REMARK")
	private String remark;
	
	@Column(name="CREATE_TIME",updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime ;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getScoreType() {
		return scoreType;
	}

	public void setScoreType(int scoreType) {
		this.scoreType = scoreType;
	}

	public String getScourceType() {
		return scourceType;
	}

	public void setScourceType(String scourceType) {
		this.scourceType = scourceType;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getAddScore() {
		return addScore;
	}

	public void setAddScore(int addScore) {
		this.addScore = addScore;
	}

	public int getReduceScore() {
		return reduceScore;
	}

	public void setReduceScore(int reduceScore) {
		this.reduceScore = reduceScore;
	}

	public int getDestId() {
		return destId;
	}

	public void setDestId(int destId) {
		this.destId = destId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
	
}
