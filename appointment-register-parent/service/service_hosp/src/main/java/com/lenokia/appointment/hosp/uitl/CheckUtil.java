package com.lenokia.appointment.hosp.uitl;

import com.lenokia.appointment.common.Exception.YyghException;
import com.lenokia.appointment.common.Result.ResultCodeEnum;
import com.lenokia.appointment.common.utils.MD5;
import com.lenokia.appointment.hosp.service.HospitalSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CheckUtil {

    @Autowired
    private HospitalSetService hospitalSetService;

    public void check(Map<String, Object> map) {
        String hoscode = (String)map.get("hoscode");
        String hospSign = (String) map.get("sign");
        String signkey = hospitalSetService.getSignKey(hoscode);

        String signKeyMD5 = MD5.encrypt(signkey);
        if (!hospSign.equals(signKeyMD5)) {
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }
    }
}
