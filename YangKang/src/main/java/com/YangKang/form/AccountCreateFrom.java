package com.YangKang.form;

import com.YangKang.entity.Account;
import com.YangKang.validation.AccountUsernameExist;
import com.YangKang.validation.AccountUsernameNotExists;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
public class AccountCreateFrom {
    @NotBlank(message = "username is cannot blank")
    @AccountUsernameNotExists
    private String username;
    @Email(message = "Invalid Email")
    private String email;
    @NotBlank(message = "Password is cannot blank")
    private String password;
    private String firstName;
    private String lastName;
    @NotEmpty(message = "Role mustn't be null value")
    private Account.Role role;
    @NotEmpty(message = "Status mustn't be null value")
    private Account.Status status;
    private String avatarurl;
}
