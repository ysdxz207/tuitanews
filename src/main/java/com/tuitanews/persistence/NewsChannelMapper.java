package com.tuitanews.persistence;

import com.tuitanews.domain.NewsChannel;
import com.tuitanews.domain.NewsChannelExample;
import java.util.List;

public interface NewsChannelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_channel
     *
     * @mbggenerated Thu Aug 25 17:31:05 CST 2016
     */
    int countByExample(NewsChannelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_channel
     *
     * @mbggenerated Thu Aug 25 17:31:05 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_channel
     *
     * @mbggenerated Thu Aug 25 17:31:05 CST 2016
     */
    int insert(NewsChannel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_channel
     *
     * @mbggenerated Thu Aug 25 17:31:05 CST 2016
     */
    int insertSelective(NewsChannel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_channel
     *
     * @mbggenerated Thu Aug 25 17:31:05 CST 2016
     */
    List<NewsChannel> selectByExample(NewsChannelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_channel
     *
     * @mbggenerated Thu Aug 25 17:31:05 CST 2016
     */
    NewsChannel selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_channel
     *
     * @mbggenerated Thu Aug 25 17:31:05 CST 2016
     */
    int updateByPrimaryKeySelective(NewsChannel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_channel
     *
     * @mbggenerated Thu Aug 25 17:31:05 CST 2016
     */
    int updateByPrimaryKey(NewsChannel record);
}