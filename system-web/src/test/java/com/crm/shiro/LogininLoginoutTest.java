package com.crm.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

import java.security.SecureRandom;

/**
 * Created by jason_moo on 2018/1/30.
 */
public class LogininLoginoutTest {


    @Test
    public void testHelloWorld(){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");
        try {
            subject.login(token);
        }catch (AuthenticationException e){
            System.out.println("authentication fail!");
        }

        Assert.assertEquals(true,subject.isAuthenticated());

        Assert.assertTrue(subject.hasRole("role1"));

        Assert.assertTrue(subject.isPermitted("user:create"));

        Assert.assertTrue(subject.isPermitted("user:delete"));

        System.out.println("authentication success!");

        subject.logout();

    }

    public static void main(String[] args) {
        SecureRandom secureRandom = new SecureRandom();
        int[] ints = new int[10];
        for (int i = 0 ;i< 10; i ++){
            ints[i] = secureRandom.nextInt();
        }

        SecureRandom secureRandom1 = new SecureRandom();
        int[] ints1 = new int[10];
        for (int i = 0 ;i< 10; i ++){
            ints1[i] = secureRandom.nextInt();
        }

    }

}
