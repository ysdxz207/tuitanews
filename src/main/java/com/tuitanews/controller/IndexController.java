package com.tuitanews.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tuitanews.domain.ApiChannelVO;
import com.tuitanews.domain.NewsBean;
import com.tuitanews.domain.NewsBeanExample;
import com.tuitanews.domain.NewsBeanExample.Criteria;
import com.tuitanews.domain.NewsBeanVO;
import com.tuitanews.service.ApiChannelService;
import com.tuitanews.service.NewsApiService;
import com.tuitanews.service.NewsBeanService;
import com.tuitanews.utils.Constants;


@Controller
public class IndexController {
	
	@Autowired
	NewsApiService newsApiService;
	@Autowired
	NewsBeanService newsBeanService;
	@Autowired
	ApiChannelService apiChannelService;
	
	private final static Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@RequestMapping(value="/index", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView doIndex(Model model) {
		return new ModelAndView("index");
	}
	
	@RequestMapping(value="/index.ajax", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Object getIndexData(Model model,
			@RequestParam(value="pageOffset", required = true) Integer pageOffset,
			@RequestParam(value="pageSize", required = true) Integer pageSize,
			@RequestParam(value="newsChannelId", required = false) Integer newsChannelId) {
		
		if (newsChannelId == null){
			newsChannelId = 0;//默认为推荐新闻页
		}
		Map<String, Object> map = new HashMap<String, Object>();
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("pageOffset", pageOffset);
		params.put("pageSize", pageSize);
		params.put("newsChannelId", newsChannelId);
		List<NewsBeanVO> list = null;
		try {
			list = newsBeanService.selectNewsBeanList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("newsList", list);
		return map;
	}
	
	@RequestMapping(value="/newsDetail.ajax", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Object getNewsDetailData(Model model,
			@RequestParam(value="newsBeanId", required = true) Integer newsBeanId) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		NewsBean newsbean = null;
		try {
			newsbean = newsBeanService.selectNewsBean(newsBeanId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
		}
		
		map.put("news", newsbean);
		return map;
	}
	
	@RequestMapping(value="/syncNews", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView sysncNewsBean(Model model) throws UnsupportedEncodingException{
		Map<String, Object> map = new HashMap<String, Object>();
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("channelId", "5572a10bb3cdc86cf39001f7");//科普最新
		params.put("needContent", 1);
		params.put("needHtml", 1);
		
		List<NewsBeanVO> list = newsApiService.getNewsListByApi(params);
		try {
			for (NewsBeanVO newsBeanVO : list) {
				NewsBeanExample newsBeanExample = new NewsBeanExample();
				Criteria criteria = newsBeanExample.createCriteria();
				criteria.andTitleEqualTo(newsBeanVO.getTitle());
				int count = newsBeanService.countByExample(newsBeanExample);
				System.out.println(count);
				if (count <= 0){
					newsBeanService.insertNewsBeanByVo(newsBeanVO);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
		}
		params.clear();
		params.put("pageOffset", 0);
		params.put("pageSize", Constants.DEFAULT_PAGE_SIZE);
		List<NewsBeanVO> listNews = newsBeanService.selectNewsBeanList(params);
		map.put("newsList", listNews);
		return new ModelAndView("index", map);
	}
	
	@RequestMapping(value="/sysncApiChannel", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> sysncApiChannel(Model model) throws UnsupportedEncodingException{
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<ApiChannelVO> list = newsApiService.getApiChannelByApi();
		try {
			for (ApiChannelVO apiChannelVO : list) {
				apiChannelService.insertApiChannelByVo(apiChannelVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
		}
		map.put("success", "同步成功");
		return map;
	}
	
}
