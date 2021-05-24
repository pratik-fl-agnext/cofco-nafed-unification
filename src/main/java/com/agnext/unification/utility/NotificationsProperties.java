package com.agnext.unification.utility;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "notification")
public class NotificationsProperties {

    
	private String emailFrom;
	
	private String[] emailAdmin;
	
	private String serverUrl;

	public String getEmailFrom() {
		return emailFrom;
	}

	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

	public String[] getEmailAdmin() {
		return emailAdmin;
	}

	public void setEmailAdmin(String[] emailAdmin) {
		this.emailAdmin = emailAdmin;
	}

	public String getServerUrl() {
		return serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	
		
   
}
