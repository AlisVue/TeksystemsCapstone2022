package com.teksystem.capstone.database.dao;

import com.teksystem.capstone.database.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDAO extends JpaRepository<Product, Long> {

    public Product findById(@Param("id") Integer id);

    public List<Product> findProductBySize(String productSize);
}
