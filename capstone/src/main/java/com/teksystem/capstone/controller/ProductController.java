package com.teksystem.capstone.controller;

import com.teksystem.capstone.bean.ProductFormBean;
import com.teksystem.capstone.database.dao.ProductDAO;
import com.teksystem.capstone.database.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@Slf4j
@Controller
public class ProductController {

    @Autowired
    private ProductDAO productDAO;

//    @RequestMapping(value = "/products", method = RequestMethod.GET)
//    public ModelAndView displayProducts() throws Exception {
//        ModelAndView response = new ModelAndView();
//        response.setViewName("product/products");
//        List<Product> products = productDao.findAll();
//
//        response.addObject("products", products);
//
//
//        return response;
//    }
//
//
////    @RequestMapping(value = "/product/productSubmit", method = RequestMethod.GET)
////    public ModelAndView submit(@Valid ProductFormBean form, BindingResult bindingResult) throws Exception {
////        ModelAndView response= new ModelAndView();
////        response.setViewName("product");
////
////        log.debug(form.toString());
////
////        if (bindingResult.hasErrors())  {
////            // this is the error case
////            for ( FieldError error : bindingResult.getFieldErrors()) {
////                log.debug(error.toString());
////            }
////
////            // add the errors to the model to be displayed on the page
////            response.addObject("bindingResult", bindingResult);
////
////            // add the form bean back to the model so I can fill the form with the user input
////            response.addObject("form", form);
////        } else {
////            // this is the success case
////            // we are going to save the product to the database
////
////            Product product = new Product();
////
////            product.setName(form.getProductName());
////            product.setDescription(form.getDescription());
////            product.setPrice(form.getPrice());
////            product.setImageUrl(form.getImageURL());
////
////            productDao.save(product);
////
////        }
////
////
////        return response;
////    }
////
////
////    @RequestMapping(value = "/product/delete/{id}", method = RequestMethod.GET)
////    public ModelAndView delete(@PathParam("id") Integer id) throws Exception {
////        ModelAndView response = new ModelAndView();
////        response.setViewName("product");
////
////        Product p = productDao.findById(id);
////        if ( p == null ) {
////            // this is an error
////        } else {
////            productDao.delete(p);
////        }
////
////        return response;
////    }
////
//    @RequestMapping(value = "/product/productForm", method = RequestMethod.GET)
//    public ModelAndView displayProductForm() throws Exception{
//        ModelAndView response =  new ModelAndView();
//        response.setViewName("product/create_product");
//
//
//        return response;
//    }
//
//    @RequestMapping(value = "/product/create", method = RequestMethod.POST)
//    public ModelAndView saveProduct(@Valid ProductFormBean form, BindingResult bindingResult) throws Exception{
//        ModelAndView response = new ModelAndView();
//        response.setViewName("product/product");
//
//        Product newProduct = new Product();
//        newProduct.setName(form.getProductName());
//        newProduct.setDescription(form.getDescription());
//        newProduct.setPrice(form.getPrice());
//        newProduct.setImageUrl(form.getImageURL());
//
//        productDao.save(newProduct);
//
//        return response;
//    }

    @RequestMapping(value = "/shop/products", method = RequestMethod.GET)
    public ModelAndView shop() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("product/products");

        List<Product> allProducts = productDAO.findAll();
        List<Product> xsmallProducts = productDAO.findProductBySize("xsmall");
        List<Product> smallProducts = productDAO.findProductBySize("small");
        List<Product> mediumProducts = productDAO.findProductBySize("medium");
        List<Product> largeProducts = productDAO.findProductBySize("large");


        response.addObject("allProducts", allProducts);
        response.addObject("xsmallProducts", xsmallProducts);
        response.addObject("smallProducts", smallProducts);
        response.addObject("mediumProducts", mediumProducts);
        response.addObject("largeProducts", largeProducts);


        return response;
    }

    @GetMapping("/shop/products/details/{id}")
    public ModelAndView productEdit(@PathVariable("id") Integer id) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("/shop/products/details");

        ProductFormBean productFormBean = new ProductFormBean();

        Product product = productDAO.findById(id);

        log.info(String.valueOf(id));


        productFormBean.setId(product.getId());
        productFormBean.setProductName(product.getName());
        productFormBean.setPrice(product.getPrice());
        productFormBean.setImageURL(product.getImageUrl());
        productFormBean.setDescription(product.getDescription());

        // in this case we are adding the RegisterFormBean to the model
        response.addObject("product", productFormBean);

        log.info(String.valueOf(productFormBean));

        return response;

    }
}
