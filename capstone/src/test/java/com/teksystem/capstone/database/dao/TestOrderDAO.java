package com.teksystem.capstone.database.dao;

import com.teksystem.capstone.database.entity.Orders;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@Slf4j
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class TestOrderDAO {

    @Autowired
    private OrderDAO orderDAO;

    //create order
    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveProduct(){
        Orders order = Orders.builder().status("PENDING").cardholderName("Alis").creditcard("1111-1111-1111").build();
        Orders order2 = Orders.builder().status("COMPLETED").cardholderName("De").creditcard("1111-2222-3333").build();
        orderDAO.save(order);
        orderDAO.save(order2);

        log.info(String.valueOf(order));

        Assertions.assertTrue(order.getId() > 0);
    }

    //read order
    @ParameterizedTest
    @Order(2)
    @ValueSource(strings = {"Alis", "De"})
    public void findOrderTest(String cardholderName){
        Orders orders = orderDAO.findByCardholderName(cardholderName);
        Assertions.assertNotNull(orders);
    }

    @Test
    @Order(3)
    public void getOrderTest() {
        Orders orders = orderDAO.findById(1);
        Assertions.assertEquals(1, orders.getId());
    }
    @Test
    @Order(4)
    public void getOrderListTest() {
        List<Orders> orderList = orderDAO.findAll();
        Assertions.assertTrue(orderList.size() > 0);
    }
    @Test
    @Order(5)
    @Rollback(value = false)
    public void updateOrderTest() {
        Orders order = orderDAO.findById(1);
        order.setStatus("COMPLETED");
        Assertions.assertEquals(orderDAO.findById(1).getStatus(), order.getStatus());
    }
    @Test
    @Order(6)
    @Rollback(value = false)
    public void deleteOrderTest(){
        Orders order = orderDAO.findById(1);
        orderDAO.delete(order);
        Optional<Orders> optionalOrder = Optional.ofNullable(orderDAO.findById(order.getId()));
        Orders temporaryOrder = null;
        if(optionalOrder.isPresent()){
            temporaryOrder = orderDAO.findById(order.getId());
        }
        Assertions.assertNull(temporaryOrder);
    }
}