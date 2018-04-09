package com.codecube.saathii.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "gsterrorcodes")
@XmlRootElement
public class GstErrorCodes {
	
	@Id
	@Basic(optional=false)
	@Column(name="error_cd")
	private String error_cd;
	
	@Basic(optional=false)
	@Column(name="error_description")
	private String error_description;
	
	@Basic(optional=false)
	@Column(name="error_message")
	private String error_message;
	
	public String getError_cd() {
		return error_cd;
	}
	public void setError_cd(String error_cd) {
		this.error_cd = error_cd;
	}
	public String getError_description() {
		return error_description;
	}
	public void setError_description(String error_description) {
		this.error_description = error_description;
	}
	public String getError_message() {
		return error_message;
	}
	public void setError_message(String error_message) {
		this.error_message = error_message;
	}	
}
