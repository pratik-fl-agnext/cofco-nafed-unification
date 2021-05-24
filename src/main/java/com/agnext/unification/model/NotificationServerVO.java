package com.agnext.unification.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotificationServerVO {

	@JsonProperty("notification_type_id")
	private Integer notificationTypeId;

	@JsonProperty("push_notification")
	private PushNotificationData pushNotification;

	public Integer getNotificationTypeId() {
		return notificationTypeId;
	}

	public void setNotificationTypeId(Integer notificationTypeId) {
		this.notificationTypeId = notificationTypeId;
	}

	public PushNotificationData getPushNotification() {
		return pushNotification;
	}

	public void setPushNotification(PushNotificationData pushNotification) {
		this.pushNotification = pushNotification;
	}
}
