package com.YangKang.service;

import com.YangKang.dto.AccountDTO;
import com.YangKang.entity.Account;
import com.YangKang.event.OnSendRegisterUserConfirmViaEmailEven;
import com.YangKang.event.OnUpdatePasswordEvent;
import com.YangKang.form.AccountCreateFrom;
import com.YangKang.form.AccountFillterForm;
import com.YangKang.form.AccountUpdateForm;
import com.YangKang.form.AuthChangePasswordForm;
import com.YangKang.repository.IAccountRepository;
import com.YangKang.specification.AccountSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IAccountRepository repository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public Page<Account> findAll(Pageable pageable, AccountFillterForm form){
        Specification<Account> specification = AccountSpecification.buildWhere(form);
        return repository.findAll(specification,pageable);
    }
    @Override
    public Account findById(Integer id){
        return repository.findById(id).orElse(null);
    }
    @Override
    public Account findByUsername(String username){
        return repository.findByUsername(username);
    }
    @Override
    public Account findByEmail(String email){
        return repository.findByEmail(email);
    }
    @Override
    public void createAccount(AccountCreateFrom from){
        String password = passwordEncoder.encode(from.getPassword());
        Account account = mapper.map(from,Account.class);
        account.setPassword(password);
       account.setStatus(Account.Status.NOT_ACTIVE);
       repository.save(account);
       sendConfirmRegisterUser(account.getEmail());

    }
    @Override
    public void updateAccount(AccountUpdateForm form){
        String password = passwordEncoder.encode(form.getPassword());
        Account account = mapper.map(form,Account.class);
        account.setPassword(password);
        account.setStatus(Account.Status.NOT_ACTIVE);
        repository.save(account);
    }
    private void sendConfirmRegisterUser(String email){
        eventPublisher.publishEvent(new OnSendRegisterUserConfirmViaEmailEven(email));
    }



    @Override
    public void delete(Integer id){
        repository.deleteById(id);
    }
    @Override
    public void deleteListId(List<Integer> ids){
        repository.deleteAllById(ids);
    }
    @Override
    public void changePassword(AuthChangePasswordForm form){
//        Account account = mapper.map(form,Account.class);
        System.out.println( form.getId());
        System.out.println( form.getPassword());
        repository.changePassword(form.getId(),passwordEncoder.encode(form.getPassword()));
        // form -> account
        sendUpdatePassword(form);
    }
    private void sendUpdatePassword(AuthChangePasswordForm form){
        Account account = mapper.map(form, Account.class);
        eventPublisher.publishEvent(new OnUpdatePasswordEvent(account));
    }
    @Override
    public void activeAccount(Integer id){
        Account account = repository.findById(id).orElse(null);
        account.setStatus(Account.Status.ACTIVE);
        repository.save(account);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = repository.findByUsername(username);
        System.out.println("load user: ");
        System.out.println(account);
        if (account == null) throw new UsernameNotFoundException(username);

        if (account.getRole() != null){
            return new User(
                    account.getUsername(),
                    account.getPassword(),
                    AuthorityUtils.createAuthorityList(account.getRole().toString())
            );
        } else {
            return new User(
                    account.getUsername(),
                    account.getPassword(),
                    AuthorityUtils.createAuthorityList("EMPOYEE")
            );
        }
    }
}
