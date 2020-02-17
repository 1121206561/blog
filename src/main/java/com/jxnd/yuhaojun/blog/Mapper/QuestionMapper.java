package com.jxnd.yuhaojun.blog.Mapper;

import com.jxnd.yuhaojun.blog.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

//对问题数据库的信息进行处理
@Mapper
public interface QuestionMapper {
    @Insert("INSERT INTO question (title,description,gmt_create,gmt_modified,creator,tag) VALUES (#{title},#{description},#{gmt_create},#{gmt_modified},#{creator},#{tag})")
    void insert(Question question);
}
