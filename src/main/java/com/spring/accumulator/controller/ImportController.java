package com.spring.accumulator.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.accumulator.common.response.ResponseResult;
import com.spring.accumulator.component.aspect.log.EnumLogType;
import com.spring.accumulator.component.aspect.log.LogAnnotation;
import com.spring.accumulator.component.excel.ExcelComponent;
import com.spring.accumulator.dao.PersonMapper;
import com.spring.accumulator.entity.PersonPO;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/person")
public class ImportController {

    @Resource
    private ExcelComponent excelComponent;

    @Resource
    private PersonMapper personMapper;

    @PostMapping("/import")
    public ResponseResult<Boolean> importPerson(@RequestParam("file") MultipartFile file) throws IOException {
        excelComponent.importPersonFile(file);
        return ResponseResult.success("导入成功", true);
    }

    @GetMapping("/export")
    public void exportPerson(HttpServletResponse response) {
        excelComponent.exportPersonFile(response);
    }

    @GetMapping("/page")
    public ResponseResult<Page<PersonPO>> listPerson(@RequestParam Integer current,
                                                     @RequestParam Integer size) {
        Page<PersonPO> page = new Page<>(current, size);
        page = personMapper.selectPage(page, Wrappers.emptyWrapper());
        return ResponseResult.success(page);
    }

    @LogAnnotation(type = EnumLogType.INSERT, description = "新增人员")
    @PostMapping("/add")
    public ResponseResult<Boolean> addPerson(@RequestBody PersonPO personPO) {
        personPO.setId(null);
        personMapper.insert(personPO);
        ResponseResult<Boolean> result = ResponseResult.success(true);
        result.setLog("人员ID:" + personPO.getId());
        return result;
    }

    @LogAnnotation(type = EnumLogType.UPDATE, description = "修改人员")
    @PostMapping("/modify")
    public ResponseResult<Boolean> modifyPerson(@RequestBody PersonPO personPO) {
        Assert.isTrue(personPO.getId() != null, "修改人员的ID不能为空");
        personMapper.updateById(personPO);
        ResponseResult<Boolean> result = ResponseResult.success(true);
        result.setLog("人员ID:" + personPO.getId());
        return result;
    }

    @LogAnnotation(type = EnumLogType.DELETE, description = "删除人员")
    @PostMapping("/delete/{id}")
    public ResponseResult<Boolean> deletePerson(@PathVariable Long id) {
        int rows = personMapper.deleteById(id);
        ResponseResult<Boolean> result = ResponseResult.success(true);
        if (rows > 0) {
            result.setLog("人员ID:" + id);
        }
        return result;
    }
}
