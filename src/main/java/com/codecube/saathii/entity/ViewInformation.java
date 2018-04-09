package com.codecube.saathii.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "usertbls")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ViewInformation.findAll", query = "SELECT t FROM ViewInformation t")
})
public class ViewInformation implements Serializable{

	private static final long serialVersionUID = 1L;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreated_Date() {
		return created_Date;
	}
	public void setCreated_Date(Date created_Date) {
		this.created_Date = created_Date;
	}
	public String getCreated_By() {
		return created_By;
	}
	public void setCreated_By(String created_By) {
		this.created_By = created_By;
	}
	public Date getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	@Id
	@Basic(optional=false)
	@Column(name="userid")
	private String userId;
	
	@Basic(optional=false)
	@Column(name="password")
	private String password;
	
	@Basic(optional=false)
	@Column(name="created_date")
	private Date created_Date;
	
	@Basic(optional=false)
	@Column(name="created_By")
	private String created_By;
	
	@Basic(optional=false)
	@Column(name="updatd_date")
	private Date update_date;
	
	@Basic(optional=false)
	@Column(name="deleted")
	private String deleted;
	
}
