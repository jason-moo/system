package me.gacl.domain;

import java.io.Serializable;
import java.util.Date;

public class Dessy implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dessy.id
     *
     * @mbggenerated Tue Aug 30 17:48:40 CST 2016
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dessy.user_id
     *
     * @mbggenerated Tue Aug 30 17:48:40 CST 2016
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dessy.content
     *
     * @mbggenerated Tue Aug 30 17:48:40 CST 2016
     */
    private String content;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dessy.create_time
     *
     * @mbggenerated Tue Aug 30 17:48:40 CST 2016
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dessy.update_time
     *
     * @mbggenerated Tue Aug 30 17:48:40 CST 2016
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table dessy
     *
     * @mbggenerated Tue Aug 30 17:48:40 CST 2016
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dessy.id
     *
     * @return the value of dessy.id
     *
     * @mbggenerated Tue Aug 30 17:48:40 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dessy.id
     *
     * @param id the value for dessy.id
     *
     * @mbggenerated Tue Aug 30 17:48:40 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dessy.user_id
     *
     * @return the value of dessy.user_id
     *
     * @mbggenerated Tue Aug 30 17:48:40 CST 2016
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dessy.user_id
     *
     * @param userId the value for dessy.user_id
     *
     * @mbggenerated Tue Aug 30 17:48:40 CST 2016
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dessy.content
     *
     * @return the value of dessy.content
     *
     * @mbggenerated Tue Aug 30 17:48:40 CST 2016
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dessy.content
     *
     * @param content the value for dessy.content
     *
     * @mbggenerated Tue Aug 30 17:48:40 CST 2016
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dessy.create_time
     *
     * @return the value of dessy.create_time
     *
     * @mbggenerated Tue Aug 30 17:48:40 CST 2016
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dessy.create_time
     *
     * @param createTime the value for dessy.create_time
     *
     * @mbggenerated Tue Aug 30 17:48:40 CST 2016
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dessy.update_time
     *
     * @return the value of dessy.update_time
     *
     * @mbggenerated Tue Aug 30 17:48:40 CST 2016
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dessy.update_time
     *
     * @param updateTime the value for dessy.update_time
     *
     * @mbggenerated Tue Aug 30 17:48:40 CST 2016
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}