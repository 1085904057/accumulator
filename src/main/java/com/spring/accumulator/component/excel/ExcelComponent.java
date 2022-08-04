package com.spring.accumulator.component.excel;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.spring.accumulator.dao.BatchInsertMapper;
import com.spring.accumulator.dao.PersonMapper;
import com.spring.accumulator.entity.PersonPO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Excel导入导出组件
 *
 * @author wangrubin
 * @date 2022-08-02
 */
@Slf4j
@Component
public class ExcelComponent {

    @Resource
    private PersonMapper personMapper;

    @Resource
    private ExcelExportHandler excelExportHandler;

    /**
     * Excel文件分批导入数据库
     *
     * @param file 上传的文件
     * @throws IOException 读取文件异常
     */
    public void importPersonFile(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream())
                .head(PersonPO.class)
                .registerReadListener(new ExcelImportListener<PersonPO>() {
                    @Override
                    protected BatchInsertMapper<PersonPO> getMapper() {
                        return personMapper;
                    }
                }).sheet().doRead();
    }

    /**
     * 将库里面的数据批量导出
     * EasyExcel写参考：https://easyexcel.opensource.alibaba.com/docs/current/quickstart/write
     *
     * @param response response
     */
    public void exportPersonFile(HttpServletResponse response) {
        List<PersonPO> data = personMapper.selectList(new QueryWrapper<>());
        excelExportHandler.export(response, "人员表", data, PersonPO.class);
    }
}
