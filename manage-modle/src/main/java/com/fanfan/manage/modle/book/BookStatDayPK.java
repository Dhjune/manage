package com.fanfan.manage.modle.book;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fanfan.manage.modle.base.PlatForm;

@Embeddable
public class BookStatDayPK implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4865526830778882199L;

	@Column(name="STAT_DAY")
	private String statDay;

	@OneToOne
	@JoinColumn(name="BOOK_ID")
	private Book booId;
	
	
	@OneToOne
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name="PLATFORM_ID")
	private PlatForm  platForm;


	public Book getBooId() {
		return booId;
	}


	public void setBooId(Book booId) {
		this.booId = booId;
	}


	public PlatForm getPlatForm() {
		return platForm;
	}


	public void setPlatForm(PlatForm platForm) {
		this.platForm = platForm;
	}


	public String getStatDay() {
		return statDay;
	}


	public void setStatDay(String statDay) {
		this.statDay = statDay;
	}
	
	
	
}
