package com.lenokia.appointment.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lenokia.yygh.model.user.Patient;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PatientMapper extends BaseMapper<Patient> {
}
