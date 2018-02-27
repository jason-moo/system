package com.crm.service;

import com.crm.base.BaseJunit4Test;
import com.crm.dao.mapper.CodeDao;
import me.gacl.domain.Code;
import me.gacl.plugin.Page;
import me.gacl.service.SystemTempleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Arrays;
import java.util.List;

public class CUserServiceTest extends BaseJunit4Test{

    @Autowired
    SystemTempleService systemTempleService;

    @Autowired
    CodeDao codeDao;

    @Autowired
    private TransactionTemplate transactionTemplate;

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


    @Test
    public void testUpdate(){
        Long[] ids = {1l,2l,3l,4l,5l,6l,7l};
        List<Long> idList = Arrays.asList(ids);

        transactionTemplate.execute(new TransactionCallback<Void>() {

            @Override
            public Void doInTransaction(TransactionStatus transactionStatus) {
//                boolean rollBack = idList.stream().anyMatch(e->
//                    codeDao.updateStatus(e) == 0
//                );
                boolean rollBack2 = idList.parallelStream().anyMatch(e->
                        codeDao.updateStatus(e) == 0
                );
//                System.out.println(rollBack);
                System.out.println(rollBack2);
                if (rollBack2){
                    transactionStatus.setRollbackOnly();
                }
                return null;
            }
        });
    }

}
