package com.tuitanews.persistence;

import com.tuitanews.domain.NewsBean;
import com.tuitanews.domain.NewsBeanExample;
import java.util.List;

public interface NewsBeanMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_bean
     *
     * @mbggenerated Wed Sep 07 20:26:38 CST 2016
     */
    int countByExample(NewsBeanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_bean
     *
     * @mbggenerated Wed Sep 07 20:26:38 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_bean
     *
     * @mbggenerated Wed Sep 07 20:26:38 CST 2016
     */
    int insert(NewsBean record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_bean
     *
     * @mbggenerated Wed Sep 07 20:26:38 CST 2016
     */
    int insertSelective(NewsBean record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_bean
     *
     * @mbggenerated Wed Sep 07 20:26:38 CST 2016
     */
    List<NewsBean> selectByExample(NewsBeanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_bean
     *
     * @mbggenerated Wed Sep 07 20:26:38 CST 2016
     */
    NewsBean selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_bean
     *
     * @mbggenerated Wed Sep 07 20:26:38 CST 2016
     */
    int updateByPrimaryKeySelective(NewsBean record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_bean
     *
     * @mbggenerated Wed Sep 07 20:26:38 CST 2016
     */
    int updateByPrimaryKey(NewsBean record);
}