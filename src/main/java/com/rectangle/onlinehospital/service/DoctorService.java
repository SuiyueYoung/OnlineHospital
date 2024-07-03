package com.rectangle.onlinehospital.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rectangle.onlinehospital.entity.Doctor;
import com.rectangle.onlinehospital.entity.response.PhyExamReserveVo;
import com.rectangle.onlinehospital.utils.Result;

public interface DoctorService extends IService<Doctor> {
    Result<String> doctorLogin(String username, String password);

    IPage<PhyExamReserveVo> getPhyExamReserve(Integer page, Integer size, Integer doctorID);
}
