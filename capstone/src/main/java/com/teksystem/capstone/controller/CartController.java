package com.teksystem.capstone.controller;

import com.teksystem.capstone.database.dao.OrderDAO;
import com.teksystem.capstone.database.dao.OrderProductDAO;
import com.teksystem.capstone.database.dao.ProductDAO;
import com.teksystem.capstone.database.dao.UserDAO;
import com.teksystem.capstone.database.entity.OrderProduct;
import com.teksystem.capstone.database.entity.Orders;
import com.teksystem.capstone.database.entity.Product;
import com.teksystem.capstone.database.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class CartController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private OrderProductDAO orderProductDAO;

    @Autowired
    private OrderDAO orderDAO;

    @RequestMapping(value = "/cart/addProduct/{id}")
    public ModelAndView addProductToCart(@PathVariable("id") Integer id){
        ModelAndView response = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userDAO.findByEmail(username);

        log.info("User:" + user.getId());


        Orders orders = orderDAO.findByUserIdAndCartStatus(user.getId(), "PENDING"); // find current users' cart

        if(orders == null){ // if there are no pending orders for this user
            orders = new Orders(); // create a new one
            orders.setStatus("PENDING");
            orders.setUser(user); // assign this cart to the current user
            orders = orderDAO.save(orders); // save to db and reassign "order" variable to DB response (this ensures we have the correct id from sql auto-increment)
        }


        // getting the user order, if not create one
        Product product = productDAO.findById(id);
        OrderProduct cartItem = orderProductDAO.findProductOrderByOrdersAndProduct(orders, product); // check if item already in the cart?
        if(cartItem == null){ // if not, add it
            cartItem = new OrderProduct();
            cartItem.setOrders(orders);
            cartItem.setProduct(product);
            cartItem.setQuantity(1);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + 1); // if the item is already in the cart, just increment qty
        }
        OrderProduct savedCartItem = orderProductDAO.save(cartItem);
        response.addObject("savedCartItem", savedCartItem);
        log.info("added: " + cartItem.getProduct().getName());


        response.setViewName("redirect:/shop/checkout");

        return response;
    }

    @RequestMapping(value = "/cart/deleteItem/{id}", method = RequestMethod.GET)
    public ModelAndView productRemove(@PathVariable("id") Integer id) throws Exception {

        OrderProduct selectedCartLine = orderProductDAO.findById(id);

        if ( selectedCartLine == null ) {
            log.info("selectedCartLine is null");
            // this is an error
        } else {

            orderProductDAO.delete(selectedCartLine);
            System.out.println("product removed from cart");
        }

        return new ModelAndView("redirect:/shop/checkout");
    }

}
