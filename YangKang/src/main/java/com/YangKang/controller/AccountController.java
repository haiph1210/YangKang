package com.YangKang.controller;

import com.YangKang.dto.AccountDTO;
import com.YangKang.entity.Account;
import com.YangKang.form.AccountCreateFrom;
import com.YangKang.form.AccountFillterForm;
import com.YangKang.form.AccountUpdateForm;
import com.YangKang.repository.IAccountRepository;
import com.YangKang.service.IAccountService;
import com.YangKang.validation.AccountIdIsExists;
import net.minidev.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("api/v1/accounts")
@Validated
public class AccountController {
    @Autowired
    private IAccountService service;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private JSONObject message;

    @GetMapping()
    public Page<AccountDTO> findAll(Pageable pageable, AccountFillterForm form){
        Page<Account> page = service.findAll(pageable,form);
        List<Account> accounts = page.getContent();
        List<AccountDTO> dtos = mapper.map(accounts,new TypeToken< List<AccountDTO>>(){}.getType());
        return new PageImpl<>(dtos,pageable,page.getTotalPages());
    }

    @GetMapping("/id/{id}")
    public AccountDTO findById(@PathVariable Integer id){
        Account account = service.findById(id);
        AccountDTO accountDTO = mapper.map(account, AccountDTO.class);
        return accountDTO;
    }
    @GetMapping("/username/{username}")
    public AccountDTO findByUsername(@PathVariable String username){
        Account account = service.findByUsername(username);
        AccountDTO accountDTO = mapper.map(account, AccountDTO.class);
        return accountDTO;
    }
    @GetMapping("/email/{email}")
    public AccountDTO findByEmail(@PathVariable String email){
        Account account = service.findByEmail(email);
        AccountDTO accountDTO = mapper.map(account, AccountDTO.class);
        return accountDTO;
    }
    @PostMapping()
    public ResponseEntity<?> createAccount(@RequestBody @Valid AccountCreateFrom from){
        System.out.println(from);
        service.createAccount(from);
        message.put("RusultText","Account Inserted Successfully!!");
        message.put("status",200);
        return ResponseEntity.status(HttpStatus.OK).body(message.toString());
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable(name = "id")  @Valid @AccountIdIsExists int id, @RequestBody AccountUpdateForm form){
//        System.out.println("id:" +id);
//        System.out.println(account);
        form.setId(id);
        service.updateAccount(form);
        message.put("rusultText","Account Update Successfully!!!");
        message.put("status",200);
        return ResponseEntity.status(HttpStatus.OK).body(message.toString());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        service.delete(id);
        message.put("rusultText","Account Deleted!!!");
        message.put("status",200);
        return ResponseEntity.status(HttpStatus.OK).body(message.toString());
    }
    @DeleteMapping("/deletelist")
    public ResponseEntity<?> deleteListId (@RequestBody List<Integer> ids){
        System.out.println(ids);
        service.deleteListId(ids);
        message.put("rusultText","Accounts deleted");
        message.put("status",200);
        return ResponseEntity.status(HttpStatus.OK).body(message.toString());
    }
//    public ResponseEntity<?> changePassword(@RequestParam(value = "username") String username,@RequestParam(value = "newPassword") String newPassword){
//        System.out.println("username:"+ username);
//        System.out.println("newPassword:"+ newPassword);
//
//        Account account = service.findByUsername(username);
//
//    }
//    public void activeAccount(Integer id){
//        Account account = service.findById(id);
//        account.setStatus(Account.Status.ACTIVE);
//        service.save(account);
//    }
}
