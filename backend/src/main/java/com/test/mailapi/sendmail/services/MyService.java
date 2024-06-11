package com.test.mailapi.sendmail.services;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.test.mailapi.sendmail.model.EmailMessage;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MyService {

	@Autowired
	private JavaMailSender javaMailSender;
	@Value("${spring.mail.username}")
	private String sender;

	// Method 1
	// To send a simple email
	// public String sendSimpleMail(EmailMessage details) {
	// // Try block to check for exceptions
	// try {
	// // Creating a simple mail message
	// SimpleMailMessage mailMessage = new SimpleMailMessage();
	// // Setting up necessary details
	// mailMessage.setFrom(sender);
	// mailMessage.setTo(details.getTo());
	// mailMessage.setText(details.getBody());
	// mailMessage.setSubject(details.getSubject());
	// // Sending the mail
	// javaMailSender.send(mailMessage);
	// return "Mail Sent Successfully...";
	// }
	// // Catch block to handle the exceptions
	// catch (Exception e) {
	// e.printStackTrace();
	// return "Error while Sending Mail";
	// }
	// }
	// Method 2
	// To send an email with attachment
	public void sendMailWithAttachment(EmailMessage details) {
		// Creating a mime message
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper;
		try {
			// Setting multipart as true for attachments to
			// be send
			mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setFrom(sender);
			mimeMessageHelper.setTo(details.getTo());
			mimeMessageHelper.setText(details.getBody());
			mimeMessageHelper.setSubject(
					details.getSubject());
			// Adding the attachment
			FileSystemResource file = new FileSystemResource(
					new File("c:\\Users\\91982\\Desktop\\mohit.pdf"));
			mimeMessageHelper.addAttachment(
					file.getFilename(), file);
			// Sending the mail
			javaMailSender.send(mimeMessage);
		}
		// Catch block to handle MessagingException
		catch (MessagingException e) {
			// Display message when exception occurred
			e.printStackTrace();
		}
	}

	// public void sendEmail(String body, String subject, String to) {
	// // Sender's email ID
	// String from = "msakra000@gmail.com";
	// // Assuming you are sending email from through gmail's smtp
	// String host = "smtp.gmail.com";
	// // Get system properties
	// Properties properties = System.getProperties();
	// // Setup mail server
	// properties.put("mail.smtp.host", host);
	// properties.put("mail.smtp.port", "587");
	// properties.put("mail.smtp.auth", "true");
	// properties.put("mail.smtp.starttls.enable", "true");
	// properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	// // Get the Session object.
	// Session session = Session.getInstance(properties, new Authenticator() {
	// protected PasswordAuthentication getPasswordAuthentication() {
	// return new PasswordAuthentication("username", "password");
	// }
	// });
	// try {
	// // Create a default MimeMessage object.
	// MimeMessage message = new MimeMessage(session);
	// // Set From: header field
	// message.setFrom(new InternetAddress(from));
	// // Set To: header field
	// message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	// // Set Subject: header field
	// message.setSubject(subject);
	// // Now set the actual message
	// message.setText(body);
	// // Send message
	// Transport.send(message);
	// System.out.println("Sent message successfully....");
	// } catch (MessagingException mex) {
	// mex.printStackTrace();
	// }
	// }
	// attachement..
	// // file path
	// String path = "C:\\Users\\user\\Desktop\\ca_logo.png";
	// MimeMultipart mimeMultipart = new MimeMultipart();
	// // text
	// // file
	// MimeBodyPart textMime = new MimeBodyPart();
	// MimeBodyPart fileMime = new MimeBodyPart();
	// try
	// {
	// textMime.setText(message);
	// File file = new File(path);
	// fileMime.attachFile(file);
	// mimeMultipart.addBodyPart(textMime);
	// mimeMultipart.addBodyPart(fileMime);
	// }catch(
	// Exception e)
	// {
	// e.printStackTrace();
	// }
	// m.setContent(mimeMultipart);
	// // send
	// // Step 3 : send the message using Transport class
	// Transport.send(m);
	// }catch(
	// Exception e)
	// {
	// e.printStackTrace();
	// }
}