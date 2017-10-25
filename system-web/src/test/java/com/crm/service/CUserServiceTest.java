package com.crm.service;

import com.crm.base.BaseJunit4Test;
import com.crm.dao.mapper.CUserMapper;
import com.crm.dao.mapper.CodeDao;
import me.gacl.domain.CUser;
import me.gacl.domain.Code;
import me.gacl.plugin.Page;
import me.gacl.service.SystemTempleService;
import me.gacl.service.UserService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public class CUserServiceTest extends BaseJunit4Test{

    @Autowired
    UserService userService;

    @Autowired
    CUserMapper cUserMapper;

    @Autowired
    SystemTempleService systemTempleService;

    @Autowired
    CodeDao codeDao;

    @Test
    @Transactional
    @Rollback
    public void testInsert(){
//        CUser cUser = new CUser();
//        cUser.setPassword("142323");
//        cUser.setPhone("32112451");
//        cUser.setScert("dsfsdfddfas");
//        cUser.setType(1);
//        cUser.setUserName("fdasdas");
//        cUser.setCreateTime(new Date());
//        cUser.setLastLoginTime(new Date());
//        userService.insertUser("dsadsa","dsadasdsa","dsadasrwgsr");
//        CUser cUser1 = cUserMapper.selectByPrimaryKey(6);
//        System.out.println("jieshule!");
        Page page = new Page();
        page.setPageNo(1);
        page.setPageSize(10);
        List<Code> allcode  = codeDao.selectAllCode(1,page);
        List<Code> sda = codeDao.queryCode(new Page());
    }


    @Test
    @Transactional
    @Rollback
    public void testredis(){
        systemTempleService.set("abc","hahhaha");
        System.out.println(systemTempleService.get("abc").toString());
    }
}
