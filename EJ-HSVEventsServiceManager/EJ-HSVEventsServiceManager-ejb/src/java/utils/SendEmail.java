/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Vish
 */
public class SendEmail {
    
    private final int port = 587;
    private final String host = "smtp.gmail.com";
    private final String from = "testappedev@gmail.com";
    private final boolean auth = true;
    private final String username = "testappedev@gmail.com";
    private final String password = "test@app123";
    public void sendEmail(String emailId, String userMessage, String mailSubject){                    
        boolean debug = true;
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.password",password);
        Authenticator authenticator = null;
        if (auth) {
            props.put("mail.smtp.auth", true);
            authenticator = new Authenticator() {
                private PasswordAuthentication pa = new PasswordAuthentication(username, password);
                @Override
                public PasswordAuthentication getPasswordAuthentication() {
                    return pa;
                }
            };
        }
        Session session = Session.getInstance(props, authenticator);
        session.setDebug(debug);
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(emailId)};
            message.setRecipients(Message.RecipientType.TO, address);
            message.setSubject(mailSubject);
            message.setSentDate(new Date());
            message.setText(userMessage);
            Transport.send(message);
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }    
}