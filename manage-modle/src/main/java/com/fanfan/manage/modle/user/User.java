package com.fanfan.manage.modle.user;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="APP_USER_BASE")
@DynamicInsert(value=true)
@DynamicUpdate(value=true)
@SequenceGenerator(name="SEQ_USER_ID",sequenceName="SEQ_USER_ID",allocationSize=1)
public class User {
	
	@Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_USER_ID")
	private int id ;
	
	@Column(name="USER_NAME")
	private String name;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="NICK_NAME")
	private String nickName;
	
	@Column(name="SEX")
	private String sex;
	
	@Column(name="BIRTHDATE")
	private String birthdate;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="HEAD_IMAGE")
	private String headImage;
	
	@Column(name="REAL_NAME")
	private String realName;
	
	@Column(name="MOBILE_NO")
	private String mobile;
	
	@Column(name="TELEPHONE")
	private String telephone;
	
	@Column(name="QQ")
	private String qq;
	
	@Column(name="INTRO")
	private String intro;
	
	@Column(name="REG_PLATFORMID")
	private Integer regPlatFormId;
	
	@Column(name="DEVICE_ID")
	private String deviceId;
	
	@Column(name="REG_SOURCE")
	private String regSourse;
	
	@Column(name="SOURCE_ID")
	private String sourseId;
	
	@Column(name="REG_TIME",updatable=false)
	@Temporal(TemporalType.TIME)
	private Date regDate;
	
	@Column(name="IS_VIP")
	private Integer isVip;
	
	@Column(name="AUTH_FLAG")  //0未认证 1认证中 2已认证 3认证被拒 
	private Integer authFlag;
	
	@Column(name="AUTH_TIME")
	@Temporal(TemporalType.TIME)
	private Date authTime;
	
	@Column(name="USER_TYPE")
	private Integer userType; //0普通用户 1资深漫迷 2作者
	
	@Column(name="USER_LEVEL")
	private Integer userLevel;
	
	@Column(name="STATUS")
	private Integer status; //1正常2注销3禁用4异常 
	
	@Column(name="UPDATE_TIME")
	@Temporal(TemporalType.TIME)
	private Date updateTime;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public int getRegPlatFormId() {
		return regPlatFormId;
	}

	public void setRegPlatFormId(int regPlatFormId) {
		this.regPlatFormId = regPlatFormId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getRegSourse() {
		return regSourse;
	}

	public void setRegSourse(String regSourse) {
		this.regSourse = regSourse;
	}

	public String getSourseId() {
		return sourseId;
	}

	public void setSourseId(String sourseId) {
		this.sourseId = sourseId;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getIsVip() {
		return isVip;
	}

	public void setIsVip(int isVip) {
		this.isVip = isVip;
	}

	public int getAuthFlag() {
		return authFlag;
	}

	public void setAuthFlag(int authFlag) {
		this.authFlag = authFlag;
	}

	public Date getAuthTime() {
		return authTime;
	}

	public void setAuthTime(Date authTime) {
		this.authTime = authTime;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public int getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
	
	
	
}
