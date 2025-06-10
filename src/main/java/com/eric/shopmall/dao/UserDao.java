package com.eric.shopmall.dao;

import com.eric.shopmall.dto.UserRegisterRequest;
import com.eric.shopmall.model.User;

public interface UserDao {

    User getUserById(Integer userId);

    Integer createUser(UserRegisterRequest userRegisterRequest);




}
