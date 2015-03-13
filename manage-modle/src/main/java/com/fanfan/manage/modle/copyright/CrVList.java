package com.fanfan.manage.modle.copyright;

import java.math.BigDecimal;

public class CrVList {
	
	private BigDecimal BOOK_ID;
	
	private BigDecimal AUTHOR_ID;
	
	private String BOOK_NAME="";
	
	private String PEN_NAME="";
	
	//CR_NATURE
	private String CR_NATURE="";
	
	//DEADLINE
	private String DEADLINE="";
	
	
	private String BOOK_TYPE="";
	
	private String COUNTRY="";
	
	private String BOOK_COLOR="";
	
	private String BOOK_LENGTH="";
	
	private String UPDATE_STATUS="";
	
	private String AUTHOR_NAME="";
	
	private BigDecimal STATUS ;
	
	
	
	private BigDecimal ROWNUM_;

	public BigDecimal getBook_id() {
		return BOOK_ID;
	}

	public void setBook_id(BigDecimal book_id) {
		this.BOOK_ID = book_id;
	}

	public BigDecimal getAuthor_id() {
		return AUTHOR_ID;
	}

	public void setAuthor_id(BigDecimal author_id) {
		this.AUTHOR_ID = author_id;
	}

	public String getBook_name() {
		return BOOK_NAME;
	}

	public void setBook_name(String book_name) {
		this.BOOK_NAME = book_name;
	}

	public String getPen_name() {
		return PEN_NAME;
	}

	public void setPen_name(String pen_name) {
		this.PEN_NAME = pen_name;
	}

	public String getBook_type() {
		return BOOK_TYPE;
	}

	public void setBook_type(String book_type) {
		this.BOOK_TYPE = book_type;
	}

	public String getCountry() {
		return COUNTRY;
	}

	public void setCountry(String country) {
		this.COUNTRY = country;
	}

	public String getBook_color() {
		return BOOK_COLOR;
	}

	public void setBook_color(String book_color) {
		this.BOOK_COLOR = book_color;
	}

	public String getBook_length() {
		return BOOK_LENGTH;
	}

	public void setBook_length(String book_length) {
		this.BOOK_LENGTH = book_length;
	}

	public String getUpdate_status() {
		return UPDATE_STATUS;
	}

	public void setUpdate_status(String update_status) {
		this.UPDATE_STATUS = update_status;
	}

	public String getAuthor_name() {
		return AUTHOR_NAME;
	}

	public void setAuthor_name(String author_name) {
		this.AUTHOR_NAME = author_name;
	}

	public BigDecimal getStatus() {
		return STATUS;
	}

	public void setStatus(BigDecimal status) {
		this.STATUS = status;
	}
	public BigDecimal getRownum_() {
		return ROWNUM_;
	}

	public void setRownum_(BigDecimal rownum_) {
		this.ROWNUM_ = rownum_;
	}

	public String getDeadline() {
		return DEADLINE;
	}

	public void setDeadline(String deadline) {
		this.DEADLINE = deadline;
	}

	public String getCr_nature() {
		return CR_NATURE;
	}

	public void setCr_nature(String cr_nature) {
		this.CR_NATURE = cr_nature;
	}
			
}
