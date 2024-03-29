package com.spring.accumulator.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.accumulator.entity.PersonPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Person)表数据库访问层
 *
 * @author wangrubin
 * @since 2022-07-15 18:22:45
 */
@Mapper
@DS("mysql")
public interface PersonMapper extends BaseMapper<PersonPO>, BatchInsertMapper<PersonPO> {
}

