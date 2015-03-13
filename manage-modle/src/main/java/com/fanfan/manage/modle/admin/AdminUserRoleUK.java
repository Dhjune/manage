package com.fanfan.manage.modle.admin;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Embeddable
public class AdminUserRoleUK implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3600814641830789603L;

	@OneToOne
	@JoinColumn(name="ADMIN_ID")
	private AdminUser user;
	
	@OneToOne
	@JoinColumn(name="ADMIN_ROLE_ID")
	private AdminRole role;

	public AdminUser getUser() {
		return user;
	}

	public void setUser(AdminUser user) {
		this.user = user;
	}

	public AdminRole getRole() {
		return role;
	}

	public void setRole(AdminRole role) {
		this.role = role;
	}
	
}
