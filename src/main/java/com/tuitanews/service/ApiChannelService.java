package com.tuitanews.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuitanews.domain.ApiChannel;
import com.tuitanews.domain.ApiChannelVO;
import com.tuitanews.persistence.ApiChannelCustomMapper;
import com.tuitanews.persistence.ApiChannelMapper;
import com.tuitanews.utils.Constants;

@Service
public class ApiChannelService {

	@Autowired
	ApiChannelCustomMapper apiChannelCustomMapper;
	@Autowired
	ApiChannelMapper apiChannelMapper;
	
	public List<ApiChannelVO> getChannelListConstants(){
		if (Constants.apiChannelList.size() == 0){
			List<ApiChannelVO> apiChannelList = apiChannelCustomMapper.selectApiChannelList(new HashMap<String, Object>());
			Constants.apiChannelList = apiChannelList;
			return apiChannelList;
		} else {
			return Constants.apiChannelList;
		}
	}

	public void insertApiChannelByVo(ApiChannelVO apiChannelVO) {
		ApiChannel apiChannel = new ApiChannel();
		BeanUtils.copyProperties(apiChannelVO, apiChannel);
		apiChannelMapper.insertSelective(apiChannel);
	}
}
