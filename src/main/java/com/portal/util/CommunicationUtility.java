package com.portal.util;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.ow2.bonita.connector.impl.email.SMTPAuthenticator;

import com.sun.mail.util.MailSSLSocketFactory;

public class CommunicationUtility {
	
	final static String senderEmailID="687915@cognizant.com";
	final static String senderEmailPassword="";
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	final static String mailPort="465";
	public static void SendMail(
            String receipientEmailID,
            String subject,
            String body,
            boolean isHtmlBody) throws GeneralSecurityException, MessagingException, UnsupportedEncodingException {

        SMTPAuthenticator auth = new SMTPAuthenticator(senderEmailID, senderEmailPassword);

        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);

        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", "mail.cognizant.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", mailPort);
        props.put("mail.smtp.socketFactory.port", mailPort);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.quitwait", "false");

        Session session = Session.getInstance(props, auth);
        session.setDebug(true);

        MimeMessage message = new MimeMessage(session);

        message.setFrom(new InternetAddress(senderEmailID, ""));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(receipientEmailID));
        message.setRecipient(Message.RecipientType.CC, new InternetAddress(senderEmailID));
        message.setSubject(subject);

        if (isHtmlBody) {
            message.setContent(body, "text/html; charset=utf-8");
        } else {
            message.setText(body);
        }

        System.out.println("Before Sending...");
        Transport.send(message);
        System.out.println("Mail Send.");
    }

     public static void main(String[] args) {
    	 try {
			CommunicationUtility.SendMail("687915@cognizant.com", "test for Assesssment portal", "Hi,This mail is from our assessment portal", false);
		} catch (UnsupportedEncodingException | GeneralSecurityException | MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }
}
