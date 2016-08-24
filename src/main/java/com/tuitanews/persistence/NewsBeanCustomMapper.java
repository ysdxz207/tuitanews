package com.tuitanews.persistence;

import java.util.HashMap;
import java.util.List;

import com.tuitanews.domain.NewsBeanVO;

public interface NewsBeanCustomMapper {

	List<NewsBeanVO> selectNewsBeanList(HashMap<String, Object> params);
}