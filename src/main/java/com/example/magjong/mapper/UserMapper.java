package com.example.magjong.mapper;

import com.example.magjong.dto.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("insert into user values(#{user.id},#{user.name},#{user.bio},#{user.avatarUrl})")
    public void createUser(@Param("user") User user);
    @Select("select count(id) from user where id=#{id}")
    public Integer judgeUser(Long id);
    @Select("select * from user where id=#{id}")
    public User selectUser(Long id);
    @Select("select * from user")
    public List<User> getList();
    @Select("select avatar_url from user where id=#{id}")
    public String getSrc(Integer id);
    @Select({"select count(1) from user where id=#{id}"})
    public Integer slectName(Integer id);
}
