package com.teamx.grocery.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamx.grocery.repository.UserRepository;
import com.teamx.grocery.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.bson.json.JsonObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.teamx.grocery.model.User;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.xml.crypto.Data;
import java.math.BigInteger;
import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Array;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController  {
    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody String payload) throws NoSuchAlgorithmException {

        JSONObject json = new JSONObject(payload);

        // handle parsing handle uppercase lowercase
        String username = json.getString("email").toLowerCase();
        String hash = getSha512Hash(json.getString("password")); // send hashed pwd to mongo
        String firstname = json.getString("fname");
        String lastname = json.getString("lname");
        String city = json.getString("city");
        String provinceTerr = json.getString("provinceTerri");
        String address = json.getString("address");

        if(userService.checkIfEmailExists(username).isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // already have account

        }
        User user = new User(username,hash,firstname,lastname,address,city,provinceTerr);

        userService.addUser(user);
        return new ResponseEntity<>(HttpStatus.OK);


    }

    public String getSha512Hash(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] messageDigest = md.digest(password.getBytes());
        BigInteger big = new BigInteger(1, messageDigest);
        String hash = big.toString(16);
        return hash;
    }
    @PostMapping(path="/authenticate")
    public ResponseEntity<?> authenticateUser(@RequestBody String payload) throws NoSuchAlgorithmException {

        JSONObject json = new JSONObject(payload);

        Optional<User> user = userService.findUserWithHash(json.getString("username").toLowerCase(),getSha512Hash(json.getString("password")));
        if(!user.isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

        JSONArray response = new JSONArray();
        JSONObject authenticated = new JSONObject();
        authenticated.put("authenticated",true);

        JSONObject email = new JSONObject();
        email.put("email",user.get().getUsername());

        JSONObject fullName = new JSONObject();
        fullName.put("fullName",user.get().getFirstName() + " " +user.get().getLastName());

        response.put(authenticated);
        response.put(email);
        response.put(fullName);


        return new ResponseEntity<>(response,HttpStatus.OK);


    }


}
