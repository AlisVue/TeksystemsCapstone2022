package com.teksystem.capstone.bean;

import com.teksystem.capstone.database.entity.User;
import lombok.Data;


import java.util.Date;

@Data
public class OrderFormBean {

    private Integer id;

    private User user;

    private String cartStatus;

    private String cardholderName;

    private String paymentMethod;

    private String ccNumber;

    private Date orderDate = new Date();


}
