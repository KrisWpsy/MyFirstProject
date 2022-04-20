package com.sg.gate.musicserver.dao;

import com.sg.gate.musicserver.domain.Singer;
import com.sg.gate.musicserver.domain.SingerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SingerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table singer
     *
     * @mbg.generated Mon Apr 11 10:05:03 CST 2022
     */
    long countByExample(SingerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table singer
     *
     * @mbg.generated Mon Apr 11 10:05:03 CST 2022
     */
    int deleteByExample(SingerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table singer
     *
     * @mbg.generated Mon Apr 11 10:05:03 CST 2022
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table singer
     *
     * @mbg.generated Mon Apr 11 10:05:03 CST 2022
     */
    int insert(Singer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table singer
     *
     * @mbg.generated Mon Apr 11 10:05:03 CST 2022
     */
    int insertSelective(Singer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table singer
     *
     * @mbg.generated Mon Apr 11 10:05:03 CST 2022
     */
    List<Singer> selectByExample(SingerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table singer
     *
     * @mbg.generated Mon Apr 11 10:05:03 CST 2022
     */
    Singer selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table singer
     *
     * @mbg.generated Mon Apr 11 10:05:03 CST 2022
     */
    int updateByExampleSelective(@Param("record") Singer record, @Param("example") SingerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table singer
     *
     * @mbg.generated Mon Apr 11 10:05:03 CST 2022
     */
    int updateByExample(@Param("record") Singer record, @Param("example") SingerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table singer
     *
     * @mbg.generated Mon Apr 11 10:05:03 CST 2022
     */
    int updateByPrimaryKeySelective(Singer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table singer
     *
     * @mbg.generated Mon Apr 11 10:05:03 CST 2022
     */
    int updateByPrimaryKey(Singer record);
}