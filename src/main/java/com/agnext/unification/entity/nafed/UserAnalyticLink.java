package com.agnext.unification.entity.nafed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the user_analytic_links database table.
 * 
 */
@Entity
@Table(name = "user_analytic_links")
public class UserAnalyticLink {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @Column(name = "user_id")
    private Long userId;
    
    @Column(name = "link")
    private String link;
	
    @Column(name = "status")
    private Long status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "UserAnalyticLink [id=" + id + ", userId=" + userId + ", link=" + link + ", status=" + status + "]";
	}
    
    
}
