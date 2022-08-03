package com.spring.accumulator.controller;

import com.spring.accumulator.io.excel.ExcelComponent;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/excel")
public class ImportController {

    @Resource
    private ExcelComponent excelComponent;

    @PostMapping("/import-person")
    public Boolean importPerson(@RequestParam("file") MultipartFile file) throws IOException {
        excelComponent.importPersonFile(file);
        return true;
    }

    @GetMapping("/export-person")
    public void exportPerson(HttpServletResponse response) {
        excelComponent.exportPersonFile(response);
    }
}
