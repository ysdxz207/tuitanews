package com.tuitanews.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuitanews.core.MessageResolver;
import com.tuitanews.domain.NewsBeanVO;
import com.tuitanews.utils.ApiRequest;
import com.tuitanews.utils.Constants;

@Service
public class NewsApiService {

	@Autowired
	private MessageResolver messageResolver;
	
	
	public List<NewsBeanVO> getNewsListByApi(HashMap<String, Object> params){
		List<NewsBeanVO> list = new ArrayList<NewsBeanVO>();
		
		String baiduApikey = messageResolver.getMessage("apikey.baidu");
		String responseValue = ApiRequest.request(Constants.NEWS_YIYUAN_SEARCH_URL, baiduApikey, params);			
		JSONObject jsonObject = new JSONObject(responseValue);
		String err = (String) jsonObject.get("showapi_res_error");
		JSONObject li = jsonObject.getJSONObject("showapi_res_body").getJSONObject("pagebean");
		Integer allPages = li.getInt("allPages");
		Integer maxResult = li.getInt("maxResult");
		Integer currentPage = li.getInt("currentPage");
		JSONArray contentList = li.getJSONArray("contentlist");
		
		ObjectMapper mapper = new ObjectMapper();
		for (int i = 0; i < contentList.length(); i++) {
			JSONObject content = contentList.optJSONObject(i);
			
			JSONArray imageurls = content.optJSONArray("imageurls");
			
			JSONObject faceUrlObj = null;
			if (imageurls != null && imageurls.length() > 0){
				faceUrlObj = imageurls.getJSONObject(0);
			}
			
			
			content.remove("allList");
			content.remove("imageurls");
			try {
				NewsBeanVO newsBeanVO = mapper.readValue(content.toString(), NewsBeanVO.class);
				if (faceUrlObj != null && StringUtils.isNotEmpty(faceUrlObj.getString("url"))){
					newsBeanVO.setFaceUrl(faceUrlObj.getString("url"));
				} else {
					newsBeanVO.setFaceUrl("");
				}
				list.add(newsBeanVO);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
