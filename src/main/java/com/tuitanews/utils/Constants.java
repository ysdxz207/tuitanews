package com.tuitanews.utils;

import java.util.ArrayList;
import java.util.List;

import com.tuitanews.domain.ApiChannelVO;

public class Constants {
	public static final int DEFAULT_PAGE_SIZE = 20;
	/*
	 * 易源新闻
	 */
	public static String NEWS_YIYUAN_SEARCH_URL = "http://apis.baidu.com/showapi_open_bus/channel_news/search_news";
	public static String NEWS_YIYUAN_CHANNEL_URL = "http://apis.baidu.com/showapi_open_bus/channel_news/channel_news";
	/*
	 * 实时新闻
	 */
	public static String NEWS_REAL_TIME_SEARCH_URL = "http://apis.baidu.com/songshuxiansheng/real_time/search_news";
	
	/*
	 * api channel列表
	 */
	public static List<ApiChannelVO>  apiChannelList = new ArrayList<ApiChannelVO>();
}
