package com.spring.accumulator.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.accumulator.common.response.ResponseResult;
import com.spring.accumulator.component.aspect.log.EnumLogType;
import com.spring.accumulator.component.aspect.log.LogAnnotation;
import com.spring.accumulator.component.excel.ExcelComponent;
import com.spring.accumulator.dao.PersonMapper;
import com.spring.accumulator.entity.PersonPO;
import com.spring.accumulator.model.param.PersonParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Api(tags = "人员相关接口")
@Slf4j
@RestController
@RequestMapping("/person")
public class PersonController {

    @Resource
    private ExcelComponent excelComponent;

    @Resource
    private PersonMapper personMapper;

    @ApiOperation("上传人员数据")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "file", value = "文件", required = true),
    })
    @PostMapping("/import")
    public ResponseResult<Boolean> importPerson(@RequestParam("file") MultipartFile file) throws IOException {
        excelComponent.importPersonFile(file);
        return ResponseResult.success("导入成功", true);
    }

    @ApiOperation("下载人员数据")
    @GetMapping("/export")
    public void exportPerson(HttpServletResponse response) {
        excelComponent.exportPersonFile(response);
    }

    @ApiOperation(value = "分页查询人员")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "current", value = "当前页", required = true),
            @ApiImplicitParam(name = "size", value = "页大小", required = true)
    })
    @GetMapping("/page")
    public ResponseResult<Page<PersonPO>> listPerson(@RequestParam Integer current,
                                                     @RequestParam Integer size) {
        Page<PersonPO> page = new Page<>(current, size);
        page = personMapper.selectPage(page, Wrappers.emptyWrapper());
        return ResponseResult.success(page);
    }

    @ApiOperation("新增人员")
    @LogAnnotation(type = EnumLogType.INSERT, operateLog = "新增人员")
    @PostMapping("/add")
    public ResponseResult<Boolean> addPerson(@RequestBody PersonParam param) {
        param.setId(null);
        PersonPO personPO = new PersonPO();
        BeanUtils.copyProperties(param, personPO);
        personMapper.insert(personPO);
        ResponseResult<Boolean> result = ResponseResult.success(true);
        result.setBusinessLog("人员ID:" + personPO.getId());
        return result;
    }

    @ApiOperation("修改人员")
    @LogAnnotation(type = EnumLogType.UPDATE, operateLog = "修改人员")
    @PostMapping("/modify")
    public ResponseResult<Boolean> modifyPerson(@RequestBody PersonParam param) {
        Assert.isTrue(param.getId() != null, "修改人员的ID不能为空");
        PersonPO personPO = new PersonPO();
        BeanUtils.copyProperties(param, personPO);
        personMapper.updateById(personPO);
        ResponseResult<Boolean> result = ResponseResult.success(true);
        result.setBusinessLog("人员ID:" + param.getId());
        return result;
    }

    @ApiOperation("删除人员")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "人员ID", required = true)
    })
    @LogAnnotation(type = EnumLogType.DELETE, operateLog = "删除人员,ID:{#id}", containsParam = true)
    @PostMapping("/delete/{id}")
    public ResponseResult<Boolean> deletePerson(@PathVariable Long id) {
        personMapper.deleteById(id);
        return ResponseResult.success(true);
    }
}
