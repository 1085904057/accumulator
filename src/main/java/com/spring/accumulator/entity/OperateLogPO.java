package com.spring.accumulator.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.NoArgsConstructor;
import lombok.Data;

/**
 * (OperateLog)表实体类
 *
 * @author wangrubin
 * @since 2022-08-04 15:05:34
 */
@Data
@NoArgsConstructor
@TableName("operate_log")
public class OperateLogPO implements Serializable {
    /**
     * 自增ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 操作
     */
    private Integer operate;

    /**
     * 日志
     */
    private String log;

    /**
     * 时间
     */
    private Timestamp time;

}

