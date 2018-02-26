package com.crm.CglibProxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by jason_moo on 2018/1/19.
 */
public class SubjectCglibProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("收代理费");
        return methodProxy.invoke(o,objects);
    }

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        SubjectCglibProxy subjectCglibProxy = new SubjectCglibProxy();
//        SubjectCglibProxy subjectCglibProxy1 = new SubjectCglibProxy(new Subject("Jack") );
//        SubjectCglibProxy[] subjectCglibProxies = {subjectCglibProxy,subjectCglibProxy1};
        enhancer.setSuperclass(Subject.class);
        enhancer.setCallback(subjectCglibProxy);
//        enhancer.setCallbackFilter(new CallBackFilter());
        Subject subject = (Subject) enhancer.create();
        subject.create();
    }
}
