package com.spring.accumulator.common.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.accumulator.common.ResponseResult;
import com.spring.accumulator.common.dao.PersonMapper;
import com.spring.accumulator.common.entity.PersonPO;
import com.spring.accumulator.common.model.param.PersonParam;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/person")
public class PersonController {


    @Resource
    private PersonMapper personMapper;


    @PostMapping("/import")
    public ResponseResult<Boolean> importPerson(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseResult.success("导入成功", true);
    }

    @GetMapping("/export")
    public void exportPerson(HttpServletResponse response) {

    }

    @GetMapping("/page")
    public ResponseResult<Page<PersonPO>> listPerson(@RequestParam Integer current,
                                                     @RequestParam Integer size) {
        Page<PersonPO> page = new Page<>(current, size);
        page = (Page<PersonPO>) personMapper.selectPage(page, Wrappers.emptyWrapper());
        return ResponseResult.success(page);
    }

    @PostMapping("/add")
    public ResponseResult<Boolean> addPerson(@RequestBody PersonParam param) {
        return null;
    }

    @PostMapping("/modify")
    public ResponseResult<Boolean> modifyPerson(@RequestBody PersonParam param) {
        return null;
    }

    @PostMapping("/delete/{id}")
    public ResponseResult<Boolean> deletePerson(@PathVariable Long id) {
        return null;
    }
}
