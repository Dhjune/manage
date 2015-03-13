package com.fanfan.manage.modle.user;

import java.math.BigDecimal;

public class UserStat {
	
	
	private BigDecimal COUNT;
	
	private BigDecimal PLATFORM_ID;
	
	private String PLATFORM_NAME;
	
	private String STAT_DAY;
	
	private BigDecimal ROWNUM_;

	public BigDecimal getCount() {
		return COUNT;
	}

	public void setCount(BigDecimal count) {
		this.COUNT = count;
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

	public String getStat_day() {
		return STAT_DAY;
	}

	public void setStat_day(String stat_day) {
		this.STAT_DAY = stat_day;
	}

	public BigDecimal getRownum_() {
		return ROWNUM_;
	}

	public void setRownum_(BigDecimal rownum_) {
		this.ROWNUM_ = rownum_;
	}
	
	
	
	
	
}
