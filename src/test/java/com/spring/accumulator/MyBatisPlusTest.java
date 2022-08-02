package com.spring.accumulator;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.spring.accumulator.dao.PersonMapper;
import com.spring.accumulator.entity.PersonPO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = AccumulatorApplication.class)
public class MyBatisPlusTest {

    @Resource
    private PersonMapper personMapper;

    @Test
    public void testQueryWrapper() {
        QueryWrapper<PersonPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(PersonPO::getId, 1L);
        PersonPO personPo = personMapper.selectOne(queryWrapper);
        System.out.println(personPo);
    }
}
