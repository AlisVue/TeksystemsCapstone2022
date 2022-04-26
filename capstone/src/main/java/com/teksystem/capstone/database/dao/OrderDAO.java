package com.teksystem.capstone.database.dao;

import com.teksystem.capstone.database.entity.Orders;
import com.teksystem.capstone.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDAO extends JpaRepository<Orders, Long> {

    @Query(value = "SELECT * FROM orders WHERE user_id = :userId AND cart_status = :cartStatus", nativeQuery = true)
    public Orders findByUserIdAndCartStatus(@Param("userId") Integer userId, @Param("cartStatus") String cartStatus);

    public Orders findById(@Param("id") Integer id);

    public List<Orders> findAllByUser(@Param("user") User user);

    public Orders findByCardholderName(@Param("cardholderName") String cardholderName);

}