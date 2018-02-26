package com.crm.service;

import com.crm.base.BaseJunit4Test;
import com.crm.dao.mapper.CodeDao;
import me.gacl.domain.Code;
import me.gacl.plugin.Page;
import me.gacl.utils.RandomUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.io.FileInputStream;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
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
//            code.setDiscountCode(String.valueOf(System.identityHashCode(UUID.randomUUID().toString()+System.nanoTime())));
            code.setDiscountCode(RandomUtils.generateNumberString(9));
            codeDao.insert(code);
        }
    }

    @Test
    public void testmget(){
        String[] keys = {"sadsads","fdsfsfd","ffgsdada"};
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                StringRedisConnection stringRedisConnection =  (StringRedisConnection)redisConnection;
                List<String> strings = stringRedisConnection.mGet(keys);
                return null;
            }
        });


    }


    @Test
    public void getCode(){
        Set<String> allCode = redisTemplate.keys(codeKey+"*");
        if (allCode == null || allCode.size() == 0){
            List<Code> codeList = codeDao.selectAllCode(1,new Page<>());
            if (!CollectionUtils.isEmpty(codeList)){
                codeList.forEach(e -> e.setDiscountCode(codeKey+e.getDiscountCode()));
                Map<String,Integer> map = codeList.stream().collect(Collectors.toMap(Code::getDiscountCode,code -> 0));
                redisTemplate.opsForValue().multiSet(map);
            }
        }
        allCode = redisTemplate.keys(codeKey+"*");
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
        List<String> codeList = selectiveCode.stream().map(e->e.substring(8,e.length())).collect(Collectors.toList());
        if (codeDao.batchUpdateStatus(codeList) > 0){
            redisTemplate.execute(new RedisCallback() {
                @Override
                public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                    StringRedisConnection stringRedisConnection = (StringRedisConnection) redisConnection;
                    codeList.forEach(e-> stringRedisConnection.del(codeKey+e));
                    stringRedisConnection.close();
                    return null;
                }
            });
        }
//        taskExecutor.execute(new UpdateCodeStatus(codeList));

    }

    class UpdateCodeStatus implements Runnable{

        private List<String> codes;

        public UpdateCodeStatus(List<String> codeList) {
            this.codes = codeList;
        }
        @Override
        public void run() {
            if (codeDao.batchUpdateStatus(codes) > 0){
                redisTemplate.execute(new RedisCallback() {
                    @Override
                    public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                        StringRedisConnection stringRedisConnection = (StringRedisConnection) redisConnection;
                        codes.forEach(e-> stringRedisConnection.del(e));
                        stringRedisConnection.close();
                        return null;
                    }
                });
            }
        }
    }

    @Test
    public void test(){
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                StringRedisConnection stringRedisConnection = (StringRedisConnection)redisConnection;
                stringRedisConnection.select(2);
                List<String> keys = new ArrayList<>();
                keys.add("qax");
                keys.add("wsx");
                keys.add("wsxddd");
                String[] keyArray = new String[keys.size()];
                keys.toArray(keyArray);
                byte[][] ret = new byte[keys.size()][];
                for (int i = 0; i < ret.length; i++) {
                    ret[i] = keyArray[i].getBytes();
                }
                stringRedisConnection.watch(ret);
                keys.parallelStream().forEach(key-> System.out.println(key));
                stringRedisConnection.multi();
                keys.stream().forEach(key ->{
                    stringRedisConnection.setNX(key,"sdads");
                });
                List<Object> result = stringRedisConnection.exec();

                stringRedisConnection.set("gfdfsfdadddd","");
                String value1 = stringRedisConnection.get("gfdfsfdadddd");
                System.out.println(value1);
                return null;
            }

        });


    }



    @Test
    public void delete(){
        Code code = new Code();
        code.setId(13L);
        code.setDiscountCode("gfdgsfsfdsdsa");
        code.setStatus(1);
        redisTemplate.opsForValue().set("avd",code);
        Code code1 = (Code)redisTemplate.opsForValue().get("avd");
        System.out.println(code1.getDiscountCode());
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                StringRedisConnection stringRedisConnection = (StringRedisConnection)redisConnection;
                stringRedisConnection.select(0);
                return null;
            }
        });
//        List<String> sda = new ArrayList<>();
//        sda.add("allCode_442020858");
//        sda.add("allCode_791542953");
//        sda.add("allCode_740887201");
//        sda.add("allCode_218810378");
//        sda.add("allCode_503778859");
//        sda.add("allCode_851251274");
//        List<String> codeList = sda.stream().map(e->e.substring(8,e.length())).collect(Collectors.toList());
//        System.out.println(codeDao.batchUpdateStatus(codeList) > 0);
    }


    public StringRedisConnection getConnection(){
        return (StringRedisConnection) redisTemplate.getConnectionFactory().getConnection();
    }


}
