package com.rectangle.onlinehospital.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rectangle.onlinehospital.entity.Hospital;
import com.rectangle.onlinehospital.utils.Result;
import org.springframework.web.bind.annotation.RequestBody;

public interface HospitalService extends IService<Hospital> {
   // Result<Hospital> getByHospitalID(Integer hpID);
}
