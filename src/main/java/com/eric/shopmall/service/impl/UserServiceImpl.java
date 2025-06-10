package com.eric.shopmall.service.impl;

import com.eric.shopmall.dao.UserDao;
import com.eric.shopmall.dto.UserRegisterRequest;
import com.eric.shopmall.model.User;
import com.eric.shopmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

     @Autowired
     private UserDao userDao;

     @Override
     public User getUserById(Integer userId) {

          return userDao.getUserById(userId);
     }

     @Override
     public Integer register(UserRegisterRequest userRegisterRequest) {

          return userDao.createUser(userRegisterRequest);
     }


}
