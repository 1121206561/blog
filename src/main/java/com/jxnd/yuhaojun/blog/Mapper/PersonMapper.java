package com.jxnd.yuhaojun.blog.Mapper;

import com.jxnd.yuhaojun.blog.model.Person;
import com.jxnd.yuhaojun.blog.model.PersonExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PersonMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person
     *
     * @mbg.generated Thu Mar 12 08:56:55 CST 2020
     */
    long countByExample(PersonExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person
     *
     * @mbg.generated Thu Mar 12 08:56:55 CST 2020
     */
    int deleteByExample(PersonExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person
     *
     * @mbg.generated Thu Mar 12 08:56:55 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person
     *
     * @mbg.generated Thu Mar 12 08:56:55 CST 2020
     */
    int insert(Person record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person
     *
     * @mbg.generated Thu Mar 12 08:56:55 CST 2020
     */
    int insertSelective(Person record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person
     *
     * @mbg.generated Thu Mar 12 08:56:55 CST 2020
     */
    List<Person> selectByExample(PersonExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person
     *
     * @mbg.generated Thu Mar 12 08:56:55 CST 2020
     */
    Person selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person
     *
     * @mbg.generated Thu Mar 12 08:56:55 CST 2020
     */
    int updateByExampleSelective(@Param("record") Person record, @Param("example") PersonExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person
     *
     * @mbg.generated Thu Mar 12 08:56:55 CST 2020
     */
    int updateByExample(@Param("record") Person record, @Param("example") PersonExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person
     *
     * @mbg.generated Thu Mar 12 08:56:55 CST 2020
     */
    int updateByPrimaryKeySelective(Person record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person
     *
     * @mbg.generated Thu Mar 12 08:56:55 CST 2020
     */
    int updateByPrimaryKey(Person record);
}