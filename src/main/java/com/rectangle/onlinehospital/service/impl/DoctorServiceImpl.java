package com.rectangle.onlinehospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rectangle.onlinehospital.entity.Order;
import com.rectangle.onlinehospital.entity.SetMeal;
import com.rectangle.onlinehospital.entity.User;
import com.rectangle.onlinehospital.entity.response.PhyExamReserveVo;
import com.rectangle.onlinehospital.entity.security.UserDetailsDo;
import com.rectangle.onlinehospital.mapper.*;
import com.rectangle.onlinehospital.entity.Doctor;
import com.rectangle.onlinehospital.service.DoctorService;
import com.rectangle.onlinehospital.utils.JwtTokenUtil;
import com.rectangle.onlinehospital.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorServiceImpl extends ServiceImpl<DoctorMapper, Doctor> implements DoctorService {

    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    private final DoctorMapper doctorMapper;
    private final OrderMapper orderMapper;
    private final UserMapper userMapper;
    private final SetMealMapper setMealMapper;


    @Autowired
    public DoctorServiceImpl(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, DoctorMapper doctorMapper, OrderMapper orderMapper, UserMapper userMapper, SetMealMapper setMealMapper) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.doctorMapper = doctorMapper;
        this.orderMapper = orderMapper;
        this.userMapper = userMapper;
        this.setMealMapper = setMealMapper;
    }

    @Override
    public Result<String> doctorLogin(String username, String password) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            UserDetailsDo userDetailsDo = (UserDetailsDo) authentication.getPrincipal();
            return Result.success(jwtTokenUtil.generateToken(userDetailsDo));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public IPage<PhyExamReserveVo> getPhyExamReserve(Integer page, Integer size, Integer doctorID) {
        Integer hospitalID = doctorMapper.selectById(doctorID).getHospitalID();
        System.out.println(hospitalID);
        IPage<Order> orders = orderMapper.selectPage(
                new Page<>(page, size),
                new QueryWrapper<Order>().eq("hpId", hospitalID)
        );

        List<PhyExamReserveVo> reserveVoList = new ArrayList<>();
        for (Order order : orders.getRecords()) {
            User user = userMapper.selectById(order.getUserID());
            SetMeal setMeal = setMealMapper.selectById(order.getSmID());

            PhyExamReserveVo phyExamReserveVo = new PhyExamReserveVo();

            phyExamReserveVo.setPhysicalExaminationDate(order.getOrderDate());
            phyExamReserveVo.setSex(user.getSex());
            phyExamReserveVo.setOrderID(order.getOrderID());
            phyExamReserveVo.setPhoneNumber(user.getUserID());
            phyExamReserveVo.setRealName(user.getRealName());
            phyExamReserveVo.setSetMealType(setMeal.getType());

            reserveVoList.add(phyExamReserveVo);
        }

        return new Page<PhyExamReserveVo>(page, size)
                .setRecords(reserveVoList)
                .setTotal(reserveVoList.size());
    }
}
