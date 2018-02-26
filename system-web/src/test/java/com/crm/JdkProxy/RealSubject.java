package com.crm.JdkProxy;

import com.crm.JdkProxy.Subject;

/**
 * Created by jason_moo on 2018/1/18.
 */
public class RealSubject implements Subject {

    @Override
    public void doSomething() {
        System.out.println("干些事情吧！");
    }

}
