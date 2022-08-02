package com.spring.accumulator.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.accumulator.entity.PersonPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (Person)表数据库访问层
 *
 * @author wangrubin
 * @since 2022-07-15 18:22:45
 */
@Mapper
public interface PersonMapper extends BaseMapper<PersonPO>, BatchInsertMapper<PersonPO> {
    void batchInsert(List<PersonPO> list);
}

