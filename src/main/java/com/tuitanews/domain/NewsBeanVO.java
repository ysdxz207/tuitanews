package com.tuitanews.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewsBeanVO implements Serializable{
	private Integer id;
	private String nid;
	private String title;//标题
	private String content;//内容
	private String source;//来源
	private String html;//
	private String contentWithImgs;
	private String faceUrl;//封面图片url
	private String desc;//描述
	private String channelId;//频道ID
	private String channelName;//频道名
	private String link;//链接
	private String imageurls;//图片链接
	private String pubDate;//发布时日
	private Date creatDate;//创建日期
	
	@JsonProperty("allList")
	private String allList;//
	
	private Integer newsChannelId;
	
	
	
	public Integer getNewsChannelId() {
		return newsChannelId;
	}
	public void setNewsChannelId(Integer newsChannelId) {
		this.newsChannelId = newsChannelId;
	}
	public String getContentWithImgs() {
		return contentWithImgs;
	}
	public void setContentWithImgs(String contentWithImgs) {
		this.contentWithImgs = contentWithImgs;
	}
	public String getAllList() {
		return allList;
	}
	public void setAllList(String allList) {
		this.allList = allList;
	}
	public String getNid() {
		return nid;
	}
	public void setNid(String nid) {
		this.nid = nid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFaceUrl() {
		return faceUrl;
	}
	public void setFaceUrl(String faceUrl) {
		this.faceUrl = faceUrl;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Date getCreatDate() {
		return creatDate;
	}
	public void setCreatDate(Date creatDate) {
		this.creatDate = creatDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getImageurls() {
		return imageurls;
	}
	public void setImageurls(String imageurls) {
		this.imageurls = imageurls;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	
	
	
}
