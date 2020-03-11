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

    @Select("SELECT * FROM question ORDER BY gmt_create desc limit #{offset},#{size}")
    List<Question> select(Integer offset, Integer size);

    @Select("SELECT COUNT(1) FROM question")
    Integer selectByCount();

    @Select("SELECT * FROM question WHERE creator = #{creator} ORDER BY gmt_create desc limit #{offset},#{size}")
    List<Question> selectByCreator(Integer offset, Integer size, String creator);

    @Select("SELECT COUNT(1) FROM question WHERE creator = #{creator}")
    Integer selectByCountCreator(String creator);

    @Select("SELECT * FROM question WHERE id = #{id}")
    Question selectById(Integer id);

    @Update("UPDATE question SET title= #{title},description= #{description},tag= #{tag} WHERE id = #{id}")
    void update(Question question);

    @Update("UPDATE question SET view_count = view_count + 1 WHERE id = #{id}")
    void updateByView(Integer id);

    @Update("UPDATE question SET comment_count = comment_count + 1 WHERE id = #{id}")
    void updateByComment(Integer id);

    @Select("SELECT * FROM question WHERE tag LIKE '%' #{tag} '%'")
    List<Question> selectByTag(String tag);

    @Select("SELECT * FROM question WHERE title LIKE '%' #{seach} '%' ORDER BY gmt_create desc limit #{offset},#{size}")
    List<Question> selectBySeach(String seach, Integer offset, Integer size);

    @Select("SELECT COUNT(1) FROM question WHERE title LIKE '%' #{seach} '%'")
    Integer selectByCountSeach(String seach);

    @Select("SELECT COUNT(1) FROM question WHERE tag LIKE '%' #{hotTag} '%'")
    Integer selectByCountTag(String hotTag);

    @Select("SELECT COUNT(1) FROM question WHERE title LIKE '%' #{seach} '%' AND tag = #{hotTag}")
    Integer selectByCountSeachTag(String seach, String hotTag);

    @Select("SELECT * FROM question WHERE tag LIKE '%' #{hotTag} '%' ORDER BY gmt_create desc limit #{offset},#{size}")
    List<Question> seletByLimitTag(Integer offset, Integer size, String hotTag);

    @Select("SELECT * FROM question WHERE title LIKE '%' #{seach} '%' AND tag LIKE '%' #{hotTag} '%' ORDER BY gmt_create desc limit #{offset},#{size}")
    List<Question> selectByLimitSeachTag(Integer offset, String seach, Integer size, String hotTag);

    @Select("SELECT * FROM question WHERE comment_count = 0 ORDER BY gmt_create desc limit #{offset},#{size}")
    List<Question> selectPlusZero(Integer offset,Integer size);

    @Select("SELECT * FROM question WHERE #{gmt_create} - gmt_create < 2592000000 ORDER BY gmt_create desc limit #{offset},#{size}")
    List<Question> selectPlusThirty(Long gmt_create,Integer offset,Integer size);

    @Select("SELECT * FROM question WHERE #{gmt_create} - gmt_create < 604800000 ORDER BY gmt_create desc limit #{offset},#{size}")
    List<Question> selectPlusSeven(Long gmt_create,Integer offset,Integer size);

    @Select("SELECT COUNT(1) FROM question WHERE comment_count = 0")
    Integer selectByCountZero();

    @Select("SELECT COUNT(1) FROM question WHERE #{gmt_create} - gmt_create < 2592000000 ")
    Integer selectByCountThirty(Long gmt_create);

    @Select("SELECT COUNT(1) FROM question WHERE #{gmt_create} - gmt_create < 604800000")
    Integer selectByCountSeven(Long gmt_create);
}
