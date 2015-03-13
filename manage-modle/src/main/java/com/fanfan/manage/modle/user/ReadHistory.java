package com.fanfan.manage.modle.user;

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

import com.fanfan.manage.modle.book.Book;

@Entity
@Table(name="APP_USER_READ_HIS")
@DynamicInsert(value=true)
@DynamicUpdate(value=true)
@SequenceGenerator(name="SEQ_READ_HIS_ID",sequenceName="SEQ_READ_HIS_ID",allocationSize=1)
public class ReadHistory {
	
	@Id
    @Column(name = "READ_HIS_ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_READ_HIS_ID")
	private Integer id;
	
	@JoinColumn(name="USER_ID")
	@ManyToOne
	private User user;
	
	@Column(name="DEVICE_ID")
	private String  deviceId;
	
	@Column(name="USER_TOKEN")
	private String  userToken;
	
	@Column(name="SESSION_ID")
	private String sessionId;
	
	@JoinColumn(name="BOOK_ID")
	@ManyToOne
	private Book book;
	
	@Column(name="FIRST_TIME",updatable=false)
	@Temporal(TemporalType.TIME)
	private Date firstTime;
	
	@Column(name="LAST_TIME",updatable=false)
	@Temporal(TemporalType.TIME)
	private Date lastTime;
	
	@Column(name="CREATE_TIME",updatable=false)	
	@Temporal(TemporalType.TIME)
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Date getFirstTime() {
		return firstTime;
	}

	public void setFirstTime(Date firstTime) {
		this.firstTime = firstTime;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
