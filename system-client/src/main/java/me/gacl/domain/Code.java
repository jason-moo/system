package me.gacl.domain;

import java.io.Serializable;

/**
 * Created by jason_moo on 2017/10/23.
 */
public class Code implements Serializable{

    private Long id;

    private String discountCode;

    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }
}
