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

/**
 * Created with IntelliJ IDEA.
 * User: fanfan
 * Date: 14-2-28
 * Time: 下午5:32
 * To change this template use File | Settings | File Templates.
 */


@Entity
@Table(name="app_activity_image")
@DynamicInsert(value=true)
@DynamicUpdate(true)
@SequenceGenerator(name="SEQ_ACTIVITY_IMAGE_ID",sequenceName="SEQ_ACTIVITY_IMAGE_ID",allocationSize=1)
public class ActivityImage {
	@Id
	@Column(name="activity_image_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_ACTIVITY_IMAGE_ID")
	private int id ;
	
	@JoinColumn(name="activity_id")
	@ManyToOne
	private Activity activity;
	
	@Column(name="subject")
	private String subject;
	
	@Column(name="intro")
	private String intro;
	
	@Column(name="image_url")
	private String imageUrl;
	
	@Column(name="disp_order")
	private int dispOrder;
	
	@Column(name="status")
	private int status ;
	
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

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
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
