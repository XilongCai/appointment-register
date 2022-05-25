package com.lenokia.appointment.cmn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lenokia.yygh.model.cmn.Dict;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface DictService extends IService<Dict> {
    //根据数据id查询子数据列表
    List<Dict> findChlidData(Long id);
    void exportData(HttpServletResponse response);
    void importDictData(MultipartFile file);
    String getNameByParentDictCodeAndValue(String parentDictCode, String value);

    List<Dict> findByDictCode(String dictCode);

    Dict getDictByDictCode(String dictCode);
}