package com.fanfan.manage.modle.base;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;


/**
 * Created with IntelliJ IDEA.
 * User: fanfan
 * Date: 14-2-28
 * Time: 下午5:07
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "APP_BASE_TRANS_TEAM")
@SequenceGenerator(name="SEQ_TRANS_TEAM_ID",sequenceName="SEQ_TRANS_TEAM_ID",allocationSize=1)
@DynamicInsert(true)
@DynamicUpdate(true)
public class TransTeam {

    @Id
    @Column(name = "TRANS_TEAM_ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_TRANS_TEAM_ID")
    private int id;

    @Column(name = "TEAM_NAME")
    private String name ;

    @Column(name = "LINK_NAME")
    private String linkName;

    @Column(name = "LINK_TELE")
    private String linkTele;

    @Column(name = "LINK_EMAIL")
    private String linkEmail;

    @Column(name = "BRIEF_INTRO")
    private String briefIntro;

    @Column(name = "IS_AUTH")
    private int isAuth;

    public int getIsAuth() {
		return isAuth;
	}

	public void setIsAuth(int isAuth) {
		this.isAuth = isAuth;
	}

	@Column(name = "TEAM_LEVEL")
    private int teamLevel;

    @Column(name = "BOOK_NUM")
    private int bookNum;

    @Column(name = "ADD_TIME",updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date addTime;

    @Column(name = "AUTH_TIME")
    @Temporal(TemporalType.TIME)
    private Date authTime;

    @Column(name = "STATUS")
    private int status;

    @Column(name = "UPDATE_USER")
    private String upDateUser;

    @Column(name = "UPDATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date upDateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getLinkTele() {
        return linkTele;
    }

    public void setLinkTele(String linkTele) {
        this.linkTele = linkTele;
    }

    public String getLinkEmail() {
        return linkEmail;
    }

    public void setLinkEmail(String linkEmail) {
        this.linkEmail = linkEmail;
    }

    public String getBriefIntro() {
        return briefIntro;
    }

    public void setBriefIntro(String briefIntro) {
        this.briefIntro = briefIntro;
    }

   

    public int getTeamLevel() {
        return teamLevel;
    }

    public void setTeamLevel(int teamLevel) {
        this.teamLevel = teamLevel;
    }

    public int getBookNum() {
        return bookNum;
    }

    public void setBookNum(int bookNum) {
        this.bookNum = bookNum;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getAuthTime() {
        return authTime;
    }

    public void setAuthTime(Date authTime) {
        this.authTime = authTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
}
