package com.pos.backend.notification.impl;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.pos.backend.notification.IEmailService;
import com.pos.backend.utils.IEmailConstants;

@Service("emailService")
public class EmailServiceImpl implements IEmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

	@Async
	@Override
	public void sendEmail(String to, String subject, String messageHTML) {
		MimeMessage msg = javaMailSender.createMimeMessage();

		try {
			// true = multipart message
			MimeMessageHelper helper = new MimeMessageHelper(msg, false);
			helper.setFrom(IEmailConstants.EMAIL_FROM);
			helper.setTo(to);
			helper.setSubject("POS - Account Created");

			// true = text/html
			helper.setText(messageHTML, true);

			javaMailSender.send(msg);
		} catch (Exception e) {
			logger.error("Mail sending failed to: " + to);
		}
	}

}
