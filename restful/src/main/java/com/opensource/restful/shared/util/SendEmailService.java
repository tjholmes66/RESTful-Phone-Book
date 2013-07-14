package com.opensource.restful.shared.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SendEmailService implements ISendEmailService
{
    @Autowired
    private MailSender mailSender;

    public MailSender getMailSender()
    {
        return mailSender;
    }

    public void setMailSender(MailSender mailSender)
    {
        this.mailSender = mailSender;
    }

    @Override
    public void sendMail(String from, String to, String subject, String msg)
    {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(msg);
        mailSender.send(message);
    }
}
