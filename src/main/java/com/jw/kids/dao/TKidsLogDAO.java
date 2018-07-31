package com.jw.kids.dao;

import com.jw.kids.bean.TKidsLog;
import com.jw.kids.bean.TKidsLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TKidsLogDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_kids_log
     *
     * @mbggenerated Sat Jul 14 11:42:53 CST 2018
     */
    int countByExample(TKidsLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_kids_log
     *
     * @mbggenerated Sat Jul 14 11:42:53 CST 2018
     */
    int deleteByExample(TKidsLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_kids_log
     *
     * @mbggenerated Sat Jul 14 11:42:53 CST 2018
     */
    int deleteByPrimaryKey(Long logId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_kids_log
     *
     * @mbggenerated Sat Jul 14 11:42:53 CST 2018
     */
    int insert(TKidsLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_kids_log
     *
     * @mbggenerated Sat Jul 14 11:42:53 CST 2018
     */
    int insertSelective(TKidsLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_kids_log
     *
     * @mbggenerated Sat Jul 14 11:42:53 CST 2018
     */
    List<TKidsLog> selectByExample(TKidsLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_kids_log
     *
     * @mbggenerated Sat Jul 14 11:42:53 CST 2018
     */
    TKidsLog selectByPrimaryKey(Long logId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_kids_log
     *
     * @mbggenerated Sat Jul 14 11:42:53 CST 2018
     */
    int updateByExampleSelective(@Param("record") TKidsLog record, @Param("example") TKidsLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_kids_log
     *
     * @mbggenerated Sat Jul 14 11:42:53 CST 2018
     */
    int updateByExample(@Param("record") TKidsLog record, @Param("example") TKidsLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_kids_log
     *
     * @mbggenerated Sat Jul 14 11:42:53 CST 2018
     */
    int updateByPrimaryKeySelective(TKidsLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_kids_log
     *
     * @mbggenerated Sat Jul 14 11:42:53 CST 2018
     */
    int updateByPrimaryKey(TKidsLog record);
}