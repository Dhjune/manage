package com.fanfan.manage.modle.book;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="APP_BOOK_TYPE")
@DynamicInsert(value=true)
@DynamicUpdate(value=true)
@SequenceGenerator(name="SEQ_TYPE_ID",sequenceName="SEQ_TYPE_ID",allocationSize=1)
//表示序列化时忽略的属性  
//@JsonIgnoreProperties(value = { "hasPoints" })  
public class BookType implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7190789038386580895L;

	@Id
	@Column(name="TYPE_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_TYPE_ID")
	private int id;
	
	@Column(name="TYPE_METHOD")
	private String method;
	
	@Column(name="TYPE_NAME")
	private String name;
	
	@Column(name="HAS_POINTS")
	private int hasPoints;
	
	@Column(name="HAS_FEE")
	private int hasFee;
	
	@Column(name="DISP_ORDER")
	private int dispOrder;
	
	@Column(name="STATUS")
	private int status;
	
	@Column(name="UPDATE_USER")
	private String upDateUser;

    @Column(name="UPDATE_TIME")
    @Temporal(TemporalType.TIME)
	private Date upDateTime;

    @ManyToMany(mappedBy="booktypes")
    @JsonIgnore//jackson  相关注解,忽略该属性
    private Set<Book> books =  new HashSet<Book>();
        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHasPoints() {
        return hasPoints;
    }

    public void setHasPoints(int hasPoints) {
        this.hasPoints = hasPoints;
    }

    public int getHasFee() {
        return hasFee;
    }

    public void setHasFee(int hasFee) {
        this.hasFee = hasFee;
    }

    public int getDispOrder() {
        return dispOrder;
    }

    public void setDispOrder(int dispOrder) {
        this.dispOrder = dispOrder;
    }

    public String getUpDateUser() {
        return upDateUser;
    }

    public void setUpDateUser(String upDateUser) {
        this.upDateUser = upDateUser;
    }

    public Date getUpDateTime() {
        return upDateTime;
    }

    public void setUpDateTime(Date upDateTime) {
        this.upDateTime = upDateTime;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof BookType))
			return false;
		BookType other = (BookType) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
