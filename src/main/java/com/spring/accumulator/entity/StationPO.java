package com.spring.accumulator.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.accumulator.entity.handler.PointTypeHandler;
import lombok.Data;
import org.locationtech.jts.geom.Point;

/**
 * 基站PO
 *
 * @author wangrubin1
 * @date 2022-08-16
 */
@Data
@TableName(value = "t_base_station", autoResultMap = true)
public class StationPO {

    /**
     * 基站ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 基站位置
     */
    @JsonIgnore
    @TableField(typeHandler = PointTypeHandler.class)
    private Point location;
}
