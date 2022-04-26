package com.teksystem.capstone.bean;


import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ProductFormBean {

    private String productName;

    private String description;

    private String imageURL;

    private Double price;

    private Integer Id;

    private String size;

}
