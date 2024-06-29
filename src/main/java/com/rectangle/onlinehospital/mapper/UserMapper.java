package com.rectangle.onlinehospital.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rectangle.onlinehospital.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM users WHERE userId=#{userID}")
    User findByUserID(String userID);

    @Select("SELECT * FROM users")
    List<User> findAllUser();
}
