package com.YangKang.service;
import com.YangKang.entity.Account;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import com.YangKang.entity.SendMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.io.File;
@Component
public class MailService implements IMailService {
    @Autowired
    private IAccountService accountService;
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sender;

//    @Override
//    public void sendRegistrationUserConfirm(String email) {
//        Account account= accountService.findByEmail(email);
//        SendMail sendMail = new SendMail();
//        String confirmationUrl = "http://localhost:8080/api/auth/active_account?id=" + account.getId();
//        String subject = "Xin chào Mời bạn xác nhận đăng kí Account nhé!" ;
////        " + account.getEmail() + "," + "
//        String content = "Bạn đã đăng ký thành công. Click vào link dưới đây để kích hoạt tài khoản \n"
//                + confirmationUrl;
////        sendSimpleMail(email,subject,context);
//
//        sendEmail(email, subject, content);
//    }
//
//    @Override
//    public void sendUpdatePasswordConfirm(Integer id, String email) {
//        String confirmUrl = "http://localhost:8080/api/v1/auth/active_account?id=" + id;
//        String subject = "Xác Nhận thay đổi mật khẩu";
//        String content = "Bạn đã thay đổi thành công. Click vào link để kích hoạt tài khoản! \n" + confirmUrl;
//        sendEmail(email,subject,content);
//    }
@Override
public void sendRegistrationUserConfirm(String email) {
    try {
        Account account = accountService.findByEmail(email);

        String confirmationUrl = "http://localhost:8080/api/v1/auth/active_account?id=" + account.getId();

        String subject = "Xác Nhận Đăng Ký Account";
        String content = "Bạn đã đăng ký thành công. Click vào link dưới đây để kích hoạt tài khoản \n"
                + confirmationUrl;

        sendEmail(email, subject, content);
    } catch (Exception e){
        System.out.println("e = " + e);
    }

}

    @Override
    public void sendUpdatePasswordConfirm(Integer id, String email) {


        String confirmationUrl = "http://localhost:8080/api/v1/auth/active_account?id=" + id;

        String subject = "Xác Nhận Thay Đổi Mật Khẩu";
        String content = "Bạn đã đổi mật khẩu thành công. Click vào link dưới đây để kích hoạt tài khoản \n"
                + confirmationUrl;

        sendEmail(email, subject, content);
    }

    private void sendEmail(final String recipientEmail, final String subject, final String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject(subject);
        message.setText(content);

        javaMailSender.send(message);
    }

    public String sendSimpleMail(SendMail sendMail)
    {

        // Try block to check for exceptions
        try {

            // Creating a simple mail message
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(sendMail.getRecipient()); // người nhận
            mailMessage.setText(sendMail.getMsgBody()); // message
            mailMessage.setSubject(sendMail.getSubject()); // chủ đề

            // Sending the mail
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            return "Error while Sending Mail";
        }
    }

//    @Override
//    public String sendSimpleMail(final String recipientEmail, final String subject, final String content) {
//        try {
//            SimpleMailMessage mailMessage = new SimpleMailMessage();
//            mailMessage.setFrom(sender);
//            mailMessage.setTo(recipientEmail);
//            mailMessage.setText(subject);
//            mailMessage.setSubject(content);
//
//            javaMailSender.send(mailMessage);
//            return "Mail send Sucessfully!!!";
//        }catch (Exception exception){
//            return "Error While Sending Mail!!!";
//
//        }
//
//    }

    @Override
    public String sendMailWithAttachment(SendMail sendMail) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(sendMail.getRecipient());
            mimeMessageHelper.setText(sendMail.getMsgBody());
            mimeMessageHelper.setSubject(sendMail.getSubject());

            FileSystemResource file = new FileSystemResource(new File(sendMail.getAttachment()));
            mimeMessageHelper.addAttachment(file.getFilename(),file);
            javaMailSender.send(mimeMessage);
            return "Mail and Attachment Send Succesfully!!!";

        }catch (Exception exception){
                return "Mail And Attachement Error!!!";
        }

    }

//    private void sendEmail(final String recipientEmail, final String subject, final String content) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(recipientEmail);
//        message.setSubject(subject);
//        message.setText(content);
//
//        javaMailSender.send(message);
//    }

}
