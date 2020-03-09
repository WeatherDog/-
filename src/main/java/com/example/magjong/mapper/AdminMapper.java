package com.example.magjong.mapper;

import com.example.magjong.dto.AdminDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Author 雷铭涛
 * Date:2020-03-08
 * vsersion 1.0
 */
@Mapper
public interface AdminMapper {
    @Select("select * from admin where user_name=#{username} and password=#{password}")
    public AdminDTO verification(String username,String password);
}
