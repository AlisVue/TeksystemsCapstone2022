package com.teksystem.capstone.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AccountFormBean {

    private Integer Id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String confirmPassword;

    private String phone;

}
