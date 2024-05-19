package com.backend.ecommerce.Mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

@Component
public class Mail {
    private String from;
    private String password;
    private Properties properties;
    private Session session;
    private MimeMessage mimeMessage;
    private BodyPart messageBody;
    private Multipart multipart;


    public Mail() {
        this.from = "udemymaildemo@gmail.com";
        this.password = "bgpcmvimkjdgntqp";
        this.properties = System.getProperties();
        this.properties.put("mail.smtp.auth", "true");
        this.properties.put("mail.smtp.starttls.enable", "true");
        this.properties.put("mail.smtp.host", "smtp.gmail.com");
        this.properties.put("mail.smtp.port", "587");
        this.properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        this.session = Session.getInstance(this.properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });
    }

    public void sendEmail(String to, String subject, String text){
        try {
            this.mimeMessage = new MimeMessage(this.session);
            this.mimeMessage.setFrom(new InternetAddress(this.from));
            this.mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            this.mimeMessage.setSubject(subject);

            this.messageBody = new MimeBodyPart();
            this.messageBody.setText(text);

            this.multipart = new MimeMultipart();
            this.multipart.addBodyPart(this.messageBody);

            this.mimeMessage.setContent(this.multipart);
            Transport.send(this.mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


}
