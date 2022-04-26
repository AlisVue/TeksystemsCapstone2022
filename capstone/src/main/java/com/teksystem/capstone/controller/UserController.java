package com.teksystem.capstone.controller;

import com.teksystem.capstone.bean.AccountFormBean;
import com.teksystem.capstone.bean.RegisterFormBean;
import com.teksystem.capstone.database.dao.UserDAO;
import com.teksystem.capstone.database.dao.UserRoleDAO;
import com.teksystem.capstone.database.entity.User;
import com.teksystem.capstone.database.entity.UserRoles;
import com.teksystem.capstone.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.util.Date;


@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRoleDAO userRoleDAO;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/user/register", method = RequestMethod.GET)
    public ModelAndView create() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/register");

        RegisterFormBean registerFormBean = new RegisterFormBean();
        response.addObject("form", registerFormBean);

        return response;
    }

    @RequestMapping(value = "/user/registerSubmit", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView registerSubmit(@Valid RegisterFormBean form, BindingResult bindingResult, HttpSession session) throws Exception {
        ModelAndView response = new ModelAndView();

        log.info(form.toString());

        if (bindingResult.hasErrors()) {

            for (ObjectError error : bindingResult.getAllErrors()) {
                log.info(((FieldError) error).getField() + " " + error.getDefaultMessage());
            }
            response.addObject("form", form);

            response.addObject("bindingResult", bindingResult);

            response.setViewName("user/register");
            return response;
        }

        User user = new User();

        user.setFirstname(form.getFirstName());
        user.setLastname(form.getLastName());
        user.setEmail(form.getEmail());
        user.setPhone(form.getPhoneNumber());
        user.setCreateDate(new Date());

        String password = passwordEncoder.encode(form.getPassword());
        user.setPassword(password);

        User newUser = userDAO.save(user);



        UserRoles userRole = new UserRoles();
        userRole.setId(newUser.getId());
        userRole.setUserRole("USER");

        userRoleDAO.save(userRole);

        session.setAttribute("id", newUser.getId());

        log.info(form.toString());

        log.info(form.toString());

        response.setViewName("redirect:/index");

        return response;
    }


    @RequestMapping(value = "/user/account/{id}")
    public ModelAndView userAccount(@PathVariable("id") Integer id) throws Exception {
        ModelAndView response = new ModelAndView();


        AccountFormBean accountFormBean = new AccountFormBean();

        User user = userDAO.findById(id);


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // ask spring security for current user
        String loggedUserEmail = authentication.getName(); // get current users email
        User currentUser = userDAO.findByEmail(loggedUserEmail); // find user from db with this email



        if (currentUser!= null && user.getId().equals(currentUser.getId())){

            log.info(String.valueOf(user));
            accountFormBean.setId(user.getId());
            accountFormBean.setEmail(user.getEmail());
            accountFormBean.setFirstName(user.getFirstname());
            accountFormBean.setLastName(user.getLastname());
            accountFormBean.setPhone(user.getPhone());

            response.addObject("accountFormBean", accountFormBean);

            response.setViewName("user/account");
            return response;

        }
        response.setViewName("redirect:/index");
        return response;
    }

//    @PostMapping("/user/account/")

    @PostMapping(value = "/user/account/saved")
    public ModelAndView userAccountEdit(@Valid AccountFormBean accountFormBean) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/account/");

        User user = userDAO.findById(accountFormBean.getId());

        userService.getUserDetails(accountFormBean, user);

        userDAO.save(user);

        response.setViewName("redirect:/user/account/" + user.getId());

        return response;
    }
//    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
//    @GetMapping(value = "/user/edit/{userId}")
//    public ModelAndView editUser(@PathVariable("userId") Integer userId) throws Exception {
//        ModelAndView response = new ModelAndView();
//        response.setViewName("user/register");
//
//        User user = userDAO.findById(userId);
//
//        RegisterFormBean form =  new RegisterFormBean();
//
//        form.setId(user.getId());
//        form.setEmail(user.getEmail());
//        form.setFirstName(user.getFirstname());
//        form.setLastName(user.getLastname());
//        form.setPassword(user.getPassword());
//        form.setConfirmPassword(user.getPassword());
//
//        // in this case we are adding the RegisterFormBean to the model
//        response.addObject("form", form);
//
//        return response;
//    }
//
//    @PreAuthorize("hasAuthority('ADMIN')")
//    @RequestMapping(value = "/user/search", method = {RequestMethod.POST, RequestMethod.GET} )
//    public ModelAndView search(@RequestParam(value = "firstName", required = false)String firstName){
//        ModelAndView response = new ModelAndView();
//        response.setViewName("user/search");
//
//
//        List<User> users = new ArrayList<>();
//
//        // very basic example of error checking
//        if (!StringUtils.isEmpty(firstName) ){
//            // do your query
//            users = userDAO.findByFirstnameIgnoreCaseContaining(firstName);
//        }
//
//        // this line puts the list of users that we just queried into the model
//        // the model is am map (key value store)
//        // any object of any kind can go into the model using this key value
//        // in this case it is a list of Users
//        response.addObject("usersModelKey", users);
//        response.addObject("firstName", firstName);
//
//        return response;
//    }

}
