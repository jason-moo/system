package com.crm.service;

import com.crm.base.BaseJunit4Test;
import com.crm.dao.mapper.ECouponInfoMapper;
import me.gacl.domain.CaseInfoES;
import me.gacl.domain.ECouponInfoSearchDTO;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;

/**
 * Created by jason_moo on 2017/12/24.
 */
public class ElasticSearchTest extends BaseJunit4Test {

    @Autowired
    private ECouponInfoMapper eCouponInfoMapper;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Before
    public void createIndex(){
        if(elasticsearchTemplate.indexExists(CaseInfoES.class)){
            elasticsearchTemplate.deleteIndex(CaseInfoES.INDEX);
        }
        elasticsearchTemplate.createIndex(CaseInfoES.class);
        elasticsearchTemplate.putMapping(CaseInfoES.class);
        elasticsearchTemplate.refresh(CaseInfoES.class);
    }


//    @Test
//    public void testSave(){
//        caseInfoRepository.deleteAll();
//        ECouponInfoExample infoExample = new ECouponInfoExample();
//        List<ECouponInfo> eCouponInfoList = eCouponInfoMapper.selectByExample(infoExample);
//        List<CaseInfoES> caseInfoES1 = new ArrayList<>();
//        eCouponInfoList.stream().forEach(e ->{
//            CaseInfoES caseInfoES = new CaseInfoES();
//            BeanUtils.copyProperties(e,caseInfoES);
//            caseInfoES.setId(e.getId().toString());
//            caseInfoES1.add(caseInfoES);
//        });
//        caseInfoRepository.save(caseInfoES1);
//    }

    @Test
    public void testSearch()throws Exception{
//        Criteria criteria = new Criteria();
//        criteria.and(
//                new Criteria("couponInfoName").contains("电子券"),
//                new Criteria("couponCategoryId").between(1,3),
//                new Criteria("isAvailable").is(1)
//        );
//        CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        BoolQueryBuilder builder = QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("merchantId","14")).must(QueryBuilders.termQuery("couponCategoryId",1));
//queryBuilder.withQuery(QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("couponInfoName","电子券")).must(QueryBuilders.termQuery("couponCategoryId",1)));
        queryBuilder.withSort(SortBuilders.fieldSort("createdTime").order(SortOrder.DESC));
        queryBuilder.withQuery(builder);
        queryBuilder.withPageable(new PageRequest(0,100));
        Page<CaseInfoES> page = elasticsearchTemplate.queryForPage(queryBuilder.build(),CaseInfoES.class);
    }

    @Test
    public void testSssearch(){
//        elasticsearchTemplate.deleteIndex("xkeshi_market_caseinfo");
//        elasticsearchTemplate.deleteIndex(CaseInfoES.class);
        elasticsearchTemplate.createIndex(ECouponInfoSearchDTO.class);
//        elasticsearchTemplate.putMapping(ECouponInfoSearchDTO.class);
//        elasticsearchTemplate.refresh(ECouponInfoSearchDTO.class, true);
    }

}
