package com.rectangle.onlinehospital.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rectangle.onlinehospital.entity.security.SecurityUserDo;
import com.rectangle.onlinehospital.mapper.DoctorMapper;
import com.rectangle.onlinehospital.entity.Doctor;
import com.rectangle.onlinehospital.service.DoctorService;
import com.rectangle.onlinehospital.utils.JwtTokenUtil;
import com.rectangle.onlinehospital.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class DoctorServiceImpl extends ServiceImpl<DoctorMapper, Doctor> implements DoctorService {

    private final JwtTokenUtil jwtTokenUtil;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public DoctorServiceImpl(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public Result<String> doctorLogin(String username, String password) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityUserDo securityUserDo = (SecurityUserDo) authentication.getPrincipal();
            return Result.success(jwtTokenUtil.generateToken(securityUserDo));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
