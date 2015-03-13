package com.fanfan.manage.modle.book;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
@Entity
@Table(name="APP_BOOK_STAT_MONTH")
@DynamicInsert(value=true)
@DynamicUpdate(value=true)
public class BookStatMonth {
	
	@EmbeddedId
	private BookStatMonthPK pk;
	
	@Column(name="CLICK_NUM")
	private Integer clickNum;
	
	@Column(name="DOWN_NUM")
	private Integer downNum;
	
	@Column(name="READ_NUM")
	private Integer readNum;
	
	@Column(name="COMMENT_NUM")
	private Integer commentNum;
	
	@Column(name="PRAISE_NUM")
	private Integer praiseNum;
	
	@Column(name="TREAD_NUM")
	private Integer treadNum;
	
	@Column(name="FAVORITE_NUM")
	private Integer favoriteNum;
	
	@Column(name="TICKET_NUM")
	private Integer ticketNum;
	
	@Column(name="VIDEO_NUM")
	private Integer videoNum;
	
	@Column(name="ACTIVITY_NUM")
	private Integer activityNum;
	
	@Column(name="SURROUNDING_NUM")
	private Integer surroundingNum;
	
	@Column(name="MODI_CLICK_NUM")
	private Integer modiClickNum ;
	
	@Column(name="MODI_DOWN_NUM")
	private Integer modiDownNum ;
	
	@Column(name="MODI_READ_NUM")
	private Integer modiReadNum;
	
	@Column(name="MODI_COMMENT_NUM")
	private Integer modiCommentNum;
	
	@Column(name="MODI_PRAISE_NUM")
	private Integer modiPraiseNum;
	
	@Column(name="MODI_TREAD_NUM")
	private Integer modiTreadNum;
	
	@Column(name="MODI_FAVORITE_NUM")
	private Integer modiFavoriteNum;
	
	@Column(name="MODI_TICKET_NUM")
	private Integer modiTicketNum;
	
	@Column(name="MODI_USER")
	private String modiUser;
	
	@Column(name="MODI_TIME")
	@Temporal(TemporalType.TIME)
	private Date modiTime;
	
	@Column(name="STAT_TIME",updatable=false)
	@Temporal(TemporalType.TIME)
	private Date statTime;
	
	

	public Integer getClickNum() {
		return clickNum;
	}

	public void setClickNum(Integer clickNum) {
		this.clickNum = clickNum;
	}

	public Integer getDownNum() {
		return downNum;
	}

	public void setDownNum(Integer downNum) {
		this.downNum = downNum;
	}

	public Integer getReadNum() {
		return readNum;
	}

	public void setReadNum(Integer readNum) {
		this.readNum = readNum;
	}

	public Integer getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}

	public Integer getPraiseNum() {
		return praiseNum;
	}

	public void setPraiseNum(Integer praiseNum) {
		this.praiseNum = praiseNum;
	}

	public Integer getTreadNum() {
		return treadNum;
	}

	public void setTreadNum(Integer treadNum) {
		this.treadNum = treadNum;
	}

	public Integer getFavoriteNum() {
		return favoriteNum;
	}

	public void setFavoriteNum(Integer favoriteNum) {
		this.favoriteNum = favoriteNum;
	}

	public Integer getTicketNum() {
		return ticketNum;
	}

	public void setTicketNum(Integer ticketNum) {
		this.ticketNum = ticketNum;
	}

	public Integer getVideoNum() {
		return videoNum;
	}

	public void setVideoNum(Integer videoNum) {
		this.videoNum = videoNum;
	}

	public Integer getActivityNum() {
		return activityNum;
	}

	public void setActivityNum(Integer activityNum) {
		this.activityNum = activityNum;
	}

	public Integer getSurroundingNum() {
		return surroundingNum;
	}

	public void setSurroundingNum(Integer surroundingNum) {
		this.surroundingNum = surroundingNum;
	}

	public Integer getModiClickNum() {
		return modiClickNum;
	}

	public void setModiClickNum(Integer modiClickNum) {
		this.modiClickNum = modiClickNum;
	}

	public Integer getModiDownNum() {
		return modiDownNum;
	}

	public void setModiDownNum(Integer modiDownNum) {
		this.modiDownNum = modiDownNum;
	}

	public Integer getModiReadNum() {
		return modiReadNum;
	}

	public void setModiReadNum(Integer modiReadNum) {
		this.modiReadNum = modiReadNum;
	}

	public Integer getModiCommentNum() {
		return modiCommentNum;
	}

	public void setModiCommentNum(Integer modiCommentNum) {
		this.modiCommentNum = modiCommentNum;
	}

	public Integer getModiPraiseNum() {
		return modiPraiseNum;
	}

	public void setModiPraiseNum(Integer modiPraiseNum) {
		this.modiPraiseNum = modiPraiseNum;
	}

	public Integer getModiTreadNum() {
		return modiTreadNum;
	}

	public void setModiTreadNum(Integer modiTreadNum) {
		this.modiTreadNum = modiTreadNum;
	}

	public Integer getModiFavoriteNum() {
		return modiFavoriteNum;
	}

	public void setModiFavoriteNum(Integer modiFavoriteNum) {
		this.modiFavoriteNum = modiFavoriteNum;
	}

	public Integer getModiTicketNum() {
		return modiTicketNum;
	}

	public void setModiTicketNum(Integer modiTicketNum) {
		this.modiTicketNum = modiTicketNum;
	}

	public String getModiUser() {
		return modiUser;
	}

	public void setModiUser(String modiUser) {
		this.modiUser = modiUser;
	}

	public Date getModiTime() {
		return modiTime;
	}

	public void setModiTime(Date modiTime) {
		this.modiTime = modiTime;
	}

	public Date getStatTime() {
		return statTime;
	}

	public void setStatTime(Date statTime) {
		this.statTime = statTime;
	}

	public BookStatMonthPK getPk() {
		return pk;
	}

	public void setPk(BookStatMonthPK pk) {
		this.pk = pk;
	}
	
}
