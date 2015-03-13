package com.fanfan.manage.modle.book;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class BookTypeRefPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8917255069708258051L;


	@ManyToOne
    @JoinColumn(name="BOOK_ID")
    private Book  book;


    @ManyToOne
    @JoinColumn(name="TYPE_ID")
    private BookType bookType;


	public Book getBook() {
		return book;
	}


	public void setBook(Book book) {
		this.book = book;
	}


	public BookType getBookType() {
		return bookType;
	}


	public void setBookType(BookType bookType) {
		this.bookType = bookType;
	}
	
}
