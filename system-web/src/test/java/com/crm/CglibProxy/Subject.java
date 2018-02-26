package com.crm.CglibProxy;

/**
 * Created by jason_moo on 2018/1/19.
 */
public class Subject {

    private String name;

    public Subject() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void create(){
        System.out.println("创造开始了！");
    }

    public void destory(){
        System.out.println("毁灭所有的！");
    }


}
