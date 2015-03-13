package com.fanfan.manage.modle.base.vote;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fanfan.manage.modle.base.PlatForm;


@Entity
@Table(name="APP_BASE_VOTELIST")
@DynamicInsert(true)
@DynamicUpdate(true)
@SequenceGenerator(name="SEQ_VOTE_ID",sequenceName="SEQ_VOTE_ID",allocationSize=1)
public class Vote {
	
	@Id
	@Column(name="VOTE_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_VOTE_ID")
	private int id ;
	
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name="PLATFORM_ID")
	@Cascade({CascadeType.SAVE_UPDATE})	
	private PlatForm platForm;
	
	@Column(name="SUBJECT")
	private String subject;
	
	@Column(name="VOTE_TYPE")
	private int voteType;
	
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name="PARENT_VOTEID",updatable=false)
	private Vote parent;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="parent")
	@Cascade({CascadeType.ALL})
	private Set<Vote> votes = new HashSet<Vote>();
	
	@Column(name="INTRO")
	private String intro;
	
	@Column(name="STATUS")
	private int status;
	
	@Column(name="CREATE_TIME",updatable=false)
	@Temporal(TemporalType.TIME)
	private Date createTime;
	
	@Column(name="UPDATE_USER")
	private String updateUser;
	
	@Column(name="UPDATE_TIME")	
	@Temporal(TemporalType.TIME)
	private Date updateTime;
	
	@OneToMany(mappedBy = "vote")
	private Set<VoteOption> options = new HashSet<VoteOption>();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PlatForm getPlatForm() {
		return platForm;
	}

	public void setPlatForm(PlatForm platForm) {
		this.platForm = platForm;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getVoteType() {
		return voteType;
	}

	public void setVoteType(int voteType) {
		this.voteType = voteType;
	}

	public Vote getParent() {
		return parent;
	}

	public void setParent(Vote parent) {
		this.parent = parent;
	}

	public Set<Vote> getVotes() {
		return votes;
	}

	public void setVotes(Set<Vote> votes) {
		this.votes = votes;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
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

	public Set<VoteOption> getOptions() {
		return options;
	}

	public void setOptions(Set<VoteOption> options) {
		this.options = options;
	}
	
	
	

}
