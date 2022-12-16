package com.teamx.grocery.services;

import com.teamx.grocery.model.User;
import com.teamx.grocery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {


    @Autowired
    private UserRepository repository;


    public Optional<User> checkIfEmailExists(String username){
       return repository.checkIfEmailExists(username);

    }
    public Optional<User> findUserWithHash(String username,String password){
       return repository.findUserWithHash(username,password);
    }

        public void addUser(User user){
        repository.insert(user);
        }


}
