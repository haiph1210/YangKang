package com.YangKang.controller;

import com.YangKang.configuration.security.JwtUtils;
import com.YangKang.dto.JwtResponeDTO;
import com.YangKang.dto.SigninDTO;
import com.YangKang.entity.Account;
import com.YangKang.form.AccountCreateFrom;
import com.YangKang.form.AuthChangePasswordForm;
import com.YangKang.repository.IAccountRepository;
import com.YangKang.service.IAccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private IAccountService service;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private IAccountRepository repository;

    @GetMapping("/active_account")
    public void activeAccount(@RequestParam(name = "id") int accountId) {
        service.activeAccount(accountId);
    }

    @GetMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody SigninDTO signinDTO){
        Account account = service.findByUsername(signinDTO.getUsername());
        System.out.println(account);
        if (account == null){
            throw  new UsernameNotFoundException(signinDTO.getUsername());
        }
        // sử dụng để check send mail
        if (account.getStatus().toString().equals("NOT_ACTIVE")){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error: User Not Active!!!");
        }

        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signinDTO.getUsername(),signinDTO.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String JwtToken = jwtUtils.generateJwtToken(authentication);
            UserDetails userDetails = (UserDetails)authentication.getPrincipal();
            return ResponseEntity.ok(new JwtResponeDTO(
                    JwtToken,userDetails.getUsername(),userDetails.getAuthorities().toString()));
        }catch (Exception exception){
            System.out.println("exception = " + exception);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username or Password Invalid!!!");
        }
    }
    @PostMapping("/register")
    @Transactional
    public ResponseEntity<?> register(@RequestBody @Valid AccountCreateFrom createFrom){
        if (repository.existsByUsername(createFrom.getUsername())) {
            return ResponseEntity.badRequest().body("Username is already taken");
        }
        if (repository.existsByEmail(createFrom.getEmail())) {
            return ResponseEntity.badRequest().body("Email is already taken");
        }
//         = mapper.map(createFrom, Account.class);
//        account.setStatus(Account.Status.NOT_ACTIVE);
        service.createAccount(createFrom);
        return ResponseEntity.ok().body("User Register Successfully!!!");
    }
    @GetMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody @Valid AuthChangePasswordForm form){
        service.changePassword(form);
    return ResponseEntity.ok().body("ChangePassword Successfully!!!");
    }
}
