package com.spring.accumulator.dao;

import java.util.List;

/**
 * 批量插入的Mapper, 用xml配置文件自定义批量插入，
 * 避免MyBatis的逐条插入降低性能
 *
 * @param <T>
 * @author wangrubin
 * @date 2022-08-02
 */
public interface BatchInsertMapper<T> {
    void batchInsert(List<T> list);
}
