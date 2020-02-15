package com.jxnd.yuhaojun.blog.Mapper;

import com.jxnd.yuhaojun.blog.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

//Mybatis对数据库的具体操作
@Mapper
public interface UserMapper {
    @Insert("INSERT INTO user (account_id,login,token,gmt_create,gmt_modified) VALUES (#{account_id},#{login},#{token},#{gmt_create},#{gmt_modified})")
    void insert(User user);
    @Select("SELECT * FROM user WHERE token = #{token}")
    User select(String token);
}
