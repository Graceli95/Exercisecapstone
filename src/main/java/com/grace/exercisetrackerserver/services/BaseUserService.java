package com.grace.exercisetrackerserver.services;

import com.grace.exercisetrackerserver.models.BaseUser;
import com.grace.exercisetrackerserver.repositories.BaseUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class BaseUserService {

    private final BaseUserRepository baseUserRepository;

    @Autowired
    public BaseUserService(BaseUserRepository baseUserRepository) {
        this.baseUserRepository = baseUserRepository;
    }

    public BaseUser create(BaseUser baseUser) {
        return baseUserRepository.save(baseUser);
    }

    public Optional<BaseUser> authenticateUser(String email, String password) {
        return baseUserRepository.findBaseUserByEmailAndPassword(email, password);
    }

    public Optional<BaseUser> findBaseUserById(Long userId) {
        return baseUserRepository.findBaseUserById(userId);
    }




    public Optional<BaseUser> getUserById(Long id) {
            if (id == null || id <= 0) {
                throw new IllegalArgumentException("ID must be a positive number.");
            }
            return baseUserRepository.findById(id);
    }

    public Optional<BaseUser> findByEmail(String email){
        return baseUserRepository.findBaseUserByEmail(email);
    }

    public void deleteUserById(Long id) {
        if (id == null || id <= 0) {
                throw new IllegalArgumentException("ID must be a positive number.");
        }
        baseUserRepository.deleteById(id);
        System.out.println("Deleted user with ID: " + id); // Replace with proper logging
    }

    public List<BaseUser> getAllUsers() {
            return baseUserRepository.findAll();
    }



}


