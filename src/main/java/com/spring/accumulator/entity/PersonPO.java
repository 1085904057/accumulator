package com.spring.accumulator.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.NoArgsConstructor;
import lombok.Data;

/**
 * (Person)表实体类
 *
 * @author wangrubin
 * @since 2022-07-15 18:22:45
 */
@Data
@NoArgsConstructor
@TableName("person")
public class PersonPO implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private Integer male;

    private Integer age;
}

