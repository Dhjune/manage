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
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fanfan.manage.modle.base.PlatForm;

/**
 * Created with IntelliJ IDEA.
 * User: fanfan
 * Date: 14-2-28
 * Time: 下午5:32
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="app_activity_list")
@DynamicInsert(true)
@DynamicUpdate(true)
@SequenceGenerator(name="SEQ_ACTIVITY_ID",sequenceName="SEQ_ACTIVITY_ID",allocationSize=1)
public class Activity {
	@Id
	@Column(name="activity_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_ACTIVITY_ID")
	private int id;	
	//平台
	@JoinColumn(name="platform_id")
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	private PlatForm platForm;
	
	@Column(name="kind")
	private int kind;
	
	@Column(name="subject")
	private String subject;
	
	@Column(name="intro")
	private String intro;
	
	@Column(name="image_url")
	private String imageUrl;
	
	@Column(name="show_url")
	private String showUrl;
	
	@Column(name="schedule")
	private long schedule;
	
	@Column(name="start_time")
	@Temporal(TemporalType.DATE)
	private Date startTime;
	
	@Column(name="end_time")
	@Temporal(TemporalType.DATE)
	private Date endTime;
	
	@Column(name="disp_order")
	private int dispOrder;
	
	@Column(name="hot_flag")
	private int hotFlag;
	
	@Column(name="click")
	private int click;
	
	@Column(name="status")
	private int status;
	
	@Column(name="create_time",updatable=false)
	@Temporal(TemporalType.TIME)
	private Date createTime;
	
	@Column(name="update_user")
	private String updateUser;
	
	@Column(name="update_time")
	@Temporal(TemporalType.TIME)
	private Date updateTime;

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

	public int getKind() {
		return kind;
	}

	public void setKind(int kind) {
		this.kind = kind;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
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

	public long getSchedule() {
		return schedule;
	}

	public void setSchedule(long schedule) {
		this.schedule = schedule;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getDispOrder() {
		return dispOrder;
	}

	public void setDispOrder(int dispOrder) {
		this.dispOrder = dispOrder;
	}

	public int getHotFlag() {
		return hotFlag;
	}

	public void setHotFlag(int hotFlag) {
		this.hotFlag = hotFlag;
	}

	public int getClick() {
		return click;
	}

	public void setClick(int click) {
		this.click = click;
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
