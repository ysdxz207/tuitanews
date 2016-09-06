package com.tuitanews.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuitanews.core.MessageResolver;
import com.tuitanews.domain.ApiChannelVO;
import com.tuitanews.domain.NewsBeanVO;
import com.tuitanews.utils.ApiRequest;
import com.tuitanews.utils.Constants;
import com.tuitanews.utils.DateConverter;
import com.tuitanews.utils.JsonUtils;

@Service
public class NewsApiService {

	@Autowired
	private MessageResolver messageResolver;
	
	
	private final static Logger logger = LoggerFactory.getLogger(NewsApiService.class);

	public List<NewsBeanVO> getNewsListByApi(HashMap<String, Object> params) {
		List<NewsBeanVO> list = new ArrayList<NewsBeanVO>();

		String baiduApikey = messageResolver.getMessage("apikey.baidu");
		String responseValue = ApiRequest.request(Constants.NEWS_YIYUAN_SEARCH_URL, baiduApikey, params);
		if (JsonUtils.isBadJsonObject(responseValue)){
			return list;
		}
		
		JSONObject jsonObject = null;
		try {
			jsonObject = new JSONObject(responseValue);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("responseValue=" + responseValue, e);
		}
		String err = (String) jsonObject.get("showapi_res_error");
		JSONObject li = jsonObject.getJSONObject("showapi_res_body").getJSONObject("pagebean");
		Integer allPages = li.getInt("allPages");
		Integer maxResult = li.getInt("maxResult");
		Integer currentPage = li.getInt("currentPage");
		JSONArray contentList = li.getJSONArray("contentlist");
		

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);//忽略不需要的字段
		mapper.setDateFormat(DateConverter.dateTimeFormat);//设置日期格式
		
		for (int i = 0; i < contentList.length(); i++) {
			JSONObject contentObj = contentList.optJSONObject(i);
			
			//处理图片内容
			String newsContent = getNewsContent(contentObj);
			JSONArray imageurls = contentObj.optJSONArray("imageurls");
			//封面图片
			JSONObject faceUrlObj = null;
			if (imageurls != null && imageurls.length() > 0) {
				faceUrlObj = imageurls.getJSONObject(0);
			}
			contentObj.remove("imageurls");
			
			try {
				
				NewsBeanVO newsBeanVO = mapper.readValue(contentObj.toString(), NewsBeanVO.class);
				if (faceUrlObj != null && StringUtils.isNotEmpty(faceUrlObj.getString("url"))) {
					newsBeanVO.setFaceUrl(faceUrlObj.getString("url"));
				} else {
					newsBeanVO.setFaceUrl("");
				}
				
				newsBeanVO.setContentWithImgs(newsContent);
				
				String title = contentObj.optString("title");
				String desc = contentObj.optString("desc");
				if (StringUtils.isEmpty(title)){
					newsBeanVO.setTitle(desc);
				}
				list.add(newsBeanVO);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("", e);
			}
		}
		return list;
	}
	/**
	 * 处理新闻内容图片
	 * @param allList
	 */
	private String getNewsContent(JSONObject contentObj) {
		JSONArray allList = contentObj.optJSONArray("allList");
		String content = contentObj.getString("html");
		if (allList == null){
			return content;
		}
		StringBuffer sb = new StringBuffer();
		try {
			for (int j = 0; j < allList.length(); j++) {
				Object obj = allList.get(j);
				try {
					if (JsonUtils.isGoodJsonObject(obj.toString())){
						sb.append("<img src=\"" + ((JSONObject)obj).getString("url") + "\" />");
					} else {
						sb.append("<p>" + obj.toString() + "</p>");
					}
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("obj=" + obj.toString(), e);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		contentObj.remove("allList");
		return sb.toString();
	}

	public List<ApiChannelVO> getApiChannelByApi() {
		List<ApiChannelVO> list = new ArrayList<ApiChannelVO>();

		String baiduApikey = messageResolver.getMessage("apikey.baidu");
		String responseValue = ApiRequest.request(Constants.NEWS_YIYUAN_CHANNEL_URL, baiduApikey, null);
		JSONObject jsonObject = new JSONObject(responseValue);
		String err = (String) jsonObject.get("showapi_res_error");
		JSONArray channelList = jsonObject.getJSONObject("showapi_res_body").getJSONArray("channelList");

		ObjectMapper mapper = new ObjectMapper();
		for (int i = 0; i < channelList.length(); i++) {
			JSONObject channel = channelList.optJSONObject(i);
			try {
				ApiChannelVO apiChannelVO = mapper.readValue(channel.toString(), ApiChannelVO.class);
				apiChannelVO.setChannelName(apiChannelVO.getName());
				list.add(apiChannelVO);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

}
