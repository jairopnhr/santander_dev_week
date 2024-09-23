package com.example.santander_dev_week.service.impl;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.example.santander_dev_week.model.User;
import com.example.santander_dev_week.repository.UserRepository;
import com.example.santander_dev_week.service.UserService;

import lombok.RequiredArgsConstructor;

/**
 * UserServiceIMPL
 */
@Service
@RequiredArgsConstructor
public class UserServiceIMPL implements UserService {

    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        if (userRepository.existsByAccountNumber(user.getAccount().getNumber())) {
            throw new IllegalArgumentException("This Account number already exists.");
        }
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
       return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    




}