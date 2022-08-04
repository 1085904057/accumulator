package com.spring.accumulator.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.accumulator.entity.OperateLogPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * (OperateLog)表数据库访问层
 *
 * @author wangrubin
 * @since 2022-08-04 15:05:34
 */
@Mapper
public interface OperateLogMapper extends BaseMapper<OperateLogPO> {
}

