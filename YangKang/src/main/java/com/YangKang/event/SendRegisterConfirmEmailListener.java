package com.YangKang.event;

import com.YangKang.service.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class SendRegisterConfirmEmailListener implements ApplicationListener<OnSendRegisterUserConfirmViaEmailEven> {
    @Autowired
    private IMailService mailService;
    private void sendConfirmViaEmail(String email){
        mailService.sendRegistrationUserConfirm(email);
    }
    @Override
    public void onApplicationEvent(OnSendRegisterUserConfirmViaEmailEven event) {
        sendConfirmViaEmail(event.getEmail());
    }
}
