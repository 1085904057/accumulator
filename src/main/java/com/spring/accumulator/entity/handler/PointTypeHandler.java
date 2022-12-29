package com.spring.accumulator.entity.handler;

import org.apache.ibatis.type.MappedTypes;
import org.locationtech.jts.geom.Point;

/**
 * Point的类型转换器
 *
 * @author wangrubin1
 * @date 2022-08-19
 */
@MappedTypes({Point.class})
public class PointTypeHandler extends AbstractGeometryTypeHandler<Point> {
}
