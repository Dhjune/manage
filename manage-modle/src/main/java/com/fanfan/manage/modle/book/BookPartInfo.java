package com.fanfan.manage.modle.book;

import com.fanfan.manage.modle.base.PlatForm;
import javax.persistence.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * Created with IntelliJ IDEA.
 * User: fanfan
 * Date: 14-3-3
 * Time: 下午3:42
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "APP_BOOK_PART_INFO")
@DynamicInsert(value=true)
@DynamicUpdate(value=true)
public class BookPartInfo  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7510308255298119360L;

	@EmbeddedId
	private BookPartInfoPK pk ;

    @Column(name = "IMAGE_URL")
    private String imageUrl;
    
    @Column(name = "FILE_NAME")
    private String fileName;


	@Column(name = "FILE_TYPE")
    private String fileType;

    @Column(name = "FILE_SIZE")
    private int fileSize;

    @Column(name = "HAS_POINT")
    private int hasPoint;

    @Column(name = "MAX_POINTS")
    private int maxPoints;

    @Column(name = "HAS_FEE")
    private int hasFee;

    @Column(name = "FEE",precision=6,scale=2,columnDefinition="NUMBER(6,2)")
    private Double fee;
   
	@Column(name = "STATUS")
    private int status;

    
    @Column(name = "ADD_TIME",updatable=false)
    @Temporal(TemporalType.TIME)
    private Date addTime;

    @Column(name = "UPDATE_USER")
    private String upDateUser;

    @Column(name = "UPDATE_TIME")
    @Temporal(TemporalType.TIME)
    private Date upDateTime;

   

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
    
    
    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public int getHasPoint() {
        return hasPoint;
    }

    public void setHasPoint(int hasPoint) {
        this.hasPoint = hasPoint;
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(int maxPoints) {
        this.maxPoints = maxPoints;
    }

    public int getHasFee() {
        return hasFee;
    }

    public void setHasFee(int hasFee) {
        this.hasFee = hasFee;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
		this.fee = fee;
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

	public BookPartInfoPK getPk() {
		return pk;
	}

	public void setPk(BookPartInfoPK pk) {
		this.pk = pk;
	}
    
    
    
}
