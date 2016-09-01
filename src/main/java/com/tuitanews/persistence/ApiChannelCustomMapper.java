package com.tuitanews.persistence;

import java.util.HashMap;
import java.util.List;

import com.tuitanews.domain.ApiChannelVO;

public interface ApiChannelCustomMapper {
	public List<ApiChannelVO> selectApiChannelList(HashMap<String, Object> params);
}