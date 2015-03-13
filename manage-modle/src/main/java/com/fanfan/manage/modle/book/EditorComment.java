package com.fanfan.manage.modle.book;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;


/**
 * Created with IntelliJ IDEA.
 * User: fanfan
 * Date: 14-3-3
 * Time: 下午3:40
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "APP_BOOK_EDITOR_COMMENT")
@DynamicInsert(value=true)
@DynamicUpdate(value=true)
@SequenceGenerator(name="SEQ_EDITOR_COMMENT_ID",sequenceName="SEQ_EDITOR_COMMENT_ID",allocationSize=1)
public class EditorComment {

    @Id
    @Column(name = "EDITOR_COMMENT_ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_EDITOR_COMMENT_ID")
    private int id;

    @JoinColumn(name = "BOOK_ID")
    @ManyToOne
    private Book book;

    @Column(name = "EDITOR_NAME")
    private String editorName;

    @Column(name = "EDITOR_COMMENT")
    private String comment;

    @Column(name ="STATUS" )
    private int status ;

    @Column(name = "RECOMMEND_FLAG")
    private int recommendFlag;

    @Column(name = "COMMENT_TIME",updatable=false)
    @Temporal(TemporalType.TIME)
    private Date commentTime;

    @Column(name = "UPDATE_TIME")
    @Temporal(TemporalType.TIME)
    private Date upDateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getEditorName() {
        return editorName;
    }

    public void setEditorName(String editorName) {
        this.editorName = editorName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRecommendFlag() {
        return recommendFlag;
    }

    public void setRecommendFlag(int recommendFlag) {
        this.recommendFlag = recommendFlag;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public Date getUpDateTime() {
        return upDateTime;
    }

    public void setUpDateTime(Date upDateTime) {
        this.upDateTime = upDateTime;
    }
}
