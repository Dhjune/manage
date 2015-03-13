package com.fanfan.manage.modle.activity;

import java.math.BigDecimal;

public class ActivityStat {

	private BigDecimal ACTIVITY_ID;
	
	private  BigDecimal COUNT;
	
	private String STAT_DAY;
	
	private String SUBJECT;
	
	private String PLATFORM_NAME;
	
	private BigDecimal PLATFORM_ID;
	
	private BigDecimal ROWNUM_;

	public BigDecimal getActivity_id() {
		return ACTIVITY_ID;
	}

	public void setActivity_id(BigDecimal activity_id) {
		this.ACTIVITY_ID = activity_id;
	}

	public BigDecimal getCount() {
		return COUNT;
	}

	public void setCount(BigDecimal count) {
		this.COUNT = count;
	}

	public String getStat_day() {
		return STAT_DAY;
	}

	public void setStat_day(String stat_day) {
		this.STAT_DAY = stat_day;
	}

	public String getSubject() {
		return SUBJECT;
	}

	public void setSubject(String subject) {
		this.SUBJECT = subject;
	}

	public BigDecimal getPlatform_id() {
		return PLATFORM_ID;
	}

	public void setPlatform_id(BigDecimal platform_id) {
		this.PLATFORM_ID = platform_id;
	}

	public BigDecimal getRownum_() {
		return ROWNUM_;
	}

	public void setRownum_(BigDecimal rownum_) {
		this.ROWNUM_ = rownum_;
	}

	public String getPlatform_name() {
		return PLATFORM_NAME;
	}

	public void setPlatform_name(String platform_name) {
		this.PLATFORM_NAME = platform_name;
	}
	
	
	
	
}
