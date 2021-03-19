package com.pos.backend.notification;

public interface IEmailService {
	public void sendEmail(String to,String subject,String messageHTML);
}
