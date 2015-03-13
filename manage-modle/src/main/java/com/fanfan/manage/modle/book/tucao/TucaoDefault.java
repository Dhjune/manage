package com.fanfan.manage.modle.book.tucao;

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

/**
 * Created with IntelliJ IDEA.
 * User: fanfan
 * Date: 14-3-3
 * Time: 下午3:43
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name="APP_BOOK_TUCAO_DEFAULT")
@DynamicInsert(value=true)
@DynamicUpdate(value=true)
@SequenceGenerator(name="SEQ_TUCAO_ITEM_ID",sequenceName="SEQ_TUCAO_ITEM_ID",allocationSize=1)
public class TucaoDefault {
	
	@Id
    @Column(name = "ITEM_ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_TUCAO_ITEM_ID")
	private int id ;
	
	@Column(name="BOOK_ID")
	private int bookId;
	
	@Column(name="PLATFORM_ID")
	private int platId ;
	
	@Column(name="PART_ID")
	private int partId;
	
	@Column(name="PAGE_NUM")
	private int pageNum;
	
	@Column(name="CONTENT")
	private String content;
	
	@Column(name="DISP_ORDER")
	private int dispOrder;
	
	@Column(name="SEND_COUNT")
	private int sendCount;
	
	@Column(name="SEND_TIME")
	@Temporal(TemporalType.TIME)
	private Date sendTime;
	
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
}
