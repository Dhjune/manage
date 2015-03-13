package com.fanfan.manage.modle.book;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: fanfan
 * Date: 14-3-3
 * Time: 下午3:41
 * To change this template use File | Settings | File Templates.
 */


@Entity
@Table(name = "APP_BOOK_PARTS")
@DynamicInsert(true)
@DynamicUpdate(true)
@SequenceGenerator(name="SEQ_PART_ID",sequenceName="SEQ_PART_ID",allocationSize=1)
public class BookPart {

    @Id
    @Column(name = "PART_ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_PART_ID")
    private int id;

    @JoinColumn(name = "BOOK_ID")
    @NotFound(action=NotFoundAction.IGNORE)
    @ManyToOne
    private Book book;

    @Column(name = "PART_NAME")
    private String partName;

    @Column(name = "PART_INDEX")
    private int partIndex;

    @Column(name = "DISP_ORDER")
    private int dispOrder;

    @Column(name = "PAGE_NUM")
    private int pageNum;

    @Column(name = "VOTE_PLAN_ID")
    private int votePlanId;

    @Column(name = "VOTE_PAGE")
    private int VotePage;

    @Column(name = "STATUS")
    private int status;

    @Column(name = "ADD_TIME",updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date addTime;

    @Column(name = "UPDATE_USER")
    private String updateUser;

    @Column(name = "UPDATE_TIME")
    @Temporal(TemporalType.TIME)
    private Date updateTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public int getPartIndex() {
		return partIndex;
	}

	public void setPartIndex(int partIndex) {
		this.partIndex = partIndex;
	}

	public int getDispOrder() {
		return dispOrder;
	}

	public void setDispOrder(int dispOrder) {
		this.dispOrder = dispOrder;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getVotePlanId() {
		return votePlanId;
	}

	public void setVotePlanId(int votePlanId) {
		this.votePlanId = votePlanId;
	}

	public int getVotePage() {
		return VotePage;
	}

	public void setVotePage(int votePage) {
		VotePage = votePage;
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
