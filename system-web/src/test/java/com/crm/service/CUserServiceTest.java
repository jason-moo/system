package com.crm.service;

import com.crm.base.BaseJunit4Test;
import com.crm.dao.mapper.CUserMapper;
import me.gacl.domain.CUser;
import me.gacl.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public class CUserServiceTest extends BaseJunit4Test{

    @Autowired
    UserService userService;

    @Autowired
    CUserMapper cUserMapper;

    @Test
    @Transactional
    @Rollback
    public void testInsert(){
        CUser cUser = new CUser();
        cUser.setPassword("142323");
        cUser.setPhone("32112451");
        cUser.setScert("dsfsdfddfas");
        cUser.setType(1);
        cUser.setUserName("fdasdas");
        cUser.setCreateTime(new Date());
        cUser.setLastLoginTime(new Date());
        userService.insertUser("dsadsa","dsadasdsa","dsadasrwgsr");
        CUser cUser1 = cUserMapper.selectByPrimaryKey(6);
    }

}
