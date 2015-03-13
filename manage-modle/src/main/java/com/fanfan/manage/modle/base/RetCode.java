package com.fanfan.manage.modle.base;

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

@Entity
@Table(name="APP_BASE_RETCODE")
@SequenceGenerator(name="APP_BASE_RETCODE",sequenceName="APP_BASE_RETCODE",allocationSize=1)
@DynamicInsert(true)
public class RetCode {

	@Id
	@Column(name="RET_CODE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="APP_BASE_RETCODE")
	private String retCode;
	
	@Column(name="RET_MESSAGE_ZH")
	private String messageZh;
	
	@Column(name="RET_MESSAGE_EN")
	private String messageEn;
	
	@Column(name="CREATE_TIME")
	@Temporal(TemporalType.TIME)
	private Date createTime;

	
	public Date getCreateTime() {
		
		return createTime;
		
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the messageEn
	 */
	public String getMessageEn() {
		return messageEn;
	}

	/**
	 * @param messageEn the messageEn to set
	 */
	public void setMessageEn(String messageEn) {
		this.messageEn = messageEn;
	}

	public String getMessageZh() {
		return messageZh;
	}

	public void setMessageZh(String messageZh) {
		this.messageZh = messageZh;
	}

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	
}
