package com.teksystem.capstone.database.dao;

import com.teksystem.capstone.database.entity.User;
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
public class TestUserDAO {

    @Autowired
    private UserDAO userDAO;

    //create user
    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveUser(){
        User user = User.builder().firstname("Alis").lastname("Vue").email("alis@gmail.com").password("password").phone("1111111111").build();
        User user2 = User.builder().firstname("Pass").lastname("Word").email("pass@word.com").password("password").phone("1111111111").build();
        userDAO.save(user);
        userDAO.save(user2);

        log.info(String.valueOf(user));

        Assertions.assertTrue(user.getId() > 0);
    }

    //read users
    @ParameterizedTest
    @Order(2)
    @ValueSource(strings = {"alis@gmail.com", "pass@word.com"})
    public void findUserTest(String email){
        User user = userDAO.findByEmail(email);
        Assertions.assertNotNull(user);
    }

    @Test
    @Order(3)
    public void getUserTest() {
        User user = userDAO.findById(1);
        Assertions.assertEquals(1, user.getId());
    }
    @Test
    @Order(4)
    public void getUserListTest() {
        List<User> userList = userDAO.findAll();
        Assertions.assertTrue(userList.size() > 0);
    }
    @Test
    @Order(5)
    @Rollback(value = false)
    public void updateUserTest() {
        User user = userDAO.findById(1);
        user.setFirstname("Alis");
        Assertions.assertEquals(userDAO.findById(1).getFirstname(), user.getFirstname());
    }
    @Test
    @Order(6)
    @Rollback(value = false)
    public void deleteUserTest(){
        User user = userDAO.findById(1);
        userDAO.delete(user);
        Optional<User> optionalUser = Optional.ofNullable(userDAO.findById(user.getId()));
        User temporaryUser = null;
        if(optionalUser.isPresent()){
            temporaryUser = userDAO.findById(user.getId());
        }
        Assertions.assertNull(temporaryUser);
    }
}