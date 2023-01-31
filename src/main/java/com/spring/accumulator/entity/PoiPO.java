package com.spring.accumulator.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName(value = "t_region_poi", autoResultMap = true)
public class PoiPO {

    /**
     * ID
     */
    private Integer id;

    /**
     * POI名称
     */
    private String name;

    /**
     * POI地址
     */
    private String address;

    /**
     * POI空间点
     */
    @JsonIgnore
    @TableField(typeHandler = PointTypeHandler.class)
    private Point geom;
}
