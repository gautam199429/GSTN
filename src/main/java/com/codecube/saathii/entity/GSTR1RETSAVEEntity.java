package com.codecube.saathii.entity;

import io.swagger.annotations.ApiModelProperty;

public class GSTR1RETSAVEEntity {
	@ApiModelProperty(example = "RETSAVE")
	private String action;
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getHmac() {
		return hmac;
	}
	public void setHmac(String hmac) {
		this.hmac = hmac;
	}
	private String data;
	private String hmac;

}
