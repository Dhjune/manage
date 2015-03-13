package com.fanfan.manage.modle.book;

import com.fanfan.manage.modle.base.PlatForm;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: fanfan
 * Date: 14-3-3
 * Time: 下午3:38
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "APP_BOOK_INFO",uniqueConstraints={@UniqueConstraint(columnNames={"BOOK_ID","PLATFORM_ID"})})
@DynamicInsert(true)
@DynamicUpdate(true)
public class BookInfo implements Serializable {

//    @Id
//    @GeneratedValue()
//    @Column(name = "INFO_ID")
//    private int id;
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
	@EmbeddedId
	private BookInfoPK pk;
    
    

    @Column(name = "BOOK_TITLE")
    private String bookTitle;

    @Column(name = "SECOND_TITLE")
    private String secondTitle;
  

    @Column(name = "HAS_TICKET")
    private int hasTicket;

    @Column(name = "HAS_FEE")
    private int hasFee;

    @Column(name = "HOT_FLAG")
    private int hotFlag;

    @Column(name = "RECOMMEND_FLAG")
    private int recommendFlag;

    @Column(name = "RECOMMEND_REASON")
    private String recommendReason;

    @Column(name = "LASTED_PART")
    private String lastedPart;
    
    @Column(name="PART_UPDATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date  partUpdateTime;
    
    @Column(name = "VOTE_PLAN_ID")
    private int votePlanId;
    

    @Column(name = "STATUS")
    private int status;

    
    @Column(name = "ADD_TIME",updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date addTime ;

    @Column(name = "UPDATE_USER")
    private String upDateUser;

    @Column(name = "UPDATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date upDateTime;
    
    @Column(name = "BIG_IMAGE")
    private String bigImage;
    
    @Column(name="SMALL_IMAGE")
    private String smallImage;
    
    public String getBigImage() {
		return bigImage;
	}

	public void setBigImage(String bigImage) {
		this.bigImage = bigImage;
	}

	public String getSmallImage() {
		return smallImage;
	}

	public void setSmallImage(String smallImage) {
		this.smallImage = smallImage;
	}

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getSecondTitle() {
        return secondTitle;
    }

    public void setSecondTitle(String secondTitle) {
        this.secondTitle = secondTitle;
    }

   

    public int getHasTicket() {
        return hasTicket;
    }

    public void setHasTicket(int hasTicket) {
        this.hasTicket = hasTicket;
    }

    public int getHasFee() {
        return hasFee;
    }

    public void setHasFee(int hasFee) {
        this.hasFee = hasFee;
    }

    public int getHotFlag() {
        return hotFlag;
    }

    public void setHotFlag(int hotFlag) {
        this.hotFlag = hotFlag;
    }

    public int getRecommendFlag() {
        return recommendFlag;
    }

    public void setRecommendFlag(int recommendFlag) {
        this.recommendFlag = recommendFlag;
    }

    public String getRecommendReason() {
        return recommendReason;
    }

    public void setRecommendReason(String recommendReason) {
        this.recommendReason = recommendReason;
    }

    public String getLastedPart() {
        return lastedPart;
    }

    public void setLastedPart(String lastedPart) {
        this.lastedPart = lastedPart;
    }

    public int getVotePlanId() {
        return votePlanId;
    }

    public void setVotePlanId(int votePlanId) {
        this.votePlanId = votePlanId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getUpDateUser() {
        return upDateUser;
    }

    public void setUpDateUser(String upDateUser) {
        this.upDateUser = upDateUser;
    }

    public Date getUpDateTime() {
        return upDateTime;
    }

    public void setUpDateTime(Date upDateTime) {
        this.upDateTime = upDateTime;
    }

	public BookInfoPK getPk() {
		return pk;
	}

	public void setPk(BookInfoPK pk) {
		this.pk = pk;
	}

	public Date getPartUpdateTime() {
		return partUpdateTime;
	}

	public void setPartUpdateTime(Date partUpdateTime) {
		this.partUpdateTime = partUpdateTime;
	}
}
