package com.one.common.lamda;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by buke on 16/2/19.
 */
public class Person {

    private String givenName;

    private String surName;

    private int age;

    private String eMail;

    private String phone;

    private String address;

    public static List<Person> createShortList() {
        List<Person> list = new ArrayList<>();
        return list;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
