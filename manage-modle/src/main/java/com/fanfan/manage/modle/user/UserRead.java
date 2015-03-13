package com.fanfan.manage.modle.user;

import java.math.BigDecimal;


import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class UserRead {
	
	private String READ_LOG_ID ;
	private BigDecimal BOOK_ID;
	private String NETWORK;	
	private BigDecimal READ_TYPE;	
	private BigDecimal READ_SECOND;
	private String BOOK_TITLE;
	private String START_TIME;
	private String END_TIME;	
	private Date PROC_TIME;
	
	
	public String getNetwork() {
		return NETWORK;
	}
	public void setNetwork(String network) {
		this.NETWORK = network;
	}
	public BigDecimal getBook_id() {
		return BOOK_ID;
	}
	public void setBook_id(BigDecimal book_id) {
		this.BOOK_ID = book_id;
	}
	public String getRead_log_id() {
		return READ_LOG_ID;
	}
	public void setRead_log_id(String read_log_id) {
		
		this.READ_LOG_ID = read_log_id;
	}
	public BigDecimal getRead_type() {
		
		return READ_TYPE;
		
	}
	public void setRead_type(BigDecimal read_type) {
		
		this.READ_TYPE = read_type;
		
	}
	public BigDecimal getRead_second() {
		
		return READ_SECOND;
		
	}
	public void setRead_second(BigDecimal read_second) {
		
		this.READ_SECOND = read_second;
		
	}
	public String getBook_title() {
		return BOOK_TITLE;
	}
	public void setBook_title(String book_title) {
		this.BOOK_TITLE = book_title;
	}
	public String getEnd_time() {
		return END_TIME;
	}
	public void setEnd_time(String end_time) {
		this.END_TIME = end_time;
	}
	public String getStart_time() {
		return START_TIME;
	}
	public void setStart_time(String start_time) {
		this.START_TIME = start_time;
	}
	public Date getProc_time() {
		return PROC_TIME;
	}
	public void setProc_time(Date proc_time) {
		this.PROC_TIME = proc_time;
	}
	
}
