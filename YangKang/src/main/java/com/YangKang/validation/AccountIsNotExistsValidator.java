package com.YangKang.validation;

import com.YangKang.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;

public class AccountIsNotExistsValidator implements ConstraintValidator<AccountIsNotExist,Integer> {
//
//    @Override
//    public void initialize(AccountIsNotExist constraintAnnotation) {
//        ConstraintValidator.super.initialize(constraintAnnotation);
//    }
    @Autowired
    private IAccountRepository repository;

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext constraintValidatorContext) {
        return !repository.existsById(id);
    }
}
