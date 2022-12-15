package com.YangKang.controller;

import com.YangKang.entity.SendMail;
import com.YangKang.service.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/emails")
public class MailController {
    @Autowired
    private IMailService service;
    @PostMapping("/sendSimpleMail")
    public String sendMail(@RequestBody SendMail sendMail){
        String sendSimpleMail = service.sendSimpleMail(sendMail);
        return sendSimpleMail;
    }

    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(
            @RequestBody SendMail sendMail)
    {
        String status
                = service.sendMailWithAttachment(sendMail);
        return status;
    }
}
