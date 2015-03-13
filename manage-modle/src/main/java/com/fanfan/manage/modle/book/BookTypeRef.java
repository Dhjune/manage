package com.fanfan.manage.modle.book;

import com.fanfan.manage.modle.base.PlatForm;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: fanfan
 * Date: 14-3-3
 * Time: 下午3:44
 * To change this template use File | Settings | File Templates.
 */


@Entity
@Table(name = "APP_BOOK_TYPE_REF",uniqueConstraints={@UniqueConstraint(columnNames={"BOOK_ID","TYPE_ID"})})
@DynamicInsert(true)
@DynamicUpdate(true)
public class BookTypeRef implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1018140733143280080L;

	@EmbeddedId
	private BookTypeRefPK pk;
	

    @Column(name = "DISP_ORDER")
    private int dispOrder;
    
    
    @Column(name = "STATUS")
    private int status;

    @Column(name = "UPDATE_USER")
    private String updateUser;
   
    @Column(name = "UPDATE_TIME")
    @Temporal(TemporalType.TIME)
    private Date updateTime;

   

    public int getDispOrder() {
        return dispOrder;
    }

    public void setDispOrder(int dispOrder) {
        this.dispOrder = dispOrder;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

	public BookTypeRefPK getPk() {
		return pk;
	}

	public void setPk(BookTypeRefPK pk) {
		this.pk = pk;
	}
}
