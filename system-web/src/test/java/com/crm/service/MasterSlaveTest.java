package com.crm.service;

import com.crm.base.BaseJunit4Test;
import com.crm.dao.mapper.CodeDao;
import com.crm.dao.mapper.OrderDao;
import me.gacl.domain.COrder;
import me.gacl.domain.Code;
import me.gacl.utils.RandomUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by jason_moo on 2018/3/22.
 */
public class MasterSlaveTest extends BaseJunit4Test{

    @Autowired
    CodeDao codeDao;

    @Autowired
    OrderDao orderDao;

    @Test
    public void testInsertAndSelect(){
        Code code = new Code();
        int i = 0;
        code.setStatus(1);
        while (i < 10){
            code.setDiscountCode("axasxa"+i);
            codeDao.insert(code);
            i++;
        }
    }

    @Test
    public void testSelect(){
        Code code = codeDao.selectById(4840);
        System.out.println(code.getDiscountCode());
    }


    @Test
    public void testInsertOrder(){
        COrder order = new COrder();
        for (int i = 1; i < 10; i++) {
            order.setId(Long.valueOf(i));
            order.setOrderId(10l);
            order.setUserId(110l);
            orderDao.insert(order);
        }
    }

}
