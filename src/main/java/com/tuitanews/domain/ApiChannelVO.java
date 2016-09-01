package com.tuitanews.domain;
import java.util.Date;
public class ApiChannelVO {
    private Integer id;
    private String name;//////
	private String channelId;
    private String channelName;
    private Date createDate;
    private Date updateTime;
    private Integer status;
    
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getChannelId() {
        return channelId;
    }
    public void setChannelId(String channelId) {
        this.channelId = channelId == null ? null : channelId.trim();
    }
    public String getChannelName() {
        return channelName;
    }
    public void setChannelName(String channelName) {
        this.channelName = channelName == null ? null : channelName.trim();
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
}