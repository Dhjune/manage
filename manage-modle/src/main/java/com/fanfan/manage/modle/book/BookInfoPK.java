package com.fanfan.manage.modle.book;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fanfan.manage.modle.base.PlatForm;

@Embeddable
public class BookInfoPK implements Serializable{

	    /**
	 * 
	 */
	private static final long serialVersionUID = 4538974333122478387L;

		@ManyToOne
	    @JoinColumn(name="BOOK_ID")
	    private Book  book;
	    
	    @ManyToOne
	    @JoinColumn(name="PLATFORM_ID")
	    private PlatForm platForm;
	    
	    public Book getBook() {
			return book;
		}

		public void setBook(Book book) {
			this.book = book;
		}

		public PlatForm getPlatForm() {
			return platForm;
		}

		public void setPlatForm(PlatForm platForm) {
			this.platForm = platForm;
		}

		
	
}
