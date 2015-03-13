package com.fanfan.manage.modle.base;
/**
 * Created with IntelliJ IDEA.
 * User: fanfan
 * Date: 14-2-28
 * Time: 下午4:52
 * To change this template use File | Settings | File Templates.
 */

import com.fanfan.manage.modle.book.Book;
import com.fanfan.manage.modle.book.BookPart;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "APP_BASE_PLATFORM")
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
@SequenceGenerator(name="SEQ_PLATFORM_ID",sequenceName="SEQ_PLATFORM_ID",allocationSize=1)
@DynamicInsert(value=true)
@DynamicUpdate(value=true)
public class PlatForm {

    @Id
    @Column(name="PLATFORM_ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_PLATFORM_ID")
    private int id;

    @ManyToOne()
    @Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
    @NotFound(action=NotFoundAction.IGNORE)
    @JoinColumn(name="PARENT_ID",updatable=false)
    private PlatForm parent;


    @Column(name="PLATFORM_NAME")
    private String name;

    @Column(name="SCREEN_WITDH")
    private int width;

    @Column(name="SCREEN_HEIGHT")
    private int height;

    @Column(name="STATUS")
    private int status;
    
    @OneToMany(fetch=FetchType.LAZY,mappedBy="parent")
    @Cascade({CascadeType.DELETE})   
    private Set<PlatForm> children = new HashSet<PlatForm>();

    public Set<PlatForm> getChildren() {
		return children;
	}

	public void setChildren(Set<PlatForm> children) {
		this.children = children;
	}

	public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name="CREATE_TIME",updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @ManyToMany
    @JoinTable(name="APP_BOOK_INFO",
            joinColumns=@JoinColumn(name="PLATFORM_ID", referencedColumnName="PLATFORM_ID"),
            inverseJoinColumns=@JoinColumn(name="BOOK_ID", referencedColumnName="BOOK_ID")
    )
    private Set<Book> books =  new HashSet<Book>();

    @ManyToMany
    @JoinTable(name="APP_BOOK_PART_INFO",
            joinColumns=@JoinColumn(name="PLATFORM_ID", referencedColumnName="PLATFORM_ID"),
            inverseJoinColumns=@JoinColumn(name="PART_ID", referencedColumnName="PART_ID")
    )
    private Set<BookPart> bookParts = new HashSet<BookPart>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PlatForm getParent() {
        return parent;
    }

    public void setParent(PlatForm parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }



    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public Set<BookPart> getBookParts() {
        return bookParts;
    }

    public void setBookParts(Set<BookPart> bookParts) {
        this.bookParts = bookParts;
    }
}
