package com.teksystem.capstone.controller;

import com.teksystem.capstone.bean.OrderFormBean;
import com.teksystem.capstone.database.dao.OrderDAO;
import com.teksystem.capstone.database.dao.OrderProductDAO;
import com.teksystem.capstone.database.dao.UserDAO;

import com.teksystem.capstone.database.entity.Orders;
import com.teksystem.capstone.database.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class CheckoutController {

    @Autowired
    private OrderProductDAO orderProductDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private OrderDAO orderDAO;

    @RequestMapping(value = "/shop/checkout", method = RequestMethod.GET)
    public ModelAndView checkout() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("cart/cart");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // ask spring security for current user
        String loggedUserEmail = authentication.getName(); // get current users email
        User user = userDAO.findByEmail(loggedUserEmail); // find user from db with this email

        // new query for producing list of all products in the cart
        List<Map<String,Object>> cartProducts = orderProductDAO.getCartProducts(user.getId(), "PENDING");
        response.addObject("cartProducts", cartProducts);

        log.info("cart product list: " + cartProducts.toString());

        double getSubTotal = 0.0;
        double salesTax = .08;
        double calculateSalesTax = 0.0;
        double getCartTotal = 0.0;

        for(Map<String,Object> row: cartProducts){
            BigDecimal price = (BigDecimal)row.get("total");
            getSubTotal += price.doubleValue();
            calculateSalesTax = getSubTotal * salesTax;
            getCartTotal = getSubTotal + calculateSalesTax;
        }

        response.addObject("subTotal", getSubTotal);
        response.addObject("salesTax", calculateSalesTax);
        response.addObject("cartTotal", getCartTotal);

        return response;
    }


    @RequestMapping(value = "/shop/checkoutSubmit", method = {RequestMethod.POST})
    public ModelAndView checkoutSubmit(@Valid OrderFormBean orderFormBean, BindingResult bindingResult) throws Exception {
        ModelAndView response = new ModelAndView();

        if (bindingResult.hasErrors()) {

            for (ObjectError error : bindingResult.getAllErrors()) {
                log.info(((FieldError) error).getField() + " " + error.getDefaultMessage());
            }
            response.addObject("orderFormBean", orderFormBean);

            response.addObject("bindingResult", bindingResult);

            response.setViewName("shop/checkout");
            return response;
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // ask spring security for current user
        String loggedUserEmail = authentication.getName(); // get current users email
        User user = userDAO.findByEmail(loggedUserEmail); // find user from db with this email

        Orders orders = orderDAO.findByUserIdAndCartStatus(user.getId(), "PENDING"); // find current users' cart

        orders.setCardholderName(orderFormBean.getCardholderName());
        orders.setCreditcard(orderFormBean.getCcNumber());

        orders.setStatus("COMPLETE");

        Orders completedOrder = orderDAO.save(orders);

        response.addObject("completedOrder", completedOrder);

        response.setViewName("redirect:/index");
        return response;
    }
}
