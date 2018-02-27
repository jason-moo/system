package com.crm.service;

import com.crm.base.BaseJunit4Test;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

/**
 * Created by jason_moo on 2018/2/26.
 */
public class ElasticSearchTest extends BaseJunit4Test{

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;


    @Test
    public void test(){

        System.out.println(elasticsearchTemplate.indexExists("fdsda"));

    }

}
