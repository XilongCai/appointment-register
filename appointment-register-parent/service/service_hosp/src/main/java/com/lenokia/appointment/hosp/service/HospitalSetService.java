package com.lenokia.appointment.hosp.service;

import com.lenokia.yygh.model.hosp.HospitalSet;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lenokia.yygh.vo.order.SignInfoVo;

public interface HospitalSetService extends IService<HospitalSet> {

    String getSignKey(String hoscode);

    SignInfoVo getSignInfoVo(String hoscode);
}
