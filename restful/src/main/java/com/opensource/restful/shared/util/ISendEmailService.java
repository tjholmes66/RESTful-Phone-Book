package com.opensource.restful.shared.util;

public interface ISendEmailService
{

    void sendMail(String from, String to, String subject, String msg);

}
