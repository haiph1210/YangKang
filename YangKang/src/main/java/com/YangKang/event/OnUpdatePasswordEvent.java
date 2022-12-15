package com.YangKang.event;

import com.YangKang.entity.Account;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
@Getter
@Setter
public class OnUpdatePasswordEvent extends ApplicationEvent {
    private int id;
    private String email;

    public OnUpdatePasswordEvent(Object source) {
        super(source);
        Account account = (Account) source;
        this.id = account.getId();
        this.email = account.getEmail();
    }
}
