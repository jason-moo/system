package com.crm.JdkProxy;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by jason_moo on 2018/1/18.
 */
public class ProxyHandler implements InvocationHandler {

    private Object proxied;

    public ProxyHandler(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //在转调具体目标对象之前，可以执行一些功能处理
        System.out.println("先干点");
        //转调具体目标对象的方法
        method.invoke( proxy, args);

        System.out.println("结束了，去喝酒");
        //在转调具体目标对象之后，可以执行一些功能处理
        return null;
    }


    public static void main(String[] args) {
        RealSubject real = new RealSubject();
        Subject proxySubject = (Subject) Proxy.newProxyInstance(Subject.class.getClassLoader(),
                new Class[]{Subject.class},
                new ProxyHandler(real));
        proxySubject.doSomething();
        Method[] methods = proxySubject.getClass().getDeclaredMethods();

        Field[] fields = proxySubject.getClass().getDeclaredFields();

    }

}
