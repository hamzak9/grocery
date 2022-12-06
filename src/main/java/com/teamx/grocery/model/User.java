package com.teamx.grocery.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.mapping.Field;
@Document(collection = "Users")
public class User {
    public String getId() {
        return id;
    }
    @Override
    public String toString() {
        return this.username + ":" + this.password;
    }
    public void setId(String id) {
        this.id = id;
    }

    @Id
    private String id;
    @Field
    private String username;

    @Field
    private String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public User(String username,String password){
        this.username = username;
        this.password = password;
    }

}
