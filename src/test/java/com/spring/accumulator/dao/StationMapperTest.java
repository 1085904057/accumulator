package com.spring.accumulator.dao;

import com.spring.accumulator.entity.StationPO;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class StationMapperTest {

    @Resource
    private StationMapper stationMapper;

    @Test
    public void testInsert() {
        GeometryFactory factory = new GeometryFactory();
        StationPO stationPo = new StationPO();
        stationPo.setLocation(factory.createPoint(new Coordinate(116.3, 25.7)));
        stationMapper.insert(stationPo);
        System.out.println(stationPo.getId());
    }

    @Test
    public void testQuery() {
        StationPO stationPo = stationMapper.selectById(9L);
        System.out.println(stationPo);
    }

}