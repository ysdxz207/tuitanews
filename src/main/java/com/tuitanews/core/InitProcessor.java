package com.tuitanews.core;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.tuitanews.domain.ApiChannelVO;
import com.tuitanews.service.ApiChannelService;

/**
 * spring启动执行
 * @author ysdxz207
 *
 */
public class InitProcessor implements ApplicationListener<ContextRefreshedEvent>{
	
	@Autowired
	private ApiChannelService apiChannelService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		putApiChannel();
	}
	
	/**
	 * 查询apiChannel
	 */
	public void putApiChannel(){
		List<ApiChannelVO> apiChannelList = apiChannelService.getChannelListConstants();
	}
}
