package com.rectangle.onlinehospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rectangle.onlinehospital.entity.Doctor;
import com.rectangle.onlinehospital.entity.security.UserDetailsDo;
import com.rectangle.onlinehospital.exception.CustomerAuthenticationException;
import com.rectangle.onlinehospital.mapper.DoctorMapper;
import com.rectangle.onlinehospital.mapper.UserMapper;
import com.rectangle.onlinehospital.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserMapper userMapper;
    private final DoctorMapper doctorMapper;

    @Autowired
    public UserDetailsServiceImpl(UserMapper userMapper, DoctorMapper doctorMapper) {
        this.userMapper = userMapper;
        this.doctorMapper = doctorMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("userID", username));
        Doctor doctor = doctorMapper.selectOne(new QueryWrapper<Doctor>().eq("docCode", username));

        if (!Objects.isNull(user) && !Objects.isNull(doctor)) {
            throw new CustomerAuthenticationException("Username duplicate");
        }
        if (!Objects.isNull(user)) {
            return new UserDetailsDo(user, null);
        }
        if (!Objects.isNull(doctor)) {
            return new UserDetailsDo(null, doctor);
        }

        throw new UsernameNotFoundException("Fail to find User or Doctor");
    }
}
