package com.teksystem.capstone.service;

import com.teksystem.capstone.bean.AccountFormBean;
import com.teksystem.capstone.database.dao.UserDAO;
import com.teksystem.capstone.database.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Date;

@Service
public class UserService {

    @Autowired
    private UserDAO userDao;

    public void getUserDetails(@Valid AccountFormBean accountFormBean, User user) {
        user.setId(accountFormBean.getId());
        user.setFirstname(accountFormBean.getFirstName());
        user.setLastname(accountFormBean.getLastName());
        user.setEmail(accountFormBean.getEmail());
        user.setPhone(accountFormBean.getPhone());
        user.setCreateDate(new Date());
    }

}
