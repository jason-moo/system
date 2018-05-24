package com.crm.service;

import me.gacl.domain.ECoupon;
import me.gacl.service.ECouponSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jason_moo on 2018/2/27.
 */
@Service
public class ECouponSearchServiceImpl implements ECouponSearchService {

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

}
