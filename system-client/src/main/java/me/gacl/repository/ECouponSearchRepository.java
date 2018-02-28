package me.gacl.repository;

import me.gacl.dto.ECouponDTO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by jason_moo on 2018/2/27.
 */
public interface ECouponSearchRepository extends ElasticsearchRepository<ECouponDTO,Long> {

}
