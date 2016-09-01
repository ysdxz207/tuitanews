package com.tuitanews.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class ApiRequest {

	/**
	 * @param urlAll
	 *            :请求接口
	 * @param params
	 *            channelId(新闻频道id，必须精确匹配,默认值：5572a109b3cdc86cf39001db)
	 *            channelName(新闻频道名称，可使用%模糊匹配，默认值：国内最新)
	 *            title(新闻标题，模糊匹配，默认值：上市)
	 *            page(页数，默认1。每页最多20条记录。默认值：1)
	 *            needContent(是否需要返回正文，1为需要，其他为不需要，默认值：0)
	 *            needHtml(是否需要返回正文的html格式，1为需要，其他为不需要，默认值：0)
	 * @return 返回结果
	 */
	public static String request(String httpUrl, String apikey, Map<String, Object> params) {
	    BufferedReader reader = null;
	    String result = null;
	    StringBuffer sbf = new StringBuffer();
	    httpUrl = httpUrl + "?" + getStringParams(params);
	    try {
	        URL url = new URL(httpUrl);
	        HttpURLConnection connection = (HttpURLConnection) url
	                .openConnection();
	        connection.setRequestMethod("GET");
	        // 填入apikey到HTTP header
	        connection.setRequestProperty("apikey",  apikey);
	        connection.connect();
	        InputStream is = connection.getInputStream();
	        reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        String strRead = null;
	        while ((strRead = reader.readLine()) != null) {
	            sbf.append(strRead);
	            sbf.append("\r\n");
	        }
	        reader.close();
	        result = sbf.toString();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}

	private static String getStringParams(Map<String, Object> params) {
		if(params == null){
			return "";
		}
		StringBuffer strParams = new StringBuffer();
		for (String key : params.keySet()) {
			String value = "";
			try {
				value = URLEncoder.encode(params.get(key).toString(), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			strParams.append(key + "=" + value + "&");
		}
		return strParams.toString();
	}
	
	public static void main(String[] args) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("channelId", "5572a10bb3cdc86cf39001f7");//科普最新
		String result = request(Constants.NEWS_YIYUAN_SEARCH_URL, "e04b1335e03ad0ec621e1d380dc2c2de", params);
		
		JSONObject json = new JSONObject(result);
		JSONObject li = json.getJSONObject("showapi_res_body").getJSONObject("pagebean");
		JSONArray contentList = li.getJSONArray("contentlist");
		for (int i = 0; i < contentList.length(); i++) {
			JSONObject content = contentList.getJSONObject(i);
			JSONArray allList = content.getJSONArray("allList");
			//System.out.println(allList);
			for (int j = 0; j < allList.length(); j++) {
				Object obj = allList.get(j);
				if (JsonUtils.isGoodJsonObject(obj.toString())){
					//System.out.println(obj);
					allList.put(j, "<img src=\"" + ((JSONObject)obj).getString("url") + "\" />");
				}
			}
			//System.out.println(allList);
		}
		
	}
}
