package com.fanfan.manage.modle.book;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fanfan.manage.modle.base.PlatForm;

@Embeddable
public class BookPartInfoPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4513685125629545841L;

	@ManyToOne
    @JoinColumn(name="PART_ID")
    private BookPart bookPart;
	
    @ManyToOne
    @NotFound(action=NotFoundAction.IGNORE)
    @JoinColumn(name="PLATFORM_ID")
    private PlatForm platForm;
    
    public BookPart getBookPart() {
        return bookPart;
    }

    public void setBookPart(BookPart bookPart) {
        this.bookPart = bookPart;
    }

    public PlatForm getPlatForm() {
        return platForm;
    }

    public void setPlatForm(PlatForm platForm) {
        this.platForm = platForm;
    }
	
}
