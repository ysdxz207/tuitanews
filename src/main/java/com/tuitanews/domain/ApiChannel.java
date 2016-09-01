package com.tuitanews.domain;

import java.util.Date;

public class ApiChannel {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column api_channel.id
     *
     * @mbggenerated Sat Aug 27 14:43:12 CST 2016
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column api_channel.channel_id
     *
     * @mbggenerated Sat Aug 27 14:43:12 CST 2016
     */
    private String channelId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column api_channel.channel_name
     *
     * @mbggenerated Sat Aug 27 14:43:12 CST 2016
     */
    private String channelName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column api_channel.create_date
     *
     * @mbggenerated Sat Aug 27 14:43:12 CST 2016
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column api_channel.update_time
     *
     * @mbggenerated Sat Aug 27 14:43:12 CST 2016
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column api_channel.status
     *
     * @mbggenerated Sat Aug 27 14:43:12 CST 2016
     */
    private Integer status;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column api_channel.id
     *
     * @return the value of api_channel.id
     *
     * @mbggenerated Sat Aug 27 14:43:12 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column api_channel.id
     *
     * @param id the value for api_channel.id
     *
     * @mbggenerated Sat Aug 27 14:43:12 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column api_channel.channel_id
     *
     * @return the value of api_channel.channel_id
     *
     * @mbggenerated Sat Aug 27 14:43:12 CST 2016
     */
    public String getChannelId() {
        return channelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column api_channel.channel_id
     *
     * @param channelId the value for api_channel.channel_id
     *
     * @mbggenerated Sat Aug 27 14:43:12 CST 2016
     */
    public void setChannelId(String channelId) {
        this.channelId = channelId == null ? null : channelId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column api_channel.channel_name
     *
     * @return the value of api_channel.channel_name
     *
     * @mbggenerated Sat Aug 27 14:43:12 CST 2016
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column api_channel.channel_name
     *
     * @param channelName the value for api_channel.channel_name
     *
     * @mbggenerated Sat Aug 27 14:43:12 CST 2016
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName == null ? null : channelName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column api_channel.create_date
     *
     * @return the value of api_channel.create_date
     *
     * @mbggenerated Sat Aug 27 14:43:12 CST 2016
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column api_channel.create_date
     *
     * @param createDate the value for api_channel.create_date
     *
     * @mbggenerated Sat Aug 27 14:43:12 CST 2016
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column api_channel.update_time
     *
     * @return the value of api_channel.update_time
     *
     * @mbggenerated Sat Aug 27 14:43:12 CST 2016
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column api_channel.update_time
     *
     * @param updateTime the value for api_channel.update_time
     *
     * @mbggenerated Sat Aug 27 14:43:12 CST 2016
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column api_channel.status
     *
     * @return the value of api_channel.status
     *
     * @mbggenerated Sat Aug 27 14:43:12 CST 2016
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column api_channel.status
     *
     * @param status the value for api_channel.status
     *
     * @mbggenerated Sat Aug 27 14:43:12 CST 2016
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}