package com.fanfan.manage.modle.book;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fanfan.manage.modle.base.PlatForm;

@Embeddable
public class BookStatCountPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9080783090038483893L;


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
	
	
}
