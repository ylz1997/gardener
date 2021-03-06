package com.jw.kids.dao;

import com.jw.kids.bean.TClassSchdule;
import com.jw.kids.bean.TClassSchduleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TClassSchduleDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_class_schdule
     *
     * @mbggenerated Sun Jul 08 10:47:51 CST 2018
     */
    int countByExample(TClassSchduleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_class_schdule
     *
     * @mbggenerated Sun Jul 08 10:47:51 CST 2018
     */
    int deleteByExample(TClassSchduleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_class_schdule
     *
     * @mbggenerated Sun Jul 08 10:47:51 CST 2018
     */
    int deleteByPrimaryKey(Long schduleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_class_schdule
     *
     * @mbggenerated Sun Jul 08 10:47:51 CST 2018
     */
    int insert(TClassSchdule record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_class_schdule
     *
     * @mbggenerated Sun Jul 08 10:47:51 CST 2018
     */
    int insertSelective(TClassSchdule record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_class_schdule
     *
     * @mbggenerated Sun Jul 08 10:47:51 CST 2018
     */
    List<TClassSchdule> selectByExample(TClassSchduleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_class_schdule
     *
     * @mbggenerated Sun Jul 08 10:47:51 CST 2018
     */
    TClassSchdule selectByPrimaryKey(Long schduleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_class_schdule
     *
     * @mbggenerated Sun Jul 08 10:47:51 CST 2018
     */
    int updateByExampleSelective(@Param("record") TClassSchdule record, @Param("example") TClassSchduleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_class_schdule
     *
     * @mbggenerated Sun Jul 08 10:47:51 CST 2018
     */
    int updateByExample(@Param("record") TClassSchdule record, @Param("example") TClassSchduleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_class_schdule
     *
     * @mbggenerated Sun Jul 08 10:47:51 CST 2018
     */
    int updateByPrimaryKeySelective(TClassSchdule record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_class_schdule
     *
     * @mbggenerated Sun Jul 08 10:47:51 CST 2018
     */
    int updateByPrimaryKey(TClassSchdule record);
}