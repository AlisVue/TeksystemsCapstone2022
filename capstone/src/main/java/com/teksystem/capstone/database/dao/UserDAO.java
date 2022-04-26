package com.teksystem.capstone.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.teksystem.capstone.database.entity.User;

import java.util.List;

@Repository
public interface  UserDAO  extends JpaRepository<User, Long> {

    public User findById(@Param("id") Integer id);

    // There is 3 ways to execute a query
    // 1) via @Query with JPA/JQL/HQL
    // 2) via @Query with a Native query
    // 3) by using a function for spring to do the query with no query

    public User findByEmail(@Param("email") String email);

    public List<User> findByFirstname(@Param("firstName") String firstName);

    public List<User> findByFirstnameAndLastname(@Param("firstName") String firstName, @Param("lastName") String lastName);

    public List<User> findByFirstnameIgnoreCaseContaining(@Param("firstName") String firstName);

    @Query(value = "select u from User u where u.password = :password", nativeQuery = true)
    public List<User> getByPassword(@Param("password") String password);

}