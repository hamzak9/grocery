package com.teamx.grocery.controller;

import com.teamx.grocery.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.teamx.grocery.model.User;
import org.springframework.data.mongodb.core.query.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController  {
    @Autowired
    private UserRepository repository;

    @PostMapping("/create")
    public void createUser(@RequestBody User user) throws NoSuchAlgorithmException {
        String hash = getSha512Hash(user.getPassword());
        user.setPassword(hash);

        repository.insert(user);
    }
    @PostMapping("/delete/{id}")
    public void deleteUser(@PathVariable String id){
        repository.deleteById(id);

    }
    public String getSha512Hash(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] messageDigest = md.digest(password.getBytes());
        BigInteger big = new BigInteger(1, messageDigest);
        String hash = big.toString(16);
        return hash;
    }
    @PostMapping(path="/loginForm")
    public ResponseEntity<?> handleLoginForm(HttpServletRequest request) throws NoSuchAlgorithmException {
        System.out.println("USER EMAIL " + request.getParameter("email"));
        System.out.println("USER PASSWORD" + request.getParameter("password"));

        String email = request.getParameter("email");
        String hash = getSha512Hash(request.getParameter("password"));

        System.out.println(hash);

        Optional<User> user = repository.findUserWithHash(email,hash);
        if(!user.isPresent()){
          return ResponseEntity.badRequest().body("Error: Invalid username or password!");
        }
        System.out.println("FOUND:==> " +user.toString());


       return new ResponseEntity<>(HttpStatus.OK); // success

    }

    public ResponseEntity<?> getAllUsers(){
        List<User> users = repository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);

    }

}
