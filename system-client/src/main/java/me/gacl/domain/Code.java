package me.gacl.domain;

import java.io.Serializable;

/**
 * Created by jason_moo on 2017/10/23.
 */
public class Code implements Serializable{

    private Integer id;

    private String discountCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }
}
