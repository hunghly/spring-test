package com.codeup.springtest.services;

import com.codeup.springtest.models.Post;
import com.mysql.cj.xdevapi.SchemaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("mailService")
public class EmailService {

//    @Autowired
    public JavaMailSender emailSender;

    public EmailService (JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Value("${spring.mail.from}")
    private String from;

    public void prepareAndSend(Post post, String subject, String body) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(from);
        msg.setTo(post.getUser().getEmail());
        msg.setSubject(subject);
        msg.setText(body);

        try {
            this.emailSender.send(msg);
        } catch (MailException me) {
            System.err.println(me.getMessage());
        }
    }

}
