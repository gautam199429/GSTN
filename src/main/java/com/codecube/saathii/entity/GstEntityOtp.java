package com.codecube.saathii.entity;

import io.swagger.annotations.ApiModelProperty;

public class GstEntityOtp {
	@ApiModelProperty(example = "OTPREQUEST")
	private String action;
	private String app_key;
	private String username;
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getApp_key() {
		return app_key;
	}
	public void setApp_key(String app_key) {
		this.app_key = app_key;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
