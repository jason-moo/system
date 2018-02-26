package com.crm.base;


import com.alibaba.druid.sql.visitor.functions.If;
import com.xkeshi.ecoupon.apis.ECouponDubboService;
import com.xkeshi.ecoupon.dtos.ecoupon.ECouponDTO;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jason_moo on 2017/11/2.
 */
public class HttpTest {

    public static void get(){
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet();
        try {
            client.execute(get);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
