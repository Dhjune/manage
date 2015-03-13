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
import org.hibernate.annotations.DynamicUpdate;


@Entity
@Table(name="APP_BASE_TRADETYPE")
@SequenceGenerator(name="APP_BASE_TRADETYPE",sequenceName="APP_BASE_TRADETYPE",allocationSize=1)
@DynamicInsert(true)
@DynamicUpdate(value=true)
public class TradeType {
	
	@Id
	@Column(name="TRADE_TYPE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="APP_BASE_TRADETYPE")
	private String tradeType;
	
	@Column(name="TRADE_NAME")
	private String tradeName;
	
	@Column(name="TRADE_DIRECTION")
	private int direction;
	
	@Column(name="STATUS")
	private int status ;
	
	@Column(name="OPEN_TIME")
	@Temporal(TemporalType.TIME)
	private Date openTime;
	
	@Column(name="CLOSE_TIME")
	@Temporal(TemporalType.TIME)
	private Date closeTime;
	
	@Column(name="CREATE_TIME")
	@Temporal(TemporalType.TIME)
	private Date createTime;

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public Date getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
		
}
