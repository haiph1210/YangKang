package com.YangKang.dto;

import com.YangKang.entity.Account;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class AccountDTO {
    private Integer id;
    private String username;
    private String email;
    private String fullName;
    private Account.Role role;
    private Account.Status status;
    private String avatarurl;
    private LocalDate createdDate;
    private LocalDateTime updateAt;
}
