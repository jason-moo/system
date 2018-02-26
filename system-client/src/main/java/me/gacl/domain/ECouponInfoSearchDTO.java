package me.gacl.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Document(indexName = ECouponInfoSearchDTO.INDEX, type= ECouponInfoSearchDTO.PARENT_TYPE, indexStoreType = "memory", shards = 1, replicas = 0, refreshInterval = "-1")
public class ECouponInfoSearchDTO implements Serializable {

    public static final String INDEX = "ecoupon_info";

    public static final String PARENT_TYPE = "ecoupon_info_search";

    /**
     * 主键
     */
    @Id
    private String id;

    /**
     * 应用名称
     */
    @Field(type = FieldType.text)
    private String name;

    /**
     * 电子券编码唯一标识
     */
    @Field(type = FieldType.text, index = false)
    private String couponInfoCode;


    @Field(type = FieldType.Long)
    private Integer couponCategoryId;

    /**
     * 通用券标识
     * 1：是
     * 0：否
     */
    @Field(type = FieldType.Long)
    private Integer general;

    /**
     * 电子券名称
     */
    @Field(type = FieldType.text)
    private String couponInfoName;

    /**
     * 创建电子券shop编号
     */
    @Field(type = FieldType.Long)
    private Long shopId;

    /**
     * 创建电子券mechent编号
     */
    @Field(type = FieldType.Long)
    private Long merchantId;

    /**
     * 创建电子券shop或者mechent名称
     */
    @Field(type = FieldType.text)
    private String businessName;

    /**
     * 领取生效时间有效天数
     * 针对生效、失效时间是领取后生效的情况，如果生效方式是固定日期，则为0
     * 例如领取电子券时间为t，则t到t+n时间段内有效
     */
    @Field(type = FieldType.Long)
    private Integer validDays;

    /**
     * 生效等待期，即领取后waitDays天开始生效
     */
    @Field(type = FieldType.Long)
    private Integer waitDays;

    /**
     * 失效时间
     */
    @Field(type = FieldType.Date)
    private Date expireTime;

    /**
     * 生效时间
     */
    @Field(type = FieldType.Date)
    private Date validTime;

    /**
     * 面额
     */
    @Field(type = FieldType.text)
    private BigDecimal denomination;

    /**
     * 描述
     */
    @Field(type = FieldType.text)
    private String description;

    /**
     * 简介
     */
    @Field(type = FieldType.text)
    private String introduction;

    /**
     * 电子券详细页图片地址
     */
    @Field(type = FieldType.text)
    private String detailPic;


    /**
     * 创建电子券时间
     */
    @Field(type = FieldType.Date)
    private Date createdTime;

    /**
     * 状态：1，正常；0，删除
     */
    @Field(type = FieldType.Long)
    private Integer status;

    /**
     * 更新时间
     */
    @Field(type = FieldType.Date)
    private Date updatedTime;

    /**
     * 适用时段{'bindingHours':['00:00-02:00','03:00-20:00']}
     */
    @Field(type = FieldType.text)
    private String bindingHours;

    /**
     * 电子券启用状态，0未启用，1启用，2已过期
     */
    @Field(type = FieldType.Long)
    private Integer isAvailable;

    /**
     * 是否优惠同享(0,不共享，10，与会员共享，20，与营销活动共享，99，全部共享)
     */
    @Field(type = FieldType.Long)
    private Integer privilegeShare;

    /**
     * 0,自制;1,代制
     */
    @Field(type = FieldType.Long)
    private Integer facture;

    /**
     * 代制者id
     */
    @Field(type = FieldType.Long)
    private Long substituteId;

    /**
     * 子代制商id
     */
    @Field(type = FieldType.Long)
    private Long subAgentId;

    /**
     * 通用券的子wemall id
     */
    @Field(type = FieldType.Long)
    private Long wemallId;

    /**
     * 核销渠道
     */
    @Field(type = FieldType.Long)
    private Integer applyWay;

    /**
     * 成本价
     */
    @Field(type = FieldType.text)
    private BigDecimal cost;

    /**
     * 建议售价
     */
    @Field(type = FieldType.text)
    private BigDecimal suggestedPrice;

    /**
     * 列表缩略图
     */
    @Field(type = FieldType.text)
    private String listPic;

    /**
     * 代制商名称
     */
    @Field(type = FieldType.text)
    private String substituteName;

    /**
     * 使用规则
     */
    @Field(type = FieldType.text)
    private String useRules;

    /**
     * 时间区间(month:每月,week:每周,day:每天)
     */
    @Field(type = FieldType.text)
    private String period;

    /**
     * 具体适用的某天， period是每周时，applyDay是1-7表示周一到周日,以英文逗号分隔，
     * 如1,2表示周一和周二; period是每月时，applyDay表示1-31中的某天,例如1表示1号，20表示20号
     */
    @Field(type = FieldType.text)
    private String applyDay;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCouponInfoCode() {
        return couponInfoCode;
    }

    public void setCouponInfoCode(String couponInfoCode) {
        this.couponInfoCode = couponInfoCode;
    }

    public Integer getCouponCategoryId() {
        return couponCategoryId;
    }

    public void setCouponCategoryId(Integer couponCategoryId) {
        this.couponCategoryId = couponCategoryId;
    }

    public Integer getGeneral() {
        return general;
    }

    public void setGeneral(Integer general) {
        this.general = general;
    }

    public String getCouponInfoName() {
        return couponInfoName;
    }

    public void setCouponInfoName(String couponInfoName) {
        this.couponInfoName = couponInfoName;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public Integer getValidDays() {
        return validDays;
    }

    public void setValidDays(Integer validDays) {
        this.validDays = validDays;
    }

    public Integer getWaitDays() {
        return waitDays;
    }

    public void setWaitDays(Integer waitDays) {
        this.waitDays = waitDays;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Date getValidTime() {
        return validTime;
    }

    public void setValidTime(Date validTime) {
        this.validTime = validTime;
    }

    public BigDecimal getDenomination() {
        return denomination;
    }

    public void setDenomination(BigDecimal denomination) {
        this.denomination = denomination;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getDetailPic() {
        return detailPic;
    }

    public void setDetailPic(String detailPic) {
        this.detailPic = detailPic;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getBindingHours() {
        return bindingHours;
    }

    public void setBindingHours(String bindingHours) {
        this.bindingHours = bindingHours;
    }

    public Integer getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Integer isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Integer getPrivilegeShare() {
        return privilegeShare;
    }

    public void setPrivilegeShare(Integer privilegeShare) {
        this.privilegeShare = privilegeShare;
    }

    public Integer getFacture() {
        return facture;
    }

    public void setFacture(Integer facture) {
        this.facture = facture;
    }

    public Long getSubstituteId() {
        return substituteId;
    }

    public void setSubstituteId(Long substituteId) {
        this.substituteId = substituteId;
    }

    public Long getSubAgentId() {
        return subAgentId;
    }

    public void setSubAgentId(Long subAgentId) {
        this.subAgentId = subAgentId;
    }

    public Long getWemallId() {
        return wemallId;
    }

    public void setWemallId(Long wemallId) {
        this.wemallId = wemallId;
    }

    public Integer getApplyWay() {
        return applyWay;
    }

    public void setApplyWay(Integer applyWay) {
        this.applyWay = applyWay;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getSuggestedPrice() {
        return suggestedPrice;
    }

    public void setSuggestedPrice(BigDecimal suggestedPrice) {
        this.suggestedPrice = suggestedPrice;
    }

    public String getListPic() {
        return listPic;
    }

    public void setListPic(String listPic) {
        this.listPic = listPic;
    }

    public String getSubstituteName() {
        return substituteName;
    }

    public void setSubstituteName(String substituteName) {
        this.substituteName = substituteName;
    }

    public String getUseRules() {
        return useRules;
    }

    public void setUseRules(String useRules) {
        this.useRules = useRules;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getApplyDay() {
        return applyDay;
    }

    public void setApplyDay(String applyDay) {
        this.applyDay = applyDay;
    }
}