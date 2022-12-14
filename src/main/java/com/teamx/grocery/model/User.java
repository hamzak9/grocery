package com.teamx.grocery.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.mapping.Field;
@Document(collection = "Users")
public class User {
    public User(String username,String password,String firstName,String lastName,
                String streetAddress,String city,String provinceTerritory) {
        this.username=username;
        this.password=password;
        this.firstName=firstName;
        this.lastName=lastName;
        this.streetAddress=streetAddress;
        this.city=city;
        this.provinceTerritory=provinceTerritory;
    }
    @Id
    private String id;
    @Field
    private String username;
    @Field
    private String password;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvinceTerritory() {
        return provinceTerritory;
    }

    public void setProvinceTerritory(String provinceTerritory) {
        this.provinceTerritory = provinceTerritory;
    }

    @Field
    private String firstName;
    @Field
    private String lastName;
    @Field
    private String streetAddress;
    @Field
    private String city;
    @Field
    private String provinceTerritory;

    public void setId(String id) {
        this.id = id;
    }
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
    public String getId() {
        return id;
    }
    @Override
    public String toString() {
        return this.username + ":" + this.password;
    }

}
