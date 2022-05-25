package com.lenokia.appointment.hosp.controller;

import com.lenokia.appointment.common.Result.Result;
import com.lenokia.appointment.hosp.service.DepartmentService;
import com.lenokia.yygh.vo.hosp.DepartmentVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin/hosp/department")
@CrossOrigin(allowCredentials = "true")
@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @ApiOperation(value = "查看科室列表")
    @GetMapping("getDeptList/{hoscode}")
    public Result getDeptList(@PathVariable String hoscode) {
        List<DepartmentVo> list = departmentService.findDeptTree(hoscode);
        return Result.ok(list);

    }
}

