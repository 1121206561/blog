package com.jxnd.yuhaojun.blog.Mapper;

import com.jxnd.yuhaojun.blog.model.Active;
import com.jxnd.yuhaojun.blog.model.ActiveExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActiveMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table active
     *
     * @mbg.generated Wed Mar 11 11:59:59 CST 2020
     */
    long countByExample(ActiveExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table active
     *
     * @mbg.generated Wed Mar 11 11:59:59 CST 2020
     */
    int deleteByExample(ActiveExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table active
     *
     * @mbg.generated Wed Mar 11 11:59:59 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table active
     *
     * @mbg.generated Wed Mar 11 11:59:59 CST 2020
     */
    int insert(Active record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table active
     *
     * @mbg.generated Wed Mar 11 11:59:59 CST 2020
     */
    int insertSelective(Active record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table active
     *
     * @mbg.generated Wed Mar 11 11:59:59 CST 2020
     */
    List<Active> selectByExample(ActiveExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table active
     *
     * @mbg.generated Wed Mar 11 11:59:59 CST 2020
     */
    Active selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table active
     *
     * @mbg.generated Wed Mar 11 11:59:59 CST 2020
     */
    int updateByExampleSelective(@Param("record") Active record, @Param("example") ActiveExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table active
     *
     * @mbg.generated Wed Mar 11 11:59:59 CST 2020
     */
    int updateByExample(@Param("record") Active record, @Param("example") ActiveExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table active
     *
     * @mbg.generated Wed Mar 11 11:59:59 CST 2020
     */
    int updateByPrimaryKeySelective(Active record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table active
     *
     * @mbg.generated Wed Mar 11 11:59:59 CST 2020
     */
    int updateByPrimaryKey(Active record);
}