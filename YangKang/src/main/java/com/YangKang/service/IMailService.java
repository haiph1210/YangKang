package com.YangKang.service;

import com.YangKang.entity.SendMail;

public interface IMailService {
    void sendRegistrationUserConfirm(String email);
    void sendUpdatePasswordConfirm(Integer id,String email);
//    String sendSimpleMail(final String recipientEmail, final String subject, final String content);
    String sendMailWithAttachment(SendMail sendMail);

    String sendSimpleMail(SendMail sendMail);
}
