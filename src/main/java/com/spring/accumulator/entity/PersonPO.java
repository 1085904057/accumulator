package com.spring.accumulator.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import com.spring.accumulator.component.excel.GenderConverter;
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
    @ExcelIgnore
    private Long id;

    @ExcelProperty(value = "姓名")
    private String name;

    @ExcelProperty(value = "性别", converter = GenderConverter.class)
    private Integer male;

    @ExcelProperty(value = "年龄")
    private Integer age;
}

