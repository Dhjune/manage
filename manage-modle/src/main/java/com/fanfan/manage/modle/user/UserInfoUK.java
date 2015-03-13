package com.fanfan.manage.modle.user;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fanfan.manage.modle.base.PlatForm;

@Embeddable
public class UserInfoUK implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1490267667037019408L;
	
	@JoinColumn(name="USER_ID")
	@OneToOne
	private User user;
	
	@JoinColumn(name="PLATFORM_ID")
	@OneToOne
	private PlatForm platForm;


	public PlatForm getPlatFome() {
		return platForm;
	}


	public void setPlatFome(PlatForm platForm) {
		this.platForm = platForm;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

}
