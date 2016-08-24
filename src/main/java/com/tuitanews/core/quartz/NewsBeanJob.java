package com.tuitanews.core.quartz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tuitanews.domain.NewsBeanExample;
import com.tuitanews.domain.NewsBeanExample.Criteria;
import com.tuitanews.domain.NewsBeanVO;
import com.tuitanews.service.NewsApiService;
import com.tuitanews.service.NewsBeanService;

public class NewsBeanJob {

	@Autowired
	private NewsApiService newsApiService;
	@Autowired
	private NewsBeanService newsBeanService;
	/**
	 * 
	 */
	public void doSyncNews() {
		Map<String, Object> map = new HashMap<String, Object>();

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("channelId", "5572a108b3cdc86cf39001cd");// 国内最新
		params.put("needHtml", 1);

		List<NewsBeanVO> list = newsApiService.getNewsListByApi(params);
		int n = list.size();
		
		try {
			for (NewsBeanVO newsBeanVO : list) {
				NewsBeanExample newsBeanExample = new NewsBeanExample();
				Criteria criteria = newsBeanExample.createCriteria();
				criteria.andTitleEqualTo(newsBeanVO.getTitle());
				int count = newsBeanService.countByExample(newsBeanExample);
				if (count > 0) {
					n --;
					continue;
				}
				newsBeanService.insertNewsBeanByVo(newsBeanVO);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("sync news num = " + n);
	}
}
