package com.spring.accumulator.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.accumulator.entity.PoiPO;
import com.spring.accumulator.model.vo.VectorTile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * POI表数据库访问层
 *
 * @author wangrubin
 * @since 2022-07-15 18:22:45
 */
@Mapper
@DS("pg")
public interface PoiMapper extends BaseMapper<PoiPO> {

    @Select({"WITH mvtgeom AS (\n" +
            "    SELECT id, name, ST_AsMVTGeom(\n" +
            "                   ST_Transform(geom, 3857),\n" +
            "                   ST_TileEnvelope(#{z}, #{x}, #{y}), extent => 4096, buffer => 8) AS geom\n" +
            "    FROM t_region_poi\n" +
            "    WHERE geom && ST_Transform(ST_TileEnvelope(#{z}, #{x}, #{y}), 4326)\n" +
            ")\n" +
            "SELECT ST_AsMVT(mvtgeom.*) as mvt\n" +
            "FROM mvtgeom"})
    VectorTile selectVectorTile(Integer z, Integer x, Integer y);
}

