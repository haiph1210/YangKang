package com.YangKang.form;

import com.YangKang.entity.Account;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AccountFillterForm {
    private String search;
    private Account.Role role;
    private Account.Status status;
//    private LocalDate createdDate;
//    private LocalDate minCreatedDate; // ngày tạo nhỏ nhất <GenterThan: lớn hơn hoặc bằng>
//    private LocalDate maxCreatedDate; // ngày tạo lớn nhất <Less than: nhỏ hơn hoặc bằng>
//    private Integer year;

}
