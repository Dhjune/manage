package com.fanfan.manage.modle.book;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: fanfan
 * Date: 14-3-3
 * Time: 下午3:42
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "APP_BOOK_PIC")
@DynamicInsert(value=true)
@DynamicUpdate(value=true)
@SequenceGenerator(name="SEQ_BOOK_PIC_ID",sequenceName="SEQ_BOOK_PIC_ID",allocationSize=1)
public class BookPic {

    @Id
    @Column(name = "BOOK_PIC_ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_BOOK_PIC_ID")
    private int id;

    @ManyToOne
    @JoinColumn(name="BOOK_ID")
    private Book  book;

    @Column(name = "PIC_URL")
    private String picUrl;

    @Column(name = "DISP_ORDER")
    private int dispOrder;

    @Column(name = "STATUS")
    private int status;

    @Column(name = "CREATE_TIME",updatable=false) 
    @Temporal(TemporalType.TIME)
    private Date createTime;

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

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
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

}
