package com.rectangle.onlinehospital.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rectangle.onlinehospital.mapper.CheckItemMapper;
import com.rectangle.onlinehospital.pojo.CheckItem;
import org.springframework.stereotype.Service;

@Service
public class CheckItemServiceImpl extends ServiceImpl<CheckItemMapper, CheckItem> implements CheckItemService {

}
