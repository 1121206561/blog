package com.jxnd.yuhaojun.blog.Mapper;

import com.jxnd.yuhaojun.blog.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

//Mybatis对数据库的具体操作
@Mapper
public interface UserMapper {
    @Insert("INSERT INTO user (account_id,login,token,gmt_create,gmt_modified,avatar_url) VALUES (#{account_id},#{login},#{token},#{gmt_create},#{gmt_modified},#{avatar_url})")
    void insert(User user);

    @Select("SELECT * FROM user WHERE token = #{token}")
    User select(String token);

    @Select("SELECT * FROM user WHERE login = #{creator}")
    User selectByCreator(String creator);


    @Select("SELECT * FROM user WHERE account_id = #{account_id}")
    User selectByAccount(Long id);

    @Update("UPDATE user SET token= #{token},gmt_create= #{gmt_create},gmt_modified= #{gmt_modified} WHERE account_id = #{account_id}")
    void update(User user);
}
