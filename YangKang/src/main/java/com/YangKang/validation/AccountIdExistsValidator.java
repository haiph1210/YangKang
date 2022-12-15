package com.YangKang.validation;

import com.YangKang.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AccountIdExistsValidator implements ConstraintValidator<AccountIdIsExists,Integer> {
    @Autowired
    private IAccountRepository repository;
    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return repository.existsById(integer);
    }
}
