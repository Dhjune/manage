package com.fanfan.manage.modle.book;

import java.math.BigDecimal;

public class StatMonthbook {

	private String BOOK_TITLE;
	
	private String STAT_MONTH;
	
	private String PLATFORM_NAME;
	
	private BigDecimal BOOK_ID;
	
	private BigDecimal PLATFORM_ID;
	
	private BigDecimal CLICK_NUM;
	
	private BigDecimal READ_NUM;
	
	private BigDecimal COMMENT_NUM;
	
	private BigDecimal DOWN_NUM;
	
	private BigDecimal PRAISE_NUM;
	
	private BigDecimal ROWNUM_;

	public BigDecimal getClick_num() {
		return CLICK_NUM;
	}

	public void setClick_num(BigDecimal click_num) {
		this.CLICK_NUM = click_num;
	}

	public String getBook_title() {
		return BOOK_TITLE;
	}

	public void setBook_title(String book_title) {
		this.BOOK_TITLE = book_title;
	}

	public BigDecimal getPraise_num() {
		return PRAISE_NUM;
	}

	public void setPraise_num(BigDecimal praise_num) {
		this.PRAISE_NUM = praise_num;
	}

	public BigDecimal getDown_num() {
		return DOWN_NUM;
	}

	public void setDown_num(BigDecimal down_num) {
		this.DOWN_NUM = down_num;
	}

	public BigDecimal getComment_num() {
		return COMMENT_NUM;
	}

	public void setComment_num(BigDecimal comment_num) {
		this.COMMENT_NUM = comment_num;
	}

	public BigDecimal getRead_num() {
		return READ_NUM;
	}

	public void setRead_num(BigDecimal read_num) {
		this.READ_NUM = read_num;
	}

	public BigDecimal getRownum_() {
		return ROWNUM_;
	}

	public void setRownum_(BigDecimal rownum_) {
		this.ROWNUM_ = rownum_;
	}

	public String getStat_month() {
		return STAT_MONTH;
	}

	public void setStat_month(String stat_month) {
		this.STAT_MONTH = stat_month;
	}
	
	public BigDecimal getBook_id() {
		return BOOK_ID;
	}

	public void setBook_id(BigDecimal book_id) {
		this.BOOK_ID = book_id;
	}

	public BigDecimal getPlatform_id() {
		return PLATFORM_ID;
	}

	public void setPlatform_id(BigDecimal platform_id) {
		this.PLATFORM_ID = platform_id;
	}

	public String getPlatform_name() {
		return PLATFORM_NAME;
	}

	public void setPlatform_name(String platform_name) {
		this.PLATFORM_NAME = platform_name;
	}
	
}
