package com.YangKang.form;

import com.YangKang.validation.AccountIdIsExists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthChangePasswordForm {
    @AccountIdIsExists
    private Integer id;
    @NotBlank(message = "new Password not Blank")
    private String password;
}

