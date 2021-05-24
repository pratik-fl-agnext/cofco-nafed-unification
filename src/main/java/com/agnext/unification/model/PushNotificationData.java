package com.agnext.unification.model;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PushNotificationData {

	@JsonProperty("client_id")
	private String client;

	@JsonProperty("data")
	private JSONObject data;

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public JSONObject getData() {
		return data;
	}

	public void setData(JSONObject data) {
		this.data = data;
	}
}
