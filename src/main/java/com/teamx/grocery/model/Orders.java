package com.teamx.grocery.model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Document(collection = "orders")
public class Orders {



    @Id
    private String Id;

    @Field
    List<LinkedHashMap<String, Object>> itemDetailsList = new ArrayList<>();

    @Field
    private String fname;
    @Field
    private String lname;

    @Field
    private String city;

    @Field
    private String province;

    @Field
    private String email;

    @Field
    private String total;

    @Field
    private String address;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public Orders(List<LinkedHashMap<String, Object>> itemDetailsList, String fname, String lname, String city, String province, String email, String total, String address) {
        this.itemDetailsList = itemDetailsList;
        this.fname = fname;
        this.lname = lname;
        this.city = city;
        this.province = province;
        this.email = email;
        this.total = total;
        this.address = address;
    }

    public List<LinkedHashMap<String, Object>> getItemDetailsList() {
        return itemDetailsList;
    }

    public void setItemDetailsList(List<LinkedHashMap<String, Object>> itemDetailsList) {
        this.itemDetailsList = itemDetailsList;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Field
    private String userid;




}