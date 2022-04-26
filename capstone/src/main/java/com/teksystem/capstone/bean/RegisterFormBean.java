package com.teksystem.capstone.bean;

import com.teksystem.capstone.validation.EmailUnique;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class RegisterFormBean {

    // This id will be null in the case of a create
    // and will be populated with the user id in the case of an edit
    private Integer id;

    @EmailUnique(message = "Email exists in database.")
    @NotBlank(message ="Email is required")
    @Email(message = "@Email from spring validator")
    @Pattern(regexp = "[a-z0-9]+@[a-z]+\\.[a-z]{2,3}", message = "Email format invalid")
    private String email;

    @NotBlank(message ="First Name is required")
    private String firstName;

    @NotBlank(message ="Last Name is required")
    private String lastName;

    @Length(min = 3, max = 15, message = "Password must be between 3 and 15 characters")
    @NotBlank(message ="Password is required")
    private String password;

    @NotBlank(message ="Confirm Password is required")
    private String confirmPassword;

    @NotBlank(message = "Phone Number is required")
    private String phoneNumber;

}