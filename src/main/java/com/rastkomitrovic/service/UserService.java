package com.rastkomitrovic.service;

import com.rastkomitrovic.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    @Resource
    private UserRepository userRepository;


    public boolean existsByUsername(String username) {
        return userRepository.existsById(username);
    }
}
