package com.tuitanews.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tuitanews.domain.NewsBeanExample;
import com.tuitanews.domain.NewsBeanExample.Criteria;
import com.tuitanews.domain.NewsBeanVO;
import com.tuitanews.service.NewsApiService;
import com.tuitanews.service.NewsBeanService;
import com.tuitanews.utils.Constants;


@Controller
public class IndexController {
	
	@Autowired
	NewsApiService newsApiService;
	@Autowired
	NewsBeanService newsBeanService;
	
	@RequestMapping(value="/index", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView doIndex(Model model) {
		return new ModelAndView("index");
	}
	
	@RequestMapping(value="/index.ajax", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Object getIndexData(Model model,
			@RequestParam(value="pageOffset", required = true) Integer pageOffset,
			@RequestParam(value="pageSize", required = true) Integer pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("pageOffset", pageOffset);
		params.put("pageSize", pageSize);
		List<NewsBeanVO> list = newsBeanService.selectNewsBeanList(params);
		map.put("newsList", list);
		return map;
	}
	
	@RequestMapping(value="/syncNews", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView sysncNewsBean(Model model) throws UnsupportedEncodingException{
		Map<String, Object> map = new HashMap<String, Object>();
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("channelId", "5572a108b3cdc86cf39001cd");//国内最新
		params.put("needHtml", 1);
		params.put("page", 2);
		
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
		}
		params.clear();
		params.put("pageOffset", 0);
		params.put("pageSize", Constants.DEFAULT_PAGE_SIZE);
		List<NewsBeanVO> listNews = newsBeanService.selectNewsBeanList(params);
		map.put("newsList", listNews);
		return new ModelAndView("index", map);
	}
}
