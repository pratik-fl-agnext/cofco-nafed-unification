package com.agnext.unification.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserLinkModel {
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("user_id")
	private Long userId;
	
	@JsonProperty("role")
	private String role;
	
	@JsonProperty("links")
	private List<String> links;
	
	@JsonProperty("status")
	private String status;
	

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<String> getLinks() {
		return links;
	}

	public void setLinks(List<String> links) {
		this.links = links;
	}

	@Override
	public String toString() {
		return "UserLinkModel [id=" + id + ", userId=" + userId + ", role=" + role + ", links=" + links + ", status="
				+ status + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
