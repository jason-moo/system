package com.crm.service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jason_moo on 2017/11/6.
 */
public class PhoneNumber {

    private int prefix; //区号

    private int phoneNumber; //电话号

    public int getPrefix() {
        return prefix;
    }

    public void setPrefix(int prefix) {
        this.prefix = prefix;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public PhoneNumber(int prefix, int phoneNumber) {
        this.prefix = prefix;
        this.phoneNumber = phoneNumber;
    }

    public static void main(String[] args) {
        Map<PhoneNumber,String> map = new HashMap<>();
        map.put(new PhoneNumber(2222,2233),"dsadad");
        map.put(new PhoneNumber(3333,2233),"dsadad");
        map.put(new PhoneNumber(4444,2233),"dsadad");
        map.put(new PhoneNumber(2222,2233),"dsadad1213");
        System.out.println(map);
        System.out.println(map.get(new PhoneNumber(2222,2233)));
        System.out.println(map.get(new PhoneNumber(3333,2233)));



    }
}
