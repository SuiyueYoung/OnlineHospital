package com.rectangle.onlinehospital.mapper;

import com.rectangle.onlinehospital.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM users WHERE userId=#{userID}")
    User findByUserID(String userID);

    @Select("SELECT * FROM users")
    List<User> findAllUser();
}
