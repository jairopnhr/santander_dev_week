package com.example.santander_dev_week.service;

import com.example.santander_dev_week.model.User;

public interface UserService {
    User save( User user);
    User findById(Long id);
}
