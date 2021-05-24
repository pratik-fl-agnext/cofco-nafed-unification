package com.agnext.unification.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agnext.notification.lib.service.EmailService;
@Component
public class EmailUtil {
	
	
	private static final String SUBJECT_CUSTOMER_CREATION="Customer Onboarded Successfully !!!";
	private static final String SUBJECT_USER_CREATION="User Onboarded Successfully !!!";
	private static final String EMAIL_TYPE_PLAIN="text/plain";
	private static final String EMAIL_TYPE_HTML="text/html";
	
	@Autowired
	NotificationsProperties nProperties;
	
	private static final String CONTENT_USER_CREATION="<p>Hi {name},</p><p>&nbsp;</p><p>Welcome to Qualix platform.</p>"
			+ "<p>Please enjoy seamless services using below credentials</p><p>User Name : {email}</p><p>Password : {password}</p>"
			+ "<p>&nbsp;</p><p>Please contact <a href=\"mailto:support@qualix.com\">support@qualix.com</a>&nbsp;for futher queries.</p>"
			+ "<p>&nbsp;</p><p>Regards,<br>Team Qualix</p><p>&nbsp;</p>";
	
	public boolean sendCustomerCreationMail(String[] to,String[] cc,String firstName,String email,String password) {
		String content = CONTENT_USER_CREATION.replace("{name}", firstName).replace("{email}", email).replace("{password}", password);
		EmailService.sendMail(nProperties.getEmailFrom(), to, cc, nProperties.getEmailAdmin(), 
				SUBJECT_CUSTOMER_CREATION, content, EMAIL_TYPE_HTML, nProperties.getServerUrl());
		return true;
	}
	
	public boolean sendUserCreationMail(String[] to,String[] cc,String firstName,String email,String password) {
		String content = CONTENT_USER_CREATION.replace("{name}", firstName).replace("{email}", email).replace("{password}", password);
		EmailService.sendMail(nProperties.getEmailFrom(), to, cc, nProperties.getEmailAdmin(), 
				SUBJECT_USER_CREATION, content, EMAIL_TYPE_HTML, nProperties.getServerUrl());
		return true;
	}

}
