package com.lenokia.appointment.hosp.service;

import com.lenokia.yygh.vo.hosp.DepartmentVo;
import org.springframework.data.domain.Page;
import com.lenokia.yygh.model.hosp.Department;
import com.lenokia.yygh.vo.hosp.DepartmentQueryVo;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    void save(Map<String, Object> map);

    Page<Department> selectPage(Integer page, Integer limit, DepartmentQueryVo departmentQueryVo);

    void remove(String hoscode, String depcode);


    List<DepartmentVo> findDeptTree(String hoscode);

    String getDepName(String hoscode, String depcode);

    Department getDepartment(String hoscode, String depcode);
}
