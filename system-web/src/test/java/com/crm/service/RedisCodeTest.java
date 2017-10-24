package com.crm.service;

import com.crm.base.BaseJunit4Test;
import com.crm.dao.mapper.CodeDao;
import me.gacl.domain.Code;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by jason_moo on 2017/10/23.
 */
public class RedisCodeTest extends BaseJunit4Test{

    private static final String codeKey = "allCode_";

    private static final Integer count = 10;

    private static final String userdkey = "allUsedCode";

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    CodeDao codeDao;

    @Test
    public void getDiscountCode(){
        for (int i = 0; i < 100; i++) {
            me.gacl.domain.Code code = new me.gacl.domain.Code();
            code.setDiscountCode(String.valueOf(System.identityHashCode(UUID.randomUUID().toString()+System.nanoTime())));
            codeDao.insert(code);
        }
    }

    @Test
    public void getCode(){
        Set<String> allCode = redisTemplate.keys(codeKey+"*");
        if (allCode == null || allCode.size() == 0){
            List<Code> codeList = codeDao.selectAllCode();
            if (!CollectionUtils.isEmpty(codeList)){
                codeList.forEach(e -> e.setDiscountCode(codeKey+e.getDiscountCode()));
                Map<String,Integer> map = codeList.stream().collect(Collectors.toMap(Code::getDiscountCode,code -> 0));
                redisTemplate.opsForValue().multiSet(map);
            }
        }
        Set<String> selectiveCode = new HashSet<>();
        for (String code :allCode){
            if (redisTemplate.opsForSet().add(userdkey,code) > 0){
                selectiveCode.add(code);
                redisTemplate.opsForValue().set(code,1);
            }else{
                continue;
            }
            if (selectiveCode.size() >= count){
                System.out.println(selectiveCode);
                break;
            }

        }
    }

    @Test
    public void delete(){
        System.out.println(redisTemplate.opsForValue().get("allCode_579447973"));
    }


    public StringRedisConnection getConnection(){
        return (StringRedisConnection) redisTemplate.getConnectionFactory().getConnection();
    }
}
