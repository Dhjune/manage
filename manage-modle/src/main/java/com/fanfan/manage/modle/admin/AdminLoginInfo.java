package com.fanfan.manage.modle.admin;

import java.math.BigDecimal;

public class AdminLoginInfo {
	
	private BigDecimal ADMIN_ID;
	
	private BigDecimal ADMIN_ROLE_ID;
	
	private String LOGIN_NAME;
	
	private String PASSWORD;
	
	private String ROLE_NAME;
	
	private boolean success;
	
	private String message;
	
	public BigDecimal getAdmin_id() {
		return ADMIN_ID;
	}

	public void setAdmin_id(BigDecimal admin_id) {
		this.ADMIN_ID = admin_id;
	}

	public BigDecimal getAdmin_role_id() {
		return ADMIN_ROLE_ID;
	}

	public void setAdmin_role_id(BigDecimal admin_role_id) {
		this.ADMIN_ROLE_ID = admin_role_id;
	}

	public String getLogin_name() {
		return LOGIN_NAME;
	}

	public void setLogin_name(String login_name) {
		this.LOGIN_NAME = login_name;
	}

	public String getPassword() {
		return PASSWORD;
	}

	public void setPassword(String password) {
		this.PASSWORD = password;
	}

	public String getRole_name() {
		return ROLE_NAME;
	}

	public void setRole_name(String role_name) {
		this.ROLE_NAME = role_name;
	}

	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	
	
}
