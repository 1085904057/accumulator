package com.spring.accumulator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.accumulator.service.PersonService;
import com.spring.accumulator.dao.PersonMapper;
import com.spring.accumulator.entity.PersonPO;
import org.springframework.stereotype.Service;

/**
 * (Person)表服务实现类
 *
 * @author wangrubin
 * @since 2022-07-15 18:22:45
 */

@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, PersonPO> implements PersonService {

}
