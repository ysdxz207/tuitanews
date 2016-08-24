package com.tuitanews.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuitanews.domain.NewsBean;
import com.tuitanews.domain.NewsBeanExample;
import com.tuitanews.domain.NewsBeanVO;
import com.tuitanews.persistence.NewsBeanCustomMapper;
import com.tuitanews.persistence.NewsBeanMapper;

@Service
public class NewsBeanService {

	@Autowired
	private NewsBeanMapper newsBeanMapper;
	@Autowired
	private NewsBeanCustomMapper newsBeanCustomMapper;
	
	
	public void insertNewsBeanByVo(NewsBeanVO newsBeanVO){
		NewsBean newsBean = new NewsBean();
		BeanUtils.copyProperties(newsBeanVO, newsBean);
		newsBean.setCreateDate(new Date());
		newsBeanMapper.insertSelective(newsBean);
	}

	public List<NewsBeanVO> selectNewsBeanList(HashMap<String, Object> params) {
		return newsBeanCustomMapper.selectNewsBeanList(params);
	}
	
	public List<NewsBean> selectByExample(NewsBeanExample newsBeanExample){
		return newsBeanMapper.selectByExample(newsBeanExample);
	}
	
	public int countByExample(NewsBeanExample newsBeanExample){
		return newsBeanMapper.countByExample(newsBeanExample);
	}
	
	
}
