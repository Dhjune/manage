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
public class BookStatMonthPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8511598164861264461L;
	
	@Column(name="STAT_MONTH")
	private String statMonth;
	
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


	public String getStatMonth() {
		return statMonth;
	}


	public void setStatMonth(String statMonth) {
		this.statMonth = statMonth;
	}

}
