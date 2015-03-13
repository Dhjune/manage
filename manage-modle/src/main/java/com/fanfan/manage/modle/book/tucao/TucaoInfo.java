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
@Table(name="APP_BOOK_TUCAO_INFO")
@DynamicInsert(value=true)
@DynamicUpdate(value=true)
@SequenceGenerator(name="SEQ_TUCAO_ID",sequenceName="SEQ_TUCAO_ID",allocationSize=1)
public class TucaoInfo {
	
	@Id
    @Column(name = "TUCAO_ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_TUCAO_ID")
	private int id ;
	
	@Column(name="USER_ID")
	private int userId ;
	
	@Column(name="DEVICE_ID")
	private String deviceId;
	
	@Column(name="BOOK_ID")
	private int bookId ;
	
	@Column(name="PLATFORM_ID")
	private int platId;
	
	@Column(name="PART_ID")
	private int partId ;
	
	@Column(name="PAGE_NUM")
	private int pageNum;
	
	@Column(name="CONTENT")
	private String content;
	
	@Column(name="SCALEX",precision=5,scale=4,columnDefinition="NUMBER(5,4)")
	private Double scalex;
	
	@Column(name="SCALEY",precision=5,scale=4,columnDefinition="NUMBER(5,4)")
	private Double scaley;
	
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
