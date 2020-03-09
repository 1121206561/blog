package com.jxnd.yuhaojun.blog.Mapper;

import com.jxnd.yuhaojun.blog.model.VideoComment;
import com.jxnd.yuhaojun.blog.model.VideoCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VideoCommentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table videocomment
     *
     * @mbg.generated Mon Mar 09 09:02:16 CST 2020
     */
    long countByExample(VideoCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table videocomment
     *
     * @mbg.generated Mon Mar 09 09:02:16 CST 2020
     */
    int deleteByExample(VideoCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table videocomment
     *
     * @mbg.generated Mon Mar 09 09:02:16 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table videocomment
     *
     * @mbg.generated Mon Mar 09 09:02:16 CST 2020
     */
    int insert(VideoComment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table videocomment
     *
     * @mbg.generated Mon Mar 09 09:02:16 CST 2020
     */
    int insertSelective(VideoComment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table videocomment
     *
     * @mbg.generated Mon Mar 09 09:02:16 CST 2020
     */
    List<VideoComment> selectByExample(VideoCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table videocomment
     *
     * @mbg.generated Mon Mar 09 09:02:16 CST 2020
     */
    VideoComment selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table videocomment
     *
     * @mbg.generated Mon Mar 09 09:02:16 CST 2020
     */
    int updateByExampleSelective(@Param("record") VideoComment record, @Param("example") VideoCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table videocomment
     *
     * @mbg.generated Mon Mar 09 09:02:16 CST 2020
     */
    int updateByExample(@Param("record") VideoComment record, @Param("example") VideoCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table videocomment
     *
     * @mbg.generated Mon Mar 09 09:02:16 CST 2020
     */
    int updateByPrimaryKeySelective(VideoComment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table videocomment
     *
     * @mbg.generated Mon Mar 09 09:02:16 CST 2020
     */
    int updateByPrimaryKey(VideoComment record);
}