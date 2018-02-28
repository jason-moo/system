package com.crm.search;

import com.crm.base.BaseJunit4Test;
import me.gacl.dto.ECouponDTO;
import me.gacl.repository.ECouponSearchRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

/**
 * Created by jason_moo on 2018/2/27.
 */
public class SearchTest extends BaseJunit4Test {

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    ECouponSearchRepository eCouponSearchRepository;

    @Test
    public void testSaveAll(){
        ECouponDTO eCouponDTO = new ECouponDTO();
        eCouponDTO.setId(213l);
        eCouponSearchRepository.save(eCouponDTO);
    }

}
