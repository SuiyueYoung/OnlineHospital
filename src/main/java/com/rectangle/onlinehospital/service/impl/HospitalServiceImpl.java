package com.rectangle.onlinehospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rectangle.onlinehospital.mapper.HospitalMapper;
import com.rectangle.onlinehospital.entity.Hospital;
import com.rectangle.onlinehospital.service.HospitalService;
import com.rectangle.onlinehospital.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalServiceImpl extends ServiceImpl<HospitalMapper, Hospital> implements HospitalService {

    private final HospitalMapper hospitalMapper;

    @Autowired
    public HospitalServiceImpl(HospitalMapper hospitalMapper) {
        this.hospitalMapper = hospitalMapper;
    }


//    @Override
//    public Result<Hospital> getByHospitalID(Integer hpID) {
//        QueryWrapper<Hospital> hospitalQueryWrapper = new QueryWrapper<>();
//        hospitalQueryWrapper.select("hpID", "name","businessHours","deadline","telephone","address");
//        hospitalQueryWrapper.eq("hpID", hpID);
//        List<Hospital> hospitalList = hospitalMapper.selectList(hospitalQueryWrapper);
//        return Result.success(hospitalList);
//    }
}
