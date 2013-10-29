package com.app.spring.util;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.plaf.basic.BasicPasswordFieldUI;

import org.apache.log4j.Logger;

import com.app.spring.service.Constants;
import com.app.spring.service.ServiceException;

public class EmailUtil {
    private String emailPass;
    private String emailUser;
    private String emailHost;
    private String emailPort;

    private String PROP_MAIL_HOST_NAME = "mail.smtp.host";
    private String PROP_MAIL_PORT_NAME = "mail.smtp.port";
    private String PROP_MAIL_AUTH_NAME = "mail.smtp.auth";
    private String PROP_MAIL_TLS_NAME = "mail.smtp.starttls.enable";
    private String PROP_MAIL_FROM_NAME = "mail.smtp.user";

    private String PROP_MAIL_FROM_VALUE = "noreply@afterthebeep.us";

    private Logger log;

    public EmailUtil(String pEmailUser, String pEmailPass, String pEmailHost, String pEmailPort) {
		this.log = Logger.getLogger(this.getClass());
        this.emailUser = pEmailUser;
        this.emailPass = pEmailPass;
        this.emailHost = pEmailHost;
        this.emailPort = pEmailPort;
    }

    private Session getMailSession() {           
        Properties props = new Properties();
        props.put(PROP_MAIL_FROM_NAME, PROP_MAIL_FROM_VALUE);
        props.put(PROP_MAIL_HOST_NAME, this.emailHost);
        props.put(PROP_MAIL_PORT_NAME, this.emailPort);
        props.put(PROP_MAIL_AUTH_NAME, true);
        props.put(PROP_MAIL_TLS_NAME, true);

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(emailUser, emailPass);
                }
        });

        return session;              
    }       

    public void sendActivateUserEmail(String pUserEmail, int pUserId, String pUserName, String pActivationId) throws ServiceException {
        try {
            getLog().info("Calling sendActivateUserEmail; [Email Address: " + pUserEmail + "; User Id: " + pUserId + "; Activation ID: " + pActivationId + "]"); 

            MimeMessage message = new MimeMessage(this.getMailSession());          
            message.setFrom(new InternetAddress(PROP_MAIL_FROM_VALUE));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(pUserEmail));
            message.setSubject("Action required to activate user at afterthebeep");
            
            Multipart multipart = new MimeMultipart();
            BodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent("Thanks for registering with afterthebeep. " +
            		"Follow the below link to activate your account for username: " + pUserName + 
            		"<br><br><a href=\"http://afterthebeep.us/activate.jsp?uid=" + pUserId + "&aid=" + pActivationId + "\">" + 
            		"http://afterthebeep.us/activate.jsp?uid=" + pUserId + "&aid=" + pActivationId + 
            		"</a>", "text/html; charset=ISO-8859-1");
            
            multipart.addBodyPart(htmlPart);
            message.setContent(multipart);
            Transport.send(message);
            
        } catch (MessagingException anEx) {
            getLog().error(anEx.getMessage());
            throw new ServiceException(Constants.RESPONSE_CODE_ERROR, Constants.RESPONSE_MESSAGE_SYSTEM_ERROR);
        }
    }

    private Logger getLog() {
        return this.log;
    }
}
