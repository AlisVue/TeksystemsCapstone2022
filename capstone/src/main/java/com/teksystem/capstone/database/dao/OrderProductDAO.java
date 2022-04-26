package com.teksystem.capstone.database.dao;

import com.teksystem.capstone.database.entity.Orders;
import com.teksystem.capstone.database.entity.OrderProduct;
import com.teksystem.capstone.database.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OrderProductDAO extends JpaRepository<OrderProduct, Long> {

    public OrderProduct findById(@Param("id") Integer id);

    public List<OrderProduct> findByOrders(@Param("order") Orders orders);

    public OrderProduct findProductOrderByOrdersAndProduct(@Param("orders") Orders orders, @Param("product")Product product);

    @Query(value="select p.id as product_id, p.name, p.price, op.quantity, o.id as order_id, (price * quantity) as total " +
            "from products p, order_products op, orders o " +
            "where p.id = op.product_id and o.id = op.order_id " +
            "and o.user_id = :userId and status = :status", nativeQuery = true)
    List<Map<String,Object>> getCartProducts(@Param("userId") Integer userId, @Param("status") String status );

}
