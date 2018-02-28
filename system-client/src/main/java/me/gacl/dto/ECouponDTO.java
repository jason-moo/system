package me.gacl.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jason_moo on 2018/2/27.
 */
@Document(indexName = "info",type = "ecoupon",replicas = 1,shards = 3,indexStoreType = "fs")
public class ECouponDTO implements Serializable{

    @Id
    private Long id;

    @Field(type = FieldType.String)
    private String xCardUserId;

    @Field(type = FieldType.String)
    private String couponDiscountCode;

    @Field(type = FieldType.String)
    private String couponVerifyCode;

    @Field(type = FieldType.String)
    private String mobileNumber;

    @Field(type = FieldType.String)
    private String couponOrderNumber;

    @Field(type = FieldType.Integer)
    private Integer orderType;

    @Field(type = FieldType.String)
    private String couponInfoCode;
    @Field(type = FieldType.String)
    private String couponInfoName;

    @Field(type = FieldType.Long)
    private Long couponInfoChannelId;

    @Field(type = FieldType.Long)
    private Long couponInfoChannelDetailId;

    @Field(type = FieldType.Long)
    private Long wemallSaleId;

    @Field(type = FieldType.Long)
    private Long wemallId;

    @Field(type = FieldType.Integer)
    private Integer couponCategoryId;

    @Field(type = FieldType.Integer)
    private Integer general;

    @Field(type = FieldType.Double)
    private BigDecimal actualPrice;

    @Field(type = FieldType.Double)
    private BigDecimal serverFee;

    @Field(type = FieldType.Double)
    private BigDecimal price;

    @Field(type = FieldType.Integer)
    private Integer exchangeType;

    @Field(type = FieldType.Double)
    private BigDecimal denomination;

    @Field(type = FieldType.Long)
    private Long exchangePrice;
    @Field(type = FieldType.Long)
    private Long shopId;

    @Field(type = FieldType.String)
    private String shopName;

    @Field(type = FieldType.String)
    private String operatorName;

    @Field(type = FieldType.Long)
    private Long operatorId;

    @Field(type = FieldType.Integer)
    private Integer result;

    @Field(type = FieldType.Date)
    private Date verifyTime;
    @Field(type = FieldType.Date)
    private Date refundApplyTime;

    @Field(type = FieldType.Date)
    private Date refundTime;

    @Field(type = FieldType.String)
    private String refundReason;

    @Field(type = FieldType.String)
    private String refundOrderNumber;

    @Field(type = FieldType.Integer)
    private Integer enableRefund;

    @Field(type = FieldType.String)
    private String refundUrl;

    @Field(type = FieldType.Integer)
    private Integer getChannel;

    @Field(type = FieldType.Integer)
    private Integer getChannelDetail;

    @Field(type = FieldType.Date)
    private Date validTime;

    @Field(type = FieldType.Date)
    private Date expireTime;

    @Field(type = FieldType.Date)
    private Date createdTime;

    @Field(type = FieldType.Date)
    private Date updatedTime;

    @Field(type = FieldType.Integer)
    private Integer paymentType;

    @Field(type = FieldType.String)
    private String paymentTypeStr;

    @Field(type = FieldType.Long)
    private Long acceptBusinessId;

    @Field(type = FieldType.String)
    private String acceptBusinessType;

    @Field(type = FieldType.String)
    private String acceptBusinessName;

    @Field(type = FieldType.Integer)
    private Integer status;

    @Field(type = FieldType.String)
    private String comment;

    @Field(type = FieldType.Long)
    private Long orderShopId;

    @Field(type = FieldType.Long)
    private Long orderMerchantId;

    @Field(type = FieldType.Integer)
    private Integer clientCode;

    @Field(type = FieldType.String)
    private String lockOrderNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getxCardUserId() {
        return xCardUserId;
    }

    public void setxCardUserId(String xCardUserId) {
        this.xCardUserId = xCardUserId;
    }

    public String getCouponDiscountCode() {
        return couponDiscountCode;
    }

    public void setCouponDiscountCode(String couponDiscountCode) {
        this.couponDiscountCode = couponDiscountCode;
    }

    public String getCouponVerifyCode() {
        return couponVerifyCode;
    }

    public void setCouponVerifyCode(String couponVerifyCode) {
        this.couponVerifyCode = couponVerifyCode;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getCouponOrderNumber() {
        return couponOrderNumber;
    }

    public void setCouponOrderNumber(String couponOrderNumber) {
        this.couponOrderNumber = couponOrderNumber;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getCouponInfoCode() {
        return couponInfoCode;
    }

    public void setCouponInfoCode(String couponInfoCode) {
        this.couponInfoCode = couponInfoCode;
    }

    public String getCouponInfoName() {
        return couponInfoName;
    }

    public void setCouponInfoName(String couponInfoName) {
        this.couponInfoName = couponInfoName;
    }

    public Long getCouponInfoChannelId() {
        return couponInfoChannelId;
    }

    public void setCouponInfoChannelId(Long couponInfoChannelId) {
        this.couponInfoChannelId = couponInfoChannelId;
    }

    public Long getCouponInfoChannelDetailId() {
        return couponInfoChannelDetailId;
    }

    public void setCouponInfoChannelDetailId(Long couponInfoChannelDetailId) {
        this.couponInfoChannelDetailId = couponInfoChannelDetailId;
    }

    public Long getWemallSaleId() {
        return wemallSaleId;
    }

    public void setWemallSaleId(Long wemallSaleId) {
        this.wemallSaleId = wemallSaleId;
    }

    public Long getWemallId() {
        return wemallId;
    }

    public void setWemallId(Long wemallId) {
        this.wemallId = wemallId;
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

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    public BigDecimal getServerFee() {
        return serverFee;
    }

    public void setServerFee(BigDecimal serverFee) {
        this.serverFee = serverFee;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(Integer exchangeType) {
        this.exchangeType = exchangeType;
    }

    public BigDecimal getDenomination() {
        return denomination;
    }

    public void setDenomination(BigDecimal denomination) {
        this.denomination = denomination;
    }

    public Long getExchangePrice() {
        return exchangePrice;
    }

    public void setExchangePrice(Long exchangePrice) {
        this.exchangePrice = exchangePrice;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Date getVerifyTime() {
        return verifyTime;
    }

    public void setVerifyTime(Date verifyTime) {
        this.verifyTime = verifyTime;
    }

    public Date getRefundApplyTime() {
        return refundApplyTime;
    }

    public void setRefundApplyTime(Date refundApplyTime) {
        this.refundApplyTime = refundApplyTime;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }

    public String getRefundOrderNumber() {
        return refundOrderNumber;
    }

    public void setRefundOrderNumber(String refundOrderNumber) {
        this.refundOrderNumber = refundOrderNumber;
    }

    public Integer getEnableRefund() {
        return enableRefund;
    }

    public void setEnableRefund(Integer enableRefund) {
        this.enableRefund = enableRefund;
    }

    public String getRefundUrl() {
        return refundUrl;
    }

    public void setRefundUrl(String refundUrl) {
        this.refundUrl = refundUrl;
    }

    public Integer getGetChannel() {
        return getChannel;
    }

    public void setGetChannel(Integer getChannel) {
        this.getChannel = getChannel;
    }

    public Integer getGetChannelDetail() {
        return getChannelDetail;
    }

    public void setGetChannelDetail(Integer getChannelDetail) {
        this.getChannelDetail = getChannelDetail;
    }

    public Date getValidTime() {
        return validTime;
    }

    public void setValidTime(Date validTime) {
        this.validTime = validTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentTypeStr() {
        return paymentTypeStr;
    }

    public void setPaymentTypeStr(String paymentTypeStr) {
        this.paymentTypeStr = paymentTypeStr;
    }

    public Long getAcceptBusinessId() {
        return acceptBusinessId;
    }

    public void setAcceptBusinessId(Long acceptBusinessId) {
        this.acceptBusinessId = acceptBusinessId;
    }

    public String getAcceptBusinessType() {
        return acceptBusinessType;
    }

    public void setAcceptBusinessType(String acceptBusinessType) {
        this.acceptBusinessType = acceptBusinessType;
    }

    public String getAcceptBusinessName() {
        return acceptBusinessName;
    }

    public void setAcceptBusinessName(String acceptBusinessName) {
        this.acceptBusinessName = acceptBusinessName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getOrderShopId() {
        return orderShopId;
    }

    public void setOrderShopId(Long orderShopId) {
        this.orderShopId = orderShopId;
    }

    public Long getOrderMerchantId() {
        return orderMerchantId;
    }

    public void setOrderMerchantId(Long orderMerchantId) {
        this.orderMerchantId = orderMerchantId;
    }

    public Integer getClientCode() {
        return clientCode;
    }

    public void setClientCode(Integer clientCode) {
        this.clientCode = clientCode;
    }

    public String getLockOrderNumber() {
        return lockOrderNumber;
    }

    public void setLockOrderNumber(String lockOrderNumber) {
        this.lockOrderNumber = lockOrderNumber;
    }
}
