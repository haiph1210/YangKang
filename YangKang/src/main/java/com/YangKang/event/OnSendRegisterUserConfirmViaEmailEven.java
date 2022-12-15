package com.YangKang.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;
@Getter
@Setter
public class OnSendRegisterUserConfirmViaEmailEven extends ApplicationEvent{
    private static final long serialVersionUID = 1L;
    private String email;

    public OnSendRegisterUserConfirmViaEmailEven(String email) {
        super(email);
        this.email = email;
    }



}
