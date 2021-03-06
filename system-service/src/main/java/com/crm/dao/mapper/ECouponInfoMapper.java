package com.crm.dao.mapper;

import java.util.List;
import me.gacl.domain.ECouponInfo;
import me.gacl.domain.ECouponInfoExample;
import org.apache.ibatis.annotations.Param;

public interface ECouponInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table e_coupon_info
     *
     * @mbggenerated Sun Dec 24 14:56:44 CST 2017
     */
    int countByExample(ECouponInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table e_coupon_info
     *
     * @mbggenerated Sun Dec 24 14:56:44 CST 2017
     */
    int deleteByExample(ECouponInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table e_coupon_info
     *
     * @mbggenerated Sun Dec 24 14:56:44 CST 2017
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table e_coupon_info
     *
     * @mbggenerated Sun Dec 24 14:56:44 CST 2017
     */
    int insert(ECouponInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table e_coupon_info
     *
     * @mbggenerated Sun Dec 24 14:56:44 CST 2017
     */
    int insertSelective(ECouponInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table e_coupon_info
     *
     * @mbggenerated Sun Dec 24 14:56:44 CST 2017
     */
    List<ECouponInfo> selectByExampleWithBLOBs(ECouponInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table e_coupon_info
     *
     * @mbggenerated Sun Dec 24 14:56:44 CST 2017
     */
    List<ECouponInfo> selectByExample(ECouponInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table e_coupon_info
     *
     * @mbggenerated Sun Dec 24 14:56:44 CST 2017
     */
    ECouponInfo selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table e_coupon_info
     *
     * @mbggenerated Sun Dec 24 14:56:44 CST 2017
     */
    int updateByExampleSelective(@Param("record") ECouponInfo record, @Param("example") ECouponInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table e_coupon_info
     *
     * @mbggenerated Sun Dec 24 14:56:44 CST 2017
     */
    int updateByExampleWithBLOBs(@Param("record") ECouponInfo record, @Param("example") ECouponInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table e_coupon_info
     *
     * @mbggenerated Sun Dec 24 14:56:44 CST 2017
     */
    int updateByExample(@Param("record") ECouponInfo record, @Param("example") ECouponInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table e_coupon_info
     *
     * @mbggenerated Sun Dec 24 14:56:44 CST 2017
     */
    int updateByPrimaryKeySelective(ECouponInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table e_coupon_info
     *
     * @mbggenerated Sun Dec 24 14:56:44 CST 2017
     */
    int updateByPrimaryKeyWithBLOBs(ECouponInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table e_coupon_info
     *
     * @mbggenerated Sun Dec 24 14:56:44 CST 2017
     */
    int updateByPrimaryKey(ECouponInfo record);
}