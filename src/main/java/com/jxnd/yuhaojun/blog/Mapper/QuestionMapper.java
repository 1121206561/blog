package com.jxnd.yuhaojun.blog.Mapper;

import com.jxnd.yuhaojun.blog.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

//对问题数据库的信息进行处理
@Mapper
public interface QuestionMapper {
    @Insert("INSERT INTO question (title,description,gmt_create,gmt_modified,creator,tag) VALUES (#{title},#{description},#{gmt_create},#{gmt_modified},#{creator},#{tag})")
    void insert(Question question);

    @Select("SELECT * FROM question limit #{offset},#{size}")
    List<Question> select(Integer offset, Integer size);

    @Select("SELECT COUNT(1) FROM question")
    Integer selectByCount();

    @Select("SELECT * FROM question WHERE creator = #{creator} limit #{offset},#{size}")
    List<Question> selectByCreator(Integer offset, Integer size, String creator);

    @Select("SELECT COUNT(1) FROM question WHERE creator = #{creator}")
    Integer selectByCountCreator(String creator);

    @Select("SELECT * FROM question WHERE id = #{id}")
    Question selectById(Integer id);

    @Update("UPDATE question SET title= #{title},description= #{description},tag= #{tag} WHERE id = #{id}")
    void update(Question question);
}
