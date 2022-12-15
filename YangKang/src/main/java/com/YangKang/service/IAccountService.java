package com.YangKang.service;

import com.YangKang.entity.Account;
import com.YangKang.form.AccountCreateFrom;
import com.YangKang.form.AccountFillterForm;
import com.YangKang.form.AccountUpdateForm;
import com.YangKang.form.AuthChangePasswordForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IAccountService extends UserDetailsService {
    Page<Account> findAll(Pageable pageable, AccountFillterForm form);

    Account findById(Integer id);

    Account findByUsername(String username);

    Account findByEmail(String email);

    void createAccount(AccountCreateFrom from);

    void updateAccount(AccountUpdateForm form);

    void delete(Integer id);

    void deleteListId(List<Integer> ids);

    void changePassword(AuthChangePasswordForm form);

    void activeAccount(Integer id);
}
