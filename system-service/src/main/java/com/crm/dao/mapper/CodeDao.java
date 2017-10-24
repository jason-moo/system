package com.crm.dao.mapper;

import me.gacl.domain.Code;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by jason_moo on 2017/10/23.
 */
public interface CodeDao {

    void insert(Code code);

    List<Code> selectAllCode();

    Integer batchUpdateStatus(@Param("codeList") List<String> codeList);

}
