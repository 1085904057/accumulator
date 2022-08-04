package com.spring.accumulator.component.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.spring.accumulator.dao.BatchInsertMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 从Excel文件流中分批导入数据到库中
 * EasyExcel参考文档：https://easyexcel.opensource.alibaba.com/docs/current/quickstart/read
 *
 * @param <T>
 * @author wangrubin
 * @date 2022-08-02
 */
@Slf4j
public abstract class ExcelImportListener<T> implements ReadListener<T> {
    /**
     * 缓存大小
     */
    private static final int BATCH_SIZE = 100;

    /**
     * 缓存数据
     */
    private List<T> cacheList = new ArrayList<>(BATCH_SIZE);

    @Override
    public void invoke(T po, AnalysisContext analysisContext) {
        cacheList.add(po);
        if (cacheList.size() >= BATCH_SIZE) {
            log.info("完成一批Excel记录的导入，条数为：{}", cacheList.size());
            getMapper().batchInsert(cacheList);
            cacheList = new ArrayList<>(BATCH_SIZE);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        getMapper().batchInsert(cacheList);
        log.info("完成最后一批Excel记录的导入，条数为：{}", cacheList.size());
    }

    protected abstract BatchInsertMapper<T> getMapper();
}
