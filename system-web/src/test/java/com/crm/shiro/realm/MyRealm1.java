package com.crm.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * Created by jason_moo on 2018/1/30.
 */
public class MyRealm1 implements Realm {

    @Override
    public String getName() {
        return MyRealm1.class.getName();
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        String passwd =  new String((char[])authenticationToken.getCredentials());
        if (!"zhang".equals(username)){
            throw new UnknownAccountException();
        }
        if (!"123".equals(passwd)){
            throw new IncorrectCredentialsException();
        }

        return new SimpleAuthenticationInfo(username,passwd,getName());
    }
}
