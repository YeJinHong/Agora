package com.ssafy.api.service;

import com.ssafy.api.request.MailDto;
import com.ssafy.api.request.UserEmailReq;

import javax.mail.MessagingException;

public interface MailService {

    MailDto makeLinkMail(UserEmailReq userEmailReq) throws MessagingException;

    public void sendMail(MailDto mailDto) throws MessagingException;
}
