package com.tuitanews.core.quartz;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.jms.Destination;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tuitanews.domain.ApiChannelVO;
import com.tuitanews.domain.NewsBeanExample;
import com.tuitanews.domain.NewsBeanExample.Criteria;
import com.tuitanews.domain.NewsBeanVO;
import com.tuitanews.service.ActivemqProducerService;
import com.tuitanews.service.NewsApiService;
import com.tuitanews.service.NewsBeanService;
import com.tuitanews.utils.Constants;
import com.tuitanews.utils.DateConverter;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class NewsBeanJob {

	@Autowired
	private NewsApiService newsApiService;
	@Autowired
	private NewsBeanService newsBeanService;
	@Autowired
	private ActivemqProducerService activemqProducerService;
	@Autowired
	private Destination destination;  
	
	private final static Logger logger = LoggerFactory.getLogger(NewsBeanJob.class);
	/**
	 * 
	 */
	public void doSyncNews() {
		for (ApiChannelVO apiChannelVO : Constants.apiChannelList) {
			getNewsFromApi(apiChannelVO);
		}
	}
	
	public void getNewsFromApi(ApiChannelVO apiChannelVO){

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("channelId", apiChannelVO.getChannelId());
		params.put("needContent", 1);
		params.put("needHtml", 1);

		List<NewsBeanVO> list = newsApiService.getNewsListByApi(params);
		
		//去重数据
		List<NewsBeanVO> listWithoutDup = new ArrayList<>(new HashSet<>(list));
		
		int n = listWithoutDup.size();
		try {
			for (NewsBeanVO newsBeanVO : listWithoutDup) {
				NewsBeanExample newsBeanExample = new NewsBeanExample();
				Criteria criteria = newsBeanExample.createCriteria();
				criteria.andTitleEqualTo(newsBeanVO.getTitle());
				int count = newsBeanService.countByExample(newsBeanExample);
				if (count > 0) {
					n --;
					continue;
				}
				activemqProducerService.sendMessage(destination, newsBeanVO);//生产者发送队列消息
				//logger.info(newsBeanVO.getTitle());
			}
			//logger.info("---------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(DateConverter.formatDateTime(new Date()) + "[" + apiChannelVO.getChannelName() + "]频道同步了" + n + "条新闻");
	}
	
}
