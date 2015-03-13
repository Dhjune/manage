package com.fanfan.manage.modle.book;

import com.fanfan.manage.modle.base.TransTeam;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;
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

/**
 * Created with IntelliJ IDEA.
 * User: fanfan
 * Date: 14-3-3
 * Time: 下午3:43
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "APP_BOOK_LIST")
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
@DynamicInsert(value=true)
@DynamicUpdate(value=true)
@SequenceGenerator(name="SEQ_BOOK_ID",sequenceName="seq_book_id",allocationSize=1)
public class Book {

    @Id
    @Column(name = "BOOK_ID")   
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_BOOK_ID")
    private Integer id;

    @Column(name = "BOOK_TITLE")
    private String bookTitle;

    @JoinColumn(name = "AUTHOR_ID")
    @NotFound(action=NotFoundAction.IGNORE)
    @ManyToOne
    @Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
    private Author author;

    @Column(name = "BOOK_AUTHOR")
    private  String bookAuthor;

    @Column(name = "BOOK_EDITOR")
    private String bookEditor;

    @Column(name = "BRIEF_INTRO")
    private String briefIntro;

    @Column(name = "PUBLISHER")
    private String publisher;

    @Column(name = "IS_OFFPRINT")
    private int offPrint;

    @Column(name = "IS_SERIALIZE")
    private int serialize;

    @Column(name = "COUNTRY")
    private String country;

    @JoinColumn(name = "TRANS_TEAM_ID")
    @ManyToOne()
    @NotFound(action=NotFoundAction.IGNORE)
    //@Cascade({CascadeType.SAVE_UPDATE}) 不是必要关联处，不要使用级联。效率低下
    private TransTeam transTeam;

    @Column(name = "TRANS_NAME")
    private String transName;

    @Column(name = "OFFICIAL_FLAG")
    private int  officialFlag;

    @Column(name = "EXCLUSIVE_FLAG")
    private int exclusiveFlag;

    @Column(name = "BOOK_TAGS")
    private String bookTags;

    @Column(name = "THEME")
    private String theme;

    @Column(name = "ONLINE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date onlineTime;

    @Column(name = "OFFLINE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date offlineTime;

    @Column(name = "REF_BOOK_ID")
    private int refBookId;

    @Column(name = "SEARCH_FLAG")
    private int searchFlag;

    @Column(name = "STATUS")
    private int status;
    
    @Column(name = "ADD_TIME",updatable=false)
    @Temporal(TemporalType.DATE)
    private Date addTime ;

    @Column(name = "UPDATE_USER")
    private String updateUser;

    @Column(name = "UPDATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    
    @Column(name="PAGE_DIRECTION")
    private int pageDirection;
    
   // CHAPTER_NUM
    @Column(name="CHAPTER_NUM")
    private int chapterNum;

    
    @ManyToMany()
    @JoinTable(name="APP_BOOK_TYPE_REF",
    joinColumns=@JoinColumn(name="BOOK_ID", referencedColumnName="BOOK_ID"),
    inverseJoinColumns=@JoinColumn(name="TYPE_ID", referencedColumnName="TYPE_ID"))
    @JsonIgnore
    private Set<BookType> booktypes = new  HashSet<BookType>();
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookEditor() {
        return bookEditor;
    }

    public void setBookEditor(String bookEditor) {
        this.bookEditor = bookEditor;
    }

    public String getBriefIntro() {
        return briefIntro;
    }

    public void setBriefIntro(String briefIntro) {
        this.briefIntro = briefIntro;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getOffPrint() {
        return offPrint;
    }
    
    public void setOffPrint(int offPrint) {
    	this.offPrint = offPrint;
    }

    public int getSerialize() {
        return serialize;
    }

    public void setSerialize(int serialize) {
    	this.serialize = serialize;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public TransTeam getTransTeam() {
        return transTeam;
    }

    public void setTransTeam(TransTeam transTeam) {
        this.transTeam = transTeam;
    }

    public String getTransName() {
        return transName;
    }

    public void setTransName(String transName) {
        this.transName = transName;
    }

    public int getOfficialFlag() {
        return officialFlag;
    }

    public void setOfficialFlag(int officialFlag) {
        this.officialFlag = officialFlag;
    }

    public int getExclusiveFlag() {
        return exclusiveFlag;
    }

    public void setExclusiveFlag(int exclusiveFlag) {
        this.exclusiveFlag = exclusiveFlag;
    }

    public String getBookTags() {
        return bookTags;
    }

    public void setBookTags(String bookTags) {
        this.bookTags = bookTags;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Date getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(Date onlineTime) {
        this.onlineTime = onlineTime;
    }

    public Date getOfflineTime() {
        return offlineTime;
    }

    public void setOfflineTime(Date offlineTime) {
        this.offlineTime = offlineTime;
    }

    public int getRefBookId() {
        return refBookId;
    }

    public void setRefBookId(int refBookId) {
        this.refBookId = refBookId;
    }

    public int getSearchFlag() {
        return searchFlag;
    }

    public void setSearchFlag(int searchFlag) {
        this.searchFlag = searchFlag;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	public Set<BookType> getBooktypes() {
		return booktypes;
	}

	public void setBooktypes(Set<BookType> booktypes) {
		this.booktypes = booktypes;
	}

	public int getPageDirection() {
		return pageDirection;
	}

	public void setPageDirection(int pageDirection) {
		this.pageDirection = pageDirection;
	}

	public int getChapterNum() {
		return chapterNum;
	}

	public void setChapterNum(int chapterNum) {
		this.chapterNum = chapterNum;
	}




}
