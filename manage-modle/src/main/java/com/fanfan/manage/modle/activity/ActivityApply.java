package com.fanfan.manage.modle.activity;

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

/**
 * Created with IntelliJ IDEA.
 * User: fanfan
 * Date: 14-2-28
 * Time: 下午5:32
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name="app_activity_apply")
@DynamicInsert(true)
@SequenceGenerator(name="SEQ_ACTIVITY_APPLY_ID",sequenceName="SEQ_ACTIVITY_APPLY_ID",allocationSize=1)
public class ActivityApply {
	
	@Id
	@Column(name="activity_apply_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_ACTIVITY_APPLY_ID")
	private int id ;
	
	@JoinColumn(name="activity_id")
	@ManyToOne
	private Activity activity;
	
	@Column(name="real_name")
	private String realName;
	
	
	@Column(name="pen_name")
	private String penName;
	
	@Column(name="mobile")
	private String mobile;
	
	@Column(name="qq")
	private String qq;
	
	@Column(name="note")
	private String note;
	
	@Column(name="email")
	private String email;
	
	@Column(name="birthdate")
	private String birthdate;
	
	@Column(name="link_addr")
	private String linkAddr;
	
	@Column(name="master_piece")
	private String masterPiece;
	
	@Column(name="piece_list")
	private String pieceList;
	@Column(name="PIECE_CREATE_TIME")
	private String pieceCreateTime ;
	@Column(name="PIECE_CONTENT_INTRO")
	private String pieceContentIntro;
	@Column(name="PIECE_IDEA_INTRO")
	private String pieceIdeaIntro;
	
	
	@Column(name="create_time",updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	@Column(name="status")
	private int status;
	
	@Column(name="update_user")
	private String updateUser;
	
	@Column(name="update_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;
	
	@Column(name="result")
	private String result;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPenName() {
		return penName;
	}

	public void setPenName(String penName) {
		this.penName = penName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getLinkAddr() {
		return linkAddr;
	}

	public void setLinkAddr(String linkAddr) {
		this.linkAddr = linkAddr;
	}

	public String getMasterPiece() {
		return masterPiece;
	}

	public void setMasterPiece(String masterPiece) {
		this.masterPiece = masterPiece;
	}

	public String getPieceList() {
		return pieceList;
	}

	public void setPieceList(String pieceList) {
		this.pieceList = pieceList;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getPieceIdeaIntro() {
		return pieceIdeaIntro;
	}

	public void setPieceIdeaIntro(String pieceIdeaIntro) {
		this.pieceIdeaIntro = pieceIdeaIntro;
	}

	public String getPieceContentIntro() {
		return pieceContentIntro;
	}

	public void setPieceContentIntro(String pieceContentIntro) {
		this.pieceContentIntro = pieceContentIntro;
	}

	public String getPieceCreateTime() {
		return pieceCreateTime;
	}

	public void setPieceCreateTime(String pieceCreateTime) {
		this.pieceCreateTime = pieceCreateTime;
	}
	
	
}
