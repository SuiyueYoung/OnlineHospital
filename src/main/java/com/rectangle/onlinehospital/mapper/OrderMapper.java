package com.rectangle.onlinehospital.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rectangle.onlinehospital.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
