package com.crm.CglibProxy;

import org.springframework.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

/**
 * Created by jason_moo on 2018/1/19.
 */
public class CallBackFilter implements CallbackFilter{

    @Override
    public int accept(Method method) {
        if ( method.getName().equals("create")){
            return 0;
        }else {
            return 1;
        }
    }
}
