package com.teksystem.capstone.validation;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.teksystem.capstone.database.dao.UserDAO;
import com.teksystem.capstone.database.entity.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailUniqueImpl implements ConstraintValidator<EmailUnique, String> {

    public static final Logger LOG = LoggerFactory.getLogger(EmailUniqueImpl.class);

    @Autowired
    private UserDAO userDAO;

    @Override
    public void initialize(EmailUnique constraintAnnotation){

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        if (StringUtils.isEmpty(value)){
            return true;
        }

        User user = userDAO.findByEmail(value);

        return (user == null);
    }
}